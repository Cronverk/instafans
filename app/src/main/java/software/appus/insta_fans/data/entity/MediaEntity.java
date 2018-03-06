package software.appus.insta_fans.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class MediaEntity {

    @SerializedName("id")
    private String id;
    @SerializedName("user")
    private FollowerEntity user;
    @SerializedName("created_time")
    private String createdTime;
    @SerializedName("caption")
    private Caption caption;
    @SerializedName("user_has_liked")
    private Boolean userHasLiked;
    @SerializedName("likes")
    private Likes likes;
    @SerializedName("tags")
    private List<String> tags = new ArrayList<>();
    @SerializedName("filter")
    private String filter;
    @SerializedName("type")
    private String type;
    @SerializedName("link")
    private String link;
    @SerializedName("users_in_photo")
    private List<FollowerEntity> usersInPhoto = new ArrayList<>();

    private class Caption implements Parcelable {
        @SerializedName("id")
        private String id;

        @SerializedName("text")
        private String text;
        @SerializedName("created_time")
        private String createdTime;
        @SerializedName("from")
        private FollowerEntity user;

        protected Caption(Parcel in) {
            id = in.readString();
            text = in.readString();
            createdTime = in.readString();
            user = in.readParcelable(FollowerEntity.class.getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(text);
            dest.writeString(createdTime);
            dest.writeParcelable(user, flags);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public FollowerEntity getUser() {
            return user;
        }

        public void setUser(FollowerEntity user) {
            this.user = user;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public final Creator<Caption> CREATOR = new Creator<Caption>() {
            @Override
            public Caption createFromParcel(Parcel in) {
                return new Caption(in);
            }

            @Override
            public Caption[] newArray(int size) {
                return new Caption[size];
            }
        };

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FollowerEntity getUser() {
        return user;
    }

    public void setUser(FollowerEntity user) {
        this.user = user;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Caption getCaption() {
        return caption;
    }

    public void setCaption(Caption caption) {
        this.caption = caption;
    }

    public Boolean getUserHasLiked() {
        return userHasLiked;
    }

    public void setUserHasLiked(Boolean userHasLiked) {
        this.userHasLiked = userHasLiked;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public class Likes implements Parcelable {
        @SerializedName("count")
        int count;

        protected Likes(Parcel in) {
            count = in.readInt();
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public final Creator<Likes> CREATOR = new Creator<Likes>() {
            @Override
            public Likes createFromParcel(Parcel in) {
                return new Likes(in);
            }

            @Override
            public Likes[] newArray(int size) {
                return new Likes[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(count);
        }
    }


    /**
     * Created by arnold.vadiiants on 11/21/17.
     */

    public interface MediaType {
        String VIDEO = "video";
        String IMAGE = "image";
        String CAROUSEL = "carousel";


    }
}