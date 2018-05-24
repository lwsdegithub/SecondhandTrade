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
import com.wanghongyun.secondhandtrade.adapter.Mine.MyCollectionAdapter;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.GoodsDetails;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Mine.MyCollection;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.CollectionHelper;
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

public class MyCollectionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ArrayList<MyCollection.SimpleGoods> simpleGoodsList=new ArrayList<>();
    MyCollectionAdapter myCollectionAdapter;
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

        myCollectionAdapter=new MyCollectionAdapter(simpleGoodsList,this);
        lvMyCollection.setAdapter(myCollectionAdapter);
        lvMyCollection.setOnItemClickListener(this);
        initData();
    }

    private void initData() {
        RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(CollectionHelper.class).getMyCollectionCall(2, UserUtils.getUserId(this)).enqueue(new Callback<MyCollection>() {
            @Override
            public void onResponse(Call<MyCollection> call, Response<MyCollection> response) {
                if (response.isSuccessful()){
                    simpleGoodsList.clear();
                    simpleGoodsList.addAll(response.body().getSimpleGoodsList());
                    myCollectionAdapter.notifyDataSetChanged();
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"请求失败");
                }
            }
            @Override
            public void onFailure(Call<MyCollection> call, Throwable t) {
                ToastUtils.showMsg(getApplicationContext(),"请求失败");
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        IntentUtils.startActivityWithInt(this, GoodDetailsActivity.class,"GOODS_ID",simpleGoodsList.get(i).getGoods_id());
    }
}
