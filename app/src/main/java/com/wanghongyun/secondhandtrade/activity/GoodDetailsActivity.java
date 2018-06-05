package com.wanghongyun.secondhandtrade.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.adapter.CommentListAdapter;
import com.wanghongyun.secondhandtrade.bean.Goods;
import com.wanghongyun.secondhandtrade.bean.User;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.CommentDetails;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.GoodsDetails;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.CollectionHelper;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.CommentHelper;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.GoodsHelper;
import com.wanghongyun.secondhandtrade.utils.BundleUtils;
import com.wanghongyun.secondhandtrade.utils.GlideUtils;
import com.wanghongyun.secondhandtrade.utils.IntentUtils;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;
import com.wanghongyun.secondhandtrade.utils.UserUtils;
import com.wanghongyun.secondhandtrade.widget.MyListView;
import com.wanghongyun.secondhandtrade.widget.dialog.ImageDialog;
import com.wanghongyun.secondhandtrade.widget.dialog.ReportDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by 李维升 on 2018/5/1.
 */

public class GoodDetailsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private int GOODS_ID;

    private int USER_ID;
    private String phone;

    private CommentListAdapter commentListAdapter;
    private ArrayList<CommentDetails> commentDetailsArrayList=new ArrayList<>();

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
        commentListAdapter=new CommentListAdapter(this,commentDetailsArrayList);
        lvGoodsDetailsComment.setAdapter(commentListAdapter);
        this.initData();

    }
    //初始化数据
    private void initData(){
        Retrofit retrofit=RetrofitUtils.getRetrofit(NetConstant.BASE_URL);
        final GoodsHelper goodsHelper=retrofit.create(GoodsHelper.class);
        Call<GoodsDetails> goodsDetailsCall=goodsHelper.getGoodsDetailsCall(GOODS_ID);
        goodsDetailsCall.enqueue(new Callback<GoodsDetails>() {
            @Override
            public void onResponse(Call<GoodsDetails> call, Response<GoodsDetails> response) {
                if (response.isSuccessful()) {
                    GoodsDetails goodsDetails = response.body();
                    Goods goods = goodsDetails.goods;
                    User user = goodsDetails.user;
                    //填充数据
                    USER_ID=user.getId();
                    phone=user.getPhone();
                    tvGoodsDetailsUserName.setText(user.getUserName());
                    tvGoodsDetailsGoodsName.setText(goods.getGoods_name());
                    tvGoodsDetailsDescription.setText(goods.getGoods_description());
                    GlideUtils.loadImage(getApplicationContext(), NetConstant.BASE_HEAD_ICON_URL + user.getHeadIcon(), civUserHeadImage);
                    String[] photos = goods.getGoods_photo().split(",");
                    //加载物品图片
                    if (!photos[0].isEmpty()) {
                        if (photos.length==1){
                            ivGoodsDetailsGoodsPhoto1.setVisibility(View.VISIBLE);
                            url1 = photos[0];
                            GlideUtils.loadImage(getApplicationContext(), NetConstant.BASE_GOODS_PHOTOS_URL + url1, ivGoodsDetailsGoodsPhoto1);
                        }else if (photos.length==2){
                            ivGoodsDetailsGoodsPhoto1.setVisibility(View.VISIBLE);
                            ivGoodsDetailsGoodsPhoto2.setVisibility(View.VISIBLE);
                            url1 = photos[0];
                            url2 = photos[1];
                            GlideUtils.loadImage(getApplicationContext(), NetConstant.BASE_GOODS_PHOTOS_URL + url1, ivGoodsDetailsGoodsPhoto1);
                            GlideUtils.loadImage(getApplicationContext(), NetConstant.BASE_GOODS_PHOTOS_URL + url2, ivGoodsDetailsGoodsPhoto2);

                        }else if (photos.length==3){
                            ivGoodsDetailsGoodsPhoto1.setVisibility(View.VISIBLE);
                            ivGoodsDetailsGoodsPhoto2.setVisibility(View.VISIBLE);
                            ivGoodsDetailsGoodsPhoto3.setVisibility(View.VISIBLE);
                            url1 = photos[0];
                            url2 = photos[1];
                            url3 = photos[2];
                            GlideUtils.loadImage(getApplicationContext(), NetConstant.BASE_GOODS_PHOTOS_URL + url1, ivGoodsDetailsGoodsPhoto1);
                            GlideUtils.loadImage(getApplicationContext(), NetConstant.BASE_GOODS_PHOTOS_URL + url2, ivGoodsDetailsGoodsPhoto2);
                            GlideUtils.loadImage(getApplicationContext(), NetConstant.BASE_GOODS_PHOTOS_URL + url3, ivGoodsDetailsGoodsPhoto3);
                        }
                    }
                    //填充评论数据
                    commentDetailsArrayList.clear();
                    commentDetailsArrayList.addAll(goodsDetails.commentDetailsList);
                    commentListAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<GoodsDetails> call, Throwable t) {
                ToastUtils.showMsg(getApplicationContext(),"加载失败！");
            }
        });
    }



    @OnClick({R.id.btn_goods_details_send_msg, R.id.iv_goods_details_goods_photo_1, R.id.iv_goods_details_goods_photo_2, R.id.iv_goods_details_goods_photo_3, R.id.btn_send_comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_goods_details_send_msg:
                showConnectionDialog();
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
                if (!etInputComment.getText().toString().isEmpty()){
                    etInputComment.clearFocus();
                    sendComment();
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"输入为空！");
                }
                break;
        }
    }
    //联系
    private void showConnectionDialog(){
        new AlertDialog.Builder(this).setTitle("联系物品主人").setMessage("选择联系方式").setPositiveButton("电话", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                IntentUtils.Call(GoodDetailsActivity.this,phone);
            }
        }).setNegativeButton("短信", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                IntentUtils.SendMsg(GoodDetailsActivity.this,phone);
            }
        }).show();
    }
    //添加评论
    private void sendComment(){
        RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(CommentHelper.class).getAddCommentCall(0,GOODS_ID,UserUtils.getUserId(this),etInputComment.getText().toString()).enqueue(new Callback<Common>() {
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==NetConstant.OK){
                        ToastUtils.showMsg(getApplicationContext(),"发送成功");
                        etInputComment.setText("");
                        initData();
                    }
                }
            }
            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                ToastUtils.showMsg(getApplicationContext(),"失败");
            }
        });
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
            //举报
            case R.id.item_report:
                new ReportDialog(this,GOODS_ID).show();
                return true;
            //添加收藏
            case R.id.item_collect:
                RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(CollectionHelper.class).getAddCollectionCall(0,GOODS_ID, UserUtils.getUserId(getApplicationContext())).enqueue(new Callback<Common>() {
                    @Override
                    public void onResponse(Call<Common> call, Response<Common> response) {
                        if (response.isSuccessful()){
                            if (response.body().getStatus()==NetConstant.OK){
                                ToastUtils.showMsg(getApplicationContext(),"收藏成功");
                            }else if (response.body().getStatus()==NetConstant.HAS_COLLECTED){
                                ToastUtils.showMsg(getApplicationContext(),"收藏失败，您已经收藏过了");
                            }
                        }else {
                            ToastUtils.showMsg(getApplicationContext(),"收藏失败");
                        }
                    }
                    @Override
                    public void onFailure(Call<Common> call, Throwable t) {
                        ToastUtils.showMsg(getApplicationContext(),"收藏失败");
                    }
                });
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
