package com.nibalaws.ebrahim.law;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Var;


public class settings extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener,
        CompoundButton.OnCheckedChangeListener {
    SeekBar seekBar1;
    Switch mySwitch;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.settings);
        setContentView(R.layout.layout_settings);
        SetStyleControls();
        db = new DatabaseHelper(this);

        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar1.setOnSeekBarChangeListener(this);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        seekBar1.setProgress(pref.getInt("fontsize", 12));
        mySwitch = (Switch) findViewById(R.id.switch1);
        mySwitch.setOnCheckedChangeListener(this);
        mySwitch.setChecked(pref.getBoolean("sound", false));

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

    }

    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.svfade_out_center);
        super.onPause();
    }

    void SetStyleControls() {
        TextView titelTxt2 = (TextView) findViewById(R.id.txtTitel);
        TextView lbl1 = (TextView) findViewById(R.id.txt1);
        TextView lbl2 = (TextView) findViewById(R.id.textView2);
        Typeface type2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        titelTxt2.setTypeface(type2);
        Typeface lbl_1 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        Typeface lbl_2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
        lbl1.setTypeface(lbl_1);
        lbl2.setTypeface(lbl_2);


    }


    public void onProgressChanged(SeekBar seekBar, int progress,
                                  boolean fromUser) {
        TextView lbl1 = (TextView) findViewById(R.id.txt1);
        lbl1.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("fontsize", progress);
        editor.apply();

        Var.FontSizeSettings = pref.getInt("fontsize", 12);


    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {

            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("sound", true);
            editor.apply();
            Var.SoundStart = pref.getBoolean("sound", true);
        } else {
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("sound", false);
            editor.apply();
            Var.SoundStart = pref.getBoolean("sound", false);

        }

    }
}



