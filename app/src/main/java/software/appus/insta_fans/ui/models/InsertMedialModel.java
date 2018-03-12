package software.appus.insta_fans.ui.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by anatolii.pozniak on 3/12/18.
 */

public class InsertMedialModel implements Parcelable {
    public List<MediaModel> mediaList;
    public int offset;
    public String newMaxId;

    public InsertMedialModel(List<MediaModel> mediaList, int offset, String newMaxId) {
        this.mediaList = mediaList;
        this.offset = offset;
        this.newMaxId = newMaxId;
    }

    protected InsertMedialModel(Parcel in) {
        mediaList = in.createTypedArrayList(MediaModel.CREATOR);
        offset = in.readInt();
        newMaxId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mediaList);
        dest.writeInt(offset);
        dest.writeString(newMaxId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InsertMedialModel> CREATOR = new Creator<InsertMedialModel>() {
        @Override
        public InsertMedialModel createFromParcel(Parcel in) {
            return new InsertMedialModel(in);
        }

        @Override
        public InsertMedialModel[] newArray(int size) {
            return new InsertMedialModel[size];
        }
    };
}
