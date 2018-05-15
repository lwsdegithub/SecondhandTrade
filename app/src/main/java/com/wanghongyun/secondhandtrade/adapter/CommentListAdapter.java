package com.wanghongyun.secondhandtrade.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.bean.Comment;
import com.wanghongyun.secondhandtrade.bean.User;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.UserHelper;
import com.wanghongyun.secondhandtrade.utils.DateUtils;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by 李维升 on 2018/5/4.
 */

public class CommentListAdapter extends BaseAdapter {
    private Context context;
    private List<Comment> commentList;
    private ViewHolder viewHolder;

    public CommentListAdapter(Context context, List<Comment> commentBeans) {
        this.context = context;
        this.commentList = commentBeans;
    }

    @Override
    public int getCount() {
        return commentList.size();
    }

    @Override
    public Object getItem(int i) {
        return commentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder=null;
        if (view==null){
            view=View.inflate(context, R.layout.item_list_comment,null);
            viewHolder=new ViewHolder();
            viewHolder.headImage=view.findViewById(R.id.civ_item_comment_user_head_image);
            viewHolder.userName=view.findViewById(R.id.tv_item_comment_user_name);
            viewHolder.content=view.findViewById(R.id.tv_item_comment_content);
            viewHolder.time=view.findViewById(R.id.tv_item_comment_time);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        Comment commentBean=commentList.get(i);

        //加载UserName，Glide加载头像,
        Retrofit retrofit=RetrofitUtils.getRetrofit(NetConstant.BASE_URL);
        UserHelper userHelper=retrofit.create(UserHelper.class);
        Call<User> userCall=userHelper.getUserByIdCall(0,commentBean.getUser_id());
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user=response.body();
                Glide.with(context).load(NetConstant.BASE_HEAD_ICON_URL +user.getHeadIcon()).into(viewHolder.headImage);
                viewHolder.userName.setText(user.getUserName());
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });

        viewHolder.content.setText(commentBean.getComment_content());
        viewHolder.time.setText(DateUtils.dateFormat1(commentBean.getComment_time()));
        return view;
    }
    private static class ViewHolder{
        CircleImageView headImage;
        TextView userName;
        TextView content;
        TextView time;
    }
}
