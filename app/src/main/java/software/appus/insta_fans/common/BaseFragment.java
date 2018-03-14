package software.appus.insta_fans.common;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {

    private View inflateView;

    public BaseActivity getBaseActivity() {
        BaseActivity bActivity = null;
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            bActivity = (BaseActivity) activity;
        }
        return bActivity;
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflateView == null) {
            inflateView = inflateView(inflater, container);
            if (inflateView != null) {
                attachFragmentViews(inflateView);
                initFragmentViews(savedInstanceState);
            }
        }
        return inflateView;
    }

    @Nullable
    private View inflateView(LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = null;
        int contentView = contentViewId();
        if (contentView > 0) {
            view = inflater.inflate(contentView, container, false);
        }
        return view;
    }

    @LayoutRes
    protected abstract int contentViewId();

    @Override
    public void onPause() {
        super.onPause();
        hideKeyboard();
    }

    public void hideKeyboard() {
        BaseActivity bActivity = getBaseActivity();
        if (bActivity != null) {
//            bActivity.hideKeyboard();
        }
    }

    protected abstract void attachFragmentViews(View view);

    protected abstract void initFragmentViews(Bundle savedInstanceState);

}
