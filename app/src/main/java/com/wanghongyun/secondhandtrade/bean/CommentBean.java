package com.wanghongyun.secondhandtrade.bean;

/**
 * Created by 李维升 on 2018/5/4.
 */

public class CommentBean {
    private int userID;
    private String commentContent;
    private String commentTime;

    public CommentBean() {
    }

    public CommentBean(int userID, String commentContent, String sommentTime) {
        this.userID = userID;
        this.commentContent = commentContent;
        this.commentTime = sommentTime;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getSommentTime() {
        return commentTime;
    }

    public void setSommentTime(String sommentTime) {
        this.commentTime = sommentTime;
    }

    @Override
    public String toString() {
        return "CommentBean{" +
                "userID=" + userID +
                ", commentContent='" + commentContent + '\'' +
                ", sommentTime='" + commentTime + '\'' +
                '}';
    }
}
