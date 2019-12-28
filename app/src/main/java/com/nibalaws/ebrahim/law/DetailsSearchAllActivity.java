package com.nibalaws.ebrahim.law;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsSearchAllActivity extends AppCompatActivity {
    @BindView(R.id.homeclick)
    ImageView homeclick;
    @BindView(R.id.backclik)
    ImageView backclik;
    @BindView(R.id.toolbar2)
    Toolbar toolbar2;
    @BindView(R.id.bt_fav)
    ImageButton btFav;
    @BindView(R.id.bt_share)
    ImageButton btShare;
    @BindView(R.id.IV_ArticleTranslationIcon)
    ImageButton IVArticleTranslationIcon;
    @BindView(R.id.bt_next)
    ImageButton btNext;
    @BindView(R.id.bt_prev)
    ImageButton btPrev;
    @BindView(R.id.CLayout)
    ConstraintLayout CLayout;
    @BindView(R.id.cardView2)
    CardView cardView2;
    @BindView(R.id.bt_show)
    Button btShow;

    @BindView(R.id.txtTitel)
    TextView txtTitel;
    @BindView(R.id.textArtTitle)
    TextView textArtTitle;
    @BindView(R.id.txtname)
    TextView txtname;
    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.txt_back)
    TextView txtBack;

    private int posetion;
    private String searchText;
    private Integer flag = 0;
    private String stringURL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        setContentView(R.layout.layout_art);
        ButterKnife.bind(this);

        setViewsTypeface();
        posetion = getIntent().getIntExtra("position", 0);
        searchText = getIntent().getStringExtra("searchText");
        stringURL = getIntent().getStringExtra("url");

        textArtTitle.setText(SearchAllActivity.mSearchAllList.get(posetion).getSubInfo());
        txtname.setText(SearchAllActivity.mSearchAllList.get(posetion).getInfo());
        txt.setText(SearchAllActivity.mSearchAllList.get(posetion).getData());

        Util.SelectionSearchWord(searchText, searchText, txt);

    }


    @OnClick({R.id.homeclick, R.id.backclik, R.id.bt_fav, R.id.bt_share, R.id.bt_next, R.id.bt_prev, R.id.bt_show})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeclick:
                Util.openActivity(this, HomeActivity.class);
                break;
            case R.id.backclik:
                finish();
                break;
            case R.id.bt_fav:
                if (flag == 0) {
                    btFav.setBackgroundResource(R.drawable.ic_path_i);
                    // db.deleteData();
                    //db.deleteData(txt2.getText().toString());

                    flag = 1;
                } else {
                    btFav.setBackgroundResource(R.drawable.addtobookmark2);
                    flag = 0;
//                    db.insertData(SearchAhkamActivity.master_stracts.get(posetion).getItem1()
//                            , SearchAhkamActivity.master_stracts.get(posetion).getItem2(),
//                            SearchAhkamActivity.master_stracts.get(posetion).getItem8());

                }
                break;
            case R.id.bt_share:
                Util.shareText(getApplicationContext(), txt);
                break;

            case R.id.bt_next:
                if (posetion >= SearchAllActivity.mSearchAllList.size() - 1) {
                    return;
                }
                posetion = posetion + 1;
                textArtTitle.setText(SearchAllActivity.mSearchAllList.get(posetion).getInfo());
                txt.setText(SearchAllActivity.mSearchAllList.get(posetion).getData());
                txt2.setText(SearchAllActivity.mSearchAllList.get(posetion).getSubInfo());
                Util.SelectionSearchWord(searchText, searchText, txt);
                break;

            case R.id.bt_prev:
                if (posetion <= 0) {
                    return;
                }
                posetion = posetion - 1;
                textArtTitle.setText(SearchAllActivity.mSearchAllList.get(posetion).getInfo());
                txt.setText(SearchAllActivity.mSearchAllList.get(posetion).getData());
                txt2.setText(SearchAllActivity.mSearchAllList.get(posetion).getSubInfo());
                Util.SelectionSearchWord(searchText, searchText, txt);
                break;
            case R.id.bt_show:
                Util.openWebsiteUrl(this, stringURL, WebViewActivity.class);
                break;
        }
    }


//    private void show_fav() {
//        if ((db.checkFavorite(ArrayTXt.get(index).getItem1().toString(), ArrayTXt.get(index).getItem8().toString()))) {
//            b3.setBackgroundResource(R.drawable.addtobookmark2);
//            flag = 0;
//        } else {
//            b3.setBackgroundResource(R.drawable.ic_path_i);
//            flag = 1;
//        }
//    }

    private void setViewsTypeface() {
        Util.setViewsTypeface(this, txtTitel);
        Util.setViewsTypeface(this, txtname);
        Util.setViewsTypeface(this, txt);
        Util.setViewsTypeface(this, txt2);
        Util.setViewsTypeface(this, btShow);
        Util.setViewsTypeface(this, textArtTitle);
        Util.setViewsTypeface(this, txtBack);
    }
}
