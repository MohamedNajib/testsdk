package com.nibalaws.ebrahim.law.Tash;

import android.app.Activity;
import android.content.Intent;
import android.gesture.Gesture;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.DataBaseManger.Master_Stract;
import com.nibalaws.ebrahim.law.DataBaseManger.MyAdapter;
import com.nibalaws.ebrahim.law.DataBaseManger.Var;
import com.nibalaws.ebrahim.law.HomeActivity;
import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.util.Util;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.refactor.lib.colordialog.PromptDialog;

public class textViewMowad extends Activity implements GestureOverlayView.OnGesturePerformedListener {
    ArrayList<Prediction> prediction;

    public static ArrayList myGlobalArray = null;
    MyAdapter adapter;
    public static int index = 0;
    public static int bt_show_index = 0;
    public static String Searchtxt = "";
    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    RelativeLayout relativeLayout;
    RelativeLayout relativeLayout2;
    TextView titelTxt;
    Button bt;
    GestureOverlayView gesture;
    GestureLibrary lib;
    ScrollView scrollView;
    ImageButton b4;
    public static ArrayList<Master_Stract> ArrayTXt = new ArrayList<>();
    TextView txt;
    TextView txt2;
    Integer flag = 0;
    DatabaseHelper db;
    public static String titlname;
    @BindView(R.id.textArtTitle)
    TextView textArtTitle;
    @BindView(R.id.txt_back)
    TextView txt_back;

    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        //setContentView(R.layout.txt);
        setContentView(R.layout.layout_articles);
        ButterKnife.bind(this);
        txt = (TextView) findViewById(R.id.txt);
        txt2 = (TextView) findViewById(R.id.txtname);
        scrollView = (ScrollView) findViewById(R.id.scrollView2);
        relativeLayout = (RelativeLayout) findViewById(R.id.rel);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.ret);
        mSVProgressHUD = new SVProgressHUD(this);
        db = new DatabaseHelper(this);
        b1 = (ImageButton) findViewById(R.id.bt_next);
        b2 = (ImageButton) findViewById(R.id.bt_prev);
        b3 = (ImageButton) findViewById(R.id.bt_fav);
        b4 = (ImageButton) findViewById(R.id.bt_share);
        bt = (Button) findViewById(R.id.bt_show);
        adapter = new MyAdapter(this, ArrayTXt);
        db = new DatabaseHelper(this);



        final MediaPlayer mp = MediaPlayer.create(this, R.raw.pageflip);


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


        homeclick.setImageResource(R.drawable.home_logo);

        backclick.setImageResource(R.drawable.ic_back_arrow_blue_copy);


        try {
            txt.setTextIsSelectable(true);
            titelTxt = (TextView) findViewById(R.id.txtTitel);
            Typeface type = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
            txt_back.setTypeface(type);

            //bt.setTypeface(type);

            textArtTitle.setText(ArrayTXt.get(index).getItem8().toString().replace("null", "").replaceAll("(?m)^[ \t]*\r?\n", ""));
            textArtTitle.setTypeface(type);

            titelTxt.setText("نص المادة");
            //titelTxt.setText(ArrayTXt.get(index).getItem8().toString().replace("null", "").replaceAll("(?m)^[ \t]*\r?\n", ""));
            titelTxt.setTypeface(type);

            txt.setText(ArrayTXt.get(index).getItem2().toString().replace("null", ""));
            txt2.setText(ArrayTXt.get(index).getItem1().toString().replace("null", ""));

            Typeface type2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
            txt.setTypeface(type2);
            Typeface type3 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
            txt2.setTypeface(type3);
            Typeface type4 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
            bt.setVisibility(View.VISIBLE);
            bt.setText("عرض الأحكام المرتبطه بالمادة");

            bt.setTypeface(type4);
            show_fav();
            txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, Var.FontSizeSettings);
            gestureDetector = new GestureDetector(new MyGestureDetector());
            gestureListener = new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    return gestureDetector.onTouchEvent(event);
                }
            };

            txt.setOnTouchListener(gestureListener);
            scrollView.setOnTouchListener(gestureListener);
            relativeLayout.setOnTouchListener(gestureListener);

            SelectionSearchWord(Searchtxt);

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }


        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (index >= ArrayTXt.size() - 1) {
                    return;
                }
                if (Var.SoundStart) {

                    mp.start();

                }

                index = index + 1;
                txt.setText(ArrayTXt.get(index).getItem2().toString().replace("null", ""));
                txt2.setText(ArrayTXt.get(index).getItem1().toString().replace("null", ""));
                //titelTxt.setText(ArrayTXt.get(index).getItem8().toString().replace("null", "").replaceAll("(?m)^[ \t]*\r?\n", ""));
                textArtTitle.setText(ArrayTXt.get(index).getItem8().toString().replace("null", "").replaceAll("(?m)^[ \t]*\r?\n", ""));
                show_fav();
                SelectionSearchWord(Searchtxt);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (index <= 0) {
                    return;
                }

                if (Var.SoundStart) {

                    mp.start();

                }

                index = index - 1;
                txt.setText(ArrayTXt.get(index).getItem2().toString().replace("null", ""));
                txt2.setText(ArrayTXt.get(index).getItem1().toString().replace("null", ""));
                //titelTxt.setText(ArrayTXt.get(index).getItem8().toString().replace("null", "").replaceAll("(?m)^[ \t]*\r?\n", ""));
                textArtTitle.setText(ArrayTXt.get(index).getItem8().toString().replace("null", "").replaceAll("(?m)^[ \t]*\r?\n", ""));
                show_fav();
                SelectionSearchWord(Searchtxt);


            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (flag == 0) {
                    b3.setBackgroundResource(R.drawable.ic_path_i);
                    // db.deleteData();
                    db.deleteData(txt2.getText().toString());

                    flag = 1;
                } else {
                    b3.setBackgroundResource(R.drawable.addtobookmark2);
                    flag = 0;
                    db.insertData(ArrayTXt.get(index).getItem1().toString(), ArrayTXt.get(index).getItem2().toString(), ArrayTXt.get(index).getItem8().toString());

                }


            }
        });


        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                new viewBToption().execute();


            }
        });


        b4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String link = "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName();
                String shareBody = txt.getText().toString() + '\n' + link;
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "مشاركه النص");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.app_name)));


            }
        });


        SelectionSearchWord(Searchtxt);
    }


    void show_fav() {

        if ((db.checkFavorite(ArrayTXt.get(index).getItem1().toString(), ArrayTXt.get(index).getItem8().toString()))) {
            b3.setBackgroundResource(R.drawable.addtobookmark2);
            flag = 0;

        } else {

            b3.setBackgroundResource(R.drawable.ic_path_i);

            flag = 1;

        }
    }

    void SelectionSearchWord(String text) {


        if (Searchtxt.toString().matches("")) {

            return;
        }
        String[] strArr = text.split("\\s+");

        if (text.toString().length() > 1) {
            for (String item : strArr) {
                String ett = item;
                String tvt = txt.getText().toString();
                int ofe = tvt.indexOf(ett, 0);
                Spannable WordtoSpan = new SpannableString(txt.getText());
                for (int ofs = 0; ofs < tvt.length() && ofe != -1; ofs = ofe + 1) {
                    ofe = tvt.indexOf(ett, ofs);
                    if (ofe == -1)
                        break;
                    else {
                        WordtoSpan.setSpan(new ForegroundColorSpan(Color.RED), ofe, ofe + ett.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                        txt.setText(WordtoSpan, TextView.BufferType.SPANNABLE);
                        WordtoSpan.getSpanStart(0);
                    }
                }
            }
        }
    }


    @Override
    public void onGesturePerformed(GestureOverlayView gestureOverlayView, Gesture gesture) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private static final int SWIPE_MIN_DISTANCE = 70;
    private static final int SWIPE_MAX_OFF_PATH = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 90;

    private int flagList = 0;
    int pos;

    class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {

                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    b1.performClick();
                } else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    b2.performClick();

                }

            } catch (Exception e) {
                // nothing
            }
            return false;
        }

    }

    void showWarrning(String msg) {
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

    private SVProgressHUD mSVProgressHUD;
    Intent intent;


    public void forceCrash(View view) {
    }

    private class viewBToption extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {


            Tashahkambymda.Master_Array = db.Ahkam_mortbtaMda(ArrayTXt.get(index).getItem5().toString());
            Tashahkambymda.titlname = "الاحكام المرتبطه " + txt2.getText().toString();
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            intent = new Intent(getApplicationContext(), Tashahkambymda.class);

            if (Tashahkambymda.Master_Array.isEmpty()) {
                showWarrning("لا تتوافر أحكام لهذه المواد");
                mSVProgressHUD.dismiss();
                return;
            }
            mSVProgressHUD.dismiss();
            intent = new Intent(getApplicationContext(), Tashahkambymda.class);
            startActivity(intent);
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