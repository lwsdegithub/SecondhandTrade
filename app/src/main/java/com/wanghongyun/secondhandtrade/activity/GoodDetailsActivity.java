package com.wanghongyun.secondhandtrade.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.wanghongyun.secondhandtrade.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 李维升 on 2018/5/1.
 */

public class GoodDetailsActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener {

    @BindView(R.id.tv_goods_details_goods_name)
    TextView tvGoodsDetailsGoodsName;
    @BindView(R.id.civ_user_head_image)
    CircleImageView civUserHeadImage;
    @BindView(R.id.tv_goods_details_user_name)
    TextView tvGoodsDetailsUserName;
    @BindView(R.id.btn_goods_details_send_msg)
    Button btnGoodsDetailsSendMsg;
    @BindView(R.id.tv_goods_details_description)
    TextView tvGoodsDetailsDescription;
    @BindView(R.id.iv_goods_details_goods_photo_1)
    ImageView ivGoodsDetailsGoodsPhoto1;
    @BindView(R.id.iv_goods_details_goods_photo_2)
    ImageView ivGoodsDetailsGoodsPhoto2;
    @BindView(R.id.iv_goods_details_goods_photo_3)
    ImageView ivGoodsDetailsGoodsPhoto3;
    @BindView(R.id.lv_goods_details_comment)
    ListView lvGoodsDetailsComment;
    @BindView(R.id.srl_good_details)
    SwipeRefreshLayout srlGoodDetails;
    @BindView(R.id.et_input_comment)
    EditText etInputComment;
    @BindView(R.id.btn_send_comment)
    Button btnSendComment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);
        ButterKnife.bind(this);
        this.initView();
    }

    private void initView() {
        //设置ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("物品详情");
    }



    @OnClick({R.id.btn_goods_details_send_msg, R.id.iv_goods_details_goods_photo_1, R.id.iv_goods_details_goods_photo_2, R.id.iv_goods_details_goods_photo_3, R.id.btn_send_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_goods_details_send_msg:
                break;
            case R.id.iv_goods_details_goods_photo_1:
                break;
            case R.id.iv_goods_details_goods_photo_2:
                break;
            case R.id.iv_goods_details_goods_photo_3:
                break;
            case R.id.btn_send_comment:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.good_details_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        return true;
    }
}
