package software.appus.insta_fans.common.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by anatolii.pozniak on 11/2/17.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    protected OnRecyclerItemClick<T> click;

    public BaseViewHolder(View itemView, OnRecyclerItemClick<T> click) {
        super(itemView);
        this.click = click;
    }

    public void onBind(T model) {
        itemView.setOnClickListener(v -> {
            if (click != null) {
                click.onItemClick(model, getAdapterPosition());
            }
        });
    }
}