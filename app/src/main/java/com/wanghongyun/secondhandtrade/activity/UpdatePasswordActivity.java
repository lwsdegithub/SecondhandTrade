package com.wanghongyun.secondhandtrade.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.UserHelper;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;
import com.wanghongyun.secondhandtrade.utils.UserUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 李维升 on 2018/5/29.
 */

public class UpdatePasswordActivity extends AppCompatActivity {

    @BindView(R.id.et_update_password)
    EditText etUpdatePassword;
    @BindView(R.id.et_update_password_confirm)
    EditText etUpdatePasswordConfirm;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("修改密码");
        actionBar.setDisplayHomeAsUpEnabled(true);
        initData();
    }

    private void initData() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @OnClick(R.id.btn_confirm)
    public void onViewClicked(View view) {
        if (view.getId()==R.id.btn_confirm){
            if (!etUpdatePassword.getText().toString().isEmpty()&&!etUpdatePasswordConfirm.getText().toString().isEmpty()){
                if (etUpdatePassword.getText().toString().equals(etUpdatePasswordConfirm.getText().toString())){
                    Map<String,String> map=new HashMap<>();
                    map.put("TYPE",6+"");
                    map.put("USER_ID", UserUtils.getUserId(this)+"");
                    map.put("PASSWORD",etUpdatePassword.getText().toString());

                    RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(UserHelper.class).getUpdateUserCall(map).enqueue(new Callback<Common>() {
                        @Override
                        public void onResponse(Call<Common> call, Response<Common> response) {
                            if (response.isSuccessful()){
                                if (response.body().getStatus()==NetConstant.OK){
                                    ToastUtils.showMsg(getApplicationContext(),"修改成功,正在重新登陆");
                                    //重新保存用户数据
                                    UserUtils.updateSpData(getApplicationContext());
                                    UpdatePasswordActivity.this.finish();
                                }
                            }else {
                                ToastUtils.showMsg(getApplicationContext(),"请求失败");
                            }
                        }
                        @Override
                        public void onFailure(Call<Common> call, Throwable t) {
                            ToastUtils.showMsg(getApplicationContext(),"修改失败");
                        }
                    });
                }else {
                    ToastUtils.showMsg(this,"两次密码输入不一致");
                }
            }else {
                ToastUtils.showMsg(this,"密码为空");
            }

        }

    }
}
