package com.wanghongyun.secondhandtrade.helper.retrofitInterfaces;

import com.wanghongyun.secondhandtrade.bean.Demand;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.DemandList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 李维升 on 2018/5/15.
 */

public interface DemandHelper {
    @GET("DemandSevlet")
    Call<DemandList> getDemandListCall(@Query("TYPE") int type);

    @POST("AddDemandSevlet")
    @FormUrlEncoded
    Call<Common> getAddDemandCall(@Field("USER_ID") int userId,@Field("CONTENT") String content);

    @GET("UserSevlet")
    Call<List<Demand>> getDemandListByUserId(@Query("TYPE") int type,@Query("USER_ID") int userId);
}
