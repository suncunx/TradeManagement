package com.trade.util;

import android.os.CountDownTimer;
import android.widget.TextView;

public class TimeCount extends CountDownTimer {
    // 因为Button其实也是继承自TextView
    private TextView textView;

    public TimeCount(long millisInFuture, long countDownInterval, TextView textView) {//单位为毫秒
        super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        this.textView = textView;
    }

    @Override
    public void onFinish() {// 计时完毕时触发
        textView.setText("重新验证");
        textView.setClickable(true);
    }

    @Override
    public void onTick(long millisUntilFinished) {// 计时过程显示
        textView.setClickable(false);
        String text = String.format("%d秒", millisUntilFinished / 1000);
        textView.setText(text);
    }
}