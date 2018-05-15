package com.wanghongyun.secondhandtrade.helper.retrofitInterfaces;

import com.wanghongyun.secondhandtrade.helper.gsonBeans.DemandList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 李维升 on 2018/5/15.
 */

public interface DemandHelper {
    @GET("DemandSevlet")
    Call<DemandList> getDemandListCall(@Query("TYPE") int type);
}
