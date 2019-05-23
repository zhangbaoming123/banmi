package com.example.lenovo.banmi.mycoupon.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.banmi.R;
import com.example.lenovo.banmi.base.BaseFragment;
import com.example.lenovo.banmi.mycoupon.activity.ViewPingActivity;
import com.example.lenovo.banmi.mycoupon.adapter.VouCherReAdapter;
import com.example.lenovo.banmi.mycoupon.presenter.VouCherPresenter;
import com.example.lenovo.banmi.mycoupon.view.ViewPingView;
import com.example.lenovo.banmi.mycoupon.view.VouCherView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class VouCherFragment extends BaseFragment<VouCherView, VouCherPresenter> {
    @BindView(R.id.voucher_recycler)
    RecyclerView mVoucherRecycler;
    private View view;
    private Unbinder unbinder;

    private ArrayList<String> strs;
    private VouCherReAdapter vouCherReAdapter;

    @Override
    protected VouCherPresenter initPenster() {
        return new VouCherPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_voucher;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mVoucherRecycler.setLayoutManager(manager);
        strs =new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strs.add("伴米旅行");
        }
        vouCherReAdapter =new VouCherReAdapter(strs,getActivity());
        mVoucherRecycler.setAdapter(vouCherReAdapter);
        vouCherReAdapter.notifyDataSetChanged();
        vouCherReAdapter.setOnClickListener(new VouCherReAdapter.OnClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), ViewPingActivity.class);
                startActivity(intent);
            }
        });
    }
}
