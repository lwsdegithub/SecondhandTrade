package com.wanghongyun.secondhandtrade.helper.retrofitInterfaces;

import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 李维升 on 2018/5/14.
 */

public interface CommentHelper {
    //插入新评论，TYPE为0时，是插入方法，具体配置服务器有
    @GET("CommentSevlet")
    Call<Common> getAddCommentCall(@Query("TYPE") int type,@Query("GOODS_ID") int goodId,@Query("USER_ID") int userId,@Query("COMMENT_CONTENT") String content);
}
