package software.appus.insta_fans.data.stores.media.local;

import java.io.IOException;
import java.util.List;

import software.appus.insta_fans.data.caches.MediaCache;
import software.appus.insta_fans.data.entity.media.MediaEntity;
import software.appus.insta_fans.data.stores.db.MediaDAO;
import software.appus.insta_fans.data.stores.media.MediaDataSource;

/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public class MediaLocalStorage implements MediaDataSource {
    private MediaCache mMediaCache;
    private MediaDAO mMediaDAO;

    public MediaLocalStorage(MediaCache userCache,
                             MediaDAO mediaDAO) {
        mMediaCache = userCache;
        mMediaDAO = mediaDAO;
    }

    @Override
    public List<MediaEntity> get(String offset_id, int count) throws IOException {
        return ("0".equals(offset_id)) ? mMediaDAO.getFirstGroup(count) : mMediaDAO.getAll();
    }

    @Override
    public void put(List<MediaEntity> list, int offset) {
        mMediaDAO.put(list);
    }
}
