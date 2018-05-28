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
import com.wanghongyun.secondhandtrade.helper.gsonBeans.CommentDetails;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.UserHelper;
import com.wanghongyun.secondhandtrade.utils.DateUtils;
import com.wanghongyun.secondhandtrade.utils.GlideUtils;
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
    private List<CommentDetails> commentDetailsList;
    private ViewHolder viewHolder;

    public CommentListAdapter(Context context, List<CommentDetails> commentBeans) {
        this.context = context;
        this.commentDetailsList = commentBeans;
    }

    @Override
    public int getCount() {
        return commentDetailsList.size();
    }

    @Override
    public Object getItem(int i) {
        return commentDetailsList.get(i);
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
        if (!commentDetailsList.isEmpty()){
            CommentDetails commentDetails=commentDetailsList.get(i);
            GlideUtils.loadImage(context,NetConstant.BASE_HEAD_ICON_URL+commentDetails.getHeadIcon(),viewHolder.headImage);
            viewHolder.userName.setText(commentDetails.getUserName());
            viewHolder.content.setText(commentDetails.getContent());
            viewHolder.time.setText(DateUtils.dateFormat1(commentDetails.getTime()));
        }
        return view;
    }
    private static class ViewHolder{
        CircleImageView headImage;
        TextView userName;
        TextView content;
        TextView time;
    }
}
