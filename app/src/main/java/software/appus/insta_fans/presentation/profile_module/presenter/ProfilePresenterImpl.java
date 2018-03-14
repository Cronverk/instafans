package software.appus.insta_fans.presentation.profile_module.presenter;

import software.appus.insta_fans.domain.common.UseCase;
import software.appus.insta_fans.domain.common.UseCaseHandler;
import software.appus.insta_fans.domain.interractors.GetUserUsecase;
import software.appus.insta_fans.presentation.profile_module.ui.ProfileView;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class ProfilePresenterImpl implements ProfilePresenter<ProfileView> {
    private ProfileView mView;
    private final GetUserUsecase mGetUserUsecase;
    private final UseCaseHandler mUseCaseHandler;

    public ProfilePresenterImpl(UseCaseHandler useCaseHandler,
                                GetUserUsecase getUserUsecase) {
        mUseCaseHandler = useCaseHandler;
        mGetUserUsecase = getUserUsecase;
    }

    @Override
    public void getUser() {

        mUseCaseHandler.execute(mGetUserUsecase, null, new UseCase.UseCaseCallback<GetUserUsecase.ResponseValue>() {
            @Override
            public void onSuccess(GetUserUsecase.ResponseValue response) {
                mView.updateUserInfo(response.data);
            }

            @Override
            public void onError() {

            }
        });
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public void attachView(ProfileView view) {
        mView = view;
    }
}
