package com.example.embadedproject.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroClass {
    private static final String BASE_URL="http://ec2-13-124-76-148.ap-northeast-2.compute.amazonaws.com:7001/";


    private static Retrofit getRetrofitInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public static APIcall getApICall(){
        return getRetrofitInstance().create(APIcall.class);
    }
}
