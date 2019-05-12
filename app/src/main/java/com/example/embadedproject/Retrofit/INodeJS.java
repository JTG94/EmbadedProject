package com.example.embadedproject.Retrofit;

import com.example.embadedproject.model.JWTToken;

import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface INodeJS {
    @POST("auth/reg")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("name") String name,
                                         @Field("email") String email,
                                         @Field("phone") String phone,
                                         @Field("password") String password,
                                         @Field("coupang_id") String coupid,
                                         @Field("coupang_pw") String couppw);

   /* @POST("auth/login")
    @FormUrlEncoded
    Call<JWTToken> loginUser(

                                    @Field("email") String email,
                                    @Field("password") String password
    );*/

    @POST("auth/search/id")
    @FormUrlEncoded
    Observable<String> findUserid(
                                    @Field("name") String name,
                                    @Field("phone") String phone

    );
    @POST("auth/search/pw")
    @FormUrlEncoded
    Observable<String> findUserpw(
                                    @Field("name") String name,
                                    @Field("email") String email,
                                    @Field("phone") String phone
    );


}
