package software.appus.insta_fans.presentation.statistic_module.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import software.appus.insta_fans.presentation.statistic_module.presenter.StatisticPresenter;
import software.appus.insta_fans.presentation.statistic_module.presenter.StatisticPresenterImpl;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class StatisticViewImpl extends AppCompatActivity implements StatisticView<StatisticPresenter> {
    private StatisticPresenter<StatisticView> presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        attachPresenter();
    }

    private void attachPresenter() {
        presenter = (StatisticPresenter<StatisticView>) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new StatisticPresenterImpl(null, null);
        }
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }

    @Override
    public StatisticPresenter<StatisticView> onRetainCustomNonConfigurationInstance() {
        return presenter;
    }
}
