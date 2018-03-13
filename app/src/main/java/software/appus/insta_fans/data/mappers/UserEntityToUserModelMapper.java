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

        return null;
    }
}
