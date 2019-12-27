package com.nibalaws.ebrahim.law;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.nibalaws.ebrahim.law.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.txtTitel)
    TextView txtTitel;
    @BindView(R.id.webview1)
    WebView webview1;
    @BindView(R.id.txt_back)
    TextView txtBack;

    private String URL;
    public SVProgressHUD mProgress;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        Util.setViewsTypeface(this, txtBack);
        Util.setViewsTypeface(this, txtTitel);
        mProgress = new SVProgressHUD(this);

        URL = getIntent().getStringExtra("stringURL");
        Toast.makeText(this, URL, Toast.LENGTH_SHORT).show();

        mProgress.show();
        WebSettings webSettings = webview1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        WebViewClient webViewClient = new WebViewClient();
        webview1.setWebViewClient(webViewClient);

        webview1.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webview1.loadUrl(URL);

        webview1.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (mProgress.isShowing()) {
                    mProgress.dismiss();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (webview1.canGoBack()) {
            webview1.canGoBack();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick({R.id.homeclick, R.id.backclik})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeclick:
                Util.openActivity(this, HomeActivity.class);
                break;
            case R.id.backclik:
                finish();
                break;
        }
    }
}
