package software.appus.insta_fans.presentation.home_module;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

import software.appus.insta_fans.R;
import software.appus.insta_fans.common.BaseFragment;
import software.appus.insta_fans.common.adapter.ListDelegateAdapter;
import software.appus.insta_fans.domain.common.UseCaseHandler;
import software.appus.insta_fans.presentation.FollowerLikesModel;
import software.appus.insta_fans.presentation.common.Injection;
import software.appus.insta_fans.presentation.common.adapter.delegates.FollowerLikeDelegate;
import software.appus.insta_fans.presentation.home_module.HomeContract.HomePresenter;
import software.appus.insta_fans.presentation.home_module.HomeContract.HomeView;
import software.appus.insta_fans.presentation.models.UserModel;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class HomeViewImpl extends BaseFragment implements HomeView<HomePresenter> {
    private HomePresenter<HomeView> presenter;

    private TextView tvProgress;
    private ProgressBar mProgressBar;
    private ImageView ivProfile;
    private TextView tvMediaCnt;
    private TextView tvFollowers;
    private ImageView ivMedia;
    private RecyclerView mRecyclerView;
    private ShimmerFrameLayout mShimmerViewContainer;
    private ListDelegateAdapter<FollowerLikesModel> mAdapter;
    private AppCompatButton btnCalculate;
    private TextView tvName;

    public static HomeViewImpl newInstance() {

        Bundle args = new Bundle();

        HomeViewImpl fragment = new HomeViewImpl();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int contentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void attachFragmentViews(View view) {
        mProgressBar = view.findViewById(R.id.progress);
        tvProgress = view.findViewById(R.id.tv_progress);
        ivMedia = view.findViewById(R.id.iv_media);
        ivProfile = view.findViewById(R.id.iv_profile);
        tvName = view.findViewById(R.id.tv_name);
        tvMediaCnt = view.findViewById(R.id.tv_media_values);
        tvFollowers = view.findViewById(R.id.tv_followers_values);
        btnCalculate = view.findViewById(R.id.btn_calculate);
        mRecyclerView = view.findViewById(R.id.recycler);
        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
    }

    @Override
    protected void initFragmentViews(Bundle savedInstanceState) {
        attachPresenter();
        presenter.getUser();
        btnCalculate.setOnClickListener(v -> eventClickCalculate());

        mAdapter = new ListDelegateAdapter.Builder<FollowerLikesModel>()
                .addDelegate(new FollowerLikeDelegate())
                .build();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void eventClickCalculate() {
        presenter.calculateLikes();
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void updateUserInfo(UserModel user) {
        Picasso.with(getContext()).load(user.getPicUrl()).into(ivProfile);
        tvMediaCnt.setText(String.valueOf(user.getMediaCount()));
        tvFollowers.setText(String.valueOf(user.getFollowedByCount()));
        tvName.setText(user.getFullName());
    }

    @Override
    public void updateProgress(String imgUrl, int iProgress) {
        Picasso.with(getContext()).load(imgUrl).into(ivMedia);
        tvProgress.setText(iProgress + "%");
        mProgressBar.setProgress(iProgress);
    }

    @Override
    public void showResult(List<FollowerLikesModel> list) {
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
        mAdapter.updateItems(list);

    }

    private void attachPresenter() {
//        presenter = (HomePresenter<HomeView>) getLastCustomNonConfigurationInstance();
//        if (presenter == null) {
        presenter = new HomePresenterImpl(this,
                UseCaseHandler.getInstance(),
                Injection.provideUserUsecase(getContext().getApplicationContext()),
                Injection.provideCalculateMediaLikesUseCase(),
                Injection.provideMediaUsecase(getContext().getApplicationContext()));
//        }
//        presenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        presenter.detachView();
        super.onDestroyView();
    }
}
