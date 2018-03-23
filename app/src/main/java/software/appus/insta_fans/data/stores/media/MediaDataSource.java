package software.appus.insta_fans.data.stores.media;


import java.io.IOException;
import java.util.List;

import software.appus.insta_fans.data.entity.media.MediaEntity;


/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public interface MediaDataSource {
    List<MediaEntity> get(String offset_id, int count) throws IOException;

    void put(List<MediaEntity> list, int offset);
}
