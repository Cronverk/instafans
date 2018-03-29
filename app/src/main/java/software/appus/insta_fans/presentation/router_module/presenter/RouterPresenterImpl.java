package software.appus.insta_fans.presentation.router_module.presenter;

import software.appus.insta_fans.domain.common.UseCaseHandler;
import software.appus.insta_fans.presentation.common.BasePresenter;
import software.appus.insta_fans.presentation.router_module.view.RouterView;

/**
 * Created by anatolii.pozniak on 3/14/18.
 */

public class RouterPresenterImpl extends BasePresenter<RouterView>
        implements RouterPresenter {
    private RouterView mView;

    public RouterPresenterImpl(UseCaseHandler useCaseHandler) {
        super(useCaseHandler);
    }

    @Override
    public void onPresenterDestroy() {

    }
}
