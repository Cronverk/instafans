package software.appus.insta_fans.presentation.router_module.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yuriy.ostapenko on 4/24/17.
 */

public class SideMenuItemModel implements Parcelable {

    private MenuType type;
    private int titleResId;
    private int iconResId;
    private boolean checked;

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
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

    public SideMenuItemModel(MenuType type, int titleResId, int iconResId) {
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

    protected SideMenuItemModel(Parcel in) {
        int tmpType = in.readInt();
        this.type = tmpType == -1 ? null : MenuType.values()[tmpType];
        this.titleResId = in.readInt();
        this.iconResId = in.readInt();
        this.checked = in.readByte() != 0;
    }

    public static final Creator<SideMenuItemModel> CREATOR = new Creator<SideMenuItemModel>() {
        @Override
        public SideMenuItemModel createFromParcel(Parcel source) {
            return new SideMenuItemModel(source);
        }

        @Override
        public SideMenuItemModel[] newArray(int size) {
            return new SideMenuItemModel[size];
        }
    };
}
