package com.wanghongyun.secondhandtrade.adapter.Mine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.bean.Collection;
import com.wanghongyun.secondhandtrade.bean.Demand;

import java.util.List;

/**
 * Created by 李维升 on 2018/5/23.
 */

public class MyDemandAdapter extends BaseAdapter {
    private List<Demand> demandList;
    private Context context;
    @Override
    public int getCount() {

        return demandList.size();
    }

    @Override
    public Object getItem(int i) {
        return demandList.get(i);
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

        return view;
    }
    static class ViewHolder{
        TextView textView;
        Button button;
    }
}
