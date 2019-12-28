package com.nibalaws.ebrahim.law;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.nibalaws.ebrahim.GetDataBaseLastUpdate;
import com.nibalaws.ebrahim.law.DataBaseManger.DatabaseHelper;
import com.nibalaws.ebrahim.law.notification.ConnectionUtilities;
import com.nibalaws.ebrahim.law.rest.SharedPrefManagerStorage;
import com.nibalaws.ebrahim.law.util.Util;

import java.io.IOException;


import static com.balsikandar.crashreporter.CrashReporter.getContext;
import static java.lang.Thread.sleep;

public class Splash_Screen extends AppCompatActivity {

    DatabaseHelper db;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setLocaleAr(this);
        setContentView(R.layout.splash__screen);
        db = new DatabaseHelper(this);

        Animation animation = AnimationUtils.loadAnimation(Splash_Screen.this, R.anim.svfade_out_center);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(1000);
        final ImageView splash = (ImageView) findViewById(R.id.imageV);
        splash.startAnimation(animation);
        db = new DatabaseHelper(this);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.v("TEST", "Refreshed token: " + refreshedToken);

        SharedPrefManagerStorage.getInstance(this).setClientApiToken(refreshedToken);

        Util.refreshedToken = refreshedToken;

        ApiCall.addDeviceCall(this, "1", refreshedToken, "1");

        new LongOperation().execute();
    }

    private boolean checkConnection() {
        // first, check connectivity
        if (ConnectionUtilities
                .checkInternetConnection(getContext())) {
            return true;
        }
        return false;
    }


    private SQLiteDatabase mDb;

    private class LongOperation extends AsyncTask<Void, Void, String> {
        protected String doInBackground(Void... params) {


            try {


                try {
                    db.updateDataBase();
                } catch (IOException mIOException) {
                    throw new Error("UnableToUpdateDatabase");
                }

                try {
                    mDb = db.getWritableDatabase();
                } catch (SQLException mSQLException) {
                    throw mSQLException;
                }


                sleep(10000);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finish();


            return "";
        }

        @Override
        protected void onPostExecute(String result) {
//           Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            //Intent intent = new Intent(getApplicationContext(), SearchAhkamActivity.class);
            //Intent intent = new Intent(getApplicationContext(), GetNotificationsActivity.class);
            //Intent intent = new Intent(getApplicationContext(), SearchTashActivity.class);
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//            Intent intent = new Intent(getApplicationContext(), GetDataBaseLastUpdate.class);
            //Intent intent = new Intent(getApplicationContext(), SearchHithiatActivity/*SearchnibaActivity*/.class);
            //Intent intent = new Intent(getApplicationContext(), textViewMowad.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


}
