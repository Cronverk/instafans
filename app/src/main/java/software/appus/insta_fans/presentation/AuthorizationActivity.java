package software.appus.insta_fans.presentation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import software.appus.insta_fans.R;
import software.appus.insta_fans.common.BaseActivity;
import software.appus.insta_fans.common.Constants;

import static software.appus.insta_fans.common.Constants.OAUTH_URI;

public class AuthorizationActivity extends BaseActivity {
    private WebView oauthWv;
    private ProgressBar pbWaiting;
    private boolean accessGranted;


    public static Intent getLaunchIntent(Context context) {
        Intent intent = new Intent(context, AuthorizationActivity.class);
        return intent;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_auth;
    }

    @Override
    protected void attachActivityViews() {
        oauthWv = findViewById(R.id.wb_oath);
        pbWaiting = findViewById(R.id.pb_waiting);

    }

    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
        oauthWv.setVerticalScrollBarEnabled(false);
        oauthWv.setHorizontalScrollBarEnabled(false);
        oauthWv.setWebViewClient(new InstagramWebViewClient());
        oauthWv.getSettings().setJavaScriptEnabled(true);
        oauthWv.loadUrl(OAUTH_URI);
    }

    private class InstagramWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pbWaiting.setVisibility(View.VISIBLE);
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pbWaiting.setVisibility(View.GONE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Pattern pattern = Pattern.compile(Constants.REG_EXP_ACCESS_TOKEN);
            Matcher matcher = pattern.matcher(url);
            while (matcher.find()) {
                String accessUrl = matcher.group();
                if (!accessGranted && accessUrl != null && !accessUrl.isEmpty()) {
                    String accessToken = accessUrl.split("access_token=")[1];
                }
            }
            if (!accessGranted) {
                oauthWv.loadUrl(url);
            }
            return !accessGranted;
        }
    }
}
