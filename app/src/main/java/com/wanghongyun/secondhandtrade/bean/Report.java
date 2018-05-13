package com.wanghongyun.secondhandtrade.bean;

public class Report {
	private int report_id;
	private int user_id;
	private int goods_id;
	private String report_content;
	private int did_handled;
	
	public Report() {
		super();
	}
	public Report(int report_id, int user_id, int goods_id, String report_content, int did_handled) {
		super();
		this.report_id = report_id;
		this.user_id = user_id;
		this.goods_id = goods_id;
		this.report_content = report_content;
		this.did_handled = did_handled;
	}
	public int getReport_id() {
		return report_id;
	}
	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getReport_content() {
		return report_content;
	}
	public void setReport_content(String report_content) {
		this.report_content = report_content;
	}
	public int getDid_handled() {
		return did_handled;
	}
	public void setDid_handled(int did_handled) {
		this.did_handled = did_handled;
	}
	@Override
	public String toString() {
		return "Report [report_id=" + report_id + ", user_id=" + user_id + ", goods_id=" + goods_id
				+ ", report_content=" + report_content + ", did_handled=" + did_handled + "]";
	}
	
}
