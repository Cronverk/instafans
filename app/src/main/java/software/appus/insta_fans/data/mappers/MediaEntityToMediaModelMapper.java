package software.appus.insta_fans.data.mappers;

import software.appus.insta_fans.data.entity.media.MediaEntity;
import software.appus.insta_fans.ui.models.MediaModel;

/**
 * Created by anatolii.pozniak on 3/12/18.
 */

public class MediaEntityToMediaModelMapper extends DataMapper<MediaModel, MediaEntity> {
    @Override
    public MediaModel transform(MediaEntity entity) {
        MediaModel model = new MediaModel();
        model.setId(entity.getId());
        if (entity.getImages() != null) {
            model.setDefResUrl(entity.getImages().getStandardResolution().getUrl());
            model.setLowResUrl(entity.getImages().getLowResolution().getUrl());
            model.setThumbUrl(entity.getImages().getThumbnail().getUrl());
        }
        return model;
    }
}
