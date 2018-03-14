package software.appus.insta_fans.presentation.settings_module.presenter;

import software.appus.insta_fans.presentation.settings_module.view.SettingsView;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class SettignsPresenterImpl implements SettingsPresenter<SettingsView> {
    private SettingsView mView;

    public SettignsPresenterImpl() {
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
