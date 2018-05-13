package com.wanghongyun.secondhandtrade.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 李维升 on 2018/5/13.
 */

public class BundleUtils {
    public static int getInt(AppCompatActivity context,String key){
        Bundle bundle=new Bundle();
        bundle=context.getIntent().getExtras();
        return bundle.getInt(key);
    }
}
