package com.wanghongyun.secondhandtrade.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by 李维升 on 2018/5/13.
 */

public class IntentUtils {
    public static void startActivity(Context context,Class clazz){
        Intent intent=new Intent(context,clazz);
        context.startActivity(intent);
    }
    public static void startActivityWithInt(Context context,Class clazz,String key,int value){
        Bundle bundle=new Bundle();
        bundle.putInt(key,value);
        Intent intent=new Intent(context,clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
    public static void startActivityWithStr(Context context,Class clazz,String key,String value){
        Bundle bundle=new Bundle();
        bundle.putString(key,value);
        Intent intent=new Intent(context,clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
