package com.wanghongyun.secondhandtrade.activity;

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
import android.widget.TextView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.adapter.CommentListAdapter;
import com.wanghongyun.secondhandtrade.bean.Comment;
import com.wanghongyun.secondhandtrade.bean.Goods;
import com.wanghongyun.secondhandtrade.bean.User;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.GoodsDetails;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.GoodsHelper;
import com.wanghongyun.secondhandtrade.utils.BundleUtils;
import com.wanghongyun.secondhandtrade.utils.GlideUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;
import com.wanghongyun.secondhandtrade.widget.MyListView;
import com.wanghongyun.secondhandtrade.widget.dialog.ImageDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 李维升 on 2018/5/1.
 */

public class GoodDetailsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private int GOODS_ID;
    private CommentListAdapter commentListAdapter;
    private ArrayList<Comment> commentList=new ArrayList<>();

    private String url1;
    private String url2;
    private String url3;

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
    MyListView lvGoodsDetailsComment;
    @BindView(R.id.srl_good_details)
    SwipeRefreshLayout srlGoodDetails;
    @BindView(R.id.et_input_comment)
    EditText etInputComment;
    @BindView(R.id.btn_send_comment)
    Button btnSendComment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GOODS_ID= BundleUtils.getInt(this,"GOODS_ID");
        setContentView(R.layout.activity_goods_details);
        ButterKnife.bind(this);
        this.initView();
    }

    private void initView() {
        //
        srlGoodDetails.setOnRefreshListener(this);
        //设置ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("物品详情");
        //ListView
        commentListAdapter=new CommentListAdapter(this,commentList);
        lvGoodsDetailsComment.setAdapter(commentListAdapter);
        this.initData();

    }
    //初始化数据
    private void initData(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(NetConstant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        final GoodsHelper goodsHelper=retrofit.create(GoodsHelper.class);
        Call<GoodsDetails> goodsDetailsCall=goodsHelper.getGoodsDetailsCall(GOODS_ID);
        goodsDetailsCall.enqueue(new Callback<GoodsDetails>() {
            @Override
            public void onResponse(Call<GoodsDetails> call, Response<GoodsDetails> response) {
                if (response.isSuccessful()){
                    GoodsDetails goodsDetails=response.body();
                    Goods goods=goodsDetails.goods;
                    User user=goodsDetails.user;
                    //填充数据
                    tvGoodsDetailsUserName.setText(user.getUserName());
                    tvGoodsDetailsGoodsName.setText(goods.getGoods_name());
                    tvGoodsDetailsDescription.setText(goods.getGoods_description());
                    GlideUtils.loadImage(getApplicationContext(),NetConstant.BASE_HEAD_ICON_URL+user.getHeadIcon(),civUserHeadImage);
                    String [] photos=goods.getGoods_photo().split(",");
                    if (photos.length>=1&&!photos[0].isEmpty()){
                        url1=photos[0];
                    GlideUtils.loadImage(getApplicationContext(),NetConstant.BASE_GOODS_PHOTOS_URL+url1,ivGoodsDetailsGoodsPhoto1);}
                    if (photos.length>=2&&!photos[1].isEmpty()){
                        url2=photos[1];
                    GlideUtils.loadImage(getApplicationContext(),NetConstant.BASE_GOODS_PHOTOS_URL+url2,ivGoodsDetailsGoodsPhoto2);}
                    if (photos.length>=3&&!photos[2].isEmpty()){
                        url3=photos[2];
                    GlideUtils.loadImage(getApplicationContext(),NetConstant.BASE_GOODS_PHOTOS_URL+url3,ivGoodsDetailsGoodsPhoto3);}
                    //填充评论数据
                    commentList.clear();
                    commentList.addAll(goodsDetails.comments);
                    commentListAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<GoodsDetails> call, Throwable t) {
            }
        });
    }



    @OnClick({R.id.btn_goods_details_send_msg, R.id.iv_goods_details_goods_photo_1, R.id.iv_goods_details_goods_photo_2, R.id.iv_goods_details_goods_photo_3, R.id.btn_send_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_goods_details_send_msg:
                break;
            case R.id.iv_goods_details_goods_photo_1:
                new ImageDialog(this,url1).show();
                break;
            case R.id.iv_goods_details_goods_photo_2:
                new ImageDialog(this,url2).show();
                break;
            case R.id.iv_goods_details_goods_photo_3:
                new ImageDialog(this,url3).show();
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.item_report:
                ToastUtils.showMsg(this,"dddd");
                return true;
            case R.id.item_collect:
                ToastUtils.showMsg(this,"dddd");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //返回键
    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onRefresh() {
        this.initData();
        if (srlGoodDetails.isRefreshing()){
            srlGoodDetails.setRefreshing(false);
        }
    }
}
