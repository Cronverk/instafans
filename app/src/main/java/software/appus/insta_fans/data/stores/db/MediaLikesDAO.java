package software.appus.insta_fans.data.stores.db;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import software.appus.insta_fans.presentation.FollowerLikesModel;
import software.appus.insta_fans.presentation.models.MediaModel;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by anatolii.pozniak on 3/23/18.
 */
@Dao
public interface MediaLikesDAO {

    @Query("SELECT * FROM FollowerLikesModel")
    List<MediaModel> getAll();

    @Insert(onConflict = REPLACE)
    void put(List<FollowerLikesModel> mediaList);

    @Query("DELETE FROM FollowerLikesModel")
    void deleteAllMedia();

    @Query("SELECT * FROM FollowerLikesModel")
    DataSource.Factory<Integer, FollowerLikesModel> mediaList();

}
