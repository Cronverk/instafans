package software.appus.insta_fans.data.stores.media.remote;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
import software.appus.insta_fans.data.entity.ResponseEntity;
import software.appus.insta_fans.data.entity.media.MediaEntity;
import software.appus.insta_fans.data.entity.media.MediaPagination;
import software.appus.insta_fans.data.mappers.MediaEntityToMediaModelMapper;
import software.appus.insta_fans.data.net.RESTClient;
import software.appus.insta_fans.data.stores.db.MediaDAO;
import software.appus.insta_fans.data.stores.media.MediaDataSource;
import software.appus.insta_fans.presentation.common.utils.ResponseErrorChecker;
import software.appus.insta_fans.presentation.models.MediaModel;

import static software.appus.insta_fans.presentation.common.Constants.ACESS_TOKEN;

/**
 * Created by anatolii.pozniak on 11/28/17.
 */

public class MediaCloudSource implements MediaDataSource {
    private MediaDAO mMediaDAO;

    public MediaCloudSource(MediaDAO mediaDAO) {
        mMediaDAO = mediaDAO;
    }


    @Override
    public List<MediaModel> get(String offset_id, int iOffset, int count) throws IOException {
        Response<ResponseEntity<List<MediaEntity>, MediaPagination>> response = RESTClient.getInstance().getMediaApi().getUserMedia(ACESS_TOKEN, offset_id, count).execute();
        ResponseEntity<List<MediaEntity>, MediaPagination> rData = ResponseErrorChecker.getInstance().checkResponse(response);

        List<MediaModel> mediaList = new MediaEntityToMediaModelMapper().transform(rData.data);
        if (rData.data != null) {
            mMediaDAO.put(mediaList);
        }
        return mediaList;
    }

    @Override
    public void put(List<MediaModel> list, int offset) {
        mMediaDAO.put(list);
    }


}
