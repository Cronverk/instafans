package software.appus.insta_fans.domain.interractors;

import android.support.annotation.NonNull;

import software.appus.insta_fans.data.entity.UserEntity;
import software.appus.insta_fans.data.mappers.UserEntityToUserModelMapper;
import software.appus.insta_fans.data.stores.user.UserRepository;
import software.appus.insta_fans.domain.common.UseCase;
import software.appus.insta_fans.presentation.models.UserModel;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class GetUserUseCase extends UseCase<GetUserUseCase.RequestValues, GetUserUseCase.ResponseValue> {

    private final UserRepository mRepository;

    public GetUserUseCase(@NonNull UserRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        UserEntity entity = mRepository.getUser();
        UserModel userModel = new UserEntityToUserModelMapper().transform(entity);
        getUseCaseCallback().onSuccess(new ResponseValue(userModel));
    }

    public static final class RequestValues implements UseCase.RequestValues {
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        public UserModel data;

        public ResponseValue(UserModel data) {
            this.data = data;
        }
    }
}