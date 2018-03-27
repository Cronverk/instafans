package software.appus.insta_fans.data.mappers;

import software.appus.insta_fans.data.common.DataMapper;
import software.appus.insta_fans.data.entity.UserEntity;
import software.appus.insta_fans.presentation.models.UserModel;

/**
 * Created by anatolii.pozniak on 3/12/18.
 */

public class UserEntityToUserModelMapper extends DataMapper<UserModel, UserEntity> {
    @Override
    public UserModel transform(UserEntity entity) {
        UserModel user = new UserModel();
        user.setId(entity.id);
        user.setBio(entity.bio);
        user.setBusiness(entity.isBusiness);
        user.setFullName(entity.fullName);
        user.setUsername(entity.username);
        user.setWebsite(entity.website);
        user.setPicUrl(entity.profilePicture);
        user.setFollowedByCount(entity.counts.getFollowedBy());
        user.setFollowsCount(entity.counts.getFollows());
        user.setMediaCount(entity.counts.getMedia());
        return user;
    }
}
