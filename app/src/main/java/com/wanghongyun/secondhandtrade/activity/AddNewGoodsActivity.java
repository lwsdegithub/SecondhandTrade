package com.wanghongyun.secondhandtrade.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.GoodsHelper;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.UserHelper;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.SharedPreferencesUtils;
import com.wanghongyun.secondhandtrade.utils.StringUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by 李维升 on 2018/5/13.
 */

public class AddNewGoodsActivity extends AppCompatActivity {
    private static final int CHOOSE_PHOTOS=0;

    private ArrayList<Uri> uris=new ArrayList<>();

    @BindView(R.id.tv_add_goods_name)
    EditText tvAddGoodsName;
    @BindView(R.id.tv_add_goods_description)
    EditText tvAddGoodsDescription;
    @BindView(R.id.btn_add_goods_photo)
    Button btnAddGoodsPhoto;
    @BindView(R.id.tv_add_goods_price)
    EditText tvAddGoodsPrice;
    @BindView(R.id.btn_add_confirm)
    Button btnAddConfirm;
    @BindView(R.id.iv_1)
    ImageView imageView1;
    @BindView(R.id.iv_2)
    ImageView imageView2;
    @BindView(R.id.iv_3)
    ImageView imageView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_goods);
        ButterKnife.bind(this);
        this.initView();
    }

    private void initView() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("添加新物品");
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
    //选择图片
    private void selectPhotos(){
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
                .maxSelectable(3)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .imageEngine(new GlideEngine())
                .thumbnailScale(0.85f)
                .forResult(CHOOSE_PHOTOS);
    }
    //选择图片后回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CHOOSE_PHOTOS&&resultCode==RESULT_OK){
            uris= (ArrayList<Uri>) Matisse.obtainResult(data);
            loadImage();
        }
    }
    //加载图片
    private void loadImage(){
        if (!uris.isEmpty()){
            if (uris.size()==1){
                imageView1.setVisibility(View.VISIBLE);
                Glide.with(this).load(uris.get(0)).into(imageView1);
            }else if (uris.size()==2){
                imageView1.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.VISIBLE);
                Glide.with(this).load(uris.get(0)).into(imageView1);
                Glide.with(this).load(uris.get(1)).into(imageView2);
            }else if (uris.size()==3){
                imageView1.setVisibility(View.VISIBLE);
                imageView2.setVisibility(View.VISIBLE);
                imageView3.setVisibility(View.VISIBLE);
                Glide.with(this).load(uris.get(0)).into(imageView1);
                Glide.with(this).load(uris.get(1)).into(imageView2);
                Glide.with(this).load(uris.get(2)).into(imageView3);
            }
        }
    }

    @OnClick({R.id.btn_add_goods_photo,R.id.btn_add_confirm})
    public void OnClick(View view){
        if (view.getId()==R.id.btn_add_confirm){
            String goodsName=tvAddGoodsName.getText().toString();
            String description=tvAddGoodsDescription.getText().toString();
            String price=tvAddGoodsPrice.getText().toString();
            if (!uris.isEmpty()){
                if (goodsName.isEmpty()||description.isEmpty()||price.isEmpty()){
                    ToastUtils.showMsg(this,"信息有问题，请检查");
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"正在上传，请稍等");
                    upload(goodsName,description,price);
                }
            }else {
                ToastUtils.showMsg(getApplicationContext(),"请至少选择一张图片！");
            }
        }else if (view.getId()==R.id.btn_add_goods_photo){
            selectPhotos();
        }
    }
    //上传
    private void upload(String goodsName,String description,String price){
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        //首先把得到的Uri转成绝对路径
        ArrayList<String> pathList=new ArrayList<>();
        if (!uris.isEmpty()){
            for (Uri uri:uris){
                pathList.add(StringUtils.getFilePathFromContentUri(uri,getContentResolver()));
            }
            //多张图片
            for (int i = 0; i < pathList.size(); i++) {
                File file = new File(pathList.get(i));//filePath 图片地址
                RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("image"+i, file.getName(), imageBody);//"imgfile"+i 后台接收图片流的参数名
            }
        }


        //其他的参数
        builder.addFormDataPart("USER_ID", String.valueOf(SharedPreferencesUtils.getData(this,SharedPreferencesUtils.USER,SharedPreferencesUtils.USER_ID,1)));
        builder.addFormDataPart("GOODS_NAME",goodsName);
        builder.addFormDataPart("DESCRIPTION",description);
        builder.addFormDataPart("PRICE",price);


        List<MultipartBody.Part> parts = builder.build().parts();
        Retrofit retrofit=RetrofitUtils.getRetrofit(NetConstant.BASE_URL);
        GoodsHelper goodsHelper=retrofit.create(GoodsHelper.class);
        goodsHelper.getAddGoodsCall(parts).enqueue(new Callback<Common>() {
            @Override
            public void onResponse(Call<Common> call, Response<Common> response) {
                if (response.isSuccessful()){
                    if (response.body().getStatus()==NetConstant.UPLOAD_SUCCESS){
                        ToastUtils.showMsg(getApplicationContext(),"上传成功");
                        AddNewGoodsActivity.this.finish();
                    }
                }else {
                    ToastUtils.showMsg(getApplicationContext(),"上传失败");
                }
            }

            @Override
            public void onFailure(Call<Common> call, Throwable t) {
                ToastUtils.showMsg(getApplicationContext(),"上传失败");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
