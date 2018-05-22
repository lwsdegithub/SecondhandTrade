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
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.DemandHelper;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;
import com.wanghongyun.secondhandtrade.utils.UserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by 李维升 on 2018/5/15.
 */

public class AddNewDemandActivity extends AppCompatActivity {

    @BindView(R.id.et_demand)
    EditText etDemand;
    @BindView(R.id.btn_add_confirm)
    Button btnAddConfirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_demands);
        ButterKnife.bind(this);
        this.initView();
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("添加新需求");
        actionBar.setDisplayHomeAsUpEnabled(true);

        this.initData();
    }
    @OnClick({R.id.btn_add_confirm})
    public void OnClick(View view){
        if (view.getId()==R.id.btn_add_confirm){
            this.upLoad();
        }
    }
    private void upLoad(){
        if (!etDemand.getText().toString().isEmpty()){
            Retrofit retrofit= RetrofitUtils.getRetrofit(NetConstant.BASE_URL);
            DemandHelper demandHelper=retrofit.create(DemandHelper.class);
            demandHelper.getAddDemandCall(UserUtils.getUserId(this),etDemand.getText().toString()).enqueue(new Callback<Common>() {
                @Override
                public void onResponse(Call<Common> call, Response<Common> response) {
                    if (response.isSuccessful()){
                        if (response.body().getStatus()==NetConstant.UPLOAD_SUCCESS){
                            ToastUtils.showMsg(getApplicationContext(),"上传成功");
                            AddNewDemandActivity.this.finish();
                        }
                    }else {
                        ToastUtils.showMsg(getApplicationContext(),"上传失败");
                    }
                }
                @Override
                public void onFailure(Call<Common> call, Throwable t) {
                    ToastUtils.showMsg(getApplicationContext(),"上传失败");
                }
            });
        }else {
            ToastUtils.showMsg(getApplicationContext(),"输入为空");
        }
    }


    private void initData() {
    }


    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}

