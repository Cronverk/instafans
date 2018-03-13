package software.appus.insta_fans.data.caches;

import android.content.SharedPreferences;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import software.appus.insta_fans.data.common.BaseCache;
import software.appus.insta_fans.data.entity.UserEntity;


/**
 * Created by anatolii.pozniak on 11/20/17.
 */

public class UserCache extends BaseCache<UserEntity> {

    public static final String KEY = "user";
    public static final String KEY_DATE = KEY + " date";

    public UserCache(SharedPreferences sp) {
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
        return new TypeToken<UserEntity>() {
        }.getType();
    }

    @Override
    protected String getCacheItemKey() {
        return KEY;
    }

    @Override
    public boolean isExpired() {
        return false;
    }
}
