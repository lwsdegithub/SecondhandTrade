package com.wanghongyun.secondhandtrade.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.utils.GlideUtils;

/**
 * Created by 李维升 on 2018/5/14.
 */

public class ImageDialog extends AlertDialog{
    private String url;
    private Context context;
    public ImageDialog(Context context,String url) {
        super(context);
        this.context=context;
        this.url=url;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_image);
        ImageView imageView=findViewById(R.id.iv_dialog);
        GlideUtils.loadImage(context, NetConstant.BASE_GOODS_PHOTOS_URL+url,imageView);
    }

}
