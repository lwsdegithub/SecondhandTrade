package com.wanghongyun.secondhandtrade.adapter.Mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.bean.Collection;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Mine.MyCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李维升 on 2018/5/23.
 */

public class MyCollectionAdapter extends BaseAdapter implements View.OnClickListener {
    private ArrayList<MyCollection.SimpleGoods> collectionList;
    private Context context;

    public MyCollectionAdapter(ArrayList<MyCollection.SimpleGoods> collectionList, Context context) {
        this.collectionList = collectionList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return collectionList.size();
    }

    @Override
    public Object getItem(int i) {
        return collectionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=new ViewHolder();
        if (view==null){
            view=View.inflate(context, R.layout.item_list_common,null);
            viewHolder.textView=view.findViewById(R.id.tv_item_common);
            viewHolder.button=view.findViewById(R.id.btn_item_common);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        if (!collectionList.isEmpty()){
            viewHolder.textView.setText(collectionList.get(i).getGoods_name());
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
