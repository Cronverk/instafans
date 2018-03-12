package software.appus.insta_fans.data.entity.media;

import com.google.gson.annotations.SerializedName;

/**
 * Created by anatolii.pozniak on 3/12/18.
 */

public class MediaPagination {
    @SerializedName("next_max_id")
    public String newMaxId;
    @SerializedName("next_url")
    public String nextUrl;
}
