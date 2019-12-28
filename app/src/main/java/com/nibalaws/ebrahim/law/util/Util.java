package com.nibalaws.ebrahim.law.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.google.firebase.iid.FirebaseInstanceId;
import com.nibalaws.ebrahim.law.ApiCall;
import com.nibalaws.ebrahim.law.HomeActivity;
import com.nibalaws.ebrahim.law.R;
import com.nibalaws.ebrahim.law.rest.SharedPrefManagerStorage;

import java.util.Locale;

import cn.refactor.lib.colordialog.PromptDialog;

public class Util {

    public  static   String refreshedToken;

    public static void setLocaleAr(Activity activity) {
        Locale locale = new Locale("ar");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        activity.getBaseContext().getResources().updateConfiguration(config,
                activity.getBaseContext().getResources().getDisplayMetrics());
    }

    public static void showWarrning(Context context, String msg) {
        new PromptDialog(context)
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

    public static void openActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }

    public static void setViewsTypeface(Context context, TextView textView) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "NG4ASANS-REGULAR.TTF");
        textView.setTypeface(type);
    }

    public static void setViewsTypeface(Context context, EditText editText) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "NG4ASANS-REGULAR.TTF");
        editText.setTypeface(type);
    }

    public static void setViewsTypeface(Context context, Button button) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "NG4ASANS-REGULAR.TTF");
        button.setTypeface(type);
    }

    public static void setViewsTypeface(Context context, RadioButton radioButton) {
        Typeface type = Typeface.createFromAsset(context.getAssets(), "NG4ASANS-REGULAR.TTF");
        radioButton.setTypeface(type);
    }

    public static void SelectionSearchWord(String text, String searchText, TextView txt) {
        if (searchText.toString().matches("")) {
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

    public static void shareText(Context context, TextView txt) {
        String link = "https://play.google.com/store/apps/details?id="
                + context.getPackageName();
        String shareBody = txt.getText().toString() + '\n' + link;
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "مشاركه النص");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(sharingIntent, context.getResources().getString(R.string.app_name)));
    }

    public static boolean isInternetConnection(Context context) {
        ConnectivityManager connectivityManager =
                ((ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static boolean checkNetworkAvailable(Context context) {
        if (isInternetConnection(context)) {
            return true;
        } else {
            showWarrning(context, "تاكد من اتصالك بالانترنت");
            return false;
        }
    }

    public static void openWebsiteUrl(Context context, String url, Class<?> cls) {
        if (checkNetworkAvailable(context)) {
            if (SharedPrefManagerStorage.getInstance(context).getClientApiToken() != null) {
                setIntentWithExtra(context, url, cls);
            } else {
                ApiCall.addDeviceCall(context, "1", FirebaseInstanceId.getInstance().getToken(), "1");
                SharedPrefManagerStorage.getInstance(context)
                        .setClientApiToken(FirebaseInstanceId.getInstance().getToken());
            }
        } else {
            ApiCall.addDeviceCall(context, "1", FirebaseInstanceId.getInstance().getToken(), "1");
            SharedPrefManagerStorage.getInstance(context)
                    .setClientApiToken(FirebaseInstanceId.getInstance().getToken());
        }
    }

    private static void setIntentWithExtra(Context context, String stringURL, Class<?> cls) {
        Intent intent = new Intent(context, /*WebViewActivity*/cls);
        intent.putExtra("stringURL", stringURL);
        context.startActivity(intent);
    }

}
