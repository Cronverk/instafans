package software.appus.insta_fans.presentation.router_module.presenter;

import software.appus.insta_fans.presentation.router_module.view.RouterView;

/**
 * Created by anatolii.pozniak on 3/14/18.
 */

public class RouterPresenterImpl implements RouterPresenter {
    private RouterView mView;



    @Override
    public void detachView() {
        mView = null;

    }

    @Override
    public void attachView(RouterView view) {
        mView = view;
    }
}
