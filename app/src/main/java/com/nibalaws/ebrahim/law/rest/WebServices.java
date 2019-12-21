package com.nibalaws.ebrahim.law.rest;


import com.nibalaws.ebrahim.law.rest.apiModel.AutoCompResponse;
import com.nibalaws.ebrahim.law.rest.apiModel.GetNotificationsResponse;
import com.nibalaws.ebrahim.law.rest.apiModel.Search;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchAhkamResponse;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchAllResponse;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchTashResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WebServices {

    @POST("GetNotifications")
    Single<List<GetNotificationsResponse>> getNotifications();

    @POST("SearchAhkam")
    @FormUrlEncoded
    Single<List<SearchAhkamResponse>> SearchAhkam(@Field("Hkm_num_From") String Hkm_num_From,
                                                  @Field("Hkm_num_To") String Hkm_num_To,
                                                  @Field("Office_from") String Office_from,
                                                  @Field("office_To") String office_To,
                                                  @Field("Page_FROM") String Page_FROM,
                                                  @Field("Page_to") String Page_to,
                                                  @Field("Part_FROM") String Part_FROM,
                                                  @Field("Part_to") String Part_to,
                                                  @Field("court_sys") String court_sys,
                                                  @Field("court_place") String court_place,
                                                  @Field("Type_ids") String Type_ids,
                                                  @Field("date_from") String date_from,
                                                  @Field("date_to") String date_to,
                                                  @Field("Word") String Word,
                                                  @Field("Search_type") String Search_type,
                                                  @Field("Page") int Page,
                                                  @Field("Hkm_Year_From") String Hkm_Year_From,
                                                  @Field("Hkm_Year_To") String Hkm_Year_To);

    @POST("SearchTash")
    @FormUrlEncoded
    Single<List<SearchTashResponse>> SearchTash(@Field("Tash_no_from") String Tash_no_from,
                                                @Field("Tash_no_to") String Tash_no_to,
                                                @Field("Tash_year_from") String Tash_year_from,
                                                @Field("Tash_year_to") String Tash_year_to,
                                                @Field("date_from") String date_from,
                                                @Field("date_to") String date_to,
                                                @Field("Type_ids") String Type_ids,
                                                @Field("Word") String Word,
                                                @Field("Search_type") String Search_type,
                                                @Field("tash_name") String tash_name,
                                                @Field("Page") int Page);

    @POST("Searchniba")
    @FormUrlEncoded
    Single<List<Search>> SearchNiba(@Field("Word") String word,
                                    @Field("Search_type") String Search_type,
                                    @Field("Type_ids") String Type_ids,
                                    @Field("page") int page);

    @POST("SearchHithiat")
    @FormUrlEncoded
    Single<List<Search>> SearchHithiat(@Field("Word") String word,
                                       @Field("Type_ids") String Type_ids,
                                       @Field("page") int page,
                                       @Field("Search_type") String Search_type);

    @POST("GetDataBaseLastUpdate")
    @FormUrlEncoded
    Single<List<Search>> GetDataBaseLastUpdate(@Field("Type_id") String Type_id);

    @GET("chkForActiveCode")
    Single<Integer> chkForActiveCode(@Query("code") String code);

    @GET("chkForActiveMob")
    Single<Integer> chkForActiveMob(@Query("phone") String phone);

    @GET("AddDevice")
    Single<String> AddDevice(@Query("os") String os,
                             @Query("token") String token,
                             @Query("auto") String auto);

    @GET("SearchAll")
    Single<List<SearchAllResponse>> searchAll(@Query("word") String word,
                                              @Query("page") int page,
                                              @Query("tb_index") String tb_index);

    @GET("AutoComp")
    Single<List<AutoCompResponse>> autoComp(@Query("Txt") String txt);


    @GET("TransLateText")
    Single<String> transLateText(@Query("Text") String txt);


//    @GET("everything")
//    Single<ArticlesResponse> getNews(@Query("sources") String sources,
//                                     @Query("language") String language,
//                                     @Query("apiKey") String apiKey);


}
