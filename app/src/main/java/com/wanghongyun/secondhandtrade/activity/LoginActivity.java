package com.wanghongyun.secondhandtrade.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cunoraz.gifview.library.GifView;
import com.wanghongyun.secondhandtrade.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 李维升 on 2018/5/16.
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        this.initView();

    }

    private void initView(){
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("登录");
        actionBar.setDisplayHomeAsUpEnabled(true);

        ImageView ivBg=findViewById(R.id.gif_bg_login);
        Glide.with(this).load(R.mipmap.login_bg).asGif().into(ivBg);
        //初始化gif view
        //GifView gifView=findViewById(R.id.gif_view_login);
        //gifView.setGifResource(R.mipmap.login_bg);

        //gifView.play();
    }



    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
