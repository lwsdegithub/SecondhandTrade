package com.wanghongyun.secondhandtrade.helper.retrofitInterfaces;

import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Mine.MyCollection;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 李维升 on 2018/5/22.
 */

public interface CollectionHelper {
    @POST("CollectionServlet")
    @FormUrlEncoded
    Call<Common> getAddCollectionCall(@Field("TYPE") int type,@Field("GOODS_ID") int goodsId,@Field("USER_ID") int userId);

    @GET("UserSevlet")
    Call<MyCollection> getMyCollectionCall(@Query("TYPE") int type,@Query("USER_ID") int userId);

    @GET("CollectionServlet")
    Call<Common> getDeleteCollectionCall(@Query("TYPE") int type,@Query("COLLECTION_ID") int collectionId);
}
