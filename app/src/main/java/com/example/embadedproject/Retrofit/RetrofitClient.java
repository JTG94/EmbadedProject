/*
package com.example.embadedproject.Retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static Retrofit instance;

    public static Retrofit getInstance(){
        Gson gson = new GsonBuilder().setLenient().create();
        if(instance == null)

            instance = new Retrofit.Builder()
                        .baseUrl("http://ec2-52-78-171-9.ap-northeast-2.compute.amazonaws.com:7001/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
        return instance;
    }
    public static INodeJS getINodeJS()
    {
        return getInstance().create(INodeJS.class);
    }
}
*/
