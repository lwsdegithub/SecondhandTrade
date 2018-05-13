package com.wanghongyun.secondhandtrade.bean;

public class Collection {
	private int collection_id;
	private int goods_id;
	private int user_id;
	
	public Collection() {
		super();
	}
	public Collection(int collection_id, int goods_id, int user_id) {
		super();
		this.collection_id = collection_id;
		this.goods_id = goods_id;
		this.user_id = user_id;
	}
	public int getCollection_id() {
		return collection_id;
	}
	public void setCollection_id(int collection_id) {
		this.collection_id = collection_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "Collection [collection_id=" + collection_id + ", goods_id=" + goods_id + ", user_id=" + user_id + "]";
	}
	
}
