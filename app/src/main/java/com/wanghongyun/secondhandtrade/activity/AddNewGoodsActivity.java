package com.wanghongyun.secondhandtrade.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by 李维升 on 2018/5/13.
 */

public class AddNewGoodsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initView();
    }
    private void initView(){
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("添加新物品");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
