package com.wanghongyun.secondhandtrade.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.bean.CommentBean;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 李维升 on 2018/5/4.
 */

public class CommentListAdapter extends BaseAdapter {
    private Context context;
    private List<CommentBean> commentBeans;

    public CommentListAdapter(Context context, List<CommentBean> commentBeans) {
        this.context = context;
        this.commentBeans = commentBeans;
    }

    @Override
    public int getCount() {
        return commentBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return commentBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommentBean commentBean=commentBeans.get(i);
        view=View.inflate(context, R.layout.item_list_comment,viewGroup);
        CircleImageView headImage=view.findViewById(R.id.civ_item_comment_user_head_image);
        TextView userName=view.findViewById(R.id.tv_item_comment_user_name);
        TextView content=view.findViewById(R.id.tv_item_comment_content);
        TextView time=view.findViewById(R.id.tv_item_comment_time);
        Glide.with(context).load(commentBean.getUserID()).into(headImage);
        userName.setText(commentBean.getUserID());
        content.setText(commentBean.getCommentContent());
        time.setText(commentBean.getSommentTime());
        return view;
    }
}
