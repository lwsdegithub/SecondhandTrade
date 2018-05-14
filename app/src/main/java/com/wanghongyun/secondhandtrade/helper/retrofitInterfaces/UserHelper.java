package com.wanghongyun.secondhandtrade.helper.retrofitInterfaces;

import com.wanghongyun.secondhandtrade.bean.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 李维升 on 2018/5/14.
 */

public interface UserHelper {
    @GET("UserSevlet")
    Call<User> getUserByIdCall(@Query("TYPE") int type,@Query("USER_ID") int userID);
}
