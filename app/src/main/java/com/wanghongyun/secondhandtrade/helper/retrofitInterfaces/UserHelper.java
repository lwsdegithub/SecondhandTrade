package com.wanghongyun.secondhandtrade.helper.retrofitInterfaces;

import com.wanghongyun.secondhandtrade.bean.User;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Mine.MyGoods;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by 李维升 on 2018/5/14.
 */

public interface UserHelper {
    @GET("UserSevlet")
    Call<User> getUserByIdCall(@Query("TYPE") int type,@Query("USER_ID") int userID);

    @POST("RegisterSevlet")
    @FormUrlEncoded
    Call<Common> getRegisterCall(@Field("PHONE") String phone, @Field("USER_NAME") String userName, @Field("PASSWORD") String password);

    @POST("LoginSevlet")
    @FormUrlEncoded
    Call<Common> getLoginCall(@Field("PHONE") String phone, @Field("PASSWORD") String password);

    @POST("UserSevlet")
    @FormUrlEncoded
    Call<User> getUserByPhone(@Field("TYPE") String type,@Field("PHONE")String phone);

    @GET("UserSevlet")
    Call<MyGoods> getMyGoodsCall(@Query("TYPE") int type,@Query("USER_ID") int userId);

    @GET("UserSevlet")
    Call<Common> getUpdateUserCall(@QueryMap Map<String,String> map);

    @POST("UpdateUserHeadIconServlet")
    @Multipart
    Call<Common> getUpdateHeadIconCall(@Part List<MultipartBody.Part> parts);
}
