package com.nibalaws.ebrahim.law;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.adapter.DialogSearchCustomAdapter;
import com.nibalaws.ebrahim.law.adapter.SearchLocaleAhkamAdapter;
import com.nibalaws.ebrahim.law.rest.APIManager;
import com.nibalaws.ebrahim.law.rest.DialogSearchDataModel;
import com.nibalaws.ebrahim.law.rest.InputValidation;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchAhkamResponse;
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

public class SearchAhkamActivity extends AppCompatActivity {

    @BindView(R.id.localeAhkamRV)
    RecyclerView localeAhkamRV;
    @BindView(R.id.ScrollViewAhkam)
    ScrollView ScrollViewAhkam;
    @BindView(R.id.txtTitel)
    TextView txtTitel;
    @BindView(R.id.backText)
    TextView backText;
    @BindView(R.id.toolbar2)
    Toolbar toolbar2;
    @BindView(R.id.tt1)
    TextView tt1;
    @BindView(R.id.tt2)
    TextView tt2;
    @BindView(R.id.tt3)
    TextView tt3;
    @BindView(R.id.tt4)
    TextView tt4;
    @BindView(R.id.tt5)
    TextView tt5;
    @BindView(R.id.tt6)
    TextView tt6;
    @BindView(R.id.tt7)
    TextView tt7;
    @BindView(R.id.tt8)
    TextView tt8;
    @BindView(R.id.tt9)
    TextView tt9;
    @BindView(R.id.tt10)
    TextView tt10;
    @BindView(R.id.tt11)
    TextView tt11;
    @BindView(R.id.BTN_SearchAhkam)
    Button BTNSearchAhkam;
    @BindView(R.id.ET_Word)
    EditText Word;
    @BindView(R.id.Hkm_num_From)
    EditText HkmNumFrom;
    @BindView(R.id.Hkm_num_To)
    EditText HkmNumTo;
    @BindView(R.id.Hkm_Year_From)
    EditText HkmYearFrom;
    @BindView(R.id.Hkm_Year_To)
    EditText HkmYearTo;
    @BindView(R.id.date_from)
    EditText DateFrom;
    @BindView(R.id.date_To)
    EditText DateTo;
    @BindView(R.id.Office_from)
    EditText OfficeFrom;
    @BindView(R.id.Office_To)
    EditText OfficeTo;
    @BindView(R.id.Rule_from)
    EditText RuleFrom;
    @BindView(R.id.Rule_To)
    EditText RuleTo;
    @BindView(R.id.Part_from)
    EditText PartFrom;
    @BindView(R.id.Part_To)
    EditText PartTo;
    @BindView(R.id.Page_from)
    EditText PageFrom;
    @BindView(R.id.Page_To)
    EditText PageTo;
    @BindView(R.id.ChooseCourt)
    TextView ChooseCourt;
    @BindView(R.id.court_sys)
    EditText CourtSys;
    @BindView(R.id.court_place)
    EditText CourtPlace;

    private ArrayList<DialogSearchDataModel> dataModels;
    private DialogSearchCustomAdapter adapter;
    private List<String> listOfIds;
    private List<String> listOfType;
    private SVProgressHUD mProgress;

    private String hkmNumFrom, hkmNumTo, officeFrom, officeTo, pageFrom, pageTo,
            partFrom, partTo, dateFrom, dateTo, hkmYearFrom, hkmYearTo;

    private String court_sys, court_place, word;

