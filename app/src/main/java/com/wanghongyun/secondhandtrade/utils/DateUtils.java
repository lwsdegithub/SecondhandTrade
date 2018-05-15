package com.wanghongyun.secondhandtrade.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 李维升 on 2018/5/14.
 */

public class DateUtils {

    //格式 24小时制：2016-07-06 09:39:58,并且去掉时间
    public static String dateFormat1(String s){
        Date date=new Date();
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            date=dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFormat.format(date);
    }
}
