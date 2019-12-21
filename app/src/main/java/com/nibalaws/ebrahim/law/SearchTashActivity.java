package com.nibalaws.ebrahim.law;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.adapter.DialogSearchCustomAdapter;
import com.nibalaws.ebrahim.law.adapter.SearchLocaleTashAdapter;
import com.nibalaws.ebrahim.law.rest.APIManager;
import com.nibalaws.ebrahim.law.rest.DialogSearchDataModel;
import com.nibalaws.ebrahim.law.rest.InputValidation;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchTashResponse;
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

public class SearchTashActivity extends AppCompatActivity {

    @BindView(R.id.localeRV)
    RecyclerView localeRV;
    @BindView(R.id.scrollView3)
    ScrollView scrollView3;
    @BindView(R.id.txtTitel)
    TextView txtTitel;
    @BindView(R.id.backText)
    TextView backText;
    @BindView(R.id.t1)
    TextView t1;
    @BindView(R.id.t2)
    TextView t2;
    @BindView(R.id.t3)
    TextView t3;
    @BindView(R.id.t4)
    TextView t4;
    @BindView(R.id.t5)
    TextView t5;
    @BindView(R.id.t6)
    TextView t6;
    @BindView(R.id.BTN_SearchTash)
    Button BTNSearchTash;
    @BindView(R.id.tash_name)
    EditText TashName;
    @BindView(R.id.Word)
    EditText Word;
    @BindView(R.id.Tash_no_from)
    EditText TashNoFrom;
    @BindView(R.id.Tash_no_to)
    EditText TashNoTo;
    @BindView(R.id.Tash_year_from)
    EditText TashYearFrom;
    @BindView(R.id.Tash_year_to)
    EditText TashYearTo;
    @BindView(R.id.date_from)
    EditText DateFrom;
    @BindView(R.id.date_to)
    EditText DateTo;
    @BindView(R.id.Type_ids)
    TextView TypeIds;


    private DatabaseHelper databaseHelper;
    private ArrayList<DialogSearchDataModel> dataModels;
    private DialogSearchCustomAdapter adapter;
    private List<String> listOfIds;
    private List<String> listOfType;

    private int backState = 0;

    private ArrayList<Master_Stract> master_stracts;
    private SearchLocaleTashAdapter searchTashAdapter;

