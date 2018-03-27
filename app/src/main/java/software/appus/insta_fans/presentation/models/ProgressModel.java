package software.appus.insta_fans.presentation.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anatolii.pozniak on 3/12/18.
 */

public class ProgressModel implements Parcelable {
    private String imageUrl;
    private int progress;
//    private int amount;
//    private int size;
//    private int mOffset;
//    private String mNextValue;
//    private List<FollowerLikesModel> mFollowerLikesModels;


    public ProgressModel(String imageUrl, int progress) {
        this.imageUrl = imageUrl;
        this.progress = progress;
    }

    protected ProgressModel(Parcel in) {
        imageUrl = in.readString();
        progress = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeInt(progress);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProgressModel> CREATOR = new Creator<ProgressModel>() {
        @Override
        public ProgressModel createFromParcel(Parcel in) {
            return new ProgressModel(in);
        }

        @Override
        public ProgressModel[] newArray(int size) {
            return new ProgressModel[size];
        }
    };
}
