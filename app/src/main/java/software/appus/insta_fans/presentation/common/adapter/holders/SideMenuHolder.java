package software.appus.insta_fans.presentation.common.adapter.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import software.appus.insta_fans.R;
import software.appus.insta_fans.common.adapter.BaseViewHolder;
import software.appus.insta_fans.common.adapter.OnRecyclerItemClick;
import software.appus.insta_fans.presentation.router_module.models.SideMenuItemModel;

public class SideMenuHolder extends BaseViewHolder<SideMenuItemModel> {

    private TextView tvTitle;
    private ImageView ivIcon;

    public SideMenuHolder(View itemView, OnRecyclerItemClick<SideMenuItemModel> click) {
        super(itemView, click);

        tvTitle = itemView.findViewById(R.id.tv_title);
        ivIcon = itemView.findViewById(R.id.iv_icon);
    }

    @Override
    public void onBind(SideMenuItemModel model) {
        super.onBind(model);
        tvTitle.setText(model.getTitleResId());
        ivIcon.setImageResource(model.getIconResId());
    }
}