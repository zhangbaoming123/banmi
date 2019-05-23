package com.example.lenovo.banmi.mycoupon.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.lenovo.banmi.R;
import com.example.lenovo.banmi.base.BaseActivity;
import com.example.lenovo.banmi.mycoupon.adapter.MyCouponFpAdapter;
import com.example.lenovo.banmi.mycoupon.fragment.GiftFragment;
import com.example.lenovo.banmi.mycoupon.fragment.VouCherFragment;
import com.example.lenovo.banmi.mycoupon.presenter.MyCouponPresenter;
import com.example.lenovo.banmi.mycoupon.view.MyCouponView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCouponActivity extends BaseActivity<MyCouponView, MyCouponPresenter> {
    @BindView(R.id.viewpagertab)
    SmartTabLayout mViewpagertab;
    @BindView(R.id.coupon_pager)
    ViewPager mCouponPager;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private MyCouponFpAdapter fpAdapter;

    @Override
    protected MyCouponPresenter initPresenter() {
        return new MyCouponPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mycoupon;
    }

    @Override
    protected void initView() {
        titles = new ArrayList<>();
        titles.add("礼品券");
        titles.add("代金券");
        fragments = new ArrayList<>();
        fragments.add(new GiftFragment());
        fragments.add(new VouCherFragment());
        fpAdapter =new MyCouponFpAdapter(getSupportFragmentManager(),fragments,titles);
        mCouponPager.setAdapter(fpAdapter);
        mViewpagertab.setViewPager(mCouponPager);


    }

}
