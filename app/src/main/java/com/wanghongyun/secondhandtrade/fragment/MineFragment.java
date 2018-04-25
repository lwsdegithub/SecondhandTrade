package com.wanghongyun.secondhandtrade.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 李维升 on 2018/4/25.
 */

public class MineFragment extends BaseFragment {
    private View mainView;
    private Unbinder unbinder;

    @BindView(R.id.tool_bar_mine)
    Toolbar toolbar;
    @BindView(R.id.tv_mid_title)
    TextView tvMidTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mainView=inflater.inflate(R.layout.fragment_mine,null,false);
        unbinder= ButterKnife.bind(this,mainView);
        initView();
        return mainView;
    }
    private void initView(){
        //设置ToolBar
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //设置Title
        tvMidTitle.setText("我的");
        //设置返回键不可见
        ivBack.setVisibility(View.GONE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除绑定
        unbinder.unbind();
    }
}
