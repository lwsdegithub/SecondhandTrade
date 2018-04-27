package com.wanghongyun.secondhandtrade.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by 李维升 on 2018/4/27.
 */

public class FastScrollView extends ScrollView {
    public FastScrollView(Context context) {
        super(context);
    }

    public FastScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FastScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY*3);
    }
}
