package com.wanghongyun.secondhandtrade.helper.retrofitInterfaces;

import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.GoodsDetails;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.GoodsList;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by 李维升 on 2018/5/10.
 */

public interface GoodsHelper {
    //获取所有的物品
    @GET("GetGoodsListSevlet")
    Call<GoodsList> getCall();
    //根据物品ID获取
    @GET("GoodsDetailsSevlet")
    Call<GoodsDetails> getGoodsDetailsCall(@Query("GOODS_ID") int GOODS_ID);
    //上传物品POST请求
    //@Field("USER_ID") int userId,@Field("GOODS_NAME") String goodsName,@Field("DESCRIPTION") String description,@Field("PRICE") String price
    @POST("AddGoodsSevlet")
    @Multipart
    Call<Common> getAddGoodsCall(@Part List<MultipartBody.Part> bodyPartList);
}
