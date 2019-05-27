package com.example.embadedproject.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//"http://203.249.127.32:65003/"
//ec2-52-78-171-9.ap-northeast-2.compute.amazonaws.com:7001/
public class RetroClass {
    private static final String BASE_URL="http://ec2-52-78-171-9.ap-northeast-2.compute.amazonaws.com:7001/";




    private static Retrofit getRetrofitInstance(){
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .connectTimeout(100,TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder().setLenient().create();
        return new Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
    }

    public static APIcall getApICall(){
        return getRetrofitInstance().create(APIcall.class);
    }
}
