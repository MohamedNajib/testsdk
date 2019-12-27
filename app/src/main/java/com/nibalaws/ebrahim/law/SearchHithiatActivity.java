package com.nibalaws.ebrahim.law;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.adapter.SearchHithiatApiAdapter;
import com.nibalaws.ebrahim.law.adapter.SearchNiabhApiAdapter;
import com.nibalaws.ebrahim.law.adapter.SearchHithiatLocaleAdapter;
import com.nibalaws.ebrahim.law.rest.apiModel.Search;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchHithiatActivity extends AppCompatActivity {

    @BindView(R.id.txtTitel)
    TextView txtTitel;
    @BindView(R.id.FavoriteTxtBack)
    TextView FavoriteTxtBack;
    @BindView(R.id.RV_SearchHithiat)
    RecyclerView RVSearchHithiat;
    @BindView(R.id.editText)
    EditText editText;

    private SearchHithiatLocaleAdapter localeAdapter;
    private SearchHithiatApiAdapter mSearchHithiatApiAdapter;
    private SVProgressHUD mProgress;
    private DatabaseHelper databaseHelper;
    private ArrayList<Master_Stract> localeHithiatList;

    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        setContentView(R.layout.activity_search_hithiat);
        ButterKnife.bind(this);
        txtTitel.setText("حيثيات");
        state = getIntent().getStringExtra("state");
        setViewsTypeface();
        RVSearchHithiat.setLayoutManager(new LinearLayoutManager(this));
        RVSearchHithiat.setHasFixedSize(true);
        RVSearchHithiat.setItemAnimator(new DefaultItemAnimator());
        mProgress = new SVProgressHUD(this);
        databaseHelper = new DatabaseHelper(this);
        localeHithiatList = new ArrayList<>();
    }

    public void initLocaleRecyclerView(final List<Master_Stract> localeHithiat) {
        localeAdapter = new SearchHithiatLocaleAdapter(this, localeHithiat, new SearchHithiatLocaleAdapter.OnItemClick() {
            @Override
            public void setOnItemClicked(int position) {

            }
        });
        RVSearchHithiat.setAdapter(localeAdapter);
        mProgress.dismiss();
    }

    public void initApiRecyclerView(List<Search> search) {
        mSearchHithiatApiAdapter = new SearchHithiatApiAdapter(this, search,
                new SearchHithiatApiAdapter.OnItemClick() {
                    @Override
                    public void setOnItemClicked(int position) {

                    }
                });
        RVSearchHithiat.setAdapter(mSearchHithiatApiAdapter);
    }

    @OnClick({R.id.homeclick, R.id.backclik, R.id.Fl_SearchIcon})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeclick:
                Util.openActivity(this, HomeActivity.class);
                break;
            case R.id.backclik:
                finish();
                break;
            case R.id.Fl_SearchIcon:
                if (state.equals("OnLine")) {
                    searchApi();
                } else if (state.equals("OfLine")) {
                    searchLocale();
                }
                break;
        }
    }

    private void searchApi() {
        if (editText.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "لا يوجد كلمة بحث", Toast.LENGTH_SHORT).show();
        } else {
            localeHithiatList = databaseHelper.searchListQiods(editText.getText().toString());
            mProgress.show();
            ApiCall.searchHithiatCall(this,"قتل" /*editText.getText().toString()*/,
                    "1,2,3,4,5,7,8,6,10,9,20", 1, "1", mProgress);
        }
    }

    private void searchLocale() {
        if (!editText.getText().toString().trim().isEmpty()) {
            mProgress.show();
            localeHithiatList = databaseHelper.searchHithiatLocal(editText.getText().toString());
            initLocaleRecyclerView(localeHithiatList);
        } else {
            Toast.makeText(this, "لا يوجد كلمة بحث", Toast.LENGTH_SHORT).show();
        }
    }

    private void setViewsTypeface() {
        Util.setViewsTypeface(this, txtTitel);
        Util.setViewsTypeface(this, FavoriteTxtBack);
        Util.setViewsTypeface(this, editText);
    }


}
