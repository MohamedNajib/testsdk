package com.nibalaws.ebrahim.law.util;

import android.content.Context;

import com.bigkoo.svprogresshud.SVProgressHUD;

public class ProgressUtil {

    private Context mContext;

    public SVProgressHUD mProgress;

    public ProgressUtil(Context mContext) {
        this.mContext = mContext;
        mProgress = new SVProgressHUD(mContext);
    }

    public void showProgress() {
        mProgress.show();
    }

    public void dismissProgress() {
        mProgress.dismiss();
    }
}
