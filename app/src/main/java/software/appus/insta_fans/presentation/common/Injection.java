package software.appus.insta_fans.presentation.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import software.appus.insta_fans.data.caches.UserCache;
import software.appus.insta_fans.data.net.RESTClient;
import software.appus.insta_fans.data.net.UserApi;
import software.appus.insta_fans.data.stores.user.UserRepository;
import software.appus.insta_fans.domain.interractors.GetUserUsecase;

import static android.content.Context.MODE_PRIVATE;

public class Injection {

    private static final String SHARED_PREFERENCES_NAME = "instafans";


    public static GetUserUsecase provideUserUsecase(Context context) {
        return new GetUserUsecase(provideUserRepository(context));
    }


    private static UserRepository provideUserRepository(@NonNull Context context) {
        return UserRepository.getInstance(provideUserApi(), provideUserCache(context));
    }

    private static UserCache provideUserCache(Context context) {
        return new UserCache(provideSharedPreferences(context));
    }

    private static SharedPreferences provideSharedPreferences(@NonNull Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
    }


    private static UserApi provideUserApi() {
        return RESTClient.getInstance().getRetrofit().create(UserApi.class);
    }

}