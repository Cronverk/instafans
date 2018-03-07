package software.appus.insta_fans.data.entity.media;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MediaEntity {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("images")
    @Expose
    private Images images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

}