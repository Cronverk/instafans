package software.appus.insta_fans.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by anatolii.pozniak on 6/30/17.
 */

public class FollowerEntity implements Parcelable {
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("id")
    @Expose
    private String id;

    protected FollowerEntity(Parcel in) {
        username = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        type = in.readString();
        id = in.readString();
    }

    public static final Creator<FollowerEntity> CREATOR = new Creator<FollowerEntity>() {
        @Override
        public FollowerEntity createFromParcel(Parcel in) {
            return new FollowerEntity(in);
        }

        @Override
        public FollowerEntity[] newArray(int size) {
            return new FollowerEntity[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(type);
        dest.writeString(id);
    }
}