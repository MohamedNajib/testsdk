package com.nibalaws.ebrahim.law.rest;

import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {

    public static final String BASE_URL = "http://niaba-laws.com/api/PhoneApi/";
    public static final String API_TAG = "api";

    public static final int CONNECTION_TIMEOUT = 10; // 10 seconds
    public static final int READ_TIMEOUT = 2; // 2 seconds
    public static final int WRITE_TIMEOUT = 2; // 2 seconds

    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.e(API_TAG, message);
                }
            });

            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor)
//                    // establish connection to server
//                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
//
//                    // time between each byte read from the server
//                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
//
//                    // time between each byte sent to server
//                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
//
//                    .retryOnConnectionFailure(false)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public static WebServices getApis() {
        return getInstance().create(WebServices.class);
    }
}
