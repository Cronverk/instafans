package software.appus.insta_fans.presentation.settings_module.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import software.appus.insta_fans.R;
import software.appus.insta_fans.common.BaseFragment;
import software.appus.insta_fans.common.adapter.ListDelegateAdapter;
import software.appus.insta_fans.presentation.common.adapter.delegates.SettingsDelegate;
import software.appus.insta_fans.presentation.common.utils.RateAppUtil;
import software.appus.insta_fans.presentation.models.UserModel;
import software.appus.insta_fans.presentation.settings_module.model.SettingsItemModel;
import software.appus.insta_fans.presentation.settings_module.model.SettingsProvider;
import software.appus.insta_fans.presentation.settings_module.presenter.SettignsPresenterImpl;
import software.appus.insta_fans.presentation.settings_module.presenter.SettingsPresenter;

/**
 * Created by anatolii.pozniak on 3/13/18.
 */

public class SettingsViewImpl extends BaseFragment implements SettingsView<SettingsPresenter> {
    private RecyclerView mRecycler;
    private ListDelegateAdapter<SettingsItemModel> settingsAdapter;

    private SettingsPresenter<SettingsView> presenter;


    public static SettingsViewImpl newInstance() {

        Bundle args = new Bundle();

        SettingsViewImpl fragment = new SettingsViewImpl();
        fragment.setArguments(args);
        return fragment;
    }

    private void attachPresenter() {
//        presenter = (SettingsPresenter<SettingsView>) getLastCustomNonConfigurationInstance();
        if (presenter == null) {
            presenter = new SettignsPresenterImpl();
        }
//        presenter.attachView(this);
    }

    @Override
    public void updateUserInfo(UserModel user) {

    }

    @Override
    protected int contentViewId() {
        return R.layout.fragment_recycler;
    }

    @Override
    protected void attachFragmentViews(View view) {
        attachPresenter();
        mRecycler = view.findViewById(R.id.recycler);
    }

    @Override
    protected void initFragmentViews(Bundle savedInstanceState) {
        intiRecyler();
    }

    private void intiRecyler() {
        settingsAdapter = new ListDelegateAdapter.Builder<SettingsItemModel>()
                .addDelegate(new SettingsDelegate())
                .setItems(SettingsProvider.getInstance().provide())
                .setListener(this::eventItemClicked)
                .build();
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(settingsAdapter);
    }

    private void eventItemClicked(SettingsItemModel model, int position) {
        switch (model.getType()) {
            case TERMS_AND_CONDITIONS:
                showTerms();
                break;
            case WHITE_FEEDBACK:
                whiteFeedback();
                break;
            case SHARE:
                shareAppLink();
                break;
            case CHECK_OUT_MORE_OUR_APPS:
                checkOutMoreOurApps();
                break;
            case RATE_THIS_APP:
                rateThisApp();
                break;
        }
    }

    private void showTerms() {
//        Intent i = new Intent(getActivity(), TermOfServiceActivity.class);
//        startActivity(i);
    }

    private void shareAppLink() {

    }

    private void whiteFeedback() {
        String mailto = "mailto:support@appus.software" +
                "?subject=" + Uri.encode(getString(R.string.likulator_user_feedback));

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse(mailto));
        try {
            startActivity(Intent.createChooser(emailIntent, "Send feedback..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkOutMoreOurApps() {
        //  startActivity(AppListActivity.getLaunchIntent(getActivity(), ResourcesUtil.getString(R.string.developer_id)));
    }

    private void rateThisApp() {
        RateAppUtil.rateThisApp(getActivity());
    }

    @Override
    public void onDetach() {
        presenter.detachView();
        super.onDetach();
    }
}
