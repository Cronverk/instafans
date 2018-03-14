package software.appus.insta_fans.presentation.profile_module.presenter;

import software.appus.insta_fans.presentation.common.BasePresenterInterface;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public interface ProfilePresenter<T> extends BasePresenterInterface<T> {

    void getUser();
}
