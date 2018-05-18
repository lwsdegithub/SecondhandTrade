package com.wanghongyun.secondhandtrade.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.activity.LoginActivity;
import com.wanghongyun.secondhandtrade.activity.MyDetailsActivity;
import com.wanghongyun.secondhandtrade.base.BaseFragment;
import com.wanghongyun.secondhandtrade.bean.User;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.UserHelper;
import com.wanghongyun.secondhandtrade.utils.GlideUtils;
import com.wanghongyun.secondhandtrade.utils.IntentUtils;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.SharedPreferencesUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        Log.e("oncreaste","oncreateView");
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
        navHeadView.setOnClickListener(this);

        this.initData();
    }
    private void initData(){

    }
    //登陆成功后，在这里更新UI
    @Override
    public void onResume() {
        super.onResume();
        if ((boolean)SharedPreferencesUtils.getData(getContext(),SharedPreferencesUtils.USER,SharedPreferencesUtils.IS_LOGIN,false)){
            Retrofit retrofit=RetrofitUtils.getRetrofit(NetConstant.BASE_URL);
            UserHelper userHelper=retrofit.create(UserHelper.class);
            Call<User> userCall=userHelper.getUserByPhone("1",(String) SharedPreferencesUtils.getData(getContext(),SharedPreferencesUtils.USER,SharedPreferencesUtils.PHONE,"1"));
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user=response.body();
                    GlideUtils.loadImage(getContext(),NetConstant.BASE_HEAD_ICON_URL+user.getHeadIcon(),headIcon);
                    userName.setText(user.getUserName());
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                }
            });
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

        return false;
    }

    @Override
    public void onClick(View view) {
        if ((Boolean) SharedPreferencesUtils.getData(getContext(),SharedPreferencesUtils.USER,SharedPreferencesUtils.IS_LOGIN,false)){
            IntentUtils.startActivity(getContext(), MyDetailsActivity.class);
        }else {
            ToastUtils.showMsg(getContext(),"亲，还没有登录哦");
            IntentUtils.startActivity(getContext(), LoginActivity.class);
        }
    }
}
