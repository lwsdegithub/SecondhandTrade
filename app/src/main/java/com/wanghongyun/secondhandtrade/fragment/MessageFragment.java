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
import android.widget.ListView;
import android.widget.TextView;

import com.mob.imsdk.MobIM;
import com.mob.imsdk.MobIMCallback;
import com.mob.imsdk.MobIMMessageReceiver;
import com.mob.imsdk.model.IMConversation;
import com.mob.imsdk.model.IMMessage;
import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.adapter.MsgListAdapter;
import com.wanghongyun.secondhandtrade.base.BaseFragment;
import com.wanghongyun.secondhandtrade.bean.Other.MsgBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 李维升 on 2018/4/25.
 */

public class MessageFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,MobIMMessageReceiver {

    private View mainView;
    private Unbinder unbinder;

    private ArrayList<MsgBean> msgBeanList=new ArrayList<>();
    private MsgListAdapter msgListAdapter;

    @BindView(R.id.tool_bar_message)
    Toolbar toolbar;
    @BindView(R.id.tv_mid_title)
    TextView tvMidTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.srl_message)
    SwipeRefreshLayout srlMessage;
    @BindView(R.id.lv_msg)
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_message, null, false);
        unbinder = ButterKnife.bind(this, mainView);
        //设置监听
        MobIM.addMessageReceiver(this);
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

        msgListAdapter=new MsgListAdapter(msgBeanList,getContext());
        listView.setAdapter(msgListAdapter);
        initData();
    }
    //未实现
    private void initData(){
        MobIM.getChatManager().getAllLocalConversations(new MobIMCallback<List<IMConversation>>() {
            @Override
            public void onSuccess(List<IMConversation> imConversations) {
                for (IMConversation imConversation:imConversations){
                    imConversation.getLastMessage().getBody();
                }
            }
            @Override
            public void onError(int i, String s) {

            }
        });
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

    @Override
    public void onMessageReceived(List<IMMessage> list) {
        if (!list.isEmpty()){
            msgBeanList.clear();
            for (IMMessage imMessage:list){
                msgBeanList.add(new MsgBean(Integer.parseInt(imMessage.getFromUserInfo().getId()),imMessage.getFromUserInfo().getNickname()));
            }
        }
        msgListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        MobIM.removeMessageReceiver(this);
        super.onDestroy();
    }
}
