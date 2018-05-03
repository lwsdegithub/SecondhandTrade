package com.wanghongyun.secondhandtrade.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.bean.GoodsBean;

import java.util.List;

/**
 * Created by 李维升 on 2018/4/27.
 */

public class GoodsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<GoodsBean> goodsBeanList;
    private mViewHolder mViewHolder;
    private OnItemClickListener onItemClickListener;
    public GoodsRecyclerAdapter(Context context, List<GoodsBean> goodsBeanList) {
        this.context = context;
        this.goodsBeanList = goodsBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_recycler_goods,parent,false);
        mViewHolder = new mViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        GoodsBean goodsBean=goodsBeanList.get(position);
        mViewHolder.tvGoodsName.setText(goodsBean.getGoodsName());
        mViewHolder.tvGoodsDescription.setText(goodsBean.getGoodsDescriptions());
        //如果没有图片，设置图片不可见，如果有，通过Glide设置加载image
        if (goodsBean.getGoodsImageUrl().isEmpty()){
            mViewHolder.ivGoodsImage.setVisibility(View.GONE);
        }else {
            Glide.with(context).load(goodsBean.getGoodsImageUrl()).into(mViewHolder.ivGoodsImage);
        }
        mViewHolder.tvGoodsCollectionCount.setText(goodsBean.getGoodsCollectionCount());
        mViewHolder.tvGoodsCommentCount.setText(goodsBean.getGoodsCommentCount());
        if (onItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.OnItemClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return goodsBeanList.size();
    }

    //viewHolder
    static class mViewHolder extends RecyclerView.ViewHolder{
        TextView tvGoodsName;
        TextView tvGoodsDescription;
        ImageView ivGoodsImage;
        TextView tvGoodsCollectionCount;
        TextView tvGoodsCommentCount;
        public mViewHolder(View itemView) {
            super(itemView);
            tvGoodsName=itemView.findViewById(R.id.tv_goods_name);
            tvGoodsDescription=itemView.findViewById(R.id.tv_goods_description);
            ivGoodsImage=itemView.findViewById(R.id.iv_goods_image);
            tvGoodsCollectionCount=itemView.findViewById(R.id.tv_goods_collection_count);
            tvGoodsCommentCount=itemView.findViewById(R.id.tv_goods_comment_count);
        }
    }

    //自定义RecyclerView的item点击接口
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
