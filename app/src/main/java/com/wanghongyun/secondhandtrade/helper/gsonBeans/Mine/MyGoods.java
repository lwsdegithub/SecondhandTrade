package com.wanghongyun.secondhandtrade.helper.gsonBeans.Mine;

import java.util.List;
import com.wanghongyun.secondhandtrade.bean.Goods;

public class MyGoods {
	public List<MyCollection.SimpleGoods> simpleGoodsList;

	public List<MyCollection.SimpleGoods> getSimpleGoodsList() {
		return simpleGoodsList;
	}
	public void setSimpleGoodsList(List<MyCollection.SimpleGoods> simpleGoodsList) {
		this.simpleGoodsList = simpleGoodsList;
	}
}
