package com.wanghongyun.secondhandtrade.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by 李维升 on 2018/5/15.
 */

public class AddNewDemandActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initView();
    }
    private void initView(){
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加新需求");
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.initData();
    }


    private void initData(){

    }



    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}

