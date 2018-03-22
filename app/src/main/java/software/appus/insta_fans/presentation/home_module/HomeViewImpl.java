package software.appus.insta_fans.presentation.home_module;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import software.appus.insta_fans.R;
import software.appus.insta_fans.data.entity.media.MediaEntity;
import software.appus.insta_fans.domain.common.UseCaseHandler;
import software.appus.insta_fans.presentation.common.BaseActivity;
import software.appus.insta_fans.presentation.common.Injection;
import software.appus.insta_fans.presentation.home_module.HomeContract.HomePresenter;
import software.appus.insta_fans.presentation.home_module.HomeContract.HomeView;
import software.appus.insta_fans.presentation.models.UserModel;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class HomeViewImpl extends BaseActivity implements HomeView<HomePresenter> {
    private HomePresenter<HomeView> presenter;

    private ConcurrentHashMap<String, Long> mapLikes;
    private List<MediaEntity> mMediaList;
    private TextView tvProgress;
    private ProgressBar mProgressBar;
    private ImageView ivMedia;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        attachPresenter();
    }

    @Override
    public void updateUserInfo(UserModel user) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void attachActivityViews() {

    }

    @Override
    protected void initActivityViews() {
        attachPresenter();
        presenter.getUser();

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void attachPresenter() {
        presenter = (HomePresenter<HomeView>) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new HomePresenterImpl(UseCaseHandler.getInstance(),
                    Injection.provideUserUsecase(getApplicationContext()));
        }
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public HomePresenter<HomeView> onRetainCustomNonConfigurationInstance() {
        return presenter;
    }



}
