package com.wanghongyun.secondhandtrade.base;

import android.app.Application;

import com.mob.MobSDK;

/**
 * Created by 李维升 on 2018/4/25.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
    }
}
