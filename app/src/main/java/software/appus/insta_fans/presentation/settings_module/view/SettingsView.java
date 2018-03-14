package software.appus.insta_fans.presentation.settings_module.view;

import software.appus.insta_fans.presentation.common.BasePresenterInterface;
import software.appus.insta_fans.presentation.common.BaseView;
import software.appus.insta_fans.presentation.models.UserModel;

/**
 * Created by anatolii.pozniak on 3/14/18.
 */

public interface SettingsView<T extends BasePresenterInterface> extends BaseView<T> {
    void updateUserInfo(UserModel user);
}

