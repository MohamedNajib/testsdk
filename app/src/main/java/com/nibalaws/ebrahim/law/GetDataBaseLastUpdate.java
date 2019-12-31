package com.nibalaws.ebrahim.law;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.nibalaws.ebrahim.law.ApiCall;
import com.nibalaws.ebrahim.law.HomeActivity;
import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.adapter.SearchHithiatApiAdapter;
import com.nibalaws.ebrahim.law.rest.apiModel.Search;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetDataBaseLastUpdate extends AppCompatActivity {

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

    private SVProgressHUD mProgress;
    private SearchHithiatApiAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        setContentView(R.layout.activity_search_all);
        ButterKnife.bind(this);
        setViewsTypeface();

        RVSearchAll.setLayoutManager(new LinearLayoutManager(this));
        RVSearchAll.setHasFixedSize(true);
        RVSearchAll.setItemAnimator(new DefaultItemAnimator());
        mProgress = new SVProgressHUD(this);
        mProgress.show();

        ApiCall.getDataBaseLastUpdateCall(this, "1", mProgress);
    }

    public void initReccyclerView(List<Search> searchesList){
        mAdapter =new SearchHithiatApiAdapter(this, searchesList, new SearchHithiatApiAdapter.OnItemClick() {
            @Override
            public void setOnItemClicked(int position) {

            }
        });
        RVSearchAll.setAdapter(mAdapter);
    }

    public void chickButton(View view) {
        int radioId = toggle.getCheckedRadioButtonId();
        switch (radioId) {
            case R.id.calm1:
                mProgress.show();
                ApiCall.getDataBaseLastUpdateCall(this, "1", mProgress);
                // التشريعات
                break;
            case R.id.calm2:
                mProgress.show();
                // النقض
                ApiCall.getDataBaseLastUpdateCall(this, "3", mProgress);
                break;
            case R.id.calm3:
                mProgress.show();
                ApiCall.getDataBaseLastUpdateCall(this, "4", mProgress);
                // قيود واوصاف
                break;
            case R.id.calm4:
                mProgress.show();
                ApiCall.getDataBaseLastUpdateCall(this, "5", mProgress);
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
