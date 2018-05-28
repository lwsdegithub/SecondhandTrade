package com.wanghongyun.secondhandtrade.helper.gsonBeans;

import com.wanghongyun.secondhandtrade.bean.Comment;
import com.wanghongyun.secondhandtrade.bean.Goods;
import com.wanghongyun.secondhandtrade.bean.User;

import java.util.List;

/**
 * Created by 李维升 on 2018/5/14.
 */

public class GoodsDetails {
    public Goods goods;
    public User user;
    public List<CommentDetails> commentDetailsList;

    public GoodsDetails(Goods goods, User user, List<CommentDetails> comments) {
        super();
        this.goods = goods;
        this.user = user;
        this.commentDetailsList = comments;
    }
}
