package com.wanghongyun.secondhandtrade.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.UserHelper;
import com.wanghongyun.secondhandtrade.utils.IntentUtils;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.SharedPreferencesUtils;
import com.wanghongyun.secondhandtrade.utils.StringUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by 李维升 on 2018/5/16.
 */

public class RegisterActivity extends AppCompatActivity {

    private static final int GET_CODE_SUCCESS=0;
    private static final int GET_CODE_ERROR=1;
    private static final int SUBMIT_CODE_SUCCESS=2;
    private static final int SUBMIT_CODE_ERROR=3;

    //ButterKnife
    @BindView(R.id.gif_bg_register)
    ImageView gifBgRegister;
    @BindView(R.id.et_user_name_register)
    TextInputEditText etUserNameRegister;
    @BindView(R.id.et_phone_register)
    TextInputEditText etPhoneRegister;
    @BindView(R.id.et_password_register)
    TextInputEditText etPasswordRegister;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.et_code)
    TextInputEditText etCode;
    @BindView(R.id.btn_register_confirm)
    Button btnRegisterConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        this.initView();
    }



    private void initView() {
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("注册");
        actionBar.setDisplayHomeAsUpEnabled(true);

        Glide.with(this).load(R.mipmap.login_bg).asGif().into(gifBgRegister);
    }

    //用来更新主线程UI
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case GET_CODE_SUCCESS:
                    ToastUtils.showMsg(getApplicationContext(),"亲，获取验证码成功，稍等呦。。。");
                    break;
                case GET_CODE_ERROR:
                    ToastUtils.showMsg(getApplicationContext(),"亲，获取验证码失败，请检查网络设置");
                    break;
                case SUBMIT_CODE_SUCCESS:
                    Retrofit retrofit=RetrofitUtils.getRetrofit(NetConstant.BASE_URL);
                    UserHelper userHelper=retrofit.create(UserHelper.class);
                    Call<Common> commonCall=userHelper.getRegisterCall(etPhoneRegister.getText().toString(),etUserNameRegister.getText().toString(),etUserNameRegister.getText().toString());
                    commonCall.enqueue(new retrofit2.Callback<Common>() {
                        @Override
                        public void onResponse(Call<Common> call, Response<Common> response) {
                            Common common=response.body();
                            if (common.getStatus()==NetConstant.REGISTER_SUCCESS){
                                ToastUtils.showMsg(getApplicationContext(),"注册成功！，返回登录呦！");
                                RegisterActivity.this.finish();
                            }else if (common.getStatus()==NetConstant.ACCOUNT_IS_EXISTED) {
                                ToastUtils.showMsg(getApplicationContext(),"账户已经存在");
                            }
                        }
                        @Override
                        public void onFailure(Call<Common> call, Throwable t) {
                            ToastUtils.showMsg(getApplicationContext(),"请检查网络");
                        }
                    });
                    break;
                case SUBMIT_CODE_ERROR:
                    ToastUtils.showMsg(getApplicationContext(),"验证失败，请重新输入验证码");
                    break;
            }

        }
    };

    //ButterKnife
    @OnClick({R.id.btn_get_code,R.id.btn_register_confirm})
    public void onClick(View view){
        int id=view.getId();
        switch (id){
            case R.id.btn_get_code:
                String phone=etPhoneRegister.getText().toString();
                if (StringUtils.isRightPhone(phone)){
                    sendCode("+86",phone);
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"请输入正确的手机号");
                }
                break;
            case R.id.btn_register_confirm:
                String phone1=etPhoneRegister.getText().toString();
                if (StringUtils.isRightPhone(phone1)&&!etUserNameRegister.getText().toString().isEmpty()&&!etPasswordRegister.getText().toString().isEmpty()){
                    submitCode("+86",phone1,etCode.getText().toString());
                    //测试用
                    //Message message=new Message();
                    //message.what=SUBMIT_CODE_SUCCESS;
                    //handler.sendMessage(message);
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"输入有误，请重新输入！");
                }
                break;
        }
    }






    //发送验证码
    private void sendCode( String country,String phone) {
        // 注册一个事件回调，用于处理发送验证码操作的结果,不在主线程里面，不能进行UI操作,使用Handler
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                //子线程中不能更新UI
                if (result == SMSSDK.RESULT_COMPLETE) {
                    Message message=new Message();
                    message.what=GET_CODE_SUCCESS;
                    handler.sendMessage(message);
                } else{
                    Message message=new Message();
                    message.what=GET_CODE_ERROR;
                    handler.sendMessage(message);
                }
            }
        });
        SMSSDK.getVerificationCode(country, phone);
    }

    // 提交验证码
    public void submitCode(String country, String phone, String code) {
        // 注册一个事件回调，用于处理提交验证码操作的结果
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                //子线程中不能更新UI
                if (result == SMSSDK.RESULT_COMPLETE) {
                    Message message=new Message();
                    message.what=SUBMIT_CODE_SUCCESS;
                    handler.sendMessage(message);
                }else {
                    Message message=new Message();
                    message.what=SUBMIT_CODE_ERROR;
                    handler.sendMessage(message);
                }
            }
        });
        // 触发操作
        SMSSDK.submitVerificationCode(country, phone, code);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //用完回调要注销掉，否则可能会出现内存泄露
        SMSSDK.unregisterAllEventHandler();
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
