package com.nibalaws.ebrahim.law.rest;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManagerStorage {

    public static final String SHARED_PREF_NAME = "ApiToken";
    private static String CLIENT_API_TOKEN;

    private static SharedPrefManagerStorage mInstance;
    private Context mContext;

    private SharedPrefManagerStorage(Context context) {
        this.mContext = context;
    }

    public static synchronized SharedPrefManagerStorage getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManagerStorage(context);
        }
        return mInstance;
    }

    public void setClientApiToken(String apiToken) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CLIENT_API_TOKEN, apiToken);
        editor.apply();
    }

    public String getClientApiToken() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CLIENT_API_TOKEN, null);
    }

    public void clare() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
