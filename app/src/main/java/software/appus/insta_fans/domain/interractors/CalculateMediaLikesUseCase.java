package software.appus.insta_fans.domain.interractors;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import software.appus.insta_fans.data.entity.FollowerEntity;
import software.appus.insta_fans.data.entity.UserEntity;
import software.appus.insta_fans.data.net.MediaApi;
import software.appus.insta_fans.data.net.UserApi;
import software.appus.insta_fans.domain.common.UseCase;
import software.appus.insta_fans.presentation.FollowerLikesModel;
import software.appus.insta_fans.presentation.common.utils.ResponseErrorChecker;
import software.appus.insta_fans.presentation.models.MediaModel;
import software.appus.insta_fans.presentation.models.ProgressModel;

import static software.appus.insta_fans.presentation.common.Constants.ACESS_TOKEN;

/**
 * Created by anatolii.pozniak on 3/23/18.
 */

public class CalculateMediaLikesUseCase extends UseCase<CalculateMediaLikesUseCase.RequestValue, CalculateMediaLikesUseCase.ResponseValue> {

    private UserApi mUserApi;
    private MediaApi mMediaApi;

    public CalculateMediaLikesUseCase(UserApi userApi,
                                      MediaApi mediaApi) {
        mUserApi = userApi;
        mMediaApi = mediaApi;
    }

    @Override
    protected void executeUseCase(RequestValue values) {
        try {
            ProgressModel progress = new ProgressModel();
            MediaModel media = values.mMediaModel;
            List<FollowerEntity> followers = ResponseErrorChecker.getInstance()
                    .checkResponse(mMediaApi.getMediaLikes(media.getId(), ACESS_TOKEN).execute())
                    .data;

            Map<String, Long> mapLikes = values.mapLikes;
            Map<String, UserEntity> users = values.users;

            for (FollowerEntity follower : followers) {
                long likes = 1l;
                UserEntity user;
                if (mapLikes.containsKey(follower.getId())) {
                    likes = mapLikes.get(follower.getId()) + 1;
                    mapLikes.put(follower.getId(), likes);
                } else {
//                    user = ResponseErrorChecker.getInstance()
//                            .checkResponse(mUserApi.getUser(follower.getId(), ACESS_TOKEN).execute())
//                            .data;
                    user = new UserEntity(follower.getId(),follower.getUsername() ,follower.getLastName() );
                    users.put(follower.getId(), user);
                }
                mapLikes.put(follower.getId(), likes);
            }

            progress.setImageUrl(media.getThumbUrl());
            progress.setFollowerLikesModels(bindMaps(mapLikes, users));
            getUseCaseCallback().onSuccess(ResponseValue.create(progress));
        } catch (Exception e) {
            getUseCaseCallback().onError(e);
        }
    }

    private List<FollowerLikesModel> bindMaps(Map<String, Long> mapLikes, Map<String, UserEntity> users) {
        List<FollowerLikesModel> followersList = new ArrayList<>();
        for (Map.Entry<String, Long> entry : mapLikes.entrySet()) {
            UserEntity user = users.get(entry.getKey());
            if (followersList.size() == 0) {
                followersList.add(new FollowerLikesModel(user.username, user.profilePicture, entry.getValue()));
            } else {
                for (int i = 0; i < followersList.size(); i++) {
                    if (followersList.get(i).likes <= entry.getValue()) {
                        followersList.add(i, new FollowerLikesModel(user.username, user.profilePicture, entry.getValue()));
                        break;
                    }
                }
            }
        }
        return followersList;
    }


    public static class RequestValue implements UseCase.RequestValues {
        private MediaModel mMediaModel;
        private Map<String, Long> mapLikes;
        private Map<String, UserEntity> users;

        private RequestValue(MediaModel mediaModel, Map<String, Long> mapLikes, Map<String, UserEntity> users) {
            mMediaModel = mediaModel;
            this.mapLikes = mapLikes;
            this.users = users;
        }

        public static RequestValue create(MediaModel mediaModel,
                                          Map<String, Long> mapLikes,
                                          Map<String, UserEntity> users) {
            return new RequestValue(mediaModel, mapLikes, users);
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        public ProgressModel mModel;

        private ResponseValue(ProgressModel model) {
            mModel = model;
        }

        public static ResponseValue create(ProgressModel model) {
            return new ResponseValue(model);
        }
    }
}