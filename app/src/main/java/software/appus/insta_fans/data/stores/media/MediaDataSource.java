package software.appus.insta_fans.data.stores.media;


import java.util.List;

import software.appus.insta_fans.data.entity.UserEntity;
import software.appus.insta_fans.data.entity.media.MediaEntity;


/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public interface MediaDataSource {
    List<MediaEntity> get();

    void put(UserEntity userEntity);
}
