package com.nibalaws.ebrahim.law;

import android.content.Context;
import android.widget.Toast;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.nibalaws.ebrahim.GetDataBaseLastUpdate;
import com.nibalaws.ebrahim.law.rest.APIManager;
import com.nibalaws.ebrahim.law.rest.apiModel.Search;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ApiCall {

    public static List<SearchResponse> searchNibaList;
    public static void searchNibaCall(final Context context, String word, String searchType,
                                  String typeIds, int page, final SVProgressHUD mProgress) {
        searchNibaList = new ArrayList<>();
        APIManager.getApis().SearchNiba(word, searchType, typeIds, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<SearchResponse>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<SearchResponse> searchnibaResponses) {
                        mProgress.dismiss();
                        searchNibaList.addAll(searchnibaResponses);
                        ((SearchnibaActivity)context).iniApitRecyclerView(searchNibaList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgress.dismiss();
                        Toast.makeText(context, "Oops Error: \n"
                                + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    public static List<Search> searchHithiatList;
    public static void searchHithiatCall(final Context context, String word, String Type_ids,
                                         int page, String Search_type, final SVProgressHUD mProgress) {
        searchHithiatList = new ArrayList<>();
        APIManager.getApis().SearchHithiat(word, Type_ids, page, Search_type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Search>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Search> searchnibaResponses) {
                        mProgress.dismiss();
                        searchHithiatList.addAll(searchnibaResponses);
                        ((SearchHithiatActivity)context).initApiRecyclerView(searchHithiatList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgress.dismiss();
                        Toast.makeText(context, "Oops Error: \n"
                                + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public static List<Search> searchesList;
    public static void getDataBaseLastUpdateCall(final Context context, String Type_id, final SVProgressHUD mProgress) {
        searchesList = new ArrayList<>();

        APIManager.getApis().GetDataBaseLastUpdate(Type_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Search>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Search> searchnibaResponses) {
                        mProgress.dismiss();
                        searchesList.addAll(searchnibaResponses);
                        ((GetDataBaseLastUpdate) context).initReccyclerView(searchesList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgress.dismiss();
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
//                        ((HomeActivity)context).tv.append(integer.toString());
//                        ((HomeActivity)context).tv.append("\n");

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
//                        ((HomeActivity)context).tv.append(integer.toString());
//                        ((HomeActivity)context).tv.append("\n");

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
