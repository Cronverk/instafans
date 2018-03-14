package software.appus.insta_fans.presentation.home_module.ui;

import software.appus.insta_fans.presentation.common.BasePresenterInterface;
import software.appus.insta_fans.presentation.common.BaseView;
import software.appus.insta_fans.presentation.models.UserModel;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public interface HomeView<T extends BasePresenterInterface> extends BaseView<T> {
    void updateUserInfo(UserModel user);
}
