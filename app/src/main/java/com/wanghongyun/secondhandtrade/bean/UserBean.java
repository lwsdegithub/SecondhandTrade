package com.wanghongyun.secondhandtrade.bean;

/**
 * Created by 李维升 on 2018/5/7.
 */

public class UserBean {
    private int userID;
    private String userName;
    private String phone;
    private String password;
    private String qq;
    private String createTime;
    private String headIcon;//以字符串保存头像的URL
    private int creditScore;

    public UserBean() {
    }

    public UserBean(int userID, String userName, String phone, String password, String qq, String createTime, String headIcon, int creditScore) {
        this.userID = userID;
        this.userName = userName;
        this.phone = phone;
        this.password = password;
        this.qq = qq;
        this.createTime = createTime;
        this.headIcon = headIcon;
        this.creditScore = creditScore;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", qq='" + qq + '\'' +
                ", createTime='" + createTime + '\'' +
                ", headIcon='" + headIcon + '\'' +
                ", creditScore=" + creditScore +
                '}';
    }
}
