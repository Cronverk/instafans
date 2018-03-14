package software.appus.insta_fans.presentation.common;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import software.appus.insta_fans.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;

    public abstract int getLayoutId();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(getLayoutId());
        attachActivityViews();
        initActivityViews();
    }

    protected abstract void attachActivityViews();

    protected abstract void initActivityViews();


    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = this.getCurrentFocus();
        if (v != null) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            v.clearFocus();
        }
    }

    public void replaceFragment(Fragment fragment, boolean useBackstack, View... sharedViews) {
        replaceFragment(fragment, useBackstack, false, sharedViews);
    }

    public void addFragment(Fragment fragment, boolean useBackstack, View... sharedViews) {
        replaceFragment(fragment, useBackstack, true, sharedViews);
    }

    private void replaceFragment(Fragment fragment, boolean useBackstack, boolean isAdd, View... sharedViews) {
        if (fragment == null) {
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (isAdd) {
            fragmentTransaction.add(R.id.fl_container, fragment, fragment.getClass().getSimpleName());
        } else {
            fragmentTransaction.replace(R.id.fl_container, fragment, fragment.getClass().getSimpleName());
        }


        if (useBackstack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
//        if (sharedViews != null && sharedViews.length != 0) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                Fragment previousFragment = getSupportFragmentManager().findFragmentById(R.id.fl_container);
//                fragment.setSharedElementEnterTransition(new DetailsTransition());
//                fragment.setEnterTransition(new Fade());
//                if (previousFragment != null) {
//                    previousFragment.setExitTransition(new Fade());
//                }
//                fragment.setSharedElementReturnTransition(new DetailsTransition());
//                for (View view : sharedViews) {
//                    fragmentTransaction.addSharedElement(view, ViewCompat.getTransitionName(view));
//                }
//            }
//        }
        fragmentTransaction.commitAllowingStateLoss();
    }

}
