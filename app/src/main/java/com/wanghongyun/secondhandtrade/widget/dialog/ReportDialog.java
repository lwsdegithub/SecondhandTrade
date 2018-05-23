package com.wanghongyun.secondhandtrade.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.wanghongyun.secondhandtrade.R;
import com.wanghongyun.secondhandtrade.constant.NetConstant;
import com.wanghongyun.secondhandtrade.helper.gsonBeans.Common;
import com.wanghongyun.secondhandtrade.helper.retrofitInterfaces.ReportHelper;
import com.wanghongyun.secondhandtrade.utils.RetrofitUtils;
import com.wanghongyun.secondhandtrade.utils.ToastUtils;
import com.wanghongyun.secondhandtrade.utils.UserUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 李维升 on 2018/5/23.
 */

public class ReportDialog extends AlertDialog{
    private Context context;
    private int goodsId;

    public ReportDialog(Context context,int goodsId) {
        super(context);
        this.context=context;
        this.goodsId=goodsId;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_report);
        this.setView(View.inflate(context,R.layout.dialog_report,null));

        Window window = this.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;//如果不设置,可能部分机型出现左右有空隙,也就是产生margin的感觉
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;//显示dialog的时候,就显示软键盘
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;//就是这个属性导致不能获取焦点,默认的是FLAG_NOT_FOCUSABLE,故名思义不能获取输入焦点,
        params.dimAmount=0.5f;//设置对话框的透明程度背景(非布局的透明度)
        window.setAttributes(params);

        final EditText editText=this.findViewById(R.id.et_report);
        final Button button=this.findViewById(R.id.btn_commit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RetrofitUtils.getRetrofit(NetConstant.BASE_URL).create(ReportHelper.class).getAddReportCall(0, UserUtils.getUserId(context),goodsId,editText.getText().toString()).enqueue(new Callback<Common>() {
                    @Override
                    public void onResponse(Call<Common> call, Response<Common> response) {
                        if (response.isSuccessful()){
                            if (response.body().getStatus()==NetConstant.OK){
                                ToastUtils.showMsg(context,"提交成功");
                                ReportDialog.this.dismiss();
                            }
                        }else {
                            ToastUtils.showMsg(context,"提交失败");
                        }
                    }
                    @Override
                    public void onFailure(Call<Common> call, Throwable t) {
                        ToastUtils.showMsg(context,"提交失败");
                    }
                });
            }
        });
    }

}
