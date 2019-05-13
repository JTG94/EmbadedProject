package com.example.embadedproject.remote;

import com.example.embadedproject.model.JWTToken;
import com.example.embadedproject.model.Message;
import com.example.embadedproject.model.MonthValue;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIcall {

    @FormUrlEncoded
    @POST("auth/login")
    Call<JWTToken> userlogin(
            @Field("email") String email,
            @Field("password") String password
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

/*    @GET("history/month?year=2019&month=5")
    Call<MonthValue> Mainreq(
            @Header("x-access-token") String token

    );*/
    @GET("history/month?year=2019&month=5")
    Call<MonthValue> Mainreq(
            @Header("x-access-token") String token

    );
}
