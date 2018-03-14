package software.appus.insta_fans.presentation.home_module.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import software.appus.insta_fans.domain.common.UseCaseHandler;
import software.appus.insta_fans.presentation.common.Injection;
import software.appus.insta_fans.presentation.models.UserModel;
import software.appus.insta_fans.presentation.home_module.presenter.HomePresenter;
import software.appus.insta_fans.presentation.home_module.presenter.HomePresenterImpl;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class HomeViewImpl extends AppCompatActivity implements HomeView<HomePresenter> {
    private HomePresenter<HomeView> presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


    @Override
    public void updateUserInfo(UserModel user) {

    }

}
