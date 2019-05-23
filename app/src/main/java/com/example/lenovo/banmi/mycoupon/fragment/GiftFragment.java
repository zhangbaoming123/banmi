package com.example.lenovo.banmi.mycoupon.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.banmi.R;
import com.example.lenovo.banmi.base.BaseFragment;
import com.example.lenovo.banmi.mycoupon.adapter.GiftReAdapter;
import com.example.lenovo.banmi.mycoupon.adapter.VouCherReAdapter;
import com.example.lenovo.banmi.mycoupon.presenter.GiftPresenter;
import com.example.lenovo.banmi.mycoupon.view.GiftView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class GiftFragment extends BaseFragment<GiftView, GiftPresenter> {
    @BindView(R.id.gift_recycler)
    RecyclerView mGiftRecycler;
    private View view;
    private Unbinder unbinder;
    private ArrayList<String> strings;
    private GiftReAdapter adapter;

    @Override
    protected GiftPresenter initPenster() {
        return new GiftPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gift;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mGiftRecycler.setLayoutManager(manager);
        strings =new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("一家民宿");
        }
        adapter =new GiftReAdapter(strings,getActivity());
        mGiftRecycler.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
