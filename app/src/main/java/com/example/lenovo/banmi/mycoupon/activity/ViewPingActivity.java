package com.example.lenovo.banmi.mycoupon.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.lenovo.banmi.R;
import com.example.lenovo.banmi.base.BaseActivity;
import com.example.lenovo.banmi.mycoupon.presenter.ViewPingPresenter;
import com.example.lenovo.banmi.mycoupon.view.ViewPingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewPingActivity extends BaseActivity<ViewPingView, ViewPingPresenter> implements ViewPingView, View.OnClickListener {


    @BindView(R.id.view_iv)
    ImageView mViewIv;
    @BindView(R.id.coupon_btn)
    Button mCouponBtn;

    @Override
    protected ViewPingPresenter initPresenter() {
        return new ViewPingPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_viewping;
    }

    @Override
    protected void initView() {
        mCouponBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String s = mCouponBtn.getText().toString();
        if(s.equals("兑换")){
            mCouponBtn.setText("查看");
        }
        if(s.equals("查看")){
            mCouponBtn.setText("兑换");
        }
    }
}
