package software.appus.insta_fans.ui;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import software.appus.insta_fans.R;
import software.appus.insta_fans.common.adapter.ListDelegateAdapter;
import software.appus.insta_fans.data.entity.FollowerEntity;
import software.appus.insta_fans.data.entity.media.MediaEntity;
import software.appus.insta_fans.data.entity.ResponseEntity;
import software.appus.insta_fans.data.entity.UserEntity;
import software.appus.insta_fans.data.net.RESTClient;
import software.appus.insta_fans.ui.adapter.FollowerLikeDelegate;

public class MainActivity extends AppCompatActivity {

    public static final String TOKEN = "token";

    private ConcurrentHashMap<String, Long> mapLikes;
    private List<MediaEntity> mMediaList;
    private TextView tvProgress;
    private ProgressBar mProgressBar;
    private ImageView ivMedia;

    private AtomicInteger mediaPosition = new AtomicInteger(0);

    private RecyclerView mRecyclerView;
    private ListDelegateAdapter<FollowerLikesModel> mAdapter;

    public static Intent getLAunchInstance(Context context, String token) {

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(TOKEN, token);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initProgressView();
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new ListDelegateAdapter.Builder<FollowerLikesModel>()
                .addDelegate(new FollowerLikeDelegate())
                .build();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mapLikes = new ConcurrentHashMap<>();
        String token = "3208472180.1123882.bbbfa25c7021492199f8e171a3733db4";


        RESTClient.getInstance().getRestApi().getSelf(token).enqueue(new Callback<ResponseEntity<UserEntity>>() {
            @Override
            public void onResponse(Call<ResponseEntity<UserEntity>> call, Response<ResponseEntity<UserEntity>> response) {
                if (response.body() != null) {
                    UserEntity user = response.body().data;

                    RESTClient.getInstance().getRestApi().getUserMedia(token, 0, user.getCounts().getMedia())
                            .enqueue(new Callback<ResponseEntity<List<MediaEntity>>>() {
                                @Override
                                public void onResponse(Call<ResponseEntity<List<MediaEntity>>> call, Response<ResponseEntity<List<MediaEntity>>> response) {
                                    if (response.body() != null) {
                                        mMediaList = response.body().data;
                                        fetchLikesFromMedia(token, user.getCounts().getMedia(), 0);
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseEntity<List<MediaEntity>>> call, Throwable t) {

                                }
                            });
                }
            }

            @Override
            public void onFailure(Call<ResponseEntity<UserEntity>> call, Throwable t) {

            }
        });


    }

    private void initProgressView() {
        tvProgress = findViewById(R.id.tv_progress);
        mProgressBar = findViewById(R.id.progress);
        ivMedia = findViewById(R.id.iv_media);
    }

    private void startCalculation() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
    }

    private void fetchLikesFromMedia(String token, int amount, int offset) {
        new LikesCounterTask().execute(token, amount, offset, mMediaList);
    }


    public class LikesCounterTask extends AsyncTask<Object, Object, List<FollowerLikesModel>> {
        private int amount;
        private int head;

        @Override
        protected List<FollowerLikesModel> doInBackground(Object[] lists) {
            String token = (String) lists[0];
            this.amount = (int) lists[1];
            this.head = (int) lists[2];
            List<MediaEntity> mediaList = (List<MediaEntity>) lists[3];
            HashMap<String, UserEntity> users = new HashMap<>();
            ConcurrentHashMap<String, Long> mapLikes = new ConcurrentHashMap<>();
            List<FollowerLikesModel> followersList = new ArrayList<>();
            for (int position = 0; position < mediaList.size(); position++) {
                Response<ResponseEntity<List<FollowerEntity>>> response = null;
                try {
                    response = RESTClient.getInstance().getRestApi().getMediaLikes(mediaList.get(position).getId(), token).execute();
                    if (response.isSuccessful() && response.body() != null) {
                        followersList = mapToFollowersLikes(users, mapLikes);
                        publishProgress(mediaList.get(position).getImages().getThumbnail().getUrl(), position + 1, followersList);
                        List<FollowerEntity> followers = response.body().data;
                        for (FollowerEntity follower : followers) {
                            if (mapLikes.containsKey(follower.getId())) {
                                Long cntr = mapLikes.get(follower.getId());
                                mapLikes.put(follower.getId(), ++cntr);
                            } else {
                                Response<ResponseEntity<UserEntity>> uResp = RESTClient.getInstance().getRestApi().getUser(follower.getId(), token).execute();
                                if (uResp.body() != null) {
                                    UserEntity user = uResp.body().data;
                                    users.put(follower.getId(), user);
                                } else {
                                    users.put(follower.getId(), new UserEntity(follower.getId(), follower.getUsername(), follower.getLastName()));
                                }
                                mapLikes.put(follower.getId(), 1L);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return followersList;
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            String img = (String) values[0];
            int prog = (int) values[1];
            List<FollowerLikesModel> list = (List<FollowerLikesModel>) values[2];
            int progress = 100 * (head + prog) / amount;
            mProgressBar.setProgress(progress);
            tvProgress.setText(progress + "%");
            mAdapter.updateItems(list);
            Picasso.with(ivMedia.getContext()).load(img).into(ivMedia);
        }

        @Override
        protected void onPostExecute(List<FollowerLikesModel> list) {
            mProgressBar.setProgress(100);
            tvProgress.setText("100%");
            mAdapter.updateItems(list);
        }
    }

    private List<FollowerLikesModel> mapToFollowersLikes(HashMap<String, UserEntity> users,
                                                         ConcurrentHashMap<String, Long> mapLikes) {
        List<FollowerLikesModel> followers = new ArrayList<>();
        for (Map.Entry<String, Long> entry : mapLikes.entrySet()) {
            UserEntity user = users.get(entry.getKey());
            followers.add(new FollowerLikesModel(user.getUsername(), user.getProfilePicture(), entry.getValue()));
        }
        return followers;
    }

}
