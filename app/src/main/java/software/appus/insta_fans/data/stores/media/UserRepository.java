package software.appus.insta_fans.data.stores.media;

import android.support.annotation.NonNull;

import software.appus.insta_fans.data.caches.UserCache;
import software.appus.insta_fans.data.entity.UserEntity;
import software.appus.insta_fans.data.net.UserApi;


/**
 * Created by anatolii.pozniak on 11/24/17.
 */

public class UserRepository {

    private static UserRepository INSTANCE = null;
    private final MediaSourceFactory mSourceFactory;

    private UserRepository(@NonNull UserApi api,
                           @NonNull UserCache userCache) {
        mSourceFactory = new MediaSourceFactory(api, userCache);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param tasksRemoteDataSource the backend data source
     * @param tasksLocalDataSource  the device storage data source
     * @return the {@link TasksRepository} instance
     */

    /**
     * @param api       - network data source
     * @param userCache - locla data source
     * @return
     */
    public static UserRepository getInstance(UserApi api,
                                             UserCache userCache) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(api, userCache);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(UserApi, UserCache)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


    public UserEntity getUser() {
        return mSourceFactory.create().get();
    }
}
