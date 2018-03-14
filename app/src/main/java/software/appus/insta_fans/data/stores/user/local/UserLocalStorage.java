package software.appus.insta_fans.data.stores.user.local;

import software.appus.insta_fans.data.caches.UserCache;
import software.appus.insta_fans.data.entity.UserEntity;
import software.appus.insta_fans.data.stores.user.UserDataSource;

/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public class UserLocalStorage implements UserDataSource {
    private UserCache mUserCache;

    public UserLocalStorage(UserCache userCache) {
        mUserCache = userCache;
    }

    @Override
    public UserEntity get() {
        return null;
    }

    @Override
    public void put(UserEntity userEntity) {

    }

//    @Override
//    public Observable<UserEntity> get() {
//        return Observable.fromCallable(mUserCache::get);
//    }
//
//    @Override
//    public Completable put(UserEntity userEntity) {
//        return Completable.fromCallable(() -> {
//            mUserCache.put(userEntity);
//            return true;
//        });
//    }
//
//    @Override
//    public Completable changePassword(String oldPassword, String newPassword) {
//        return Completable.fromCallable(() -> actionUpdateStoragePassword(newPassword));
//    }
//
//    @Override
//    public Completable changeAddress(Map<String, String> data) {
//        return null;
//    }
//
//    @Override
//    public Completable changeDeliveryAddress(Map<String, String> data) {
//        return null;
//    }
//
//    @Override
//    public Completable changePayOption(Map<String, String> data) {
//        return null;
//    }
//
//    @Override
//    public Completable changeSettings(String setting, boolean value) {
//        return null;
//    }
//
//    private boolean actionUpdateStoragePassword(String newPassword) {
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            mPasswordCash.put(CryptoUtils.encode(newPassword));
//        }
//        return true;
//    }
//
//    @Override
//    public Completable invalidate() {
//        return Completable.fromCallable(() -> {
//            mUserCache.evictAll();
//            return true;
//        });
//    }
}
