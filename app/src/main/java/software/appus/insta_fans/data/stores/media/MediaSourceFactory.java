package software.appus.insta_fans.data.stores.media;


import software.appus.insta_fans.data.caches.MediaCache;
import software.appus.insta_fans.data.stores.db.MediaDAO;
import software.appus.insta_fans.data.stores.media.remote.MediaCloudSource;

/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public class MediaSourceFactory {
    private MediaDAO mMediaDAO;
    private MediaCache mMediaCache;

    public MediaSourceFactory(MediaCache mediaCache,
                              MediaDAO mediaDAO) {
        mMediaDAO = mediaDAO;
        mMediaCache = mediaCache;
    }

    public MediaDataSource create() {
        return /*(!mMediaCache.isExpired() && mMediaCache.isCached()) ?
                new MediaLocalStorage(mMediaCache, mMediaDAO) :*/ new MediaCloudSource(mMediaDAO);
    }
}
