package com.wanghongyun.secondhandtrade.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.utils.IntentUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 李维升 on 2018/5/16.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.gif_bg_login)
    ImageView gifBgLogin;
    @BindView(R.id.et_phone_login)
    TextInputEditText etPhoneLogin;
    @BindView(R.id.et_password_login)
    TextInputEditText etPasswordLogin;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        this.initView();

    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("登录");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Glide.with(this).load(R.mipmap.login_bg).asGif().into(gifBgLogin);
    }

    @OnClick({R.id.btn_login,R.id.btn_register})
    public void onClick(View view){
        int id=view.getId();
        switch (id){
            case R.id.btn_login:
                break;
            case R.id.btn_register:
                IntentUtils.startActivity(this,RegisterActivity.class);
                break;
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
