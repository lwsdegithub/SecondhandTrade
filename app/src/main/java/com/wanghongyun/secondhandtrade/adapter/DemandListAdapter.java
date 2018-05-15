package com.wanghongyun.secondhandtrade.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.DemandList;
import com.wanghongyun.secondhandtrade.utils.DateUtils;
import com.wanghongyun.secondhandtrade.utils.GlideUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 李维升 on 2018/5/15.
 */

public class DemandListAdapter extends BaseAdapter implements View.OnClickListener {
    private Context context;
    private List<DemandList.DemandDetails> demandDetailsList;

    public DemandListAdapter(Context context, List<DemandList.DemandDetails> demandDetailsList) {
        this.context = context;
        this.demandDetailsList = demandDetailsList;
    }

    @Override
    public int getCount() {
        return demandDetailsList.size();
    }

    @Override
    public Object getItem(int i) {
        return demandDetailsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null){
            view=View.inflate(context, R.layout.item_list_demands,null);
            viewHolder=new ViewHolder();
            viewHolder.headIcon=view.findViewById(R.id.civ_item_demands_user_head_image);
            viewHolder.userName=view.findViewById(R.id.tv_item_demand_user_name);
            viewHolder.sendMsg=view.findViewById(R.id.btn_demand_send_msg);
            viewHolder.content=view.findViewById(R.id.tv_demand_content);
            viewHolder.time=view.findViewById(R.id.tv_demand_time);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        DemandList.DemandDetails demandDetails=demandDetailsList.get(i);
        //加载
        GlideUtils.loadImage(context, NetConstant.BASE_HEAD_ICON_URL+demandDetails.getUserHeadIcon(),viewHolder.headIcon);
        viewHolder.userName.setText(demandDetails.getUserName());
        viewHolder.content.setText(demandDetails.getContent());
        viewHolder.time.setText(DateUtils.dateFormat1(demandDetails.getTime()));
        viewHolder.sendMsg.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        ToastUtils.showMsg(context,"nihaoa");
    }

    private static class ViewHolder{
        CircleImageView headIcon;
        TextView userName;
        Button sendMsg;
        TextView content;
        TextView time;
    }
}
