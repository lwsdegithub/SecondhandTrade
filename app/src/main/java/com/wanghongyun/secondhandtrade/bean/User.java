package com.wanghongyun.secondhandtrade.bean;

public class User {
	private int user_id;
	private String user_name;
	private String phone;
	private String password;
	private String qq;
	private String create_time;
	private String head_icon;
	private int credit_score;
	
	public User() {
		super();
	}
	public User(int user_id, String user_name, String phone, String password, String qq, String create_time,
			String head_icon, int credit_score) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.phone = phone;
		this.password = password;
		this.qq = qq;
		this.create_time = create_time;
		this.head_icon = head_icon;
		this.credit_score = credit_score;
	}
	public int getId() {
		return user_id;
	}
	public void setId(int id) {
		this.user_id = id;
	}
	public String getUserName() {
		return user_name;
	}
	public void setUserName(String userName) {
		this.user_name = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getCreateTime() {
		return create_time;
	}
	public void setCreateTime(String createTime) {
		this.create_time = createTime;
	}
	public String getHeadIcon() {
		return head_icon;
	}
	public void setHeadIcon(String headIcon) {
		this.head_icon = headIcon;
	}
	public int getCreditScore() {
		return credit_score;
	}
	public void setCreditScore(int creditScore) {
		this.credit_score = creditScore;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", phone=" + phone + ", password=" + password
				+ ", qq=" + qq + ", create_time=" + create_time + ", head_icon=" + head_icon + ", credit_score="
				+ credit_score + "]";
	}
	
}
