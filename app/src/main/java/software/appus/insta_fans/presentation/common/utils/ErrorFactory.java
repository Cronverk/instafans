package software.appus.insta_fans.presentation.common.utils;

import software.appus.insta_fans.R;
import software.appus.insta_fans.data.net.AppException;

/**
 * Created by anatolii.pozniak on 3/23/18.
 */

public class ErrorFactory {
    private static ErrorFactory INSTANCE;

    private ErrorFactory() {
    }

    public static ErrorFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ErrorFactory();
        }
        return INSTANCE;
    }

    public AppException create(AppException.Type type, String exceptionMessage) {
        switch (type) {
            case TIMEOUT:
                return new AppException.Builder(type)
                        .setMessage(exceptionMessage)
                        .setMessageId(R.id.error_timeout)
                        .build();
            case INTERNET:
                return new AppException.Builder(type)
                        .setMessage(exceptionMessage)
                        .setMessageId(R.id.error_internet)
                        .build();
            case WRONG_DATA:
                return new AppException.Builder(type)
                        .setMessage(exceptionMessage)
                        .setMessageId(R.id.wrong_data)
                        .build();
            case BAD_REQUEST:
                return new AppException.Builder(type)
                        .setMessage(exceptionMessage)
                        .setMessageId(R.id.bad_request)
                        .build();
            case UNAUTHORIZED:
                return new AppException.Builder(type)
                        .setMessage(exceptionMessage)
                        .setMessageId(R.id.unauthorized)
                        .build();
            default:
                return new AppException.Builder(AppException.Type.DEFAULT)
                        .setMessage(exceptionMessage)
                        .setMessageId(R.id.error_default)
                        .build();
        }

    }
}
