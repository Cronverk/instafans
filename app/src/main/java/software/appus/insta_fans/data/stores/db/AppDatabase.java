package software.appus.insta_fans.data.stores.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import software.appus.insta_fans.presentation.models.MediaModel;

/**
 * Created by anatolii.pozniak on 3/23/18.
 */
@Database(entities = {MediaModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MediaDAO mediaDAO();
}
