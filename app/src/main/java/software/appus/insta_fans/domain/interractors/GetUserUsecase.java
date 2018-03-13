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

public class GetUserUsecase extends UseCase<GetUserUsecase.RequestValues, GetUserUsecase.ResponseValue> {

    private final UserRepository mRepository;

    public GetUserUsecase(@NonNull UserRepository repository) {
        mRepository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        UserEntity entity = mRepository.getUser();
        UserModel userModel = new UserEntityToUserModelMapper().transform(entity);
        getUseCaseCallback().onSuccess(new ResponseValue());
    }

    public static final class RequestValues implements UseCase.RequestValues {
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
    }
}
