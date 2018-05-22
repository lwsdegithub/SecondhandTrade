package com.wanghongyun.secondhandtrade.helper.retrofitInterfaces;

import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by 李维升 on 2018/5/22.
 */

public interface CollectionHelper {
    @POST("CollectionServlet")
    @FormUrlEncoded
    Call<Common> getAddCollectionCall(@Field("TYPE") int type,@Field("GOODS_ID") int goodsId,@Field("USER_ID") int userId);
}
