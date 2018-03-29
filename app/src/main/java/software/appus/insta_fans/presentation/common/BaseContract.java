package software.appus.insta_fans.presentation.common;

import android.arch.lifecycle.Lifecycle;

/**
 * Created by Владелец on 29.03.2018.
 */

public class BaseContract {
    public interface View {

    }

    public interface Presenter<V extends View> {

        void attachLifecycle(Lifecycle lifecycle);

        void detachLifecycle(Lifecycle lifecycle);

        void attachView(V view);

        void detachView();

        V getView();

        boolean isViewAttached();

        void onPresenterDestroy();
    }
}
