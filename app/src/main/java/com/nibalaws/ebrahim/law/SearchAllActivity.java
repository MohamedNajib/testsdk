package com.nibalaws.ebrahim.law;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.nibalaws.ebrahim.law.adapter.SearchAllAdapter;
import com.nibalaws.ebrahim.law.rest.APIManager;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchAllResponse;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchAllActivity extends AppCompatActivity {

    @BindView(R.id.RV_SearchAll)
    RecyclerView RVSearchAll;
    @BindView(R.id.txtTitel)
    TextView txtTitel;
    @BindView(R.id.CallUsTxtBack)
    TextView CallUsTxtBack;
    @BindView(R.id.toggle)
    RadioGroup toggle;
    @BindView(R.id.calm1)
    RadioButton calm1;
    @BindView(R.id.calm2)
    RadioButton calm2;
    @BindView(R.id.calm3)
    RadioButton calm3;
    @BindView(R.id.calm4)
    RadioButton calm4;

    // var
    private SearchAllAdapter mSearchAllAdapter;
    public static List<SearchAllResponse> mSearchAllList;
    private SVProgressHUD mProgress;

    private String searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        setContentView(R.layout.activity_search_all);
        ButterKnife.bind(this);
        setViewsTypeface();

        mSearchAllList = new ArrayList<>();
        mProgress = new SVProgressHUD(this);
        mProgress.show();

        searchText = getIntent().getStringExtra("search_text");
        searchAll(searchText, 1, "1");
    }

    private void initRecyclerView(final List<SearchAllResponse> searchAllList) {
        RVSearchAll.setLayoutManager(new LinearLayoutManager(this));
        RVSearchAll.setHasFixedSize(true);
        RVSearchAll.setItemAnimator(new DefaultItemAnimator());

        mSearchAllAdapter = new SearchAllAdapter(searchAllList, this,
                new SearchAllAdapter.OnItemClick() {
                    @Override
                    public void setOnItemClicked(int position) {
                        SearchAllResponse searchAllResponse = searchAllList.get(position);

                        Intent intent = new Intent(SearchAllActivity.this, DetailsSearchAllActivity.class);
                        intent.putExtra("position", position);
                        intent.putExtra("searchText", searchText);
                        intent.putExtra("url", searchAllResponse.getUrl());
                        startActivity(intent);
                    }
                });
        RVSearchAll.setAdapter(mSearchAllAdapter);
    }

    private void searchAll(String word, int page, String tb_index) {
        mSearchAllList.clear();
        APIManager.getApis().searchAll(word, page, tb_index)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<SearchAllResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<SearchAllResponse> searchAllResponses) {
                        mProgress.dismiss();
                        mSearchAllList.addAll(searchAllResponses);
                        initRecyclerView(mSearchAllList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgress.dismiss();
                        Util.showWarrning(SearchAllActivity.this, e.getLocalizedMessage());
                    }
                });
    }

    public void chickButton(View view) {
        int radioId = toggle.getCheckedRadioButtonId();
        switch (radioId) {
            case R.id.calm1:
                mProgress.show();
                searchAll(searchText, 1, "1");
                // التشريعات
                break;
            case R.id.calm2:
                mProgress.show();
                // النقض
                searchAll(searchText, 1, "3");
                break;
            case R.id.calm3:
                mProgress.show();
                searchAll(searchText, 1, "4");
                // قيود واوصاف
                break;
            case R.id.calm4:
                mProgress.show();
                searchAll(searchText, 1, "5");
                // العملية
                break;
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

    private void setViewsTypeface() {
        Util.setViewsTypeface(this, txtTitel);
        Util.setViewsTypeface(this, CallUsTxtBack);
        Util.setViewsTypeface(this, calm1);
        Util.setViewsTypeface(this, calm2);
        Util.setViewsTypeface(this, calm3);
        Util.setViewsTypeface(this, calm4);
    }
}
