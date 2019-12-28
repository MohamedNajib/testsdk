package com.nibalaws.ebrahim.law;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.util.ProgressUtil;
import com.nibalaws.ebrahim.law.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsSearchNiabhActivity extends AppCompatActivity {

    @BindView(R.id.txtTitel)
    TextView txtTitel;
    @BindView(R.id.txtname)
    TextView txtname;
    @BindView(R.id.txtArt)
    TextView txtArt;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.bt_show)
    Button btShow;
    @BindView(R.id.TV_back)
    TextView TVBack;
    private int posetion;
    private boolean state;
    private String URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        setContentView(R.layout.layout_law_articles);
        ButterKnife.bind(this);

        txtArt.setTextIsSelectable(true);
        txtTitel.setText("عرض التقاصيل");

        setViewsTypeface();
        posetion = getIntent().getIntExtra("position", 0);
        state = getIntent().getBooleanExtra("state", true);

        URL = getIntent().getStringExtra("url");


        if (state) {
            txtname.setText(SearchnibaActivity.localeNiabaList.get(posetion).getItem1());
            txtArt.setText(SearchnibaActivity.localeNiabaList.get(posetion).getItem2());
            txt2.setText(SearchnibaActivity.localeNiabaList.get(posetion).getItem3());
        } else {
            txtname.setText(ApiCall.searchNibaList.get(posetion).getInfo());
            txtArt.setText(ApiCall.searchNibaList.get(posetion).getDetails());
            //txt2.setText(ApiCall.searchNibaList.get(posetion).getItem3());
        }
    }

    @OnClick({R.id.homeclick, R.id.txtTitel, R.id.bt_fav, R.id.bt_share,
            R.id.IV_ArticleTranslationIcon, R.id.bt_next, R.id.bt_prev, R.id.bt_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeclick:
                Util.openActivity(this, HomeActivity.class);
                break;

            case R.id.txtTitel:
                finish();
                break;

            case R.id.bt_fav:
                break;

            case R.id.bt_share:
                Util.shareText(getApplicationContext(), txtArt);
                break;

            case R.id.IV_ArticleTranslationIcon:
                break;

            case R.id.bt_next:
                if (state) {
                    if (posetion >= SearchnibaActivity.localeNiabaList.size() - 1) {
                        return;
                    }
                    posetion = posetion + 1;
                    txtname.setText(SearchnibaActivity.localeNiabaList.get(posetion).getItem1());
                    txtArt.setText(SearchnibaActivity.localeNiabaList.get(posetion).getItem2());
                    txt2.setText(SearchnibaActivity.localeNiabaList.get(posetion).getItem3());
                } else {
                    if (posetion >= ApiCall.searchNibaList.size() - 1) {
                        return;
                    }
                    posetion = posetion + 1;
                    txtname.setText(ApiCall.searchNibaList.get(posetion).getInfo());
                    txtArt.setText(ApiCall.searchNibaList.get(posetion).getDetails());
                }
                break;

            case R.id.bt_prev:
                if (posetion <= 0) {
                    return;
                }
                posetion = posetion - 1;
                if (state) {
                    txtname.setText(SearchnibaActivity.localeNiabaList.get(posetion).getItem1());
                    txtArt.setText(SearchnibaActivity.localeNiabaList.get(posetion).getItem2());
                    txt2.setText(SearchnibaActivity.localeNiabaList.get(posetion).getItem3());
                } else {
                    txtname.setText(ApiCall.searchNibaList.get(posetion).getInfo());
                    txtArt.setText(ApiCall.searchNibaList.get(posetion).getDetails());
//                    txt2.setText(SearchAhkamActivity.master_stracts.get(posetion).getItem6());
                }
                break;

            case R.id.bt_show:
                Util.openWebsiteUrl(this, URL, WebViewActivity.class);
                break;
        }
    }

    private void setViewsTypeface() {
        Util.setViewsTypeface(this, txtTitel);
        Util.setViewsTypeface(this, txtname);
        Util.setViewsTypeface(this, txtArt);
        Util.setViewsTypeface(this, txt2);
        Util.setViewsTypeface(this, btShow);
        Util.setViewsTypeface(this, TVBack);
    }
}
