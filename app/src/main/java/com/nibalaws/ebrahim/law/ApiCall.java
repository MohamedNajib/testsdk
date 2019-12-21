package com.nibalaws.ebrahim.law;

import android.content.Context;
import android.widget.Toast;

import com.nibalaws.ebrahim.law.rest.APIManager;
import com.nibalaws.ebrahim.law.rest.apiModel.Search;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ApiCall {

    public static void searchNibaCall(final Context context, String word, String searchType,
                                  String typeIds, int page) {
        APIManager.getApis().SearchNiba(word, searchType, typeIds, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Search>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Search> searchnibaResponses) {
                        for (int i = 0; i < searchnibaResponses.size(); i++) {
                            ((HomeActivity)context).tv.append(searchnibaResponses.get(i).getInfo());
                            ((HomeActivity)context).tv.append("\n");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Oops Error: \n"
                                + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void searchHithiatCall(final Context context, String word, String Type_ids,
                                         int page, String Search_type) {
        APIManager.getApis().SearchHithiat(word, Type_ids, page, Search_type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Search>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Search> searchnibaResponses) {
                        for (int i = 0; i < searchnibaResponses.size(); i++) {
                            ((HomeActivity)context).tv.append(searchnibaResponses.get(i).getInfo());
                            ((HomeActivity)context).tv.append("\n");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Oops Error: \n"
                                + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void getDataBaseLastUpdateCall(final Context context, String Type_id) {
        APIManager.getApis().GetDataBaseLastUpdate(Type_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Search>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Search> searchnibaResponses) {
                        for (int i = 0; i < searchnibaResponses.size(); i++) {
                            ((HomeActivity)context).tv.append(searchnibaResponses.get(i).getInfo());
                            ((HomeActivity)context).tv.append("\n");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Oops Error: \n"
                                + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void chkForActiveCodeCall(final Context context, String code) {
        APIManager.getApis().chkForActiveCode(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        ((HomeActivity)context).tv.append(integer.toString());
                        ((HomeActivity)context).tv.append("\n");

                        Toast.makeText(context, integer.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Oops Error: \n"
                                + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void chkForActiveMobCall(final Context context, String phone) {
        APIManager.getApis().chkForActiveMob(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Integer integer) {
                        ((HomeActivity)context).tv.append(integer.toString());
                        ((HomeActivity)context).tv.append("\n");

                        Toast.makeText(context, integer.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Oops Error: \n"
                                + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static void addDeviceCall(final Context context, String os, String token, String auto) {
        APIManager.getApis().AddDevice(os, token, auto)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
//                        ((HomeActivity)context).tv.append(s);
//                        ((HomeActivity)context).tv.append("\n");
                        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Oops Error: \n"
                                + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
