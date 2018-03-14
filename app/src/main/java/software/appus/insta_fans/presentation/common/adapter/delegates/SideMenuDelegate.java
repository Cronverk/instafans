package software.appus.insta_fans.presentation.common.adapter.delegates;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import software.appus.insta_fans.R;
import software.appus.insta_fans.common.adapter.AdapterDelegate;
import software.appus.insta_fans.common.adapter.BaseViewHolder;
import software.appus.insta_fans.common.adapter.OnRecyclerItemClick;
import software.appus.insta_fans.presentation.common.adapter.holders.SideMenuHolder;
import software.appus.insta_fans.presentation.router_module.models.SideMenuItemModel;

/**
 * Created by anatolii.pozniak on 3/7/18.
 */

public class SideMenuDelegate extends AdapterDelegate<SideMenuItemModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.item_side_menu;
    }

    @Override
    protected boolean isForViewType(@NonNull SideMenuItemModel item, int position) {
        return true;
    }

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @Nullable OnRecyclerItemClick<SideMenuItemModel> listener) {
        return new SideMenuHolder(getViewInflater(parent), listener);
    }

    @Override
    protected void onViewRecycled(@NonNull BaseViewHolder viewHolder) {

    }
}
