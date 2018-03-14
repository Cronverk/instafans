package software.appus.insta_fans.presentation.common;

/**
 * Created by Владелец on 13.03.2018.
 */

public interface BasePresenterInterface<T> {
    void detachView();

    void attachView(T view);
}
