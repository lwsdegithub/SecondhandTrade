package com.wanghongyun.secondhandtrade.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.bean.Goods;
import com.wanghongyun.secondhandtrade.constant.NetConstant;

import java.util.List;

/**
 * Created by 李维升 on 2018/5/13.
 */

public class GoodsListAdapter extends BaseAdapter {
    private Context context;
    private List<Goods> goodsList;

    public GoodsListAdapter(Context context, List<Goods> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Object getItem(int i) {
        return goodsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder myViewHolder=null;
        if (view==null){
            view=View.inflate(context, R.layout.item_list_goods,null);
            myViewHolder=new MyViewHolder();
            myViewHolder.tvGoodsName=view.findViewById(R.id.tv_goods_name);
            myViewHolder.tvGoodsDescription=view.findViewById(R.id.tv_goods_description);
            myViewHolder.ivGoodsImage=view.findViewById(R.id.iv_goods_image);
            myViewHolder.tvGoodsCollectionCount=view.findViewById(R.id.tv_goods_collection_count);
            myViewHolder.tvGoodsCommentCount=view.findViewById(R.id.tv_goods_comment_count);
            myViewHolder.tvGoodsPrice=view.findViewById(R.id.tv_goods_price);
            view.setTag(myViewHolder);
        }else {
            myViewHolder= (MyViewHolder) view.getTag();
        }
        Goods goods=goodsList.get(i);
        myViewHolder.tvGoodsName.setText(goods.getGoods_name());
        myViewHolder.tvGoodsDescription.setText(goods.getGoods_description());
        //如果没有图片，设置图片不可见，如果有，通过Glide设置加载image
            if (goods.getGoods_photo().isEmpty()) {
                myViewHolder.ivGoodsImage.setVisibility(View.GONE);
            } else {
                //默认加载第一个
                Glide.with(context).load(NetConstant.BaseGoodsPhotosUrl+goods.getGoods_photo().split(",")[0]).into(myViewHolder.ivGoodsImage);
            }
        myViewHolder.tvGoodsCollectionCount.setText(goods.getCollection_count());
        myViewHolder.tvGoodsCommentCount.setText(goods.getComment_count());
        myViewHolder.tvGoodsPrice.setText(goods.getGoods_price());
        return view;
    }
    static class MyViewHolder{
        TextView tvGoodsName=null;
        TextView tvGoodsDescription=null;
        ImageView ivGoodsImage=null;
        TextView tvGoodsCollectionCount=null;
        TextView tvGoodsCommentCount=null;
        TextView tvGoodsPrice=null;
    }
}
