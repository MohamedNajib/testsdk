package com.nibalaws.ebrahim.law.search;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;

import java.util.Calendar;

import cn.refactor.lib.colordialog.PromptDialog;

import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.HomeActivity;
import com.nibalaws.ebrahim.law.R;

public class searchAhkam extends AppCompatActivity {
    DatabaseHelper db  ;
    private SVProgressHUD mSVProgressHUD;
    private DatePicker datePicker;
    private Calendar calendar;
    Button bt_from ;
    Button bt_to;
    private int year;
    private int month;
    private int day;
     EditText txt_page ;
    EditText txt_office ;
    EditText txt_word   ;
    EditText txt_ta3n  ;
    EditText txt_year ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_ahkam);
         calendar = Calendar.getInstance();
        SetStyleControls() ;
        db =  new DatabaseHelper(this);
        db = new DatabaseHelper(this);
        mSVProgressHUD = new SVProgressHUD(this);


        ImageView homeclick= (ImageView) findViewById(R.id.homeclick);
        homeclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        ImageView backclick= (ImageView) findViewById(R.id.backclik);
        backclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });




        ImageView clear= (ImageView) findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt_page = (EditText) findViewById(R.id.txt_page);
                txt_office = (EditText) findViewById(R.id.txt_year_tash);
                txt_word = (EditText) findViewById(R.id.txt_word_tash);
                txt_ta3n = (EditText) findViewById(R.id.txt_name_tash);
                txt_year = (EditText) findViewById(R.id.txt_num_tash);
                txt_page.setText("");
                txt_office.setText("");
                txt_word.setText("");
                txt_ta3n.setText("");
                txt_year.setText("");

            }
        });
        txt_ta3n = (EditText) findViewById(R.id.txt_name_tash);


        txt_ta3n.setFocusable(false);
        txt_ta3n.setFocusableInTouchMode(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


    void SetStyleControls() {
        TextView titelTxt= (TextView) findViewById(R.id.txtTitel);
        Typeface type = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        titelTxt.setTypeface(type);
        bt_from = (Button) findViewById(R.id.date_from);
        bt_to = (Button) findViewById(R.id.date_to);
        Button bt_search = (Button) findViewById(R.id.bt_search);

        TextView  lbl1 = (TextView) findViewById(R.id.lbl1);
        TextView  lbl2 = (TextView) findViewById(R.id.lbl2);
        TextView  lbl3 = (TextView) findViewById(R.id.lbl3);
        TextView  lbl4 = (TextView) findViewById(R.id.lbl4);
        TextView  lbl5 = (TextView) findViewById(R.id.lbl5);
        TextView  lbl6 = (TextView) findViewById(R.id.lbl6);

        Typeface lbl_1 = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        Typeface lbl_2 = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        Typeface lbl_3 = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        Typeface lbl_4 = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        Typeface lbl_5 = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        Typeface lbl_6 = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        Typeface bt_from_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        Typeface bt_to_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        Typeface bt_search_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");

        lbl1.setTypeface(lbl_1);
        lbl2.setTypeface(lbl_2);
        lbl3.setTypeface(lbl_3);
        lbl4.setTypeface(lbl_4);
        lbl5.setTypeface(lbl_5);
        lbl6.setTypeface(lbl_6);
        bt_search.setTypeface(bt_search_);

        bt_from.setTypeface(bt_from_);
        bt_to.setTypeface(bt_to_);

          txt_page = (EditText) findViewById(R.id.txt_page);
          txt_office = (EditText) findViewById(R.id.txt_year_tash);
          txt_word = (EditText) findViewById(R.id.txt_word_tash);
          txt_ta3n = (EditText) findViewById(R.id.txt_name_tash);
          txt_year = (EditText) findViewById(R.id.txt_num_tash);
        Typeface txt_page_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-MEDIUM.TTF");
        Typeface txt_office_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-MEDIUM.TTF");
        Typeface txt_word_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-MEDIUM.TTF");
        Typeface txt_ta3n_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-MEDIUM.TTF");
        Typeface txt_year_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-MEDIUM.TTF");

        txt_page.setTypeface(txt_page_);
        txt_office.setTypeface(txt_office_);
        txt_word.setTypeface(txt_word_);
        txt_ta3n.setTypeface(txt_ta3n_);
        txt_year.setTypeface(txt_year_);


    }


    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.svfade_out_center);
        super.onPause();
    }


}



