package com.wanghongyun.secondhandtrade.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.base.BaseFragment;
import com.wanghongyun.secondhandtrade.util.ImageFlipperLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 李维升 on 2018/4/25.
 */

public class HomePageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,OnBannerListener{
    private View mainView;
    private Unbinder unbinder;
    private ArrayList<String> imageLists=new ArrayList<>();
    @BindView(R.id.tool_bar_home_page) Toolbar toolbar;
    @BindView(R.id.tv_mid_title) TextView tvMidTitle;
    @BindView(R.id.iv_back) ImageView ivBack;
    @BindView(R.id.srl_home_page) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_home_page) RecyclerView recyclerView;
    @BindView(R.id.banner_image_flipper) Banner bannerImageFlipper;

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
        bannerImageFlipper.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        bannerImageFlipper.setImageLoader(new ImageFlipperLoader());
        bannerImageFlipper.setBannerAnimation(Transformer.DepthPage);
        bannerImageFlipper.setIndicatorGravity(BannerConfig.CENTER);
        bannerImageFlipper.setImages(imageLists);
        bannerImageFlipper.start();
    }


    //在这里进行数据刷新操作，包括轮播图片数据源，帖子数据源
    @Override
    public void onRefresh() {

        Toast.makeText(getContext(),"你好",Toast.LENGTH_LONG).show();
        imageLists.clear();
        imageLists.add("http://e.hiphotos.baidu.com/image/pic/item/5d6034a85edf8db1effe3ec40a23dd54574e74b9.jpg");
        imageLists.add("http://a.hiphotos.baidu.com/image/pic/item/d439b6003af33a8709c74f2bc55c10385343b55d.jpg");
        imageLists.add("http://e.hiphotos.baidu.com/image/pic/item/cb8065380cd7912333d46579af345982b2b78083.jpg");
        imageLists.add("http://e.hiphotos.baidu.com/image/pic/item/5d6034a85edf8db1effe3ec40a23dd54574e74b9.jpg");
        imageLists.add("http://a.hiphotos.baidu.com/image/pic/item/d439b6003af33a8709c74f2bc55c10385343b55d.jpg");
        imageLists.add("http://e.hiphotos.baidu.com/image/pic/item/cb8065380cd7912333d46579af345982b2b78083.jpg");
        swipeRefreshLayout.setRefreshing(false);
        bannerImageFlipper.setImages(imageLists);
        bannerImageFlipper.start();

    }
    //在这里进行图片轮播组件点击跳转操作
    @Override
    public void OnBannerClick(int position) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除ButterKnife绑定
        unbinder.unbind();
    }
}
