package software.appus.insta_fans.data.stores.media.local;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import software.appus.insta_fans.data.caches.MediaCache;
import software.appus.insta_fans.data.stores.db.MediaDAO;
import software.appus.insta_fans.data.stores.media.MediaDataSource;
import software.appus.insta_fans.presentation.models.MediaModel;

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
    public List<MediaModel> get(String offset_id, int count) throws IOException {
       //mMediaDAO.mediaList().
        return new ArrayList<>();
    }

    @Override
    public void put(List<MediaModel> list, int offset) {
        mMediaDAO.put(list);
    }
}
