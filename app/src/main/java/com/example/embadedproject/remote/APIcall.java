package com.example.embadedproject.remote;

import com.example.embadedproject.model.Budget;
import com.example.embadedproject.model.BudgetStatus;
import com.example.embadedproject.model.CategoryData;
import com.example.embadedproject.model.DayList;
import com.example.embadedproject.model.JWTToken;
import com.example.embadedproject.model.Message;
import com.example.embadedproject.model.MonthPercent;
import com.example.embadedproject.model.MonthValue;
import com.example.embadedproject.model.PrevCompare;
import com.example.embadedproject.model.PurchaseList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface APIcall {
    @GET("history/excelmobile")
    Call<Message> requestExcel(
            @Header("x-access-token") String token,
            @Query("year") int year,
            @Query("month") int month
    );



    @GET("/history/category")
    Call<CategoryData> requestCategoryPrice(
            @Header("x-access-token") String token,
            @Query("category") String category
    );

    @FormUrlEncoded
    @PUT("/user/budget")
    Call<Budget> postBudget(
            @Header("x-access-token")String token,
            @Field("budget") int budget
    );

    @FormUrlEncoded
    @POST("/history/new")
    Call<PurchaseList> InputPurchase(
            @Header("x-access-token") String token,
            @Field("name") String name,
            @Field("price") int price,
            @Field("date") String date
    );

    @GET("/history/budget")
    Call<BudgetStatus> requestBudgetStatus(
            @Header("x-access-token") String token
    );

    @GET("user/budget")
    Call<Budget> requestBudget(
            @Header("x-access-token") String token

    );

    @GET("history/day")
    Call<DayList> DayListCall(
            @Header("x-access-token") String token,
            @Query("year") int year,
            @Query("month") int month,
            @Query("day") int day
    );

    @GET("/history/prev")
    Call<PrevCompare> PrevCompareCall(
            @Header("x-access-token") String token,
            @Query("month") int month

    );

    @GET("/history/month")
    Call<MonthValue> MontlyList(
            @Header("x-access-token") String token,
            @Query("year") int year,
            @Query("month") int month

    );
    @GET("/history/percent")
    Call<MonthPercent> PercentList(
            @Header("x-access-token") String token
    );

    @FormUrlEncoded
    @POST("auth/login")
    Call<JWTToken> userlogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @DELETE("user/withdrawl")
    Call<Message> outreq(
            @Header("x-access-token") String token,
            @Field("pass") String pass
    );


    @POST("auth/reg")
    @FormUrlEncoded
    Call<Message>  registerUser(@Field("name") String name,
                                @Field("email") String email,
                                @Field("phone") String phone,
                                @Field("password") String password,
                                @Field("coupang_id") String coupid,
                                @Field("coupang_pw") String couppw);

    @POST("auth/search/id")
    @FormUrlEncoded
    Call<Message> findUserid(

            @Field("name") String name,
            @Field("phone") String phone

    );


    @POST("auth/search/pw")
    @FormUrlEncoded
    Call<Message> findUserpw(
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone
    );

    @POST("/user/feedback")
    @FormUrlEncoded
    Call<Message> questionReq(
            @Header("x-access-token") String token,
            @Field("content") String content

    );


    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "/user/withdrawl", hasBody = true)
    Call<Message> userOut(
            @Header("x-access-token") String token,
            @Field("password") String password
    );



}
