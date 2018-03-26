package software.appus.insta_fans.presentation.profile_module.presenter;

import software.appus.insta_fans.domain.common.UseCase;
import software.appus.insta_fans.domain.common.UseCaseHandler;
import software.appus.insta_fans.domain.interractors.GetUserUseCase;
import software.appus.insta_fans.presentation.profile_module.ui.ProfileView;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class ProfilePresenterImpl implements ProfilePresenter<ProfileView> {
    private ProfileView mView;
    private final GetUserUseCase mGetUserUsecase;
    private final UseCaseHandler mUseCaseHandler;

    public ProfilePresenterImpl(UseCaseHandler useCaseHandler,
                                GetUserUseCase getUserUsecase) {
        mUseCaseHandler = useCaseHandler;
        mGetUserUsecase = getUserUsecase;
    }

    @Override
    public void getUser() {

        mUseCaseHandler.execute(mGetUserUsecase, null, new UseCase.UseCaseCallback<GetUserUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetUserUseCase.ResponseValue response) {
                mView.updateUserInfo(response.data);
            }

            @Override
            public void onError(Exception e) {

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
