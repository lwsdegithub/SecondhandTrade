package com.wanghongyun.secondhandtrade.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.adapter.GoodsListAdapter;
import com.wanghongyun.secondhandtrade.bean.Goods;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.GoodsHelper;
import com.wanghongyun.secondhandtrade.utils.IntentUtils;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 李维升 on 2018/5/27.
 */

public class SearchActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    private View actionBarLayout;
    private EditText etSearch;
    private Button btnBack;

    private ArrayList<Goods> goodsArrayList=new ArrayList<>();
    private GoodsListAdapter goodsListAdapter;

    @BindView(R.id.lv_search)
    ListView listView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initView();
    }
    private void initView(){
        //设置ActionBar
        actionBarLayout=View.inflate(this,R.layout.activity_search_action_bar,null);
        etSearch=actionBarLayout.findViewById(R.id.et_search);
        btnBack=actionBarLayout.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(this);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(actionBarLayout);

        listView.setOnItemClickListener(this);
        goodsListAdapter=new GoodsListAdapter(this,goodsArrayList);
        listView.setAdapter(goodsListAdapter);
        initData();
    }
    private void initData(){
        String key=etSearch.getText().toString();
        if (!key.isEmpty()){
            RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(GoodsHelper.class).getSearchCall(key).enqueue(new Callback<List<Goods>>() {
                @Override
                public void onResponse(Call<List<Goods>> call, Response<List<Goods>> response) {
                    if (response.isSuccessful()){
                        if (response.body().isEmpty()){
                            ToastUtils.showMsg(getApplicationContext(),"没有找到您想要的物品");
                        }else {
                            goodsArrayList.clear();
                            goodsArrayList.addAll(response.body());
                            goodsListAdapter.notifyDataSetChanged();
                        }
                    }else {
                        ToastUtils.showMsg(getApplicationContext(),"查询失败");
                    }
                }
                @Override
                public void onFailure(Call<List<Goods>> call, Throwable t) {
                    ToastUtils.showMsg(getApplicationContext(),"查询失败");
                }
            });
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                if (!etSearch.getText().toString().isEmpty()){
                    ToastUtils.showMsg(getApplicationContext(),"查询中...");
                    initData();
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"输入为空");
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        IntentUtils.startActivityWithInt(this,GoodDetailsActivity.class,"GOODS_ID",goodsArrayList.get(i).getGoods_id());
    }
}
