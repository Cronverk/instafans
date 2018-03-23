package software.appus.insta_fans.data.caches;

import android.content.SharedPreferences;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import software.appus.insta_fans.data.common.BaseCache;


/**
 * Created by anatolii.pozniak on 11/20/17.
 */

public class MediaCache extends BaseCache<Boolean> {

    public static final String KEY = "media";
    public static final String KEY_DATE = KEY + " date";

    public MediaCache(SharedPreferences sp) {
        super(sp);
    }

    @Override
    protected String getDateCacheKey() {
        return KEY_DATE;
    }

    @Override
    protected long getCacheExpirationTime() {
        return 3000000;
    }

    @Override
    protected Type getType() {
        return new TypeToken<Boolean>() {
        }.getType();
    }

    @Override
    protected String getCacheItemKey() {
        return KEY;
    }

}
