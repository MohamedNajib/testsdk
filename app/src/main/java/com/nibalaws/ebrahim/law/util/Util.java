package com.nibalaws.ebrahim.law.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nibalaws.ebrahim.law.HomeActivity;

import java.util.Locale;

import cn.refactor.lib.colordialog.PromptDialog;

public class Util {

    public static void setLocaleAr (Activity activity){
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

}
