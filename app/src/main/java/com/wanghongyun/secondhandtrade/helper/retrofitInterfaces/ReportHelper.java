package com.wanghongyun.secondhandtrade.helper.retrofitInterfaces;

import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 李维升 on 2018/5/23.
 */

public interface ReportHelper {
    @POST("ReportSevlet")
    @FormUrlEncoded
    Call<Common> getAddReportCall(@Field("TYPE") int type,@Field("USER_ID") int userId,@Field("GOODS_ID") int goodsId,@Field("CONTENT") String content);
}
