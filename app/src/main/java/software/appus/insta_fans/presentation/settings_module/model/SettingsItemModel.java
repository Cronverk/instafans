package software.appus.insta_fans.presentation.settings_module.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SettingsItemModel implements Parcelable {

    public enum Types{
        TERMS_AND_CONDITIONS,
        WHITE_FEEDBACK,
        SHARE,
        CHECK_OUT_MORE_OUR_APPS,
        RATE_THIS_APP
    }

    private Types type;
    private int titleResId;
    private int iconResId;
    private boolean checked;

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public void setTitleResId(int titleResId) {
        this.titleResId = titleResId;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public SettingsItemModel(Types type, int titleResId, int iconResId) {
        this.type = type;
        this.titleResId = titleResId;
        this.iconResId = iconResId;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type == null ? -1 : this.type.ordinal());
        dest.writeInt(this.titleResId);
        dest.writeInt(this.iconResId);
        dest.writeByte(this.checked ? (byte) 1 : (byte) 0);
    }

    protected SettingsItemModel(Parcel in) {
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : Types.values()[tmpType];
        this.titleResId = in.readInt();
        this.iconResId = in.readInt();
        this.checked = in.readByte() != 0;
    }

    public static final Creator<SettingsItemModel> CREATOR = new Creator<SettingsItemModel>() {
        @Override
        public SettingsItemModel createFromParcel(Parcel source) {
            return new SettingsItemModel(source);
        }

        @Override
        public SettingsItemModel[] newArray(int size) {
            return new SettingsItemModel[size];
        }
    };
}
