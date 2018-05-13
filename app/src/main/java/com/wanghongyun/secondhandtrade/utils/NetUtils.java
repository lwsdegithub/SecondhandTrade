package com.wanghongyun.secondhandtrade.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 李维升 on 2018/5/5.
 */

public class NetUtils {
    /* 网络状态 */
    public static boolean isnet = true;
    public static enum nettype
    {
        wifi, cmnet, cmwap, nonenet
    }

    /**
     * @方法说明:判断wifi网络是否可用
     * @方法名称:iswificonnected
     * @param context
     * @return
     * @返回值:boolean
     */
    public static boolean isWifiConnected(Context context)
    {
        if (context != null)
        {
            ConnectivityManager mconnectivitymanager = (ConnectivityManager) context
                    .getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo mwifinetworkinfo = mconnectivitymanager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mwifinetworkinfo != null)
            {
                return mwifinetworkinfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * @方法说明:判断mobile网络是否可用
     * @方法名称:ismobileconnected
     * @param context
     * @return
     * @返回值:boolean
     */
    public static boolean ismobileconnected(Context context)
    {
        if (context != null)
        {
            ConnectivityManager mconnectivitymanager = (ConnectivityManager) context
                    .getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo mmobilenetworkinfo = mconnectivitymanager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mmobilenetworkinfo != null)
            {
                return mmobilenetworkinfo.isAvailable();
            }
        }
        return false;
    }



    /**
     * @方法说明:判断是否有网络连接
     * @方法名称:isnetworkconnected
     * @param context
     * @return
     * @返回值:boolean
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mconnectivitymanager = (ConnectivityManager) context
                    .getSystemService(context.CONNECTIVITY_SERVICE);
            NetworkInfo mnetworkinfo = mconnectivitymanager
                    .getActiveNetworkInfo();
            if (mnetworkinfo != null) {
                return mnetworkinfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * @方法说明:网络是否可用
     * @方法名称:isnetworkavailable
     * @param context
     * @return
     * @返回值:boolean
     */
    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager mgr = (ConnectivityManager) context
                .getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        if (info != null)
        {
            for (int i = 0; i < info.length; i++)
            {
                if (info[i].getState() == NetworkInfo.State.CONNECTED)
                {
                    return true;
                }
            }
        }
        return false;
    }

}
