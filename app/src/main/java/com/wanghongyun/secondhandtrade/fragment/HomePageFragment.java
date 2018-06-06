package com.wanghongyun.secondhandtrade.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.app.ActionBar;

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
import com.wanghongyun.secondhandtrade.activity.LoginActivity;
import com.wanghongyun.secondhandtrade.activity.SearchActivity;
import com.wanghongyun.secondhandtrade.adapter.GoodsListAdapter;
import com.wanghongyun.secondhandtrade.base.BaseFragment;
import com.wanghongyun.secondhandtrade.bean.Goods;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.GoodsList;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.GoodsHelper;
import com.wanghongyun.secondhandtrade.helper.ImageFlipperLoader;
import com.wanghongyun.secondhandtrade.utils.IntentUtils;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;
import com.wanghongyun.secondhandtrade.utils.UserUtils;
import com.wanghongyun.secondhandtrade.widget.MyListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    //轮播图片数据源
    private List<HashMap<String,String>> imageFipperUrlList=new ArrayList<>();

    //控件绑定
    @BindView(R.id.tool_bar_home_page) Toolbar toolbar;
    @BindView(R.id.tv_mid_title) TextView tvMidTitle;
    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.tv_right_title) ImageView tvRightTitle;
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
        tvRightTitle.setBackground(getActivity().getResources().getDrawable(R.drawable.ic_search));
        //设置返回键不可见
        ivBack.setVisibility(View.GONE);

        swipeRefreshLayout.setOnRefreshListener(this);

        bannerImageFlipper.setOnBannerListener(this);
        //配置ListView
        adapter=new GoodsListAdapter(getActivity(),goodsList);
        goodsListView.setOnItemClickListener(this);
        goodsListView.setAdapter(adapter);
        this.initData();
    }
    //初始化数据
    private void initData(){
        RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(GoodsHelper.class).getCall().enqueue(new Callback<GoodsList>() {
            @Override
            public void onResponse(Call<GoodsList> call, Response<GoodsList> response) {
                if (response.isSuccessful()){
                    GoodsList temp =  response.body();
                    goodsList.clear();
                    imageFipperUrlList.clear();
                    imageLists.clear();
                    adapter.notifyDataSetChanged();
                    if (!temp.goodsList.isEmpty()){
                        //添加ImageFlipper
                        int i=0;
                        for (Goods goods:temp.goodsList){
                            HashMap<String,String> hashMap=new HashMap<>();
                            hashMap.put("GOODS_ID",goods.getGoods_id()+"");
                            hashMap.put("URL",NetConstant.BASE_GOODS_PHOTOS_URL+goods.getGoods_photo().split(",")[0]);
                            imageFipperUrlList.add(hashMap);
                            i++;
                            //最多加载4个
                            if (i==4){
                                break;
                            }
                        }
                        for (HashMap<String,String> map:imageFipperUrlList){
                            imageLists.add(map.get("URL"));
                        }
                        //配置BannerImageFlipper
                        bannerImageFlipper.setImages(imageLists);
                        bannerImageFlipper.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                        bannerImageFlipper.setImageLoader(new ImageFlipperLoader());
                        bannerImageFlipper.setBannerAnimation(Transformer.DepthPage);
                        bannerImageFlipper.setIndicatorGravity(BannerConfig.CENTER);
                        bannerImageFlipper.start();
                        //添加List
                        goodsList.addAll(temp.goodsList);
                        adapter.notifyDataSetChanged();
                    }
                }else {
                    ToastUtils.showMsg(getContext(),"服务器出错！！！");
                }
            }
            @Override
            public void onFailure(Call<GoodsList> call, Throwable t) {
                ToastUtils.showMsg(getContext(),"服务器出错！！！");
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
        IntentUtils.startActivityWithInt(getActivity(),GoodDetailsActivity.class,"GOODS_ID", Integer.parseInt(imageFipperUrlList.get(position).get("GOODS_ID")));
    }

    @OnClick({R.id.fab_home_page,R.id.tv_right_title})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.fab_home_page:
                if (UserUtils.isLogin(getActivity())){
                    IntentUtils.startActivity(getActivity(), AddNewGoodsActivity.class);
                }else {
                    ToastUtils.showMsg(getActivity(), "还没有登录哦");
                    IntentUtils.startActivity(getActivity(), LoginActivity.class);
                }
                break;
            case R.id.tv_right_title:
                if (UserUtils.isLogin(getActivity())){
                    IntentUtils.startActivity(getActivity(), SearchActivity.class);
                }else {
                    ToastUtils.showMsg(getActivity(), "还没有登录哦");
                    IntentUtils.startActivity(getActivity(), LoginActivity.class);
                }
        }
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
        if (UserUtils.isLogin(getActivity())){
            IntentUtils.startActivityWithInt(getActivity(), GoodDetailsActivity.class,"GOODS_ID",goodsList.get(i).getGoods_id());
        }
        else {
            ToastUtils.showMsg(getActivity(),"还没有登录哦");
            IntentUtils.startActivity(getActivity(), LoginActivity.class);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.initData();
    }
}
