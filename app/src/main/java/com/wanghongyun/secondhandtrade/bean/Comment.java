package com.wanghongyun.secondhandtrade.bean;

public class Comment {
	private int comment_id;
	private int goods_id;
	private int user_id;
	private String comment_content;
	private String comment_time;

	public Comment() {
	}

	public Comment(int comment_id, int goods_id, int user_id, String comment_content, String comment_time) {
		this.comment_id = comment_id;
		this.goods_id = goods_id;
		this.user_id = user_id;
		this.comment_content = comment_content;
		this.comment_time = comment_time;
	}

	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
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
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getComment_time() {
		return comment_time;
	}
	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}
	@Override
	public String toString() {
		return "Comment [comment_id=" + comment_id + ", goods_id=" + goods_id + ", user_id=" + user_id
				+ ", comment_content=" + comment_content + ", comment_time=" + comment_time + "]";
	}
	
}
