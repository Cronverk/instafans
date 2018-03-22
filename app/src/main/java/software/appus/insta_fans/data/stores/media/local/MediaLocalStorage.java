package software.appus.insta_fans.data.stores.media.local;

import java.util.List;

import software.appus.insta_fans.data.caches.MediaCache;
import software.appus.insta_fans.data.entity.UserEntity;
import software.appus.insta_fans.data.entity.media.MediaEntity;
import software.appus.insta_fans.data.stores.media.MediaDataSource;

/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public class MediaLocalStorage implements MediaDataSource {
    private MediaCache mMediaCache;

    public MediaLocalStorage(MediaCache userCache) {
        mMediaCache = userCache;
    }

    @Override
    public List<MediaEntity> get() {
        return mMediaCache.get();
    }

    @Override
    public void put(UserEntity userEntity) {

    }

//    @Override
//    public Observable<UserEntity> get() {
//        return Observable.fromCallable(mMediaCache::get);
//    }
//
//    @Override
//    public Completable put(UserEntity userEntity) {
//        return Completable.fromCallable(() -> {
//            mMediaCache.put(userEntity);
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
//            mMediaCache.evictAll();
//            return true;
//        });
//    }
}
