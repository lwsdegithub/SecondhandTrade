package com.wanghongyun.secondhandtrade.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

/**
 * Created by 李维升 on 2018/5/4.
 */

public class SendMsgDialog extends AlertDialog.Builder {
    private View view;
    private EditText editText;
    public SendMsgDialog(Context context) {
        super(context);
    }

}
