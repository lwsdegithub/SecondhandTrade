package com.wanghongyun.secondhandtrade.utils;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 李维升 on 2018/5/14.
 */

public class RetrofitUtils {
    public  static Retrofit getRetrofit(String url){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }

}
