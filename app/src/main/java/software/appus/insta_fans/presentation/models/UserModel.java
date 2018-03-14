package software.appus.insta_fans.presentation.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class UserModel implements Parcelable {
    private String id;
    private String username;
    private String picUrl;
    private String fullName;
    private String bio;
    private String website;
    private boolean isBusiness;
    private int mediaCount;
    private int followsCount;
    private int followedByCount;

    public UserModel() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }

    public int getMediaCount() {
        return mediaCount;
    }

    public void setMediaCount(int mediaCount) {
        this.mediaCount = mediaCount;
    }

    public int getFollowsCount() {
        return followsCount;
    }

    public void setFollowsCount(int followsCount) {
        this.followsCount = followsCount;
    }

    public int getFollowedByCount() {
        return followedByCount;
    }

    public void setFollowedByCount(int followedByCount) {
        this.followedByCount = followedByCount;
    }

    public static Creator<UserModel> getCREATOR() {
        return CREATOR;
    }

    protected UserModel(Parcel in) {
        id = in.readString();
        username = in.readString();
        picUrl = in.readString();
        fullName = in.readString();
        bio = in.readString();
        website = in.readString();
        isBusiness = in.readByte() != 0;
        mediaCount = in.readInt();
        followsCount = in.readInt();
        followedByCount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(username);
        dest.writeString(picUrl);
        dest.writeString(fullName);
        dest.writeString(bio);
        dest.writeString(website);
        dest.writeByte((byte) (isBusiness ? 1 : 0));
        dest.writeInt(mediaCount);
        dest.writeInt(followsCount);
        dest.writeInt(followedByCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
