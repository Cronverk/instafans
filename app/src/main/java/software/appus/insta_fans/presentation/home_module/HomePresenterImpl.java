package software.appus.insta_fans.presentation.home_module;

import software.appus.insta_fans.domain.common.UseCase;
import software.appus.insta_fans.domain.common.UseCaseHandler;
import software.appus.insta_fans.domain.interractors.GetUserUsecase;
import software.appus.insta_fans.presentation.home_module.HomeContract.HomePresenter;
import software.appus.insta_fans.presentation.home_module.HomeContract.HomeView;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class HomePresenterImpl implements HomePresenter<HomeView> {
    private HomeView mView;
    private final GetUserUsecase mGetUserUsecase;
    private final UseCaseHandler mUseCaseHandler;

    public HomePresenterImpl(UseCaseHandler useCaseHandler,
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
    public void attachView(HomeView view) {
        mView = view;
    }
}