    private LinearLayoutManager mLayoutManager;
    private DatabaseHelper databaseHelper;
    public static ArrayList<Master_Stract> master_stracts;
    private int backState = 0;
    private SearchLocaleAhkamAdapter searchTashAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ahkam);
        ButterKnife.bind(this);
        ScrollViewAhkam.setVisibility(View.VISIBLE);
        localeAhkamRV.setVisibility(View.GONE);
        txtTitel.setText("بحث مخصص");

        setViewsTypeface();
        mProgress = new SVProgressHUD(this);

        databaseHelper = new DatabaseHelper(this);
        master_stracts = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this);
        localeAhkamRV.setLayoutManager(mLayoutManager);
        localeAhkamRV.setHasFixedSize(true);
        localeAhkamRV.setItemAnimator(new DefaultItemAnimator());


        dataModels = new ArrayList();
        listOfIds = new ArrayList<>();
        listOfType = new ArrayList<>();
        dataModels = databaseHelper.gettypeDialogSearchAhkam();

    }

    @Override
    public void onBackPressed() {
        if (backState == 1) {
            backState = 0;
            ScrollViewAhkam.setVisibility(View.VISIBLE);
            localeAhkamRV.setVisibility(View.GONE);
            txtTitel.setText("بحث مخصص");
        } else {
            super.onBackPressed();
        }
    }


    private void SearchAhkam(String Hkm_num_From, String Hkm_num_To, String Office_from, String office_To,
                             String Page_FROM, String Page_to, String Part_FROM, String Part_to, String court_sys,
                             String court_place, String Type_ids, String date_from, String date_to, String Word,
                             String Search_type, int Page, String Hkm_Year_From, String Hkm_Year_To) {

        backState = 1;
        ScrollViewAhkam.setVisibility(View.GONE);
        localeAhkamRV.setVisibility(View.VISIBLE);

        APIManager.getApis().SearchAhkam(Hkm_num_From, Hkm_num_To, Office_from, office_To, Page_FROM, Page_to, Part_FROM,
                Part_to, court_sys, court_place, Type_ids, date_from, date_to, Word,
                Search_type, Page, Hkm_Year_From, Hkm_Year_To)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<SearchAhkamResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<SearchAhkamResponse> searchAhkamResponses) {
                        mProgress.dismiss();
                        for (int i = 0; i < searchAhkamResponses.size(); i++) {

                            master_stracts.add(new Master_Stract(searchAhkamResponses.get(i).getId(),
                                    searchAhkamResponses.get(i).getInfo(), searchAhkamResponses.get(i).getUrl(),
                                    "", "", "", "", ""));
                        }
                        //searchTashAdapter = new SearchLocaleTashAdapter(master_stracts);
                        localeAhkamRV.setAdapter(searchTashAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgress.dismiss();
                        Toast.makeText(SearchAhkamActivity.this, "Oops Error: \n"
                                + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @OnClick(R.id.BTN_SearchAhkam)
    public void onViewClicked() {
        //getUserSearchInput();
        isValidInput();
    }

    private void localeSearch() {
        backState = 1;
        ScrollViewAhkam.setVisibility(View.GONE);
        localeAhkamRV.setVisibility(View.VISIBLE);
        txtTitel.setText("نتيجة البحث");

        master_stracts = databaseHelper.SearchAhkam(word, hkmNumFrom, hkmNumTo, hkmYearFrom, hkmYearTo,
                officeFrom, officeTo, pageFrom, pageTo, partFrom, partTo, court_sys,
                court_place, dateFrom, dateTo, getTypeIds(listOfIds));

        searchTashAdapter = new SearchLocaleAhkamAdapter(SearchAhkamActivity.this,
                master_stracts, new SearchLocaleAhkamAdapter.OnItemClick() {
            @Override
            public void setOnItemClicked(int position) {
                Intent intent = new Intent(SearchAhkamActivity.this, DetailsSearchAll.class);
                intent.putExtra("position", position);
                startActivity(intent);
                Toast.makeText(SearchAhkamActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        localeAhkamRV.setAdapter(searchTashAdapter);
        mProgress.dismiss();
    }

    private void apiSearch() {
//        mOnEndless = new OnEndless(mLayoutManager, 1) {
//            @Override
//            public void onLoadMore(int current_page) {
//                if (current_page < maxPages) {
//                    if (maxPages != 0){
//
//                        Toast.makeText(SearchAhkamActivity.this, "current page: " + current_page
//                                , Toast.LENGTH_SHORT).show();
//
//                        SearchAhkam(hkmNumFrom, hkmNumTo, officeFrom, officeTo, pageFrom, pageTo, partFrom, partTo, court_sys,
//                                court_place, getTypeIds(listOfIds),
//                                dateFrom, dateTo, word, "", 1, hkmYearFrom, hkmYearTo);
//                    }
//                }
//            }
//        };
//
//        /* Adds the scroll listener to RecyclerView */
//        localeAhkamRV.addOnScrollListener(mOnEndless);
//        localeAhkamRV.setAdapter(searchTashAdapter);

//        SearchAhkam(hkmNumFrom, hkmNumTo, officeFrom, officeTo, pageFrom, pageTo, partFrom, partTo, court_sys,
//                court_place, getTypeIds(listOfIds),
//                dateFrom, dateTo, word, "", 1, hkmYearFrom, hkmYearTo);
    }

    private void isValidInput() {
        if (!InputValidation.emptyInput(CourtSys) && !InputValidation.emptyInput(CourtPlace) && !InputValidation.emptyInput(Word)
                && !InputValidation.emptyInput(HkmNumFrom) && !InputValidation.emptyInput(HkmNumTo)
                && !InputValidation.emptyInput(OfficeFrom) && !InputValidation.emptyInput(OfficeTo)
                && !InputValidation.emptyInput(PageFrom) && !InputValidation.emptyInput(PageTo)
                && !InputValidation.emptyInput(PartFrom) && !InputValidation.emptyInput(PartTo)
                && !InputValidation.emptyInput(DateFrom) && !InputValidation.emptyInput(DateTo)
                && !InputValidation.emptyInput(HkmYearFrom) && !InputValidation.emptyInput(HkmYearTo)
                && getTypeIds(listOfType).trim().isEmpty()) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            court_sys = CourtSys.getText().toString();
            court_place = CourtPlace.getText().toString();
            word = Word.getText().toString();

            hkmNumFrom = HkmNumFrom.getText().toString();
            hkmNumTo = HkmNumTo.getText().toString();
            officeFrom = OfficeFrom.getText().toString();
            officeTo = OfficeTo.getText().toString();
            pageFrom = PageFrom.getText().toString();
            pageTo = PageTo.getText().toString();
            partFrom = PartFrom.getText().toString();
            partTo = PartTo.getText().toString();
            dateFrom = DateFrom.getText().toString();
            dateTo = DateTo.getText().toString();
            hkmYearFrom = HkmYearFrom.getText().toString();
            hkmYearTo = HkmYearTo.getText().toString();

            String hkmNum = InputValidation.isValidFromTo(hkmNumFrom, hkmNumTo);
            String office = InputValidation.isValidFromTo(officeFrom, officeTo);
            String page = InputValidation.isValidFromTo(pageFrom, pageTo);
            String part = InputValidation.isValidFromTo(partFrom, partTo);
            String date = InputValidation.isValidFromTo(dateFrom, dateTo);
            String hkmYear = InputValidation.isValidFromTo(hkmYearFrom, hkmYearTo);

            if (hkmNum == null) {

            } else {
                hkmNumFrom = hkmNum;
                hkmNumTo = hkmNum;
            }

            if (office == null) {

            } else {
                officeFrom = office;
                officeTo = office;
            }

            if (page == null) {

            } else {
                pageFrom = page;
                pageTo = page;
            }

            if (part == null) {

            } else {
                partFrom = part;
                partTo = part;
            }

            if (date == null) {

            } else {
                dateFrom = date;
                dateTo = date;
            }

            if (hkmYear == null) {

            } else {
                hkmYearFrom = hkmYear;
                hkmYearTo = hkmYear;
            }

            mProgress.show();

            //apiSearch();
            localeSearch();
        }
    }

    @OnClick(R.id.ChooseCourt)
    public void onChooseCourtViewClicked() {
        showExpensesDialog();
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

        txtTitel.setText("أختيار المحكمة");
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

                Toast.makeText(SearchAhkamActivity.this, getTypeIds(listOfIds), Toast.LENGTH_SHORT).show();

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
                ChooseCourt.setText(getTypeIds(listOfType));
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
                Util.openActivity(SearchAhkamActivity.this, HomeActivity.class);
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
                    ScrollViewAhkam.setVisibility(View.VISIBLE);
                    localeAhkamRV.setVisibility(View.GONE);
                    txtTitel.setText("بحث مخصص");
                }
                break;
        }
    }

    private void setViewsTypeface() {
        Util.setViewsTypeface(this, txtTitel);
        Util.setViewsTypeface(this, backText);
        Util.setViewsTypeface(this, tt1);
        Util.setViewsTypeface(this, Word);
        Util.setViewsTypeface(this, tt2);
        Util.setViewsTypeface(this, HkmNumFrom);
        Util.setViewsTypeface(this, HkmNumTo);
        Util.setViewsTypeface(this, tt3);
        Util.setViewsTypeface(this, HkmYearFrom);
        Util.setViewsTypeface(this, HkmYearTo);
        Util.setViewsTypeface(this, tt4);
        Util.setViewsTypeface(this, DateFrom);
        Util.setViewsTypeface(this, DateTo);
        Util.setViewsTypeface(this, tt5);
        Util.setViewsTypeface(this, OfficeFrom);
        Util.setViewsTypeface(this, OfficeTo);
        Util.setViewsTypeface(this, tt6);
        Util.setViewsTypeface(this, RuleFrom);
        Util.setViewsTypeface(this, RuleTo);
        Util.setViewsTypeface(this, tt7);
        Util.setViewsTypeface(this, PartFrom);
        Util.setViewsTypeface(this, PartTo);
        Util.setViewsTypeface(this, tt8);
        Util.setViewsTypeface(this, PageFrom);

        Util.setViewsTypeface(this, PageTo);
        Util.setViewsTypeface(this, tt9);
        Util.setViewsTypeface(this, ChooseCourt);
        Util.setViewsTypeface(this, tt10);
        Util.setViewsTypeface(this, CourtSys);
        Util.setViewsTypeface(this, tt11);
        Util.setViewsTypeface(this, CourtPlace);
        Util.setViewsTypeface(this, BTNSearchAhkam);
    }
}
