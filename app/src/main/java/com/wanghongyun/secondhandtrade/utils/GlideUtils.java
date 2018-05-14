package com.wanghongyun.secondhandtrade.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by 李维升 on 2018/5/14.
 */

public class GlideUtils {
    public static void loadImage(Context context, String url, ImageView imageView){
        Glide.with(context).load(url).into(imageView);
    }
}
