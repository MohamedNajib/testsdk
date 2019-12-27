package com.nibalaws.ebrahim.law;

import android.content.Intent;
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
import com.nibalaws.ebrahim.law.adapter.SearchNiabhApiAdapter;
import com.nibalaws.ebrahim.law.adapter.SearchHithiatLocaleAdapter;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchResponse;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchnibaActivity extends AppCompatActivity {

    @BindView(R.id.txtTitel)
    TextView txtTitel;
    @BindView(R.id.FavoriteTxtBack)
    TextView FavoriteTxtBack;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.RV_SearchHithiat)
    RecyclerView RVSearchHithiat;

    private SearchHithiatLocaleAdapter localeAdapter;
    private SearchNiabhApiAdapter mSearchHithiatApiAdapter;
    private SVProgressHUD mProgress;
    public static ArrayList<Master_Stract> localeNiabaList;
    private DatabaseHelper databaseHelper;

    private String state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        setContentView(R.layout.activity_search_hithiat);
        ButterKnife.bind(this);

        state = getIntent().getStringExtra("state");

        RVSearchHithiat.setLayoutManager(new LinearLayoutManager(this));
        RVSearchHithiat.setHasFixedSize(true);
        RVSearchHithiat.setItemAnimator(new DefaultItemAnimator());
        databaseHelper = new DatabaseHelper(this);

        txtTitel.setText("نيابة");
        setViewsTypeface();
        mProgress = new SVProgressHUD(this);
        localeNiabaList = new ArrayList<>();
    }

    public void initLocaleRecyclerView(List<Master_Stract> localeNiaba) {
        localeAdapter = new SearchHithiatLocaleAdapter(this, localeNiaba, new SearchHithiatLocaleAdapter.OnItemClick() {
            @Override
            public void setOnItemClicked(int position) {
                Intent intent = new Intent(SearchnibaActivity.this, DetailsSearchNiabhActivity.class);
                intent.putExtra("position", position);
                intent.putExtra("state", true);
                startActivity(intent);
            }
        });
        RVSearchHithiat.setAdapter(localeAdapter);
        mProgress.dismiss();
    }

    public void iniApitRecyclerView(final List<SearchResponse> search) {
        mSearchHithiatApiAdapter = new SearchNiabhApiAdapter(this, search,
                new SearchNiabhApiAdapter.OnItemClick() {
                    @Override
                    public void setOnItemClicked(int position) {
                        SearchResponse searchResponse = search.get(position);
                        Intent intent = new Intent(SearchnibaActivity.this, DetailsSearchNiabhActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("state", false);
                        intent.putExtra("url", searchResponse.getUrl());
                        startActivity(intent);
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
            mProgress.show();
            ApiCall.searchNibaCall(this, editText.getText().toString(),
                    "1,2,3,4,5,7,8,6,10,9,20", "1,2,3", 1, mProgress);
        }
    }

    private void searchLocale() {
        if (!editText.getText().toString().trim().isEmpty()) {
            mProgress.show();
            localeNiabaList = databaseHelper.searchListQiods(editText.getText().toString());
            initLocaleRecyclerView(localeNiabaList);
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
