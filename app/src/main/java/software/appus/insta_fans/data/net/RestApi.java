package software.appus.insta_fans.data.net;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import software.appus.insta_fans.data.entity.FollowerEntity;
import software.appus.insta_fans.data.entity.MediaEntity;
import software.appus.insta_fans.data.entity.ResponseEntity;

/**
 * Created by anatolii.pozniak on 3/6/18.
 */

public interface RestApi {

    @GET("media/{media-id}/likes")
    Call<ResponseEntity<List<FollowerEntity>>> getMediaLikes(@Path("media-id") int id,
                                                             @Query("access_token") String accessToken);

    @GET("v1/users/self/media/recent/")
    Call<ResponseEntity<List<MediaEntity>>> getUserMedia(@Query("access_token") String accessToken,
                                                         @Query("max_id") int max_id,
                                                         @Query("count") int count);

}
