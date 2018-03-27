package software.appus.insta_fans.presentation.home_module;

import java.util.List;

import software.appus.insta_fans.presentation.FollowerLikesModel;
import software.appus.insta_fans.presentation.common.BasePresenterInterface;
import software.appus.insta_fans.presentation.common.BaseView;
import software.appus.insta_fans.presentation.models.UserModel;

/**
 * Created by anatolii.pozniak on 3/22/18.
 */

public interface HomeContract {
    interface HomeView<T extends BasePresenterInterface> extends BaseView<T> {
        void updateUserInfo(UserModel user);

        void updateProgress(String imgUrl, int iProgress);

        void showResult(List<FollowerLikesModel> followerLikesModels);

    }

    interface HomePresenter<T> extends BasePresenterInterface<T> {
        void getUser();

        void calculateLikes();

        List<FollowerLikesModel> getListData();
    }

}
