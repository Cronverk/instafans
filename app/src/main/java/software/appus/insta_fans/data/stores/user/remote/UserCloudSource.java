package software.appus.insta_fans.data.stores.user.remote;

import software.appus.insta_fans.data.caches.UserCache;
import software.appus.insta_fans.data.net.UserApi;
import software.appus.insta_fans.data.stores.user.UserDataSource;

/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public class UserCloudSource implements UserDataSource {
    private UserApi mUserApi;
    private UserCache mUserCache;

    public UserCloudSource(UserApi userApi,
                           UserCache userCache) {
        mUserApi = userApi;
        mUserCache = userCache;
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
