package com.wanghongyun.secondhandtrade.helper.gsonBeans.Mine;

import java.util.List;

/**
 * Created by 李维升 on 2018/5/23.
 */

public class MyCollection {
    public List<Integer> collectionIdList;
    public List<SimpleGoods> simpleGoodsList;

    public List<SimpleGoods> getSimpleGoodsList() {
        return simpleGoodsList;
    }

    public void setSimpleGoodsList(List<SimpleGoods> simpleGoodsList) {
        this.simpleGoodsList = simpleGoodsList;
    }

    public static class SimpleGoods{

        public int getGoods_id() {
            return goods_id;
        }
        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }
        public String getGoods_name() {
            return goods_name;
        }
        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }
        int goods_id;
        String goods_name;
    }
}
