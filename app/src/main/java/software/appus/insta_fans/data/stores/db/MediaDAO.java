package software.appus.insta_fans.data.stores.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import software.appus.insta_fans.data.entity.media.MediaEntity;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by anatolii.pozniak on 3/23/18.
 */
@Dao
public interface MediaDAO {

    @Query("SELECT * FROM mediaentity")
    List<MediaEntity> getAll();

//
//    @Query("SELECT COUNT(*) FROM mediaentity")
//    List<MediaEntity> getAll(String offset, int count);


    @Query("SELECT COUNT(:count) FROM mediaentity")
    List<MediaEntity> getFirstGroup(int count);

    @Insert(onConflict = REPLACE)
    void put(List<MediaEntity> mediaList);

    @Query("DELETE FROM mediaentity")
    void deleteAllUsers();


}
