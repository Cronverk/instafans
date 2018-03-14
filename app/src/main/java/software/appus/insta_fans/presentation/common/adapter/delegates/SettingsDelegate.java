package software.appus.insta_fans.presentation.common.adapter.delegates;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import software.appus.insta_fans.R;
import software.appus.insta_fans.common.adapter.AdapterDelegate;
import software.appus.insta_fans.common.adapter.BaseViewHolder;
import software.appus.insta_fans.common.adapter.OnRecyclerItemClick;
import software.appus.insta_fans.presentation.common.adapter.holders.SettingsViewHolder;
import software.appus.insta_fans.presentation.settings_module.model.SettingsItemModel;

/**
 * Created by anatolii.pozniak on 3/7/18.
 */

public class SettingsDelegate extends AdapterDelegate<SettingsItemModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.item_settings;
    }

    @Override
    protected boolean isForViewType(@NonNull SettingsItemModel item, int position) {
        return true;
    }

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @Nullable OnRecyclerItemClick<SettingsItemModel> listener) {
        return new SettingsViewHolder(getViewInflater(parent), listener);
    }

    @Override
    protected void onViewRecycled(@NonNull BaseViewHolder viewHolder) {

    }
}
