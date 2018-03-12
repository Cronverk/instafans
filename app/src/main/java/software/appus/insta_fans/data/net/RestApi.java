package software.appus.insta_fans.data.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import software.appus.insta_fans.data.entity.FollowerEntity;
import software.appus.insta_fans.data.entity.ResponseEntity;
import software.appus.insta_fans.data.entity.UserEntity;
import software.appus.insta_fans.data.entity.media.MediaEntity;
import software.appus.insta_fans.data.entity.media.MediaPagination;

/**
 * Created by anatolii.pozniak on 3/6/18.
 */

public interface RestApi {

    @GET("v1/media/{media-id}/likes")
    Call<ResponseEntity<List<FollowerEntity>, Void>> getMediaLikes(@Path("media-id") String id,
                                                                   @Query("access_token") String accessToken);

    @GET("v1/users/self/media/recent/")
    Call<ResponseEntity<List<MediaEntity>, MediaPagination>> getUserMedia(@Query("access_token") String accessToken,
                                                                          @Query("max_id") String max_id,
                                                                          @Query("count") int count);

    @GET("v1/users/self")
    Call<ResponseEntity<UserEntity, Void>> getSelf(@Query("access_token") String accessToken);

    @GET("v1/users/{user_id}")
    Call<ResponseEntity<UserEntity, Void>> getUser(@Path("user_id") String id,
                                                   @Query("access_token") String accessToken);

}
