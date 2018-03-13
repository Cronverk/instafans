package software.appus.insta_fans.data.common;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by alexey.shishov on 11/1/17
 */

public abstract class BaseCache<T> {

    protected SharedPreferences sp;

    public BaseCache(SharedPreferences sp) {
        this.sp = sp;
    }

    protected abstract String getDateCacheKey();

    protected abstract long getCacheExpirationTime();

    protected abstract Type getType();

    protected abstract String getCacheItemKey();

    public boolean isCached() {
//        sp.getString(getCacheItemKey(),n)
        return sp.contains(getCacheItemKey());
    }

    public boolean isExpired() {

        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > getCacheExpirationTime());

        if (expired) {
            this.evictAll();
        }

        return expired;
    }

    public void evictAll() {
        sp.edit().remove(getCacheItemKey()).apply();
    }

    protected void setLastCacheUpdateTimeMillis() {
        final long currentMillis = System.currentTimeMillis();
        sp.edit().putLong(getDateCacheKey(), currentMillis).apply();
    }

    protected long getLastCacheUpdateTimeMillis() {
        return sp.getLong(getDateCacheKey(), 0);
    }


    public T put(T model) {
        sp.edit().putString(getCacheItemKey(), new Gson().toJson(model, getType())).apply();
        return model;
    }

    public T get() {
        return new Gson().fromJson(sp.getString(getCacheItemKey(), ""), getType());
    }
}

