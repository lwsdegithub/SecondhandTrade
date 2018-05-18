package com.wanghongyun.secondhandtrade.activity;

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
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 李维升 on 2018/5/13.
 */

public class AddNewGoodsActivity extends AppCompatActivity {
    private static final int CHOOSE_PHOTOS=0;

    private ArrayList<Uri> uris;

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

        }else if (view.getId()==R.id.btn_add_goods_photo){
            selectPhotos();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
}
