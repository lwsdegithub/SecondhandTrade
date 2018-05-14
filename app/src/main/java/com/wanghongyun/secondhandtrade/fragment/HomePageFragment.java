package com.wanghongyun.secondhandtrade.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.activity.AddNewGoodsActivity;
import com.wanghongyun.secondhandtrade.activity.GoodDetailsActivity;
import com.wanghongyun.secondhandtrade.adapter.GoodsListAdapter;
import com.wanghongyun.secondhandtrade.base.BaseFragment;
import com.wanghongyun.secondhandtrade.bean.Goods;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.GoodsList;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.GoodsHelper;
import com.wanghongyun.secondhandtrade.helper.ImageFlipperLoader;
import com.wanghongyun.secondhandtrade.utils.IntentUtils;
import com.wanghongyun.secondhandtrade.widget.MyListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 李维升 on 2018/4/25.
 */

public class HomePageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,OnBannerListener,AdapterView.OnItemClickListener{
    private View mainView;
    private Unbinder unbinder;
    private ArrayList<String> imageLists=new ArrayList<>();
    private ArrayList<Goods> goodsList=new ArrayList<>();
    private GoodsListAdapter adapter;

    //控件绑定
    @BindView(R.id.tool_bar_home_page) Toolbar toolbar;
    @BindView(R.id.tv_mid_title) TextView tvMidTitle;
    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.srl_home_page) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.lv_home_page) MyListView goodsListView;
    @BindView(R.id.banner_image_flipper) Banner bannerImageFlipper;
    @BindView(R.id.fab_home_page) FloatingActionButton floatingActionButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView=inflater.inflate(R.layout.fragment_home_page,null,false);
        unbinder= ButterKnife.bind(this,mainView);
        initView();
        return mainView;
    }
    private void initView(){

        //设置ToolBar
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //设置Title
        tvMidTitle.setText("首页");
        //设置返回键不可见
        ivBack.setVisibility(View.GONE);
        swipeRefreshLayout.setOnRefreshListener(this);

        //配置BannerImageFlipper
        imageLists.add("http://e.hiphotos.baidu.com/image/pic/item/5d6034a85edf8db1effe3ec40a23dd54574e74b9.jpg");
        imageLists.add("http://a.hiphotos.baidu.com/image/pic/item/d439b6003af33a8709c74f2bc55c10385343b55d.jpg");
        imageLists.add("http://e.hiphotos.baidu.com/image/pic/item/cb8065380cd7912333d46579af345982b2b78083.jpg");
        bannerImageFlipper.setImages(imageLists);
        bannerImageFlipper.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        bannerImageFlipper.setImageLoader(new ImageFlipperLoader());
        bannerImageFlipper.setBannerAnimation(Transformer.DepthPage);
        bannerImageFlipper.setIndicatorGravity(BannerConfig.CENTER);
        bannerImageFlipper.start();
        //配置ListView
        adapter=new GoodsListAdapter(getActivity(),goodsList);
        goodsListView.setOnItemClickListener(this);
        goodsListView.setAdapter(adapter);
        this.initData();
    }
    //初始化数据
    private void initData(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(NetConstant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        final GoodsHelper goodsHelper=retrofit.create(GoodsHelper.class);
        Call<GoodsList> goodsListCall=goodsHelper.getCall();
        goodsListCall.enqueue(new Callback<GoodsList>() {
            @Override
            public void onResponse(Call<GoodsList> call, Response<GoodsList> response) {
                GoodsList temp =  response.body();
                goodsList.clear();
                goodsList.addAll(temp.goodsList);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<GoodsList> call, Throwable t) {
            }
        });
    }

    //在这里进行数据刷新操作，包括轮播图片数据源，帖子数据源
    @Override
    public void onRefresh() {
        //轮播数据
        //帖子数据
        this.initData();
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }
    //在这里进行图片轮播组件点击跳转操作
    @Override
    public void OnBannerClick(int position) {

    }

    @OnClick({R.id.fab_home_page})
    public void onClick(View view){
        IntentUtils.startActivity(getActivity(), AddNewGoodsActivity.class);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除ButterKnife绑定
        unbinder.unbind();
    }
    //
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        IntentUtils.startActivityWithInt(getActivity(), GoodDetailsActivity.class,"GOODS_ID",goodsList.get(i).getGoods_id());
    }
}
