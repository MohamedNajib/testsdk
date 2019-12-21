package com.nibalaws.ebrahim.law;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatDelegate;

import java.util.Locale;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class App extends Application {

    private static Application mInstance;
    private String Lang_AR = "ar";
    private Locale locale;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public static synchronized Application getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        locale = new Locale("ar");
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        getBaseContext().getResources().updateConfiguration(config,
//                getBaseContext().getResources().getDisplayMetrics());

    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if (Lang_AR.equals("ar")) {
//            loc = new Locale("ar");
//        } else {
//            loc = new Locale("ar");
//        }
//
//        Locale.setDefault(loc);
//        Configuration config = new Configuration();
//        config.locale = loc;
//        getBaseContext().getResources().updateConfiguration(config,
//                getBaseContext().getResources().getDisplayMetrics());
//
//    }
}
