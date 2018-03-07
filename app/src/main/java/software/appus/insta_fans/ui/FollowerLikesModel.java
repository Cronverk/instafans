package software.appus.insta_fans.ui;

/**
 * Created by anatolii.pozniak on 3/7/18.
 */

public class FollowerLikesModel {
    public String userName;
    public String imgUrl;
    public long likes;

    public FollowerLikesModel(String userName, String imgUrl, long likes) {
        this.userName = userName;
        this.imgUrl = imgUrl;
        this.likes = likes;
    }
}
