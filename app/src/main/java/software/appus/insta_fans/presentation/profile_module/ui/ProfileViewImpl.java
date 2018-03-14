package software.appus.insta_fans.presentation.profile_module.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import software.appus.insta_fans.domain.common.UseCaseHandler;
import software.appus.insta_fans.presentation.common.Injection;
import software.appus.insta_fans.presentation.models.UserModel;
import software.appus.insta_fans.presentation.profile_module.presenter.ProfilePresenter;
import software.appus.insta_fans.presentation.profile_module.presenter.ProfilePresenterImpl;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class ProfileViewImpl extends AppCompatActivity implements ProfileView<ProfilePresenter> {
    private ProfilePresenter<ProfileView> presenter;

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
        presenter = (ProfilePresenter<ProfileView>) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new ProfilePresenterImpl(UseCaseHandler.getInstance(),
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
    public ProfilePresenter<ProfileView> onRetainCustomNonConfigurationInstance() {
        return presenter;
    }


    @Override
    public void updateUserInfo(UserModel user) {

    }

}
