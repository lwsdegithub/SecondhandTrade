package com.wanghongyun.secondhandtrade.bean;

public class Goods {
	private int goods_id;
	private int user_id;
	private String goods_name;
	private String goods_price;
	private String build_time;
	private String goods_photo;//以逗号隔开
	private String goods_description;
	private String collection_count;
	private String comment_count;
	
	public Goods() {
		super();
	}
	public Goods(int goods_id, int user_id, String goods_name, String goods_price, String build_time,
			String goods_photo, String goods_description, String collection_count, String comment_count) {
		super();
		this.goods_id = goods_id;
		this.user_id = user_id;
		this.goods_name = goods_name;
		this.goods_price = goods_price;
		this.build_time = build_time;
		this.goods_photo = goods_photo;
		this.goods_description = goods_description;
		this.collection_count = collection_count;
		this.comment_count = comment_count;
	}
	@Override
	public String toString() {
		return "Goods [goods_id=" + goods_id + ", user_id=" + user_id + ", goods_name=" + goods_name + ", goods_price="
				+ goods_price + ", build_time=" + build_time + ", goods_photo=" + goods_photo + ", goods_description="
				+ goods_description + ", collection_count=" + collection_count + ", comment_count=" + comment_count
				+ "]";
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
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goods_price) {
		this.goods_price = goods_price;
	}
	public String getBuild_time() {
		return build_time;
	}
	public void setBuild_time(String build_time) {
		this.build_time = build_time;
	}
	public String getGoods_photo() {
		return goods_photo;
	}
	public void setGoods_photo(String goods_photo) {
		this.goods_photo = goods_photo;
	}
	public String getGoods_description() {
		return goods_description;
	}
	public void setGoods_description(String goods_description) {
		this.goods_description = goods_description;
	}
	public String getCollection_count() {
		return collection_count;
	}
	public void setCollection_count(String collection_count) {
		this.collection_count = collection_count;
	}
	public String getComment_count() {
		return comment_count;
	}
	public void setComment_count(String comment_count) {
		this.comment_count = comment_count;
	}
}
