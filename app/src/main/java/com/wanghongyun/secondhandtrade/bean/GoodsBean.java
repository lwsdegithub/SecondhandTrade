package com.wanghongyun.secondhandtrade.bean;

/**
 * Created by 李维升 on 2018/4/27.
 */

public class GoodsBean {
    private int ID;
    private String goodsName;
    private String goodsDescriptions;
    private String goodsImageUrl;
    private String goodsCollectionCount;
    private String goodsCommentCount;

    public GoodsBean(int ID, String goodsName, String goodsDescriptions, String goodsImageUrl, String goodsCollectionCount, String goodsCommentCount) {
        this.ID = ID;
        this.goodsName = goodsName;
        this.goodsDescriptions = goodsDescriptions;
        this.goodsImageUrl = goodsImageUrl;
        this.goodsCollectionCount = goodsCollectionCount;
        this.goodsCommentCount = goodsCommentCount;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDescriptions() {
        return goodsDescriptions;
    }

    public void setGoodsDescriptions(String goodsDescriptions) {
        this.goodsDescriptions = goodsDescriptions;
    }

    public String getGoodsImageUrl() {
        return goodsImageUrl;
    }

    public void setGoodsImageUrl(String goodsImageUrl) {
        this.goodsImageUrl = goodsImageUrl;
    }

    public String getGoodsCollectionCount() {
        return goodsCollectionCount;
    }

    public void setGoodsCollectionCount(String goodsCollectionCount) {
        this.goodsCollectionCount = goodsCollectionCount;
    }

    public String getGoodsCommentCount() {
        return goodsCommentCount;
    }

    public void setGoodsCommentCount(String goodsCommentCount) {
        this.goodsCommentCount = goodsCommentCount;
    }
    @Override
    public String toString() {
        return "GoodsBean{" +
                "ID=" + ID +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDescriptions='" + goodsDescriptions + '\'' +
                ", goodsImageUrl='" + goodsImageUrl + '\'' +
                ", goodsCollectionCount='" + goodsCollectionCount + '\'' +
                ", goodsCommentCount='" + goodsCommentCount + '\'' +
                '}';
    }
}
