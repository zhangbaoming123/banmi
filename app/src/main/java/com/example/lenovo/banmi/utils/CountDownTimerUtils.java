package com.example.lenovo.banmi.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

public class CountDownTimerUtils extends CountDownTimer {
    private TextView textView;

    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {//控件，定时总时间,间隔时间
        super(millisInFuture, countDownInterval);
        this.textView = textView;
        start();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        textView.setClickable(false);//设置不可点击
        textView.setText(millisUntilFinished / 1000 + "秒后可重新发送");//设置倒计时时间
    }

    @Override
    public void onFinish() {
        textView.setClickable(true);//重新获得点击
        textView.setText("重新获取验证码");
    }
}
