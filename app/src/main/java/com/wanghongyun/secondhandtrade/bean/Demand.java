package com.wanghongyun.secondhandtrade.bean;

public class Demand {
	private int demand_id;
	private int user_id;
	private String demand_name;
	private String demand_content;
	private String deamnd_time;
	
	public Demand() {
		super();
	}
	public Demand(int demand_id, int user_id, String demand_name, String demand_content, String deamnd_time) {
		super();
		this.demand_id = demand_id;
		this.user_id = user_id;
		this.demand_name = demand_name;
		this.demand_content = demand_content;
		this.deamnd_time = deamnd_time;
	}
	public int getDemand_id() {
		return demand_id;
	}
	public void setDemand_id(int demand_id) {
		this.demand_id = demand_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getDemand_name() {
		return demand_name;
	}
	public void setDemand_name(String demand_name) {
		this.demand_name = demand_name;
	}
	public String getDemand_content() {
		return demand_content;
	}
	public void setDemand_content(String demand_content) {
		this.demand_content = demand_content;
	}
	public String getDeamnd_time() {
		return deamnd_time;
	}
	public void setDeamnd_time(String deamnd_time) {
		this.deamnd_time = deamnd_time;
	}
	@Override
	public String toString() {
		return "Demand [demand_id=" + demand_id + ", user_id=" + user_id + ", demand_name=" + demand_name
				+ ", demand_content=" + demand_content + ", deamnd_time=" + deamnd_time + "]";
	}
	
}
