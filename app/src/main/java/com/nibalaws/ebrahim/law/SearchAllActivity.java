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
import android.widget.Toast;

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

    private RadioButton radioButton;

    // var
    private SearchAllAdapter mSearchAllAdapter;
    public static List<SearchAllResponse> mSearchAllList;
    private SVProgressHUD mProgress;

    public void chickButton(View view){
        int radioId = toggle.getCheckedRadioButtonId();
        switch (radioId){
            case R.id.calm1:
                Toast.makeText(this, "التشريعات", Toast.LENGTH_SHORT).show();
                break;
            case R.id.calm2:
                Toast.makeText(this, "النقض", Toast.LENGTH_SHORT).show();
                break;
            case R.id.calm3:
                Toast.makeText(this, "قيود واوصاف", Toast.LENGTH_SHORT).show();
                break;
            case R.id.calm4:
                Toast.makeText(this, "العملية", Toast.LENGTH_SHORT).show();
                break;
        }
//        radioButton.findViewById(radioId);
  //      Toast.makeText(this, radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_all);
        ButterKnife.bind(this);
        setViewsTypeface();

        mSearchAllList = new ArrayList<>();
        mProgress = new SVProgressHUD(this);
        mProgress.show();

        String tex = getIntent().getStringExtra("search_text");
        searchAll(tex, 1, "3");
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

                        Intent intent = new Intent(SearchAllActivity.this, DetailsSearchAhkamActivity.class);
                        intent.putExtra("position", position);
                        startActivity(intent);

//                        Toast.makeText(SearchAllActivity.this, "" + searchAllResponse.getId()
//                                , Toast.LENGTH_SHORT).show();
                    }
                });
        RVSearchAll.setAdapter(mSearchAllAdapter);
    }

    @OnClick({R.id.homeclick, R.id.backclik})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeclick:
                openActivity(HomeActivity.class);
                break;
            case R.id.backclik:
                finish();
                break;
        }
    }

    private void openActivity(Class<?> cls) {
        Intent intent = new Intent(SearchAllActivity.this, cls);
        startActivity(intent);
    }

    private void searchAll(String word, int page, String tb_index) {
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
                        Toast.makeText(SearchAllActivity.this, "Siz" + mSearchAllList.size(),
                                Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgress.dismiss();
                        Util.showWarrning(SearchAllActivity.this, e.getLocalizedMessage());
//                        Toast.makeText(SearchAllActivity.this, "Oops Error: \n"
//                                + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void setViewsTypeface() {
        Util.setViewsTypeface(this, txtTitel);
        Util.setViewsTypeface(this, CallUsTxtBack);
    }
}
