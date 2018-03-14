package software.appus.insta_fans.presentation.common.adapter.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import software.appus.insta_fans.R;
import software.appus.insta_fans.common.adapter.BaseViewHolder;
import software.appus.insta_fans.common.adapter.OnRecyclerItemClick;
import software.appus.insta_fans.presentation.FollowerLikesModel;

/**
 * Created by anatolii.pozniak on 3/7/18.
 */

public class FollowrLikeHolder extends BaseViewHolder<FollowerLikesModel> {
    private TextView tvUserName;
    private TextView tvLikes;
    private ImageView mImageView;

    public FollowrLikeHolder(View itemView, OnRecyclerItemClick<FollowerLikesModel> click) {
        super(itemView, click);
        tvUserName = itemView.findViewById(R.id.tvUserName);
        tvLikes = itemView.findViewById(R.id.tvLikes);
        mImageView = itemView.findViewById(R.id.iv_img);
    }

    @Override
    public void onBind(FollowerLikesModel model) {
        super.onBind(model);
        tvUserName.setText(model.userName);
        tvLikes.setText(String.valueOf(model.likes));
        Picasso.with(itemView.getContext()).load(model.imgUrl).error(R.drawable.ic_person).into(mImageView);
    }
}
