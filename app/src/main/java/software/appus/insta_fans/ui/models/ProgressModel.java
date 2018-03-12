package software.appus.insta_fans.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import software.appus.insta_fans.ui.FollowerLikesModel;

/**
 * Created by anatolii.pozniak on 3/12/18.
 */

public class ProgressModel implements Parcelable {
    private String imageUrl;
    private int amount;
    private int size;
    private int mOffset;
    private String mNextValue;
    private List<FollowerLikesModel> mFollowerLikesModels;


    public ProgressModel() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    public String getNextValue() {
        return mNextValue;
    }

    public void setNextValue(String nextValue) {
        mNextValue = nextValue;
    }

    public List<FollowerLikesModel> getFollowerLikesModels() {
        return mFollowerLikesModels;
    }

    public void setFollowerLikesModels(List<FollowerLikesModel> followerLikesModels) {
        mFollowerLikesModels = followerLikesModels;
    }

    protected ProgressModel(Parcel in) {
        imageUrl = in.readString();
        amount = in.readInt();
        size = in.readInt();
        mOffset = in.readInt();
        mNextValue = in.readString();
        mFollowerLikesModels = in.createTypedArrayList(FollowerLikesModel.CREATOR);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeInt(amount);
        dest.writeInt(size);
        dest.writeInt(mOffset);
        dest.writeString(mNextValue);
        dest.writeTypedList(mFollowerLikesModels);
    }
}
