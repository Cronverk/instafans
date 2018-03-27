package software.appus.insta_fans.data.stores.media_likes.local;

import java.io.IOException;
import java.util.List;

import software.appus.insta_fans.data.stores.db.MediaLikesDAO;
import software.appus.insta_fans.data.stores.media_likes.MediaLikesDataSource;
import software.appus.insta_fans.presentation.FollowerLikesModel;

/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public class MediaLikesLocalStorage implements MediaLikesDataSource {
    private MediaLikesDAO mMediaLikesDAO;

    public MediaLikesLocalStorage(MediaLikesDAO mediaDAO) {
        mMediaLikesDAO = mediaDAO;
    }

    @Override
    public List<FollowerLikesModel> get() throws IOException {
        return null;
    }

    @Override
    public void put(List<FollowerLikesModel> list) {
        mMediaLikesDAO.put(list);
    }
}
