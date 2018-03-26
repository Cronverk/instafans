package software.appus.insta_fans.data.stores.media;


import java.io.IOException;
import java.util.List;

import software.appus.insta_fans.presentation.models.MediaModel;


/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public interface MediaDataSource {
    List<MediaModel> get(String offset_id, int count) throws IOException;

    void put(List<MediaModel> list, int offset);
}
