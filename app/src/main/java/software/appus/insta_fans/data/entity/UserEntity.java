package software.appus.insta_fans.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserEntity {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("profile_picture")
    @Expose
    public String profilePicture;
    @SerializedName("full_name")
    @Expose
    public String fullName;
    @SerializedName("bio")
    @Expose
    public String bio;
    @SerializedName("website")
    @Expose
    public String website;
    @SerializedName("is_business")
    @Expose
    public Boolean isBusiness;
    @SerializedName("counts")
    @Expose
    public Counts counts;


    public UserEntity(String id, String username, String fullName) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
    }

    public UserEntity() {
    }
}