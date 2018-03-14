package software.appus.insta_fans.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract int getLayoutId();

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(getLayoutId());
        attachActivityViews();
        initActivityViews(savedInstanceState);
    }

    protected abstract void attachActivityViews();

    protected abstract void initActivityViews(Bundle savedInstanceState);

}
