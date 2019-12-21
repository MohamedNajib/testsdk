package com.nibalaws.ebrahim.law.search;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;

import cn.refactor.lib.colordialog.PromptDialog;

import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.HomeActivity;
import com.nibalaws.ebrahim.law.R;

public class searchTash extends AppCompatActivity {
    DatabaseHelper db  ;
    EditText txt_name_tash  ;
    EditText txt_num_tash  ;
    EditText txt_year_tash  ;
    EditText txt_word_tash  ;
    private SVProgressHUD mSVProgressHUD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_tash);
         SetStyleControls() ;
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


                EditText txt_name_tash = (EditText) findViewById(R.id.txt_name_tash);
                EditText txt_num_tash = (EditText) findViewById(R.id.txt_num_tash);
                EditText txt_year_tash = (EditText) findViewById(R.id.txt_year_tash);
                EditText txt_word_tash = (EditText) findViewById(R.id.txt_word_tash);
                txt_name_tash.setText("");
                txt_num_tash.setText("");
                txt_year_tash.setText("");
                txt_word_tash.setText("");
            }
        });
    }


    void SetStyleControls() {
        TextView titelTxt= (TextView) findViewById(R.id.txtTitel);
        Typeface type = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        titelTxt.setTypeface(type);
        Button bt_search = (Button) findViewById(R.id.bt_search_1);
        Button bt_search_2 = (Button) findViewById(R.id.bt_search_2);

        TextView  lbl1 = (TextView) findViewById(R.id.lbl1);
        TextView  lbl2 = (TextView) findViewById(R.id.lbl2);
        TextView  lbl3 = (TextView) findViewById(R.id.lbl3);
        TextView  lbl4 = (TextView) findViewById(R.id.lbl4);

        Typeface lbl_1 = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        Typeface lbl_2 = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        Typeface lbl_3 = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        Typeface lbl_4 = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");

        Typeface bt_search_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");
        Typeface bt_search_x = Typeface.createFromAsset(getAssets(),"NG4ASANS-REGULAR.TTF");

        lbl1.setTypeface(lbl_1);
        lbl2.setTypeface(lbl_2);
        lbl3.setTypeface(lbl_3);
        lbl4.setTypeface(lbl_4);

        bt_search.setTypeface(bt_search_);
        bt_search_2.setTypeface(bt_search_x);



        EditText txt_name_tash = (EditText) findViewById(R.id.txt_name_tash);
        EditText txt_num_tash = (EditText) findViewById(R.id.txt_num_tash);
        EditText txt_year_tash = (EditText) findViewById(R.id.txt_year_tash);
        EditText txt_word_tash = (EditText) findViewById(R.id.txt_word_tash);

        Typeface txt_name_tash_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-MEDIUM.TTF");
        Typeface txt_num_tash_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-MEDIUM.TTF");
        Typeface txt_year_tash_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-MEDIUM.TTF");
        Typeface txt_word_tash_ = Typeface.createFromAsset(getAssets(),"NG4ASANS-MEDIUM.TTF");

        txt_name_tash.setTypeface(txt_name_tash_);
        txt_num_tash.setTypeface(txt_num_tash_);
        txt_year_tash.setTypeface(txt_year_tash_);
        txt_word_tash.setTypeface(txt_word_tash_);
     }



    public void SearchAction(View view) {
           txt_name_tash = (EditText) findViewById(R.id.txt_name_tash);
          txt_num_tash = (EditText) findViewById(R.id.txt_num_tash);
          txt_year_tash = (EditText) findViewById(R.id.txt_year_tash);


        if (txt_name_tash.getText().toString().matches("") && txt_num_tash.getText().toString().matches("") && txt_year_tash.getText().toString().matches("") ) {
            showWarrning("رجاءادخال قيمة للبحث");
            return;
        }


        new Serch().execute() ;


    }



    public void SearchAction_Mda(View view) {

        txt_word_tash = (EditText) findViewById(R.id.txt_word_tash);


        if (txt_word_tash.getText().toString().matches("")   ) {
            showWarrning("رجاءادخال قيمة للبحث");
            return;
        }


new Serch2().execute();


    }




    void showWarrning( String msg){
        new PromptDialog(this)
                .setDialogType(PromptDialog.DIALOG_TYPE_WARNING)
                .setAnimationEnable(true)
                .setTitleText(msg)
                .setContentText("")
                .setPositiveListener(("شكرا"), new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
    }

    private class Serch extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {

            //ListtashSearch.Master_Array = db.SearchTash(txt_name_tash.getText().toString(),txt_num_tash.getText().toString(),txt_year_tash.getText().toString()) ;
            ListtashSearch.titlname = "نتيجة البحث في التشريعات" ;
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);
                Intent intent = new Intent(getApplicationContext(), ListtashSearch.class);
                ListtashSearch.titlname = "تشريعات";
                if (ListtashSearch.Master_Array.isEmpty()) {
                    showWarrning("لا تتوافر نتيجة للبجث المطلوب");
                    return;
                }
                startActivity(intent);


            }catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();}

        }
        @Override
        protected void onPreExecute() {
            mSVProgressHUD.show();
        }



    }


    private class Serch2 extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {
         //   Tashmowadsearch.Master_Array = db.searhchTashMowad( txt_word_tash.getText().toString()) ;
            Tashmowadsearch.titlname = "نتيجة البحث في التشريعات" ;
           // Tashmowadsearch.Searchtxt = txt_word_tash.getText().toString();

            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);
                Intent intent = new Intent(getApplicationContext(), Tashmowadsearch.class);
                 if (Tashmowadsearch.Master_Array.isEmpty()) {
                    showWarrning("لا تتوافر نتيجة للبجث المطلوب");
                    return;
                }
                startActivity(intent);


            }catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();}

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



}



