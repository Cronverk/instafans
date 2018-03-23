package software.appus.insta_fans.presentation;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import software.appus.insta_fans.data.stores.db.AppDatabase;
import software.appus.insta_fans.data.stores.db.MediaDAO;

/**
 * Created by anatolii.pozniak on 3/7/18.
 */

public class MyApp extends MultiDexApplication {

    private AppDatabase db;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        db.mediaDAO();
    }


    public MediaDAO getMediaDAO() {
        return db.mediaDAO();
    }
}
