package software.appus.insta_fans.domain.interractors;

import software.appus.insta_fans.data.stores.media.MediaRepository;
import software.appus.insta_fans.domain.common.UseCase;

/**
 * Created by anatolii.pozniak on 3/22/18.
 */

public class GetUserMediaCountUseCase extends UseCase<> {


    private final MediaRepository mRepository;
    @Override
    protected void executeUseCase(RequestValues requestValues) {
//        RESTClient.getInstance().getRestApi().getSelf(token).enqueue(new Callback<ResponseEntity<UserEntity, Void>>() {
//            @Override
//            public void onResponse(Call<ResponseEntity<UserEntity, Void>> call, Response<ResponseEntity<UserEntity, Void>> response) {
//                if (response.body() != null) {
//                    UserEntity user = response.body().data;
//                    amount = user.counts.getMedia();
//                    loadMediaData("0", LOAD_CNT);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseEntity<UserEntity, Void>> call, Throwable t) {
//
//            }
//        });
    }
}
