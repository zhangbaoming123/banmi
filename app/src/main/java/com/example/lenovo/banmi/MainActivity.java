package com.example.lenovo.banmi;

import android.content.Intent;
import android.media.VolumeShaper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.banmi.activity.MessageActivity;
import com.example.lenovo.banmi.activity.NotivityActivity;
import com.example.lenovo.banmi.adapter.MainVpAdapter;
import com.example.lenovo.banmi.base.BaseActivity;
import com.example.lenovo.banmi.mycoupon.activity.MyCouponActivity;
import com.example.lenovo.banmi.mywallet.activity.MyWalletActivity;
import com.example.lenovo.banmi.presenter.EmptyP;
import com.example.lenovo.banmi.utils.SpUtil;
import com.example.lenovo.banmi.view.EmptyV;
import com.example.lenovo.banmi.weight.CustomDrawerLayout;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<EmptyV, EmptyP> implements View.OnClickListener, EmptyV {

    @BindView(R.id.main_iv)
    ImageView mainIv;
    @BindView(R.id.main_tool)
    Toolbar mainTool;
    @BindView(R.id.main_nav)
    NavigationView mainNav;
    @BindView(R.id.main_dl)
    CustomDrawerLayout mainDl;
    @BindView(R.id.main_vp)
    ViewPager mainvp;
    @BindView(R.id.btn1)
    RadioButton btn1;
    @BindView(R.id.btn2)
    RadioButton btn2;
    @BindView(R.id.rg)
    RadioGroup rg;
    private ArrayList<Fragment> fragments;
    private String img;
    private String name;

    @Override
    protected EmptyP initPresenter() {
        return new EmptyP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        img = intent.getStringExtra("img");
        name = intent.getStringExtra("name");

        View headerView = mainNav.getHeaderView(0);
        TextView tv_name = headerView.findViewById(R.id.header_name);
        ImageView iv = headerView.findViewById(R.id.header_iv);
        tv_name.setText(name);
        RequestOptions options = new RequestOptions().circleCrop();
        Glide.with(MainActivity.this).load(img).apply(options).into(iv);

        mainTool.setTitle("");
        setSupportActionBar(mainTool);
        //解决侧滑菜单menu部分图标不显示
        mainNav.setItemIconTintList(null);
        initFragment();

    }


    @Override
    protected void initData() {
        View headerView = mainNav.getHeaderView(0);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.btn1:
                        mainvp.setCurrentItem(0);
                        break;
                    case R.id.btn2:
                        mainvp.setCurrentItem(1);
                        break;
                }
            }
        });
    }


    private void initFragment() {
        fragments = new ArrayList<>();
        HomeFragment home = new HomeFragment();
        BanmiFragment banmi = new BanmiFragment();
        fragments.add(home);
        fragments.add(banmi);
        MainVpAdapter adapter = new MainVpAdapter(getSupportFragmentManager(), fragments);
        mainvp.setAdapter(adapter);


    }

    //选项菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.op1://通知
                startActivity(new Intent(MainActivity.this, MyWalletActivity.class));
                break;
            case R.id.op2://信息
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.main_iv, R.id.btn1, R.id.btn2})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_iv:
                mainDl.openDrawer(mainNav);
                break;
            case R.id.btn1:
                break;
            case R.id.btn2:
                break;
        }
    }

}
