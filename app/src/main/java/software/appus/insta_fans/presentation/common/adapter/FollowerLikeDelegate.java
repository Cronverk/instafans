package software.appus.insta_fans.presentation.common.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import software.appus.insta_fans.R;
import software.appus.insta_fans.common.adapter.AdapterDelegate;
import software.appus.insta_fans.common.adapter.BaseViewHolder;
import software.appus.insta_fans.common.adapter.OnRecyclerItemClick;
import software.appus.insta_fans.presentation.FollowerLikesModel;

/**
 * Created by anatolii.pozniak on 3/7/18.
 */

public class FollowerLikeDelegate extends AdapterDelegate<FollowerLikesModel> {
    @Override
    protected int getLayoutId() {
        return R.layout.follower_likes;
    }

    @Override
    protected boolean isForViewType(@NonNull FollowerLikesModel item, int position) {
        return true;
    }

    @NonNull
    @Override
    protected BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, @Nullable OnRecyclerItemClick<FollowerLikesModel> listener) {
        return new FollowrLikeHolder(getViewInflater(parent), listener);
    }

    @Override
    protected void onViewRecycled(@NonNull BaseViewHolder viewHolder) {

    }
}
