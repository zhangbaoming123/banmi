package com.example.lenovo.banmi.mywallet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.banmi.R;
import com.example.lenovo.banmi.mywallet.adapter.MyWalletReAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends AppCompatActivity {

    @BindView(R.id.mywallet_toolbar)
    Toolbar mMywalletToolbar;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.btn_bonus)
    Button mBtnBonus;
    @BindView(R.id.btn_recharge)
    Button mBtnRecharge;
    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.mywallet_recycler)
    RecyclerView mMywalletRecycler;
    private ArrayList<String> strings;
    private ArrayList<String> times;
    private MyWalletReAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);
        initView();
        initData();
        initListener();
    }

    private void initListener() {
        mBtnRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyWalletActivity.this, RechargeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initData() {
        strings =new ArrayList<>();
        times =new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("分享［伴米］");
            times.add("2017/10/25");
        }
        adapter =new MyWalletReAdapter(strings,times,this);
        mMywalletRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mMywalletRecycler.setLayoutManager(manager);
    }
}
