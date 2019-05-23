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
        /* SpannableString spannableString=new SpannableString(bt_getcord.getText().toString());//获取按钮上的文字
        ForegroundColorSpan span=new ForegroundColorSpan(Color.RED);//设置文字颜色
        bt_getcord.setAllCaps(false);
        spannableString.setSpan(span,0,2,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);////将倒计时的时间设置为红色
        bt_getcord.setText(spannableString);*/

    }

    @Override
    public void onFinish() {
        textView.setClickable(true);//重新获得点击
        textView.setText("重新获取验证码");
    }
}
