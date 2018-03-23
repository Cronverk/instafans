package software.appus.insta_fans.data.stores.media;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.List;

import software.appus.insta_fans.data.caches.MediaCache;
import software.appus.insta_fans.data.entity.media.MediaEntity;
import software.appus.insta_fans.data.stores.db.MediaDAO;

/**
 * Created by anatolii.pozniak on 3/22/18.
 */

public class MediaRepository {

    private static MediaRepository INSTANCE = null;
    private final MediaSourceFactory mSourceFactory;

    private MediaRepository(@NonNull MediaCache mediaCache, MediaDAO mediaDAO) {
        mSourceFactory = new MediaSourceFactory(mediaCache, mediaDAO);
    }

    public static MediaRepository getInstance(MediaCache mediaCache,
                                              MediaDAO mediaDAO) {
        if (INSTANCE == null) {
            INSTANCE = new MediaRepository(mediaCache, mediaDAO);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    public List<MediaEntity> getMedia(String offsetId, int count) throws IOException {
        return mSourceFactory.create().get(offsetId, count);
    }
}
