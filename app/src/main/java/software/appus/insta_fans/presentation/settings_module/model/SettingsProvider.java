package software.appus.insta_fans.presentation.settings_module.model;

import java.util.ArrayList;

import software.appus.insta_fans.R;

import static software.appus.insta_fans.presentation.settings_module.model.SettingsItemModel.Types.CHECK_OUT_MORE_OUR_APPS;
import static software.appus.insta_fans.presentation.settings_module.model.SettingsItemModel.Types.RATE_THIS_APP;
import static software.appus.insta_fans.presentation.settings_module.model.SettingsItemModel.Types.SHARE;
import static software.appus.insta_fans.presentation.settings_module.model.SettingsItemModel.Types.TERMS_AND_CONDITIONS;
import static software.appus.insta_fans.presentation.settings_module.model.SettingsItemModel.Types.WHITE_FEEDBACK;

/**
 * Created by anatolii.pozniak on 3/14/18.
 */

public class SettingsProvider {
    private static SettingsProvider INSTANCE;


    public static SettingsProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SettingsProvider();
        }
        return INSTANCE;
    }

    public ArrayList<SettingsItemModel> provide() {
        ArrayList<SettingsItemModel> settingsItemModels = new ArrayList<>();
        settingsItemModels.add(new SettingsItemModel(TERMS_AND_CONDITIONS, R.string.terms_and_conditions_title, R.drawable.ic_terms_cond));
        settingsItemModels.add(new SettingsItemModel(WHITE_FEEDBACK, R.string.write_feedback, R.drawable.ic_write_fback));
        settingsItemModels.add(new SettingsItemModel(CHECK_OUT_MORE_OUR_APPS, R.string.check_out_more_of_our_apps, R.drawable.ic_share_likulator));
        settingsItemModels.add(new SettingsItemModel(RATE_THIS_APP, R.string.rate_this_app, R.drawable.ic_write_fback1));
        settingsItemModels.add(new SettingsItemModel(SHARE, R.string.share_likulator_with_friends, R.drawable.ic_share_likulator1));
        return settingsItemModels;
    }
}