    private String tashNoFrom, tashNoTo, tashYearFrom, tashYearTo, dateFrom, dateTo;
    private String tashName, word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tash);
        ButterKnife.bind(this);
        scrollView3.setVisibility(View.VISIBLE);
        localeRV.setVisibility(View.GONE);

        txtTitel.setText("بحث مخصص");
        setViewsTypeface();

        localeRV.setLayoutManager(new LinearLayoutManager(this));
        localeRV.setHasFixedSize(true);

        // database Helper
        databaseHelper = new DatabaseHelper(this);

        dataModels = new ArrayList();
        listOfIds = new ArrayList<>();
        listOfType = new ArrayList<>();
        dataModels = databaseHelper.gettypeDialogSearch();

        master_stracts = new ArrayList<>();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (backState == 1) {
            backState = 0;
            scrollView3.setVisibility(View.VISIBLE);
            localeRV.setVisibility(View.GONE);
            txtTitel.setText("بحث مخصص");
        } else {
            super.onBackPressed();
        }
    }

    private void SearchTash(String tashNoFrom, String tashNoTo, String tashYearFrom, String tashYearTo, String dateFrom,
                            String dateTo, String typeIds, String word, String searchType, String tashName, int page) {

        scrollView3.setVisibility(View.GONE);
        localeRV.setVisibility(View.VISIBLE);
        backState = 1;
        APIManager.getApis().SearchTash(tashNoFrom, tashNoTo, tashYearFrom, tashYearTo,
                dateFrom, dateTo, typeIds, word, searchType, tashName, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<SearchTashResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<SearchTashResponse> searchTashResponses) {
                        for (int i = 0; i < searchTashResponses.size(); i++) {

                            master_stracts.add(new Master_Stract(searchTashResponses.get(i).getId(),
                                    searchTashResponses.get(i).getInfo(), searchTashResponses.get(i).getEditUrl(),
                                    searchTashResponses.get(i).getPicUrl(), searchTashResponses.get(i).getEditUrl(),
                                    searchTashResponses.get(i).getLai7aUrl(), searchTashResponses.get(i).getMdaTit(),
                                    searchTashResponses.get(i).getMdaId()));
                        }

                        searchTashAdapter = new SearchLocaleTashAdapter(master_stracts);
                        localeRV.setAdapter(searchTashAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(SearchTashActivity.this, "Oops Error: \n"
                                + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void searchLocaleTash() {
        backState = 1;
        txtTitel.setText("نتيجة البحث");

        if (word.trim() != null && !word.trim().isEmpty()) {
            master_stracts = databaseHelper.SearchTash(tashNoFrom, tashNoTo,
                    tashYearFrom, tashYearTo, getTypeIds(listOfIds), dateFrom, dateTo, 1,
                    word, tashName);
        } else {
            master_stracts = databaseHelper.SearchTash(tashNoFrom, tashNoTo,
                    tashYearFrom, tashYearTo, getTypeIds(listOfIds), dateFrom, dateTo, 0,
                    word, tashName);
        }
        scrollView3.setVisibility(View.GONE);
        localeRV.setVisibility(View.VISIBLE);

        searchTashAdapter = new SearchLocaleTashAdapter(master_stracts);
        localeRV.setAdapter(searchTashAdapter);
    }

    private void getUserSearchInput() {
        SearchTash(tashNoFrom, tashNoTo, tashYearFrom, tashYearTo, dateFrom, dateTo,
                getTypeIds(listOfIds), word, "", tashName, 1);
    }


    @OnClick(R.id.BTN_SearchTash)
    public void onViewClicked() {
        //getUserSearchInput();

        isValidInput();
    }

    private void isValidInput() {
        if (!InputValidation.emptyInput(TashName) && !InputValidation.emptyInput(Word)
                && !InputValidation.emptyInput(TashNoFrom) && !InputValidation.emptyInput(TashNoTo)
                && !InputValidation.emptyInput(TashYearFrom) && !InputValidation.emptyInput(TashYearTo)
                && !InputValidation.emptyInput(DateFrom) && !InputValidation.emptyInput(DateTo)
                && getTypeIds(listOfType).trim().isEmpty()) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            tashName = TashName.getText().toString();
            word = Word.getText().toString();

            tashNoFrom = TashNoFrom.getText().toString();
            tashNoTo = TashNoTo.getText().toString();
            tashYearFrom = TashYearFrom.getText().toString();
            tashYearTo = TashYearTo.getText().toString();
            dateFrom = DateFrom.getText().toString();
            dateTo = DateTo.getText().toString();

            String tashNo = InputValidation.isValidFromTo(tashNoFrom, tashNoTo);
            String tashYear = InputValidation.isValidFromTo(tashYearFrom, tashYearTo);
            String tashdate = InputValidation.isValidFromTo(dateFrom, dateTo);

            if (tashNo == null) {

            } else {
                tashNoFrom = tashNo;
                tashNoTo = tashNo;
            }

            if (tashYear == null) {

            } else {
                tashYearFrom = tashYear;
                tashYearTo = tashYear;
            }

            if (tashdate == null) {

            } else {
                dateFrom = tashdate;
                dateTo = tashdate;
            }
            searchLocaleTash();
            //getUserSearchInput();
        }
    }


    public void showExpensesDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.searsh_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.setCancelable(true);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;


        ListView SearchListView = dialog.findViewById(R.id.SearchListView);
        Button BTN_selected = dialog.findViewById(R.id.BTN_selected);
        ImageView dialogBackIcon = dialog.findViewById(R.id.backclik);
        EditText ET_search = dialog.findViewById(R.id.ET_search);
        TextView backText = dialog.findViewById(R.id.backText);
        TextView txtTitel = dialog.findViewById(R.id.txtTitel);
        ImageView homeclick = dialog.findViewById(R.id.homeclick);

//        txtTitel.setText("بحث التشريعات");
        txtTitel.setText("اختار نوع التشريعات");
        Util.setViewsTypeface(this, BTN_selected);
        Util.setViewsTypeface(this, ET_search);
        Util.setViewsTypeface(this, backText);
        Util.setViewsTypeface(this, txtTitel);

        adapter = new DialogSearchCustomAdapter(dataModels, R.layout.dialog_row_item, this);
        SearchListView.setAdapter(adapter);
        SearchListView.setTextFilterEnabled(true);
        SearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                //DialogSearchDataModel dataModel = dataModels.get(position);
                DialogSearchDataModel dataModel = (DialogSearchDataModel) parent.getItemAtPosition(position);
                dataModel.checked = !dataModel.checked;
                adapter.notifyDataSetChanged();

                if (dataModel.isChecked()) {
                    listOfIds.add(dataModel.getId());
                    listOfType.add(dataModel.getName());
                } else {
                    listOfIds.remove(dataModel.getId());
                    listOfType.remove(dataModel.getName());
                }

                Toast.makeText(SearchTashActivity.this, getTypeIds(listOfIds), Toast.LENGTH_SHORT).show();

            }
        });

        ET_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence.toString());

                //adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        BTN_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                TypeIds.setText(getTypeIds(listOfType));
            }
        });

        dialogBackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        homeclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util.openActivity(SearchTashActivity.this, HomeActivity.class);
            }
        });

        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.show();
    }

    /**
     * get String list Of Ids
     */
    private String getTypeIds(List<String> typeId) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < typeId.size(); i++) {
            builder.append(typeId.get(i));
            if (i != typeId.size() - 1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    @OnClick(R.id.F_Type_ids)
    public void onTypeIDViewClicked() {
        showExpensesDialog();
    }

    @OnClick({R.id.homeclick, R.id.backclik})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.homeclick:
                Util.openActivity(this, HomeActivity.class);
                break;
            case R.id.backclik:
                if (backState == 0) {
                    finish();
                } else if (backState == 1) {
                    backState = 0;
                    scrollView3.setVisibility(View.VISIBLE);
                    localeRV.setVisibility(View.GONE);
                    txtTitel.setText("بحث مخصص");
                }
                break;
        }
    }

    private void setViewsTypeface() {
        Util.setViewsTypeface(this, txtTitel);
        Util.setViewsTypeface(this, backText);
        Util.setViewsTypeface(this, t1);
        Util.setViewsTypeface(this, t2);
        Util.setViewsTypeface(this, t3);
        Util.setViewsTypeface(this, t4);
        Util.setViewsTypeface(this, t5);
        Util.setViewsTypeface(this, t6);
        Util.setViewsTypeface(this, Word);
        Util.setViewsTypeface(this, TypeIds);
        Util.setViewsTypeface(this, TashName);
        Util.setViewsTypeface(this, TashNoFrom);
        Util.setViewsTypeface(this, TashNoTo);
        Util.setViewsTypeface(this, TashYearFrom);
        Util.setViewsTypeface(this, TashYearTo);
        Util.setViewsTypeface(this, DateFrom);
        Util.setViewsTypeface(this, DateTo);
        Util.setViewsTypeface(this, BTNSearchTash);
    }

}
