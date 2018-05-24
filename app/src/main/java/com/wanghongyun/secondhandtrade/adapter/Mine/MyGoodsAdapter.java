package com.wanghongyun.secondhandtrade.adapter.Mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.bean.Collection;
import com.wanghongyun.secondhandtrade.bean.Goods;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Mine.MyCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李维升 on 2018/5/23.
 */

public class MyGoodsAdapter extends BaseAdapter implements View.OnClickListener {
    private ArrayList<MyCollection.SimpleGoods> goodsList;
    private Context context;

    public MyGoodsAdapter(ArrayList<MyCollection.SimpleGoods> goodsList, Context context) {
        this.goodsList = goodsList;
        this.context = context;
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
        MyCollectionAdapter.ViewHolder viewHolder=new MyCollectionAdapter.ViewHolder();
        if (view==null){
            view=View.inflate(context, R.layout.item_list_common,null);
            viewHolder.textView=view.findViewById(R.id.tv_item_common);
            viewHolder.button=view.findViewById(R.id.btn_item_common);
            view.setTag(viewHolder);
        }else {
            viewHolder= (MyCollectionAdapter.ViewHolder) view.getTag();
        }
        if (!goodsList.isEmpty()){
            viewHolder.textView.setText(goodsList.get(i).getGoods_name());
            viewHolder.button.setText("删除");
            viewHolder.button.setOnClickListener(this);
        }

        return view;
    }

    @Override
    public void onClick(View view) {

    }

    static class ViewHolder{
        TextView textView;
        Button button;
    }
}
