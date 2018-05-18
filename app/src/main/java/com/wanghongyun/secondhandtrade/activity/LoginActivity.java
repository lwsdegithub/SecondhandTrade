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
import com.wanghongyun.secondhandtrade.bean.User;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.UserHelper;
import com.wanghongyun.secondhandtrade.utils.GlideUtils;
import com.wanghongyun.secondhandtrade.utils.IntentUtils;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.SharedPreferencesUtils;
import com.wanghongyun.secondhandtrade.utils.StringUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
                Retrofit retrofit= RetrofitUtils.getRetrofit(NetConstant.BASE_URL);
                UserHelper userHelper=retrofit.create(UserHelper.class);
                String phone=etPhoneLogin.getText().toString();
                String password=etPasswordLogin.getText().toString();
                Call<Common> call=userHelper.getLoginCall(phone,password);
                if (StringUtils.isRightPhone(phone)){
                    call.enqueue(new Callback<Common>() {
                        @Override
                        public void onResponse(Call<Common> call, Response<Common> response) {
                            Common common=response.body();
                            if (common.getStatus()==NetConstant.LOGIN_SUCCESS){
                                ToastUtils.showMsg(getApplicationContext(),"亲，登陆成功！");
                                //数据保存
                                SharedPreferencesUtils.putData(getApplicationContext(),SharedPreferencesUtils.USER,SharedPreferencesUtils.IS_LOGIN,true);
                                SharedPreferencesUtils.putData(getApplicationContext(),SharedPreferencesUtils.USER,SharedPreferencesUtils.PHONE,etPhoneLogin.getText().toString());

                                Retrofit retrofit=RetrofitUtils.getRetrofit(NetConstant.BASE_URL);
                                UserHelper userHelper=retrofit.create(UserHelper.class);
                                Call<User> userCall=userHelper.getUserByPhone("1",(String) SharedPreferencesUtils.getData(getApplicationContext(),SharedPreferencesUtils.USER,SharedPreferencesUtils.PHONE,"1"));
                                userCall.enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        User user=response.body();
                                        //数据保存
                                        SharedPreferencesUtils.putData(getApplicationContext(),SharedPreferencesUtils.USER,SharedPreferencesUtils.USER_NAME,user.getUserName());
                                        SharedPreferencesUtils.putData(getApplicationContext(),SharedPreferencesUtils.USER,SharedPreferencesUtils.HEAD_ICON,user.getHeadIcon());
                                    }
                                    @Override
                                    public void onFailure(Call<User> call, Throwable t) {
                                    }
                                });
                                LoginActivity.this.finish();
                            }else if (common.getStatus()==NetConstant.ACCOUNT_IS_NOT_EXISTED){
                                ToastUtils.showMsg(getApplicationContext(),"账户不存在，请注册");
                            }else if (common.getStatus()==NetConstant.PASSWORD_ERROR){
                                ToastUtils.showMsg(getApplicationContext(),"密码错误");
                            }
                        }
                        @Override
                        public void onFailure(Call<Common> call, Throwable t) {
                        }
                    });
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"请输入正确的手机号！");
                }

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
