package software.appus.insta_fans.data.stores.user;


import software.appus.insta_fans.data.caches.UserCache;
import software.appus.insta_fans.data.net.UserApi;
import software.appus.insta_fans.data.stores.user.local.UserLocalStorage;
import software.appus.insta_fans.data.stores.user.remote.UserCloudSource;

/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public class UserSourceFactory {
    private UserApi mUserApi;
    private UserCache mUserCache;

    public UserSourceFactory(UserApi userApi,
                             UserCache userCache) {
        mUserApi = userApi;
        mUserCache = userCache;
    }

    public UserSourceFactory(UserCache userCache) {
        mUserCache = userCache;
    }

    public UserDataSource create() {
        return (!mUserCache.isExpired() && mUserCache.isCached()) ?
                new UserLocalStorage(mUserCache) : new UserCloudSource(mUserApi, mUserCache);
    }
}
