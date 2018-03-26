package software.appus.insta_fans.presentation.home_module;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import software.appus.insta_fans.data.entity.UserEntity;
import software.appus.insta_fans.domain.common.UseCase;
import software.appus.insta_fans.domain.common.UseCaseHandler;
import software.appus.insta_fans.domain.interractors.CalculateMediaLikesUseCase;
import software.appus.insta_fans.domain.interractors.GetUserMediaCountUseCase;
import software.appus.insta_fans.domain.interractors.GetUserUseCase;
import software.appus.insta_fans.presentation.FollowerLikesModel;
import software.appus.insta_fans.presentation.home_module.HomeContract.HomePresenter;
import software.appus.insta_fans.presentation.home_module.HomeContract.HomeView;
import software.appus.insta_fans.presentation.models.MediaModel;
import software.appus.insta_fans.presentation.models.ProgressModel;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class HomePresenterImpl implements HomePresenter<HomeView> {
    public static final int MEDIA_LOAD_COUNT = 5;
    private HomeView mView;
    private final GetUserUseCase mGetUserUseCase;
    private final UseCaseHandler mUseCaseHandler;
    private final CalculateMediaLikesUseCase mCalculateMediaLikesUseCase;
    private final GetUserMediaCountUseCase mGetUserMediaCountUseCase;
    private final ConcurrentHashMap<String, Long> mapLikes;
    private final ConcurrentHashMap<String, UserEntity> users;
    private final List<FollowerLikesModel> mFollowerLikesModels;


    public HomePresenterImpl(HomeView view,
                             UseCaseHandler useCaseHandler,
                             GetUserUseCase getUserUsecase,
                             CalculateMediaLikesUseCase calculateMediaLikesUseCase,
                             GetUserMediaCountUseCase getUserMediaCountUseCase) {
        mView = view;
        mUseCaseHandler = useCaseHandler;
        mGetUserUseCase = getUserUsecase;
        mCalculateMediaLikesUseCase = calculateMediaLikesUseCase;
        mGetUserMediaCountUseCase = getUserMediaCountUseCase;
        this.users = new ConcurrentHashMap<>();
        mapLikes = new ConcurrentHashMap<>();
        mFollowerLikesModels = new ArrayList<>();
    }

    @Override
    public void getUser() {

        mUseCaseHandler.execute(mGetUserUseCase, null, new UseCase.UseCaseCallback<GetUserUseCase.ResponseValue>() {
            @Override
            public void onSuccess(GetUserUseCase.ResponseValue response) {
                mView.updateUserInfo(response.data);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    @Override
    public void calculateLikes() {
        calculateLikes("0");

    }

    @Override
    public List<FollowerLikesModel> getListData() {
        return null;
    }

    private void calculateLikes(String offset) {
        mUseCaseHandler.execute(mGetUserMediaCountUseCase,
                new GetUserMediaCountUseCase.MediaRequest(offset, MEDIA_LOAD_COUNT),
                new UseCase.UseCaseCallback<GetUserMediaCountUseCase.MediaResponse>() {
                    @Override
                    public void onSuccess(GetUserMediaCountUseCase.MediaResponse response) {
                        List<MediaModel> mediaList = response.mList;
                        for (MediaModel media : mediaList) {
                            mUseCaseHandler.execute(mCalculateMediaLikesUseCase,
                                    CalculateMediaLikesUseCase.RequestValue.create(media,
                                            mapLikes,
                                            users),
                                    new UseCase.UseCaseCallback<CalculateMediaLikesUseCase.ResponseValue>() {
                                        @Override
                                        public void onSuccess(CalculateMediaLikesUseCase.ResponseValue response) {
                                            ProgressModel model = response.mModel;
                                            mView.updateProgress(model);
                                        }

                                        @Override
                                        public void onError(Exception exception) {

                                        }
                                    }
                            );
                        }
                        if (mediaList.size() >= MEDIA_LOAD_COUNT) {
                            HomePresenterImpl.this.calculateLikes(mediaList.get(mediaList.size() - 1).getId());
                        }
                    }

                    @Override
                    public void onError(Exception exception) {

                    }
                }
        );
    }


    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public void attachView(HomeView view) {
        mView = view;
    }
}
