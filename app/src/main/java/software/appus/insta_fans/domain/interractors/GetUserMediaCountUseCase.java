package software.appus.insta_fans.domain.interractors;

import java.util.List;

import software.appus.insta_fans.data.entity.media.MediaEntity;
import software.appus.insta_fans.data.stores.media.MediaRepository;
import software.appus.insta_fans.domain.common.UseCase;

/**
 * Created by anatolii.pozniak on 3/22/18.
 */

public class GetUserMediaCountUseCase extends UseCase<GetUserMediaCountUseCase.MediaRequest, GetUserMediaCountUseCase.MediaResponse> {
    private final MediaRepository mRepository;

    public GetUserMediaCountUseCase(MediaRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void executeUseCase(MediaRequest requestValues) {
        try {
            getUseCaseCallback().onSuccess(MediaResponse.create(mRepository.getMedia(requestValues.offset, requestValues.count)));
        } catch (Exception e) {
            getUseCaseCallback().onError(e);
        }
    }

    public static class MediaRequest implements UseCase.RequestValues {
        private String offset;
        private int count;

        private MediaRequest(String offset, int count) {
            this.offset = offset;
            this.count = count;
        }

        public static MediaRequest create(String offset, int count) {
            return new MediaRequest(offset, count);
        }
    }

    public static class MediaResponse implements UseCase.ResponseValue {
        public List<MediaEntity> mList;

        private MediaResponse(List<MediaEntity> list) {
            mList = list;
        }

        public static MediaResponse create(List<MediaEntity> mList) {
            return new MediaResponse(mList);
        }
    }


}
