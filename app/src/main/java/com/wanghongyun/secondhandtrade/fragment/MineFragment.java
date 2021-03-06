package com.wanghongyun.secondhandtrade.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.activity.AboutActivity;
import com.wanghongyun.secondhandtrade.activity.LoginActivity;
import com.wanghongyun.secondhandtrade.activity.Mine.MyCollectionActivity;
import com.wanghongyun.secondhandtrade.activity.Mine.MyDemandsActivity;
import com.wanghongyun.secondhandtrade.activity.Mine.MyDetailsActivity;
import com.wanghongyun.secondhandtrade.activity.Mine.MyGoodsActivity;
import com.wanghongyun.secondhandtrade.base.BaseFragment;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.utils.GlideUtils;
import com.wanghongyun.secondhandtrade.utils.IntentUtils;
import com.wanghongyun.secondhandtrade.utils.SharedPreferencesUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;
import com.wanghongyun.secondhandtrade.utils.UserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 李维升 on 2018/4/25.
 */

public class MineFragment extends BaseFragment implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    private View mainView;
    private Unbinder unbinder;

    private View navHeadView;
    //nav的头部组件
    private CircleImageView headIcon;
    private TextView userName;

    @BindView(R.id.tool_bar_mine)
    Toolbar toolbar;
    @BindView(R.id.tv_mid_title)
    TextView tvMidTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.nav_fragment_mine)
    NavigationView navFragmentMine;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_mine, null, false);
        unbinder = ButterKnife.bind(this, mainView);
        initView();
        return mainView;
    }

    private void initView() {
        //设置ToolBar
        setHasOptionsMenu(true);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        //设置Title
        tvMidTitle.setText("我的");
        //设置返回键不可见
        ivBack.setVisibility(View.GONE);
        //获取头组件
        navHeadView=navFragmentMine.getHeaderView(0);
        headIcon=navHeadView.findViewById(R.id.civ_fragment_mine_user_head_image);
        userName=navHeadView.findViewById(R.id.tv_fragment_mine_user_name);
        //
        navHeadView.setOnClickListener(this);
        navFragmentMine.setNavigationItemSelectedListener(this);

        this.initData();
    }
    private void initData(){

    }
    //登陆成功后，在这里更新UI
    @Override
    public void onResume() {
        super.onResume();
        if (UserUtils.isLogin(getContext())){
            UserUtils.updateSpData(getContext());
            GlideUtils.loadImage(getContext(),NetConstant.BASE_HEAD_ICON_URL+UserUtils.getUserHeadIcon(getContext()),headIcon);
            userName.setText(UserUtils.getUserName(getContext()));
        }else {
            Glide.with(getContext()).load(R.mipmap.ic_launcher).into(headIcon);
            userName.setText("未登录");
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除绑定
        unbinder.unbind();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.about){
            IntentUtils.startActivity(getContext(),AboutActivity.class);
        }
        if (UserUtils.isLogin(getContext())) {
            switch (id) {
                case R.id.collection:
                    IntentUtils.startActivity(getContext(), MyCollectionActivity.class);
                    break;
                case R.id.goods:
                    IntentUtils.startActivity(getContext(), MyGoodsActivity.class);
                    break;
                case R.id.demand:
                    IntentUtils.startActivity(getContext(), MyDemandsActivity.class);
                    break;
            }
        }
        else {
            ToastUtils.showMsg(getContext(),"还没有登录哦");
            IntentUtils.startActivity(getContext(),LoginActivity.class);
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if (UserUtils.isLogin(getContext())){
            IntentUtils.startActivity(getContext(), MyDetailsActivity.class);
        }else {
            ToastUtils.showMsg(getContext(),"亲，还没有登录哦");
            IntentUtils.startActivity(getContext(), LoginActivity.class);
        }
    }
}
