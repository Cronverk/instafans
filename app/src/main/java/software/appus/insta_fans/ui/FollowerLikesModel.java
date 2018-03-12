package software.appus.insta_fans.ui;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anatolii.pozniak on 3/7/18.
 */

public class FollowerLikesModel implements Parcelable {
    public String userName;
    public String imgUrl;
    public long likes;

    public FollowerLikesModel(String userName, String imgUrl, long likes) {
        this.userName = userName;
        this.imgUrl = imgUrl;
        this.likes = likes;
    }

    protected FollowerLikesModel(Parcel in) {
        userName = in.readString();
        imgUrl = in.readString();
        likes = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(imgUrl);
        dest.writeLong(likes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FollowerLikesModel> CREATOR = new Creator<FollowerLikesModel>() {
        @Override
        public FollowerLikesModel createFromParcel(Parcel in) {
            return new FollowerLikesModel(in);
        }

        @Override
        public FollowerLikesModel[] newArray(int size) {
            return new FollowerLikesModel[size];
        }
    };
}
