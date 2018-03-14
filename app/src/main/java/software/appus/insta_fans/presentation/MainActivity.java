package software.appus.insta_fans.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import software.appus.insta_fans.R;
import software.appus.insta_fans.common.adapter.ListDelegateAdapter;
import software.appus.insta_fans.data.entity.FollowerEntity;
import software.appus.insta_fans.data.entity.ResponseEntity;
import software.appus.insta_fans.data.entity.UserEntity;
import software.appus.insta_fans.data.entity.media.MediaEntity;
import software.appus.insta_fans.data.entity.media.MediaPagination;
import software.appus.insta_fans.data.mappers.MediaEntityToMediaModelMapper;
import software.appus.insta_fans.data.net.RESTClient;
import software.appus.insta_fans.presentation.common.adapter.delegates.FollowerLikeDelegate;
import software.appus.insta_fans.presentation.likes_service.UiThreadCallback;
import software.appus.insta_fans.presentation.models.InsertMedialModel;
import software.appus.insta_fans.presentation.models.MediaModel;
import software.appus.insta_fans.presentation.models.ProgressModel;
import software.appus.insta_fans.presentation.multithreading.CustomCallable;
import software.appus.insta_fans.presentation.multithreading.CustomThreadPoolManager;
import software.appus.insta_fans.presentation.multithreading.UiHandler;
import software.appus.insta_fans.presentation.multithreading.Util;

public class MainActivity extends AppCompatActivity implements MainLoaderView, UiThreadCallback {

    private final static int LOAD_CNT = 20;
    public static final String TOKEN = "token";

    private ConcurrentHashMap<String, Long> mapLikes;
    private List<MediaModel> mMediaList = new ArrayList<>();
    private TextView tvProgress;
    private ProgressBar mProgressBar;
    private ImageView ivMedia;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private int progressCounter;
    private int amount;

    private final String token = "3208472180.1123882.bbbfa25c7021492199f8e171a3733db4";

    private AtomicInteger mediaPosition = new AtomicInteger(0);

    private RecyclerView mRecyclerView;
    private ConcurrentHashMap<String, UserEntity> users;
    private ListDelegateAdapter<FollowerLikesModel> mAdapter;
    // The handler for the UI thread. Used for handling messages from worker threads.
    private UiHandler mUiHandler;
    // A thread pool manager
    // It is a static singleton instance by design and will survive activity lifecycle
    private CustomThreadPoolManager mCustomThreadPoolManager;
    private long startTime;

    @Override
    protected void onStart() {
        super.onStart();

        // Initialize the handler for UI thread to handle message from worker threads
        mUiHandler = new UiHandler(Looper.getMainLooper(), this);
        // get the thread pool manager instance
        mCustomThreadPoolManager = CustomThreadPoolManager.getsInstance();
        // CustomThreadPoolManager stores activity as a weak reference. No need to unregister.
        mCustomThreadPoolManager.setUiThreadCallback(this);
    }

