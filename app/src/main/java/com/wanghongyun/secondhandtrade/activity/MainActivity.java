package com.wanghongyun.secondhandtrade.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.heima.tabview.library.TabView;
import com.heima.tabview.library.TabViewChild;
import com.heima.tabview.library.TabViewUtil;
import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.base.BaseActivity;
import com.wanghongyun.secondhandtrade.fragment.DemandsFragment;
import com.wanghongyun.secondhandtrade.fragment.HomePageFragment;
import com.wanghongyun.secondhandtrade.fragment.MessageFragment;
import com.wanghongyun.secondhandtrade.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    private List<TabViewChild> tabViewChildList;
    private HomePageFragment homePageFragment;
    private DemandsFragment demandsFragment;
    private MineFragment mineFragment;
    private MessageFragment messageFragment;

    @BindView(R.id.main_tab_view) TabView mainTabView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }
    private void initView(){
        homePageFragment=new HomePageFragment();
        demandsFragment=new DemandsFragment();
        messageFragment=new MessageFragment();
        mineFragment=new MineFragment();
        tabViewChildList=new ArrayList<>();
        tabViewChildList.add(new TabViewChild(R.drawable.ic_home_page_sel,R.drawable.ic_home_page_unsel,"首页",homePageFragment));
        tabViewChildList.add(new TabViewChild(R.drawable.ic_demands_sel,R.drawable.ic_demands_unsel,"需求",demandsFragment));
        tabViewChildList.add(new TabViewChild(R.drawable.ic_message_sel,R.drawable.ic_message_unsel,"消息",messageFragment));
        tabViewChildList.add(new TabViewChild(R.drawable.ic_mine_sel,R.drawable.ic_mine_unsel,"我的",mineFragment));
        mainTabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
    }

}
