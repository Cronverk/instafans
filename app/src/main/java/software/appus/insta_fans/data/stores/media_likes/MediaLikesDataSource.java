package software.appus.insta_fans.data.stores.media_likes;


import java.io.IOException;
import java.util.List;

import software.appus.insta_fans.presentation.FollowerLikesModel;


/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public interface MediaLikesDataSource {
    List<FollowerLikesModel> get() throws IOException;

    void put(List<FollowerLikesModel> list);

}
