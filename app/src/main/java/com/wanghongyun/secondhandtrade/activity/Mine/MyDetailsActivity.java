package com.wanghongyun.secondhandtrade.activity.Mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.utils.SharedPreferencesUtils;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.item_exit_login){
            SharedPreferencesUtils.putData(this,SharedPreferencesUtils.USER,SharedPreferencesUtils.IS_LOGIN,false);
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_details_menu,menu);
        return true;
    }
}
