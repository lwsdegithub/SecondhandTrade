package com.wanghongyun.secondhandtrade.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 李维升 on 2018/4/27.
 */

public class ToastUtils {
    public static void showMsg(Context context, String showContent) {
        Toast.makeText(context, showContent, Toast.LENGTH_SHORT).show();
    }

    public static void showMsgLong(Context context, String showContent) {
        Toast.makeText(context, showContent, Toast.LENGTH_LONG).show();
    }
}
