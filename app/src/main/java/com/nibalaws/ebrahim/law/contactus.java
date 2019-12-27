package com.nibalaws.ebrahim.law;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nibalaws.ebrahim.law.util.Util;

import butterknife.BindView;
import butterknife.ButterKnife;


public class contactus extends AppCompatActivity {


    @BindView(R.id.CallUsTxtBack)
    TextView CallUsTxtBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.contactus);
        Util.setLocaleAr(this);
        setContentView(R.layout.layout_call_us);
        ButterKnife.bind(this);
        Util.setViewsTypeface(this, CallUsTxtBack);
        SetStyleControls();
        TextView txt = (TextView) findViewById(R.id.textView6);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewIn) {
                call_phone();
            }
        });


        ImageView homeclick = (ImageView) findViewById(R.id.homeclick);
        homeclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });


        ImageView backclick = (ImageView) findViewById(R.id.backclik);
        backclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


        TextView bt = (TextView) findViewById(R.id.txt_link);

        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.vision4dev.com/"));
                startActivity(myIntent);
            }
        });

    }

    void SetStyleControls() {
        TextView titelTxt = (TextView) findViewById(R.id.txt_info);
        Typeface type = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        titelTxt.setTypeface(type);

        TextView titelTxt2 = (TextView) findViewById(R.id.txtTitel);
        Typeface type2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        titelTxt2.setTypeface(type2);


        TextView lbl1 = (TextView) findViewById(R.id.textView3);
        TextView lbl2 = (TextView) findViewById(R.id.textView4);
        TextView lbl3 = (TextView) findViewById(R.id.textView5);
        TextView lbl4 = (TextView) findViewById(R.id.textView6);
        TextView lbl5 = (TextView) findViewById(R.id.textView7);
        TextView lbl6 = (TextView) findViewById(R.id.textView5);
        TextView lbl7 = (TextView) findViewById(R.id.textView5);
        TextView lbl8 = (TextView) findViewById(R.id.textView8);
        TextView lbl9 = (TextView) findViewById(R.id.txt_info2);


        Typeface lbl_1 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        Typeface lbl_2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        Typeface lbl_3 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        Typeface lbl_4 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        Typeface lbl_5 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        Typeface lbl_6 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        Typeface lbl_7 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        Typeface lbl_8 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        Typeface lbl_9 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");

        lbl1.setTypeface(lbl_1);
        lbl2.setTypeface(lbl_2);
        lbl3.setTypeface(lbl_3);
        lbl4.setTypeface(lbl_4);
        lbl5.setTypeface(lbl_5);
        lbl6.setTypeface(lbl_6);
        lbl7.setTypeface(lbl_7);
        lbl8.setTypeface(lbl_8);
        lbl9.setTypeface(lbl_9);

        Typeface bt_search_x = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        Button bt_search = (Button) findViewById(R.id.bt_send);
        bt_search.setTypeface(bt_search_x);

        EditText t1 = (EditText) findViewById(R.id.t1);
        EditText t2 = (EditText) findViewById(R.id.t2);
        EditText t3 = (EditText) findViewById(R.id.t3);

        Typeface t_1 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        Typeface t_2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");
        Typeface t_3 = Typeface.createFromAsset(getAssets(), "NG4ASANS-MEDIUM.TTF");

        t1.setTypeface(t_1);
        t2.setTypeface(t_2);
        t3.setTypeface(t_3);

    }


    public void Sendaction(View view) {
        EditText t1 = (EditText) findViewById(R.id.t1);
        EditText t2 = (EditText) findViewById(R.id.t2);
        EditText t3 = (EditText) findViewById(R.id.t3);
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"softniba@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, t2.getText().toString() + "   " + t1.getText().toString());
        i.putExtra(Intent.EXTRA_TEXT, t3.getText().toString());
        try {
            startActivity(Intent.createChooser(i, "اختر التطبيق الخاص بك"));
        } catch (ActivityNotFoundException ex) {
            Toast.makeText(contactus.this, "لا توجد تطبيقات مثبته خاصه بالبريد الإلكتروني", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onPause() {
        overridePendingTransition(R.anim.hold, R.anim.svfade_out_center);
        super.onPause();
    }


    void call_phone() {

        String phone = "010906060059";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }


}



