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
import android.widget.ListView;
import android.widget.TextView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.activity.AddNewDemandActivity;
import com.wanghongyun.secondhandtrade.activity.LoginActivity;
import com.wanghongyun.secondhandtrade.adapter.DemandListAdapter;
import com.wanghongyun.secondhandtrade.base.BaseFragment;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.DemandList;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.DemandHelper;
import com.wanghongyun.secondhandtrade.utils.IntentUtils;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;
import com.wanghongyun.secondhandtrade.utils.UserUtils;
import com.wanghongyun.secondhandtrade.widget.MyListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by 李维升 on 2018/4/25.
 */

public class DemandsFragment extends BaseFragment implements AdapterView.OnItemClickListener,SwipeRefreshLayout.OnRefreshListener {
    private View mainView;
    private Unbinder unbinder;

    private List<DemandList.DemandDetails> demandDetailsList=new ArrayList<>();
    private DemandListAdapter demandListAdapter;

    @BindView(R.id.tool_bar_demands)
    Toolbar toolbar;
    @BindView(R.id.tv_mid_title)
    TextView tvMidTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.lv_demands)
    MyListView listView;
    @BindView(R.id.fab_demands)
    FloatingActionButton floatingActionButton;
    @BindView(R.id.srl_demands)
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mainView=inflater.inflate(R.layout.fragment_demands,null,false);
        unbinder= ButterKnife.bind(this,mainView);
        initView();
        return mainView;
    }
    private void initView(){
        //设置ToolBar
        setHasOptionsMenu(true);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        //设置Title
        tvMidTitle.setText("求购");
        //设置返回键不可见
        ivBack.setVisibility(View.GONE);
        swipeRefreshLayout.setOnRefreshListener(this);

        demandListAdapter=new DemandListAdapter(getContext(),demandDetailsList);
        listView.setAdapter(demandListAdapter);
        this.initData();

    }
    private void initData(){
        Retrofit retrofit=RetrofitUtils.getRetrofit(NetConstant.BASE_URL);
        DemandHelper demandHelper=retrofit.create(DemandHelper.class);
        Call<DemandList> call=demandHelper.getDemandListCall(0);
        call.enqueue(new Callback<DemandList>() {
            @Override
            public void onResponse(Call<DemandList> call, Response<DemandList> response) {
                if (response.isSuccessful()){
                    DemandList demandList=response.body();
                    demandDetailsList.clear();
                    demandDetailsList.addAll(demandList.demandDetailsList);
                    demandListAdapter.notifyDataSetChanged();
                }else {
                    ToastUtils.showMsg(getContext(),"请求失败");
                }
            }
            @Override
            public void onFailure(Call<DemandList> call, Throwable t) {
                ToastUtils.showMsg(getContext(),"服务器出错");
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
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @OnClick({R.id.fab_demands})
    public void OnClick(View view){
        if (UserUtils.isLogin(getContext())){
            IntentUtils.startActivity(getContext(), AddNewDemandActivity.class);
        }else {
            ToastUtils.showMsg(getContext(),"还没有登录哦");
            IntentUtils.startActivity(getContext(), LoginActivity.class);
        }

    }

    @Override
    public void onRefresh() {
        this.initData();
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        this.initData();
    }
}
