package com.wanghongyun.secondhandtrade.helper.retrofitInterfaces;

import com.wanghongyun.secondhandtrade.helper.gsonBeans.GoodsDetails;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.GoodsList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 李维升 on 2018/5/10.
 */

public interface GoodsHelper {
    @GET("GetGoodsListSevlet")
    Call<GoodsList> getCall();
    @GET("GoodsDetailsSevlet")
    Call<GoodsDetails> getGoodsDetailsCall(@Query("GOODS_ID") int GOODS_ID);
}
