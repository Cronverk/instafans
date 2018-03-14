package software.appus.insta_fans.presentation.common.adapter.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import software.appus.insta_fans.R;
import software.appus.insta_fans.common.adapter.BaseViewHolder;
import software.appus.insta_fans.common.adapter.OnRecyclerItemClick;
import software.appus.insta_fans.presentation.settings_module.model.SettingsItemModel;

public class SettingsViewHolder extends BaseViewHolder<SettingsItemModel> {
    private TextView tvTitle;
    private ImageView ivIcon;

    public SettingsViewHolder(View itemView,
                              OnRecyclerItemClick<SettingsItemModel> click) {
        super(itemView, click);
        tvTitle = itemView.findViewById(R.id.tv_title);
        ivIcon = itemView.findViewById(R.id.iv_icon);
    }

    @Override
    public void onBind(SettingsItemModel model) {
        super.onBind(model);
        tvTitle.setText(model.getTitleResId());
        ivIcon.setImageResource(model.getIconResId());

    }
}