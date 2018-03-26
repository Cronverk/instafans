package software.appus.insta_fans.presentation.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import software.appus.insta_fans.R;
import software.appus.insta_fans.data.caches.MediaCache;
import software.appus.insta_fans.data.caches.UserCache;
import software.appus.insta_fans.data.net.RESTClient;
import software.appus.insta_fans.data.net.UserApi;
import software.appus.insta_fans.data.stores.db.MediaDAO;
import software.appus.insta_fans.data.stores.media.MediaRepository;
import software.appus.insta_fans.data.stores.user.UserRepository;
import software.appus.insta_fans.domain.interractors.CalculateMediaLikesUseCase;
import software.appus.insta_fans.domain.interractors.GetUserMediaCountUseCase;
import software.appus.insta_fans.domain.interractors.GetUserUseCase;
import software.appus.insta_fans.presentation.MyApp;

import static android.content.Context.MODE_PRIVATE;

public class Injection {

    private static final String SHARED_PREFERENCES_NAME = "instafans";


    public static GetUserUseCase provideUserUsecase(Context context) {
        return new GetUserUseCase(provideUserRepository(context));
    }


    public static MediaRepository getMediaRepository(Context context) {
        return MediaRepository.getInstance(new MediaCache(getSharedPreferences(context)), getMediaDao(context));
    }

//
//    public static CalculateMediaLikesUseCase provideCalculateMediaLikesUseCase(Context context) {
//        new CalculateMediaLikesUseCase();
//        return new CalculateMediaLikesUseCase(getMediaRepository(context));
//    }
//

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

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
    }

    private static MediaDAO getMediaDao(Context context) {
        return ((MyApp) context.getApplicationContext()).getMediaDAO();
    }

    public static CalculateMediaLikesUseCase provideCalculateMediaLikesUseCase() {
        return new CalculateMediaLikesUseCase(RESTClient.getInstance().getUserApi(),
                RESTClient.getInstance().getMediaApi());
    }

    public static GetUserMediaCountUseCase provideMediaUsecase(Context context) {
        return new GetUserMediaCountUseCase(getMediaRepository(context));
    }
}