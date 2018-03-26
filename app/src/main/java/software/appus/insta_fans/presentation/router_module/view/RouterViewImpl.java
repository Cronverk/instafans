package software.appus.insta_fans.presentation.router_module.view;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import software.appus.insta_fans.R;
import software.appus.insta_fans.common.adapter.ListDelegateAdapter;
import software.appus.insta_fans.presentation.common.BaseActivity;
import software.appus.insta_fans.presentation.common.adapter.delegates.SideMenuDelegate;
import software.appus.insta_fans.presentation.home_module.HomeViewImpl;
import software.appus.insta_fans.presentation.router_module.common.MenuFactory;
import software.appus.insta_fans.presentation.router_module.models.SideMenuItemModel;
import software.appus.insta_fans.presentation.router_module.presenter.RouterPresenter;
import software.appus.insta_fans.presentation.router_module.presenter.RouterPresenterImpl;
import software.appus.insta_fans.presentation.settings_module.SettingsViewImpl;

/**
 * Created by anatolii.pozniak on 3/14/18.
 */

public class RouterViewImpl extends BaseActivity implements RouterView {
    private RelativeLayout mainContainer;
    private Toolbar toolbar;
    private TextView txToolbarTitle;
    private DrawerLayout drawer;
    private NavigationView nvMenu;
    private ActionBarDrawerToggle drawerToggle;
    private ImageView mSearchMenuItem;
    private RecyclerView rvSideMenu;
    protected String mCurrentFragmentName;

    private RouterPresenter presenter;
    private ListDelegateAdapter<SideMenuItemModel> sideMenuAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_navigator;
    }

    private void attachPresenter() {
        presenter = (RouterPresenter) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new RouterPresenterImpl();
        }
        presenter.attachView(this);
    }


    @Override
    protected void attachActivityViews() {
        attachPresenter();
//        mainContainer = findViewById(getMainLayoutId());
        drawer = findViewById(R.id.drawer_layout);
        nvMenu = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        txToolbarTitle = findViewById(R.id.toolbar_title);
        rvSideMenu = findViewById(R.id.rv_side_menu);
//        mSearchMenuItem = findViewById(R.id.iv_search);
    }

    @Override
    protected void initActivityViews() {
        initMenu();

    }

    private void initMenu() {
        List<SideMenuItemModel> list = MenuFactory.getInstance().createSideMenuList(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        sideMenuAdapter = new ListDelegateAdapter.Builder<SideMenuItemModel>()
                .setItems(MenuFactory.getInstance().createSideMenuList(false))
                .addDelegate(new SideMenuDelegate())
                .setListener(this::eventMenuCLick)
                .build();
        rvSideMenu.setLayoutManager(layoutManager);
        rvSideMenu.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvSideMenu.setAdapter(sideMenuAdapter);
    }

    private void eventMenuCLick(SideMenuItemModel model, int position) {
//        mCurrentFragmentName = simpleName;
        String toolbarTitle = "";
        switch (model.getType()) {
            case BUY_PRO:
                toolbarTitle = getString(R.string.buy_pro);
                goToBuyProScreen();
                break;
            case MY_FANS:
                toolbarTitle = getString(R.string.my_fans);
                goToMyFansScreen();
                break;
            case SETTINGS:
                toolbarTitle = getString(R.string.action_settings);
                goToSettingsScreen();
                break;
            case MY_PROFILE:
                goToMyProfile();
                break;
            case RATING_LIST:
                toolbarTitle = getString(R.string.app_name);
                goToHomeScreen();
                break;
        }
//        setToolbarTitle(toolbarTitle);
    }

    private void goToHomeScreen() {
        replaceFragment(HomeViewImpl.newInstance(), true, null);
    }

    private void goToMyFansScreen() {

    }

    private void goToSettingsScreen() {
        replaceFragment(SettingsViewImpl.newInstance(), true, null);
    }

    private void goToMyProfile() {

    }

    private void goToBuyProScreen() {

    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public RouterPresenter onRetainCustomNonConfigurationInstance() {
        return presenter;
    }
}
