package software.appus.insta_fans.data.stores.media_likes;


import software.appus.insta_fans.data.stores.db.MediaLikesDAO;
import software.appus.insta_fans.data.stores.media_likes.local.MediaLikesLocalStorage;

/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public class MediaLikesSourceFactory {
    private MediaLikesDAO mMediaLikesDAO;

    public MediaLikesSourceFactory(MediaLikesDAO mediaDAO) {
        mMediaLikesDAO = mediaDAO;
    }

    public MediaLikesDataSource create() {
        return new MediaLikesLocalStorage(mMediaLikesDAO);
    }
}
