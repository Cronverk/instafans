package software.appus.insta_fans.data.stores.user;


import software.appus.insta_fans.data.entity.UserEntity;


/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public interface UserDataSource {
    UserEntity get();

    void put(UserEntity userEntity);
}
