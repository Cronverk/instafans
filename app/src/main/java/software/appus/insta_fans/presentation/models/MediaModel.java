package software.appus.insta_fans.presentation.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anatolii.pozniak on 3/12/18.
 */

public class MediaModel implements Parcelable {
    private String id;
    private String thumbUrl;
    private String lowResUrl;
    private String DefResUrl;

    public MediaModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public String getLowResUrl() {
        return lowResUrl;
    }

    public void setLowResUrl(String lowResUrl) {
        this.lowResUrl = lowResUrl;
    }

    public String getDefResUrl() {
        return DefResUrl;
    }

    public void setDefResUrl(String defResUrl) {
        DefResUrl = defResUrl;
    }

    protected MediaModel(Parcel in) {
        id = in.readString();
        thumbUrl = in.readString();
        lowResUrl = in.readString();
        DefResUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(thumbUrl);
        dest.writeString(lowResUrl);
        dest.writeString(DefResUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MediaModel> CREATOR = new Creator<MediaModel>() {
        @Override
        public MediaModel createFromParcel(Parcel in) {
            return new MediaModel(in);
        }

        @Override
        public MediaModel[] newArray(int size) {
            return new MediaModel[size];
        }
    };
}