    public static Intent getLaunchInstance(Context context, String token) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(TOKEN, token);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initProgressView();
//        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh);
//        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ListDelegateAdapter.Builder<FollowerLikesModel>()
                .addDelegate(new FollowerLikeDelegate())
                .build();
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            tvProgress.setText("0%");
            progressCounter = 0;
            mProgressBar.setProgress(0);
            mMediaList.clear();
            mapLikes.clear();
            mediaPosition.set(0);
            mAdapter.clear();
            loadMediaData("0", LOAD_CNT);
        });
        mRecyclerView.setAdapter(mAdapter);
        users = new ConcurrentHashMap<>();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mapLikes = new ConcurrentHashMap<>();
        startTime = Calendar.getInstance().getTimeInMillis();
        loadUserMediaCount();
    }

    private void loadUserMediaCount() {
        RESTClient.getInstance().getRestApi().getSelf(token).enqueue(new Callback<ResponseEntity<UserEntity, Void>>() {
            @Override
            public void onResponse(Call<ResponseEntity<UserEntity, Void>> call, Response<ResponseEntity<UserEntity, Void>> response) {
                if (response.body() != null) {
                    UserEntity user = response.body().data;
                    amount = user.counts.getMedia();
                    loadMediaData("0", LOAD_CNT);
                }
            }

            @Override
            public void onFailure(Call<ResponseEntity<UserEntity, Void>> call, Throwable t) {

            }
        });
    }

    private void loadMediaData(String offset, int cnt) {
        mCustomThreadPoolManager.addCallable(new CustomCallable(mCustomThreadPoolManager) {
            @Override
            public Object call() throws Exception {
                mSwipeRefreshLayout.setRefreshing(false);
                Response<ResponseEntity<List<MediaEntity>, MediaPagination>> response = RESTClient.getInstance().getRestApi().getUserMedia(token, offset, cnt).execute();
                if (response.body() != null) {
                    int offset = mMediaList.size();
                    List<MediaModel> mediaModels = new MediaEntityToMediaModelMapper().transform(response.body().data);
                    Message message = Util.createInsertMediasMessage(Util.MESSAGE_INSERT_MEDIA, mediaModels, offset, response.body().pagination.newMaxId);

                    if (mPoolManagerRef != null
                            && mPoolManagerRef.get() != null) {
                        mPoolManagerRef.get().sendMessageToUiThread(message);
                    }
                }
                return null;
            }
        });
    }

    private void initProgressView() {
//        tvProgress = findViewById(R.id.tv_progress);
//        mProgressBar = findViewById(R.id.progress);
//        ivMedia = findViewById(R.id.iv_media);
    }

    private void fetchLikesFromMedia(String token, int offset, int cnt, String newMaxId) {
        for (int i = offset; i < offset + cnt; i++) {
            mCustomThreadPoolManager.addCallable(new CustomCallable(mCustomThreadPoolManager) {
                @Override
                public ProgressModel call() throws Exception {
                    ProgressModel progress = new ProgressModel();
                    int position = mediaPosition.getAndIncrement();
                    Response<ResponseEntity<List<FollowerEntity>, Void>> response;
                    try {
                        response = RESTClient.getInstance().getRestApi().getMediaLikes(mMediaList.get(position).getId(), token).execute();
                        if (response.isSuccessful() && response.body() != null) {
                            List<FollowerEntity> followers = response.body().data;

                            for (FollowerEntity follower : followers) {
                                long likes = 1l;
                                UserEntity user = null;
                                if (mapLikes.containsKey(follower.getId())) {
                                    likes = mapLikes.get(follower.getId()) + 1;
                                    mapLikes.put(follower.getId(), likes);
                                } else {
                                    Response<ResponseEntity<UserEntity, Void>> uResp = RESTClient.getInstance().getRestApi().getUser(follower.getId(), token).execute();
                                    if (uResp.body() != null) {
                                        user = uResp.body().data;
                                        users.put(follower.getId(), user);
                                    } else {
                                        user = new UserEntity(follower.getId(), follower.getUsername(), follower.getLastName());
                                        users.put(follower.getId(), user);
                                    }
                                }
                                mapLikes.put(follower.getId(), likes);

                            }
                            List<FollowerLikesModel> followersList = new ArrayList<>();
                            for (Map.Entry<String, Long> entry : mapLikes.entrySet()) {
                                UserEntity user = users.get(entry.getKey());
                                if (followersList.size() == 0) {
                                    followersList.add(new FollowerLikesModel(user.username, user.profilePicture, entry.getValue()));
                                } else {
                                    for (int i = 0; i < followersList.size(); i++) {
                                        if (followersList.get(i).likes <= entry.getValue()) {
                                            followersList.add(i, new FollowerLikesModel(user.username, user.profilePicture, entry.getValue()));
                                            break;
                                        }
                                    }
                                }
                            }

                            progress.setImageUrl(mMediaList.get(position).getThumbUrl());
                            progress.setFollowerLikesModels(followersList);
                        }
                        Message message = Util.createProgress(Util.MESSAGE_PROGRESS,
                                progress);

                        if (mPoolManagerRef != null
                                && mPoolManagerRef.get() != null) {
                            mPoolManagerRef.get().sendMessageToUiThread(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
            });
        }
        if (!TextUtils.isEmpty(newMaxId)) {
            loadMediaData(newMaxId, LOAD_CNT);
        }
    }

    @Override
    public void publishToUiThread(Message message) {
        // add the message from worker thread to UI thread's message queue
        if (mUiHandler != null) {
            mUiHandler.sendMessage(message);
        }
    }

    @Override
    public void updateProgress(ProgressModel progress) {
        Picasso.with(ivMedia.getContext()).load(progress.getImageUrl()).into(ivMedia);
        int iProgress = 0;
        progressCounter++;
        if (progressCounter == amount) {
            iProgress = 100;
            Log.e("time", String.valueOf(Calendar.getInstance().getTimeInMillis() - startTime));
        } else {
            iProgress = (int) (progressCounter * 100f / amount);
        }
        mAdapter.updateItems(progress.getFollowerLikesModels());
        tvProgress.setText(iProgress + "%");
        mProgressBar.setProgress(iProgress);
    }

    @Override
    public void appendMedia(InsertMedialModel appendModel) {
        mMediaList.addAll(appendModel.mediaList);
        fetchLikesFromMedia(token, appendModel.offset, LOAD_CNT, appendModel.newMaxId);
    }

}
