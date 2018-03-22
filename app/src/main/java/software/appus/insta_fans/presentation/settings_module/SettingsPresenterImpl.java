package software.appus.insta_fans.presentation.settings_module;

import software.appus.insta_fans.presentation.settings_module.SettingsContract.SettingsPresenter;
import software.appus.insta_fans.presentation.settings_module.SettingsContract.SettingsView;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class SettingsPresenterImpl implements SettingsPresenter<SettingsView> {
    private SettingsView mView;

    public SettingsPresenterImpl() {
    }

    @Override
    public void getUser() {


    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public void attachView(SettingsView view) {
        mView = view;
    }
}
