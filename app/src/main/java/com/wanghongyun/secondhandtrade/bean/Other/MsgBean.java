package com.wanghongyun.secondhandtrade.bean.Other;

/**
 * Created by 李维升 on 2018/5/25.
 */

public class MsgBean {
    private int userId;
    private String userName;

    public MsgBean(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
