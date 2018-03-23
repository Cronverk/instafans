package software.appus.insta_fans.presentation.common.utils;

import retrofit2.Response;
import software.appus.insta_fans.data.net.AppException;

/**
 * Created by anatolii.pozniak on 3/23/18.
 */

public class ResponseErrorChecker {

    private static ResponseErrorChecker INSTANCE;

    private ResponseErrorChecker() {
    }

    public static ResponseErrorChecker getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ResponseErrorChecker();
        }
        return INSTANCE;
    }

    public <T> T checkResponse(Response<T> response) {
        if (response.isSuccessful()) {
            return response.body();
        } else {
            switch (response.code()) {
                case 400:
                    throw ErrorFactory.getInstance().create(AppException.Type.BAD_REQUEST, (response.errorBody() != null) ? response.errorBody().toString() : "");
                case 401:
                case 403:
                    throw ErrorFactory.getInstance().create(AppException.Type.UNAUTHORIZED, (response.errorBody() != null) ? response.errorBody().toString() : "");
                case 408:
                    throw ErrorFactory.getInstance().create(AppException.Type.TIMEOUT, (response.errorBody() != null) ? response.errorBody().toString() : "");
                default:
                    throw ErrorFactory.getInstance().create(AppException.Type.DEFAULT, "");
            }
        }
    }
}
