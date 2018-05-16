package com.wanghongyun.secondhandtrade.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 李维升 on 2018/5/16.
 */

public class MyDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initView();
    }

    private void initView(){
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("个人资料");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }



    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
