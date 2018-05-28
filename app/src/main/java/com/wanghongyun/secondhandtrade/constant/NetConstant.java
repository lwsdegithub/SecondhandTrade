package com.wanghongyun.secondhandtrade.constant;

/**
 * Created by 李维升 on 2018/5/10.
 *
 */

public class NetConstant {
    public static final String BASE_HOST="http://192.168.137.1:8080/";
    public static final String BASE_URL = BASE_HOST+"SecondhandTradeServer/";
    public static final String BASE_GOODS_PHOTOS_URL =BASE_URL+"GoodsPhotos/";
    public static final String BASE_HEAD_ICON_URL =BASE_URL+"HeadIcons/";

    public static final int OK=2007;
    public static final int ERROR=2015;
    //账户已经存在
    public static final int ACCOUNT_IS_EXISTED=2008;
    //注册成功
    public static final int REGISTER_SUCCESS=2009;
    //账户不存在
    public static final int ACCOUNT_IS_NOT_EXISTED=2010;
    //密码不正确
    public static final int PASSWORD_ERROR=2011;
    //登陆成功
    public static final int LOGIN_SUCCESS=2012;
    //上传成功
    public static final int UPLOAD_SUCCESS=2013;
    //已经收藏过了
    public static final int HAS_COLLECTED=2014;
}
