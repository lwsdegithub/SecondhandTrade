package com.wanghongyun.secondhandtrade.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.bean.Other.MsgBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 李维升 on 2018/5/25.
 */

public class MsgListAdapter extends BaseAdapter {
    //
    private ArrayList<MsgBean> msgBeanList;
    private Context context;

    public MsgListAdapter(ArrayList<MsgBean> msgBeanList, Context context) {
        this.msgBeanList = msgBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (!msgBeanList.isEmpty()){
            return msgBeanList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return msgBeanList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null){
            view=View.inflate(context, R.layout.item_list_msg,null);
            viewHolder.textView=view.findViewById(R.id.tv_msg_user_name);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        if (!msgBeanList.isEmpty()){
            viewHolder.textView.setText(msgBeanList.get(i).getUserName());
        }
        return view;
    }

    private static class ViewHolder{
        TextView textView;
    }
}
