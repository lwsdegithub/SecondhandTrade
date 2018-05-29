package com.wanghongyun.secondhandtrade.base;

import android.app.Application;

import com.mob.MobSDK;
import com.wanghongyun.secondhandtrade.utils.SharedPreferencesUtils;
import com.wanghongyun.secondhandtrade.utils.UserUtils;

/**
 * Created by 李维升 on 2018/4/25.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MobSDK.init(this);
        this.initData();
    }
    private void initData(){
        if (UserUtils.isLogin(this)){
            MobSDK.setUser(UserUtils.getUserId(this)+"",UserUtils.getUserName(this),null,null);
        }
        if (UserUtils.isLogin(getApplicationContext())){
            UserUtils.updateSpData(getApplicationContext());
        }
    }
}
