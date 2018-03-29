package software.appus.insta_fans.presentation.settings_module;

import software.appus.insta_fans.presentation.common.BaseContract;
import software.appus.insta_fans.presentation.common.BasePresenterInterface;
import software.appus.insta_fans.presentation.models.UserModel;

/**
 * Created by anatolii.pozniak on 3/22/18.
 */

public interface SettingsContract {

    interface SettingsView<T extends BasePresenterInterface> extends BaseContract.View {
        void updateUserInfo(UserModel user);
    }

    interface SettingsPresenter<T> extends BasePresenterInterface<T> {

        void getUser();
    }

}
