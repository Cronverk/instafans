package software.appus.insta_fans.presentation.common;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;

import software.appus.insta_fans.domain.common.UseCaseHandler;

public abstract class BasePresenter<V extends BaseContract.View> implements LifecycleObserver,
        BaseContract.Presenter<V> {

    protected V mView;
    protected final UseCaseHandler mUseCaseHandler;

    protected BasePresenter(UseCaseHandler useCaseHandler) {
        mUseCaseHandler = useCaseHandler;
    }

    @Override
    final public V getView() {
        return mView;
    }

    @Override
    final public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }

    @Override
    final public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }

    @Override
    final public void attachView(V view) {
        this.mView = view;
    }

    @Override
    final public void detachView() {
        mView = null;
    }

    @Override
    final public boolean isViewAttached() {
        return mView != null;
    }
}
