package software.appus.insta_fans.data.stores.media.remote;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import software.appus.insta_fans.data.caches.UserCache;
import software.appus.insta_fans.data.entity.ResponseEntity;
import software.appus.insta_fans.data.entity.UserEntity;
import software.appus.insta_fans.data.entity.media.MediaEntity;
import software.appus.insta_fans.data.net.UserApi;
import software.appus.insta_fans.data.stores.media.MediaDataSource;

import static software.appus.insta_fans.presentation.common.Constants.ACESS_TOKEN;

/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public class MediaCloudSource implements MediaDataSource {
    private UserApi mUserApi;
    private UserCache mUserCache;

    public MediaCloudSource(UserApi userApi,
                            UserCache userCache) {
        mUserApi = userApi;
        mUserCache = userCache;
    }

    @Override
    public List<MediaEntity> get() {
        try {
            Response<ResponseEntity<UserEntity, Void>> response = mUserApi.getSelf(ACESS_TOKEN).execute();
            if (response != null && response.isSuccessful() && response.body() != null) {
                UserEntity user = response.body().data;
                mUserCache.put(user);
                return user;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(UserEntity userEntity) {

    }

//    @Override
//    public Observable<UserEntity> get() {
//        return mAuthRepository.getTokenString()
//                .concatMap(this::getUserByToken)
//                .concatMap(this::saveSettings);
//    }
//
//    private Observable<UserEntity> saveSettings(UserEntity user) {
//        if (UserType.GUEST == user.getUserType()) {
//            return Observable.fromCallable(() -> user);
//        } else {
//            return mAuthRepository.getTokenString()
//                    .flatMap(mUserApi::getUserSettings)
//                    .onErrorReturnItem(new SettingsEntityResponse(new UserSettings(true, true)))
//                    .defaultIfEmpty(new SettingsEntityResponse(new UserSettings(true, true)))
//                    .flatMap(settingsResponse -> {
//                        user.setUserSettings(settingsResponse.mSettingsEntity);
//                        return Observable.fromCallable(() -> user);
//                    });
//        }
//    }
//
//    private Observable<UserEntity> getUserByToken(String token) {
//        if (TextUtils.isEmpty(token)) {
//            UserEntity user = new UserEntity();
//            user.userType = UserType.GUEST;
//            return Observable.fromCallable(() -> user);
//        } else {
//            return mUserApi.getUserInfo(token)
//                    .map(mUserEntityResponseToUserEntityMapper::transform);
//        }
//    }
//
//    @Override
//    public Completable put(UserEntity userEntity) {
//        //Todo update user data
//        return null;
//    }
//
//    @Override
//    public Completable changePassword(String oldPassword, String newPassword) {
//        return mAuthRepository.getTokenString()
//                .concatMap(token -> mUserApi.changePassword(token, oldPassword, newPassword))
//                .flatMapCompletable(o -> Completable.fromCallable(() -> actionUpdateStoragePassword(newPassword)));
//    }
//
//    @Override
//    public Completable changeAddress(Map<String, String> data) {
//        return get().flatMap(user -> mAuthRepository.getTokenString()
//                .flatMap(token -> UserType.CUSTOMER == user.userType ? mUserApi.changeCustomerAddress(token, data) :
//                        mUserApi.changeMerchantAddress(token, data))
//        ).flatMapCompletable(o -> Completable.fromCallable(() -> true));
//    }
//
//    @Override
//    public Completable changeDeliveryAddress(Map<String, String> data) {
//        return mAuthRepository.getTokenString()
//                .concatMap(token -> mUserApi.changeDeliveryAddress(token, data))
//                .flatMapCompletable(o -> Completable.fromCallable(() -> true));
//    }
//
//    @Override
//    public Completable changePayOption(Map<String, String> data) {
//        return mAuthRepository.getTokenString()
//                .concatMap(token -> mUserApi.changePaymentOption(token, data))
//                .flatMapCompletable(o -> Completable.fromCallable(() -> true));
//    }
//
//
//    @Override
//    public Completable changeSettings(String setting, boolean value) {
//        return mAuthRepository.getTokenString()
//                .flatMapCompletable(token -> mUserApi.setUserSettings(token, setting, value));
//    }
//
//    @Override
//    public Completable invalidate() {
//        return Completable.fromCallable(() -> {
//            mUserCache.evictAll();
//            return true;
//        });
//    }
//
//    private boolean actionUpdateStoragePassword(String newPassword) {
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            mPasswordCash.put(CryptoUtils.encode(newPassword));
//        }
//        return true;
//    }

}
