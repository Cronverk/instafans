package software.appus.insta_fans.data.stores.media_likes;

import java.io.IOException;
import java.util.List;

import software.appus.insta_fans.data.stores.db.MediaLikesDAO;
import software.appus.insta_fans.presentation.FollowerLikesModel;

/**
 * Created by anatolii.pozniak on 3/22/18.
 */

public class MediaLikesRepository {

    private static MediaLikesRepository INSTANCE = null;
    private final MediaLikesSourceFactory mSourceFactory;

    private MediaLikesRepository( MediaLikesDAO mediaDAO) {
        mSourceFactory = new MediaLikesSourceFactory(mediaDAO);
    }

    public static MediaLikesRepository getInstance(MediaLikesDAO mediaDAO) {
        if (INSTANCE == null) {
            INSTANCE = new MediaLikesRepository( mediaDAO);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    public List<FollowerLikesModel> getMedia() throws IOException {
        return mSourceFactory.create().get();
    }
}
