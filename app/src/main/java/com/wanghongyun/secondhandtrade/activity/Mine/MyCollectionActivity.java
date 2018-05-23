package com.wanghongyun.secondhandtrade.activity.Mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.wanghongyun.secondhandtrade.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李维升 on 2018/5/23.
 */

public class MyCollectionActivity extends AppCompatActivity {
    @BindView(R.id.lv_my_collection)
    ListView lvMyCollection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("我的收藏");
        initData();
    }

    private void initData() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
