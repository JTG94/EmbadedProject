package com.example.embadedproject.remote;

import com.example.embadedproject.model.JWTToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIcall {

    @FormUrlEncoded
    @POST("auth/login")
    Call<JWTToken> userlogin(
            @Field("email") String email,
            @Field("password") String password
    );
}
