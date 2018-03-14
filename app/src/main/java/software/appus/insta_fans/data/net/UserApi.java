package software.appus.insta_fans.data.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import software.appus.insta_fans.data.entity.ResponseEntity;
import software.appus.insta_fans.data.entity.UserEntity;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public interface UserApi {

    @GET("v1/users/self")
    Call<ResponseEntity<UserEntity, Void>> getSelf(@Query("access_token") String accessToken);

    @GET("v1/users/{user_id}")
    Call<ResponseEntity<UserEntity, Void>> getUser(@Path("user_id") String id,
                                                   @Query("access_token") String accessToken);
}
