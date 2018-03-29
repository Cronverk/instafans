package software.appus.insta_fans.presentation.profile_module.ui;

import software.appus.insta_fans.presentation.common.BaseContract;
import software.appus.insta_fans.presentation.common.BasePresenterInterface;
import software.appus.insta_fans.presentation.models.UserModel;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public interface ProfileView<T extends BasePresenterInterface> extends BaseContract.View {
    void updateUserInfo(UserModel user);
}
