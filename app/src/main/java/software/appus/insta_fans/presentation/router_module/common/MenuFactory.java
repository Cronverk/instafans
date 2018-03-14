package software.appus.insta_fans.presentation.router_module.common;

import java.util.ArrayList;
import java.util.List;

import software.appus.insta_fans.R;
import software.appus.insta_fans.presentation.router_module.models.SideMenuItemModel;

import static software.appus.insta_fans.presentation.router_module.models.MenuType.BUY_PRO;
import static software.appus.insta_fans.presentation.router_module.models.MenuType.LOG_OUT;
import static software.appus.insta_fans.presentation.router_module.models.MenuType.MY_FANS;
import static software.appus.insta_fans.presentation.router_module.models.MenuType.MY_PROFILE;
import static software.appus.insta_fans.presentation.router_module.models.MenuType.RATING_LIST;
import static software.appus.insta_fans.presentation.router_module.models.MenuType.SETTINGS;

/**
 * Created by anatolii.pozniak on 3/14/18.
 */

public class MenuFactory {
    private static MenuFactory INSTANCE;

    private MenuFactory() {
    }

    public static MenuFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MenuFactory();
        }
        return INSTANCE;
    }

    public List<SideMenuItemModel> createSideMenuList(boolean isGuest) {
        List<SideMenuItemModel> sideMenuItems = new ArrayList<>();
        sideMenuItems.add(new SideMenuItemModel(RATING_LIST, R.string.rating_list, R.drawable.ic_rating_sidebar));
        if (!isGuest) {
            sideMenuItems.add(new SideMenuItemModel(MY_PROFILE, R.string.my_profile, R.drawable.ic_rating_profile));
            sideMenuItems.add(new SideMenuItemModel(MY_FANS, R.string.my_fans, R.drawable.ic_gained_likes));
        }
        sideMenuItems.add(new SideMenuItemModel(BUY_PRO, R.string.buy_pro, R.drawable.ic_buy_pro_sidebar));
        sideMenuItems.add(new SideMenuItemModel(SETTINGS, R.string.action_settings, R.drawable.ic_settings));
        if (!isGuest) {
            sideMenuItems.add(new SideMenuItemModel(LOG_OUT, R.string.log_out, R.drawable.ic_logout));
        }

        return sideMenuItems;
    }
}
