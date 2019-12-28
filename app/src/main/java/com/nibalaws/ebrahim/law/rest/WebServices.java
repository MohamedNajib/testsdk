package com.nibalaws.ebrahim.law.rest;


import com.nibalaws.ebrahim.law.rest.apiModel.AutoCompResponse;
import com.nibalaws.ebrahim.law.rest.apiModel.GetNotificationsResponse;
import com.nibalaws.ebrahim.law.rest.apiModel.Search;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchAhkamResponse;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchAllResponse;
import com.nibalaws.ebrahim.law.rest.apiModel.SearchResponse;
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

//    @POST("SearchAhkam")
//    @FormUrlEncoded
//    Single<List<SearchAhkamResponse>> SearchAhkam(@Field("Hkm_num_From") String Hkm_num_From,
//                                                  @Field("Hkm_num_To") String Hkm_num_To,
//                                                  @Field("Office_from") String Office_from,
//                                                  @Field("office_To") String office_To,
//                                                  @Field("Page_FROM") String Page_FROM,
//                                                  @Field("Page_to") String Page_to,
//                                                  @Field("Part_FROM") String Part_FROM,
//                                                  @Field("Part_to") String Part_to,
//                                                  @Field("court_sys") String court_sys,
//                                                  @Field("court_place") String court_place,
//                                                  @Field("Type_ids") String Type_ids,
//                                                  @Field("date_from") String date_from,
//                                                  @Field("date_to") String date_to,
//                                                  @Field("Word") String Word,
//                                                  @Field("Search_type") String Search_type,
//                                                  @Field("Page") int Page,
//                                                  @Field("Hkm_Year_From") String Hkm_Year_From,
//                                                  @Field("Hkm_Year_To") String Hkm_Year_To);

    @GET("SearchAhkam")
    Single<List<SearchAhkamResponse>> SearchAhkam(@Query("Hkm_num_From") String Hkm_num_From,
                                                  @Query("Hkm_num_To") String Hkm_num_To,
                                                  @Query("Office_from") String Office_from,
                                                  @Query("office_To") String office_To,
                                                  @Query("Page_FROM") String Page_FROM,
                                                  @Query("Page_to") String Page_to,
                                                  @Query("Part_FROM") String Part_FROM,
                                                  @Query("Part_to") String Part_to,
                                                  @Query("court_sys") String court_sys,
                                                  @Query("court_place") String court_place,
                                                  @Query("Type_ids") String Type_ids,
                                                  @Query("date_from") String date_from,
                                                  @Query("date_to") String date_to,
                                                  @Query("Word") String Word,
                                                  @Query("Search_type") String Search_type,
                                                  @Query("Page") int Page,
                                                  @Query("Hkm_Year_From") String Hkm_Year_From,
                                                  @Query("Hkm_Year_To") String Hkm_Year_To);

//    @GET("everything")
//    Single<ArticlesResponse> getNews(@Query("sources") String sources,
//                                     @Query("language") String language,
//                                     @Query("apiKey") String apiKey);

//    @POST("SearchTash")
//    @FormUrlEncoded
//    Single<List<SearchTashResponse>> SearchTash(@Field("Tash_no_from") String Tash_no_from,
//                                                @Field("Tash_no_to") String Tash_no_to,
//                                                @Field("Tash_year_from") String Tash_year_from,
//                                                @Field("Tash_year_to") String Tash_year_to,
//                                                @Field("date_from") String date_from,
//                                                @Field("date_to") String date_to,
//                                                @Field("Type_ids") String Type_ids,
//                                                @Field("Word") String Word,
//                                                @Field("Search_type") String Search_type,
//                                                @Field("tash_name") String tash_name,
//                                                @Field("Page") int Page);

    @GET("SearchTash")
    Single<List<SearchTashResponse>> SearchTash(@Query("Tash_no_from") String Tash_no_from,
                                                @Query("Tash_no_to") String Tash_no_to,
                                                @Query("Tash_year_from") String Tash_year_from,
                                                @Query("Tash_year_to") String Tash_year_to,
                                                @Query("date_from") String date_from,
                                                @Query("date_to") String date_to,
                                                @Query("Type_ids") String Type_ids,
                                                @Query("Word") String Word,
                                                @Query("Search_type") String Search_type,
                                                @Query("tash_name") String tash_name,
                                                @Query("Page") int Page);

    @GET("Searchniba")
    Single<List<SearchResponse>> SearchNiba(@Query("Word") String word,
                                            @Query("Search_type") String Search_type,
                                            @Query("Type_ids") String Type_ids,
                                            @Query("page") int page);

    @GET("SearchHithiat")
    Single<List<Search>> SearchHithiat(@Query("Word") String word,
                                       @Query("Type_ids") String Type_ids,
                                       @Query("page") int page,
                                       @Query("Search_type") String Search_type);

    @GET("GetDataBaseLastUpdate")
    Single<List<Search>> GetDataBaseLastUpdate(@Query("Type_id") String Type_id);

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
