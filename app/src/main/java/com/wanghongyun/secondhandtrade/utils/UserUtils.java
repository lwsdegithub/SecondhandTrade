package com.wanghongyun.secondhandtrade.utils;

import android.content.Context;

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
}
