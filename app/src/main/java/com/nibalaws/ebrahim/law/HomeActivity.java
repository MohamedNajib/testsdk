package com.nibalaws.ebrahim.law;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.balsikandar.crashreporter.CrashReporter;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.Qiod.ListQioad;
import com.nibalaws.ebrahim.law.Qiod.ListQioadpdf;
import com.nibalaws.ebrahim.law.ahkam.ListAhkam;
import com.nibalaws.ebrahim.law.ahkam.Nkd_Master;
import com.nibalaws.ebrahim.law.hitiat.ListHithiat;
import com.nibalaws.ebrahim.law.rest.APIManager;
import com.nibalaws.ebrahim.law.rest.apiModel.AutoCompResponse;
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

public class HomeActivity extends AppCompatActivity implements TextWatcher {

    @BindView(R.id.autoCompleteTextView)
    AutoCompleteTextView autoCompleteTextView;
    //    @BindView(R.id.tv)
//    TextView tv;
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
    @BindView(R.id.t7)
    TextView t7;
    @BindView(R.id.t8)
    TextView t8;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv8)
    TextView tv8;
    @BindView(R.id.tv9)
    TextView tv9;
    @BindView(R.id.tv10)
    TextView tv10;
    @BindView(R.id.tv11)
    TextView tv11;
    @BindView(R.id.tv12)
    TextView tv12;
    @BindView(R.id.tv13)
    TextView tv13;
    @BindView(R.id.tv14)
    TextView tv14;
    @BindView(R.id.tv15)
    TextView tv15;
    @BindView(R.id.tv16)
    TextView tv16;
    @BindView(R.id.homeTitle)
    TextView homeTitle;
    @BindView(R.id.HorizontalScrollView1)
    HorizontalScrollView HorizontalScrollView1;
    @BindView(R.id.HorizontalScrollView2)
    HorizontalScrollView HorizontalScrollView2;
    @BindView(R.id.HorizontalScrollView3)
    HorizontalScrollView HorizontalScrollView3;
    @BindView(R.id.HorizontalScrollView4)
    HorizontalScrollView HorizontalScrollView4;

    private List<String> textautoCompList;
    private ArrayAdapter<String> adapter;

    private DatabaseHelper db;
    private Intent intent;
    private SVProgressHUD mSVProgressHUD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        textautoCompList = new ArrayList<>();
        setViewsTypeface();
        autoCompleteTextView.addTextChangedListener(this);
        db = new DatabaseHelper(this);
        mSVProgressHUD = new SVProgressHUD(this);
    }

    private void setViewsTypeface() {
        Typeface type = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        autoCompleteTextView.setTypeface(type);
        homeTitle.setTypeface(type);
        t1.setTypeface(type);
        t2.setTypeface(type);
        t3.setTypeface(type);
        t4.setTypeface(type);
        t5.setTypeface(type);
        t6.setTypeface(type);
        t7.setTypeface(type);
        t8.setTypeface(type);

        tv1.setTypeface(type);
        tv2.setTypeface(type);
        tv3.setTypeface(type);
        tv4.setTypeface(type);
        tv5.setTypeface(type);
        tv6.setTypeface(type);
        tv7.setTypeface(type);
        tv8.setTypeface(type);
        tv9.setTypeface(type);
        tv10.setTypeface(type);
        tv11.setTypeface(type);
        tv12.setTypeface(type);
        tv13.setTypeface(type);
        tv14.setTypeface(type);
        tv15.setTypeface(type);
        tv16.setTypeface(type);
    }


    private void autoCompApi(String text) {
        textautoCompList.clear();
        APIManager.getApis().autoComp(text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<AutoCompResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<AutoCompResponse> autoCompResponses) {
                        for (int i = 0; i < autoCompResponses.size(); i++) {
                            textautoCompList.add(autoCompResponses.get(i).getText());
                        }
                        adapter = new ArrayAdapter<String>(HomeActivity.this,
                                android.R.layout.simple_list_item_1, textautoCompList);
                        autoCompleteTextView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(HomeActivity.this, "Oops Error: \n"
                                + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        autoCompApi(charSequence.toString());
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @OnClick(R.id.Fl_SearchIcon)
    public void onViewClicked() {
        if (!autoCompleteTextView.getText().toString().trim().isEmpty()) {
            Intent intent = new Intent(HomeActivity.this, SearchAllActivity.class);
            intent.putExtra("search_text", autoCompleteTextView.getText().toString());
            startActivity(intent);
        } else Toast.makeText(this, "No Data Search", Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.L_ahkam, R.id.L_Nuabh, R.id.L_Acs, R.id.tsh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.L_ahkam:
                openActivity(0, "أحكام النقض");
                break;

            case R.id.L_Nuabh:
                openActivity(3, "موسوعة النيابة");
                break;

            case R.id.L_Acs:
                openActivity(2, "موسوعة الحيثيات");
                break;

            case R.id.tsh:
                openActivity(1, "التشريعات");
                break;
        }
    }

    @OnClick({R.id.settingsIcon, R.id.favouret, R.id.contactUs, R.id.Notifications, R.id.update})
    public void onViewTobIconClicked(View view) {
        switch (view.getId()) {
            case R.id.settingsIcon:
                openActivity(settings.class);
                break;
            case R.id.favouret:
                openActivity(ListFav.class);
                ListFav.Master_Array = db.ShowFav();
                break;
            case R.id.contactUs:
                openActivity(contactus.class);
                break;
            case R.id.Notifications:
                openActivity(GetNotificationsActivity.class);
                break;
            case R.id.update:
                openActivity(GetDataBaseLastUpdate.class);
                break;
        }
    }

    private void openActivity(Class<?> cls) {
        Intent intent = new Intent(HomeActivity.this, cls);
        startActivity(intent);
    }

    private void openActivity(int indx, String title) {
        Intent intent = new Intent(HomeActivity.this, homelist.class);
        homelist.lstindx = indx;
        homelist.titlname = title;
        startActivity(intent);
    }

    @OnClick({R.id.item1, R.id.item2, R.id.item3, R.id.item4, R.id.item5, R.id.item6, R.id.item7, R.id.item8,
            R.id.item9, R.id.item10, R.id.item11, R.id.item12, R.id.item13, R.id.item14, R.id.item15, R.id.item16})
    public void onViewItemClicked(View view) {
        switch (view.getId()) {
            case R.id.item1:
                tashId = "5741";
                new viewtashinfo().execute();
                break;
            case R.id.item2:
                tashId = "7423";
                new viewtashinfo().execute();
                break;
            case R.id.item3:
                tashId = "7419";
                new viewtashinfo().execute();
                break;
            case R.id.item4:
                tashId = "7413";
                new viewtashinfo().execute();
                break;
            case R.id.item5:
                listpo = 0;
                new Screennkd().execute();
                break;
            case R.id.item6:
                listpo = 1;
                new Screennkd().execute();
                break;
            case R.id.item7:
                listpo = 2;
                new Screennkd().execute();
                break;
            case R.id.item8:
                listpo = 3;
                new Screennkd().execute();
                break;
            case R.id.item9:
                listpo = 0;
                new Screeqioad().execute();
                break;
            case R.id.item10:
                listpo = 1;
                new Screeqioad().execute();
                break;
            case R.id.item11:
                listpo = 2;
                new Screeqioad().execute();
                break;
            case R.id.item12:
                listpo = 3;
                new Screeqioad().execute();
                break;
            case R.id.item13:
                listpo = 0;
                new Screehitiat().execute();
                break;
            case R.id.item14:
                listpo = 1;
                new Screehitiat().execute();
                break;
            case R.id.item15:
                listpo = 2;
                new Screehitiat().execute();
                break;
            case R.id.item16:
                listpo = 3;
                new Screehitiat().execute();
                break;
        }
    }

    private String tashId = null;

    private class viewtashinfo extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {
//            try {
                intent = new Intent(getApplicationContext(), tashri3info.class);
                tashri3info.listIndex = db.gettashinfo(tashId);

//            } catch (Exception e) {
//                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);
                intent.putExtra("ListtYPE", "تشريعات");
                startActivity(intent);

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }


    }

    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.svfade_out_center);
        super.onPause();
    }

    public static int listpo;

    private class Screennkd extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {
            try {


//                if (titlname != "أحكام الدستورية العليا" && titlname != "محكمة القضاء الإداري"
//                        && titlname != "المحكمة الإدارية العليا" && titlname != "فتاوي مجلس الدولة") {
//                    adapter.addFrag(new fregment_two());
//                    tapsButton.setVisibility(View.VISIBLE);
//                }
                if (listpo == 0) {
                    Nkd_Master.ahkam_topic = db.GetahkamTopic("1");
                    Nkd_Master.tb_ahkam_years = db.getahkamyear("1");
                    Nkd_Master.titlname = "النقض المدني";
                } else if (listpo == 1) {
                    Nkd_Master.ahkam_topic = db.GetahkamTopic("2");
                    Nkd_Master.tb_ahkam_years = db.getahkamyear("2");
                    Nkd_Master.titlname = "النقض الجنائي";
                } else if (listpo == 2) {
                    Nkd_Master.ahkam_topic = db.GetahkamTopic("4");
                    Nkd_Master.tb_ahkam_years = db.getahkamyear("4");
                } else if (listpo == 3) {
                    Nkd_Master.tb_ahkam_years = db.getahkamyear("7");
                    Nkd_Master.titlname = "محكمة القضاء الإداري";
                } else if (listpo == 4) {
                    Nkd_Master.tb_ahkam_years = db.getahkamyear("8");
                    Nkd_Master.titlname = "فتاوي مجلس الدولة";
                } else if (listpo == 5) {
                    Nkd_Master.tb_ahkam_years = db.getahkamyear("6");
                    Nkd_Master.titlname = "المحكمة الإدارية العليا";
                }
            } catch (Exception e) {
                CrashReporter.logException(e);
            }
            return null;

        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            super.onPostExecute(result);
            mSVProgressHUD.dismiss();
            intent = new Intent(HomeActivity.this, Nkd_Master.class);


            if (listpo == 2) {
                Nkd_Master.titlname = "أحكام الدستورية العليا";


            }

            //      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }
    }

    private class Screeqioad extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {

            if (listpo == 0) {
                ListQioad.titlname = "قيود وأوصاف الجنايات";

                ListQioad.Master_Array = db.getQiods("1");

            } else if (listpo == 1) {
                ListQioad.titlname = "قيود وأوصاف الجنح";
                ListQioad.Master_Array = db.getQiods("2");


            } else if (listpo == 2) {
                ListQioad.titlname = "قيود وأوصاف المخالفات";
                ListQioad.Master_Array = db.getQiods("3");


            } else if (listpo == 3) {
                ListQioad.titlname = "الأسئلة الإسترشادية";
                ListQioad.Master_Array = db.getQiods("4");


            } else if (listpo == 5) {
                ListQioad.titlname = "التعليمات القضائية";
                ListQioad.Master_Array = db.getQiods("10");

            } else if (listpo == 4) {
                ListQioad.titlname = "التعليمات الكتابية";
                ListQioad.Master_Array = db.getQiods("9");


            } else if (listpo == 6) {
                ListQioadpdf.titlname = "الكتب الدورية";
                ListQioadpdf.Master_Array = db.pdf_dawry();


            }

            return null;
        }

        Intent intent;

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);


                intent = new Intent(HomeActivity.this, ListQioad.class);

                if (listpo == 6) {
                    String url = "https://www.niaba-laws.com/SearchFehres/Index";


                    intent = new Intent(getApplicationContext(), webBr.class);
                    webBr.web_url = url;
                    startActivity(intent);

                } else if (listpo == 7) {

                }


                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                HomeActivity.this.startActivity(intent);


            } catch (Exception e) {
                CrashReporter.logException(e);
            }
        }

        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }
    }

    private class Screehitiat extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {

            if (listpo == 0) {
                ListHithiat.Master_Array = db.Gethitiat("2");
                ListHithiat.titlname = "الحيثيات المدني";
            } else if (listpo == 1) {
                ListHithiat.Master_Array = db.Gethitiat("1");
                ListHithiat.titlname = "الحيثيات الجنائي";

            } else if (listpo == 2) {
                ListQioad.Master_Array = db.getQiods("11");
                ListQioad.titlname = "المواعيد القانونية";
            } else if (listpo == 3) {

                ListQioad.Master_Array = db.getQiods("12");
                ListQioad.titlname = "البطلان الجنائي";

            } else if (listpo == 4) {
                // ListAhkam.Master_Array = db.getAhkam_ektsadia();
                ListAhkam.titlname = "أحكام المحكمة الإقتصادية";


            }
            return null;

        }

        @Override

        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);

                if (listpo == 0) {
                    intent = new Intent(HomeActivity.this, ListHithiat.class);

                } else if (listpo == 1) {
                    intent = new Intent(HomeActivity.this, ListHithiat.class);

                } else if (listpo == 2) {
                    intent = new Intent(HomeActivity.this, ListQioad.class);

                } else if (listpo == 3) {

                    intent = new Intent(HomeActivity.this, ListQioad.class);

                } else if (listpo == 4) {
                    intent = new Intent(HomeActivity.this, ListAhkam.class);
                } else if (listpo == 5) {

                    String url = "https://www.niaba-laws.com/SearchHitiat/Index";


                    intent = new Intent(getApplicationContext(), webBr.class);
                    webBr.web_url = url;
                    startActivity(intent);

                }
                startActivity(intent);

            } catch (Exception e) {
                CrashReporter.logException(e);
            }
        }


        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }


    }

}
