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
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Mine.MyCollection;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.CollectionHelper;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 李维升 on 2018/5/23.
 */

public class MyCollectionAdapter extends BaseAdapter  {
    private List<Integer> collectionIdList;
    private List<MyCollection.SimpleGoods> simpleGoodsList;
    private Context context;

    public MyCollectionAdapter(List<Integer> collectionIdList,List<MyCollection.SimpleGoods> simpleGoodsList, Context context) {
        this.collectionIdList=collectionIdList;
        this.simpleGoodsList=simpleGoodsList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return simpleGoodsList.size();
    }

    @Override
    public Object getItem(int i) {
        return simpleGoodsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=new ViewHolder();
        if (view==null){
            view=View.inflate(context, R.layout.item_list_common,null);
            viewHolder.textView=view.findViewById(R.id.tv_item_common);
            viewHolder.button=view.findViewById(R.id.btn_item_common);
            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        if (!simpleGoodsList.isEmpty()){
            viewHolder.textView.setText(simpleGoodsList.get(i).getGoods_name());
            viewHolder.button.setText("删除");
            viewHolder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(CollectionHelper.class).getDeleteCollectionCall(1,collectionIdList.get(i)).enqueue(new Callback<Common>() {
                        @Override
                        public void onResponse(Call<Common> call, Response<Common> response) {
                            if (response.isSuccessful()){
                                if (response.body().getStatus()==NetConstant.OK){
                                    ToastUtils.showMsg(context,"刪除成功");
                                    simpleGoodsList.remove(i);
                                    MyCollectionAdapter.this.notifyDataSetChanged();
                                }else if (response.body().getStatus()==NetConstant.ERROR){
                                    ToastUtils.showMsg(context,"刪除失敗");
                                }
                            }else {
                                ToastUtils.showMsg(context,"刪除失敗");
                            }
                        }
                        @Override
                        public void onFailure(Call<Common> call, Throwable t) {
                            ToastUtils.showMsg(context,"刪除失敗");
                        }
                    });
                }
            });
        }
        return view;
    }

    static class ViewHolder{
        TextView textView;
        Button button;
    }
}
