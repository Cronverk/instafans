package software.appus.insta_fans.data.stores.media;


import software.appus.insta_fans.data.caches.UserCache;
import software.appus.insta_fans.data.net.UserApi;
import software.appus.insta_fans.data.stores.media.local.MediaLocalStorage;
import software.appus.insta_fans.data.stores.media.remote.MediaCloudSource;

/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public class MediaSourceFactory {
    private UserApi mUserApi;
    private UserCache mUserCache;

    public MediaSourceFactory(UserApi userApi,
                              UserCache userCache) {
        mUserApi = userApi;
        mUserCache = userCache;
    }

    public MediaDataSource create() {
        return (!mUserCache.isExpired() && mUserCache.isCached()) ?
                new MediaLocalStorage(mUserCache) : new MediaCloudSource(mUserApi, mUserCache);
    }
}
