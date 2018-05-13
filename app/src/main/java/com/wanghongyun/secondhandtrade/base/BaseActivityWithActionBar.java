package com.wanghongyun.secondhandtrade.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 李维升 on 2018/5/13.
 */

public class BaseActivityWithActionBar extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTheme(android.support.design.R.style.Theme_Design_Light);
    }
}
