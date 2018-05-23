package com.wanghongyun.secondhandtrade.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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

public class MessageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    private View mainView;
    private Unbinder unbinder;

    @BindView(R.id.tool_bar_message)
    Toolbar toolbar;
    @BindView(R.id.tv_mid_title)
    TextView tvMidTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.srl_message)
    SwipeRefreshLayout srlMessage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_message, null, false);
        unbinder = ButterKnife.bind(this, mainView);
        initView();
        return mainView;
    }

    private void initView() {
        //设置ToolBar
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        //设置Title
        tvMidTitle.setText("消息");
        //设置返回键不可见
        ivBack.setVisibility(View.GONE);
        srlMessage.setOnRefreshListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除绑定
        unbinder.unbind();
    }

    @Override
    public void onRefresh() {
        if (srlMessage.isRefreshing()){
            srlMessage.setRefreshing(false);
        }
    }
}
