package com.wanghongyun.secondhandtrade.helper.gsonBeans;

import com.wanghongyun.secondhandtrade.bean.Goods;

import java.util.List;

/**
 * Created by 李维升 on 2018/5/10.
 */

public class GoodsList {

    public int status;
    public List<Goods> goodsList;

    public GoodsList(int status, List<Goods> goodsList) {
        this.status = status;
        this.goodsList=goodsList;
    }

}