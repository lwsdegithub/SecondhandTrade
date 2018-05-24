package com.wanghongyun.secondhandtrade.activity.Mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.adapter.Mine.MyDemandAdapter;
import com.wanghongyun.secondhandtrade.bean.Demand;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.DemandHelper;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;
import com.wanghongyun.secondhandtrade.utils.UserUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 李维升 on 2018/5/23.
 */

public class MyDemandsActivity extends AppCompatActivity {
    private ArrayList<Demand> demandList=new ArrayList<>();
    private MyDemandAdapter myDemandAdapter;
    @BindView(R.id.lv_my_demand)
    ListView lvMyDemand;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_demands);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("我的需求");
        myDemandAdapter=new MyDemandAdapter(demandList,this);
        lvMyDemand.setAdapter(myDemandAdapter);
        initData();
    }

    private void initData() {
        RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(DemandHelper.class).getDemandListByUserId(4, UserUtils.getUserId(this)).enqueue(new Callback<List<Demand>>() {
            @Override
            public void onResponse(Call<List<Demand>> call, Response<List<Demand>> response) {
                if (response.isSuccessful()){
                    if (!response.body().isEmpty()){
                        demandList.clear();
                        demandList.addAll(response.body());
                        myDemandAdapter.notifyDataSetChanged();
                    }
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"请求失败");

                }
            }

            @Override
            public void onFailure(Call<List<Demand>> call, Throwable t) {
                ToastUtils.showMsg(getApplicationContext(),"请求失败");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
