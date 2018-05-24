package com.wanghongyun.secondhandtrade.activity.Mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.activity.GoodDetailsActivity;
import com.wanghongyun.secondhandtrade.adapter.Mine.MyGoodsAdapter;
import com.wanghongyun.secondhandtrade.bean.Goods;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Mine.MyCollection;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Mine.MyGoods;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.UserHelper;
import com.wanghongyun.secondhandtrade.utils.IntentUtils;
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

public class MyGoodsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<MyCollection.SimpleGoods> goodsList=new ArrayList<>();
    private MyGoodsAdapter myGoodsAdapter;
    @BindView(R.id.lv_my_goods)
    ListView lvMyGoods;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_goods);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("我的发布");


        myGoodsAdapter=new MyGoodsAdapter(goodsList,this);
        lvMyGoods.setAdapter(myGoodsAdapter);
        lvMyGoods.setOnItemClickListener(this);
        initData();
    }

    private void initData() {
        RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(UserHelper.class).getMyGoodsCall(3, UserUtils.getUserId(this)).enqueue(new Callback<MyGoods>() {
            @Override
            public void onResponse(Call<MyGoods> call, Response<MyGoods> response) {
                if (response.isSuccessful()){
                    goodsList.clear();
                    goodsList.addAll(response.body().getSimpleGoodsList());
                    myGoodsAdapter.notifyDataSetChanged();
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"请求失败");
                }
            }

            @Override
            public void onFailure(Call<MyGoods> call, Throwable t) {
                ToastUtils.showMsg(getApplicationContext(),"请求失败");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        IntentUtils.startActivityWithInt(this, GoodDetailsActivity.class,"GOODS_ID",goodsList.get(i).getGoods_id());
    }
}
