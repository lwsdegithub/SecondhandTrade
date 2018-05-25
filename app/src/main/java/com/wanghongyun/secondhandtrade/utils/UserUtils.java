package com.wanghongyun.secondhandtrade.utils;

import android.content.Context;

import com.wanghongyun.secondhandtrade.bean.User;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.UserHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 李维升 on 2018/5/22.
 */

public class UserUtils {
    public static boolean isLogin(Context context){
        return (boolean) SharedPreferencesUtils.getData(context,SharedPreferencesUtils.USER,SharedPreferencesUtils.IS_LOGIN,false);
    }
    public static int getUserId(Context context){
        return (int) SharedPreferencesUtils.getData(context,SharedPreferencesUtils.USER,SharedPreferencesUtils.USER_ID,0);
    }
    public static String getUserName(Context context){
        return (String) SharedPreferencesUtils.getData(context,SharedPreferencesUtils.USER,SharedPreferencesUtils.USER_NAME,"头号玩家");
    }
    //从服务器更新存储在SharedPreferences的个人数据
    public static void updateSpData(final Context context){
        RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(UserHelper.class).getUserByIdCall(0,getUserId(context)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    User user=response.body();
                    SharedPreferencesUtils.putData(context,SharedPreferencesUtils.USER,SharedPreferencesUtils.USER_NAME,user.getUserName());
                    SharedPreferencesUtils.putData(context,SharedPreferencesUtils.USER,SharedPreferencesUtils.HEAD_ICON,user.getHeadIcon());
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }
}
