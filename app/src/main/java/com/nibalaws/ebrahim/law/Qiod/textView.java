package com.nibalaws.ebrahim.law.Qiod;

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
import android.support.v7.widget.RecyclerView;
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
import com.nibalaws.ebrahim.law.Tash.Tashahkambymda;
import com.nibalaws.ebrahim.law.Tash.textViewMowad;
import com.nibalaws.ebrahim.law.rest.APIManager;
import com.nibalaws.ebrahim.law.webview;

import java.util.ArrayList;

import cn.refactor.lib.colordialog.PromptDialog;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class textView extends Activity implements GestureOverlayView.OnGesturePerformedListener {
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
    TextView titelTxt;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.txt);
        setContentView(R.layout.layout_law_articles);
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

        try {
            txt.setTextIsSelectable(true);
            titelTxt = (TextView) findViewById(R.id.txtTitel);
            Typeface type = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");

            titelTxt.setText(ArrayTXt.get(index).getItem8().toString());
            titelTxt.setTypeface(type);

            txt.setText(ArrayTXt.get(index).getItem2().toString());
            txt2.setText(ArrayTXt.get(index).getItem1().toString());

            Typeface type2 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
            txt.setTypeface(type2);
            Typeface type3 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");
            txt2.setTypeface(type3);
            Typeface type4 = Typeface.createFromAsset(getAssets(), "NG4ASANS-REGULAR.TTF");


            if (bt_show_index == 0) {
                bt.setVisibility(View.INVISIBLE);
            } else if (bt_show_index == 2) {
                bt.setVisibility(View.VISIBLE);
                bt.setText("عرض صورة الحكم");
            } else if (bt_show_index == 1) {
                bt.setVisibility(View.VISIBLE);

                bt.setText("عرض الأحكام المرتبطه بالمادة");

            }
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
                try {
                    index = index + 1;
                    txt.setText(ArrayTXt.get(index).getItem2().toString());
                    txt2.setText(ArrayTXt.get(index).getItem1().toString());
                    titelTxt.setText(ArrayTXt.get(index).getItem8().toString());

                    show_fav();
                    SelectionSearchWord(Searchtxt);
                } catch (Exception e) {

                }
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
                try {
                    index = index - 1;
                    txt.setText(ArrayTXt.get(index).getItem2().toString());
                    txt2.setText(ArrayTXt.get(index).getItem1().toString());
                    titelTxt.setText(ArrayTXt.get(index).getItem8().toString());

                    show_fav();
                    SelectionSearchWord(Searchtxt);

                } catch (Exception e) {

                }

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

    protected void onPause() {
        // Whenever this activity is paused (i.e. looses focus because another activity is started etc)
        // Override how this activity is animated out of view
        // The new activity is kept still and this activity is pushed out to the left
        overridePendingTransition(R.anim.hold, R.anim.svfade_out_center);
        super.onPause();
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

    private class viewBToption extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {


            if (bt_show_index == 2) {
                intent = new Intent(getApplicationContext(), webview.class);
                // webview.web_url = ArrayTXt.get(index).getItem6().toString() + "/" + ArrayTXt.get(index).getItem7().toString() ;
                webview.web_index = "1";

            } else if (bt_show_index == 1) {

                intent = new Intent(getApplicationContext(), Tashahkambymda.class);
                Tashahkambymda.Master_Array = db.Ahkam_mortbtaMda(ArrayTXt.get(index).getItem5().toString());
                Tashahkambymda.titlname = "الأحكام المرتبطة بالمواد";

            }
            return null;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            try {
                mSVProgressHUD.dismiss();
                super.onPostExecute(result);
                if (Tashahkambymda.Master_Array.isEmpty()) {
                    showWarrning("لا تتوافر أحكام لهذه المواد");
                    return;
                }

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


}