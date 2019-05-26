package com.example.lenovo.banmi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lenovo.banmi.adapter.BanmiAdapter;
import com.example.lenovo.banmi.base.BaseFragment;
import com.example.lenovo.banmi.base.BasePresenter;
import com.example.lenovo.banmi.bean.BanMiBean;
import com.example.lenovo.banmi.presenter.BanmiPresenter;
import com.example.lenovo.banmi.view.BanmiView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class BanmiFragment extends BaseFragment<BanmiView, BasePresenter<BanmiView>> implements BanmiView {

    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private BanmiAdapter banmiAdapter;
    private List<BanMiBean.ResultBean.BanmiBean> list;

    @Override
    protected BasePresenter<BanmiView> initPenster() {
        return new BanmiPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_banmi;
    }

    @Override
    protected void initView() {
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        banmiAdapter = new BanmiAdapter(list,getContext());
        rv.setAdapter(banmiAdapter);
    }

    @Override
    public void showBanMi(BanMiBean banmiBean) {
        List<BanMiBean.ResultBean.BanmiBean> banmi = banmiBean.getResult().getBanmi();
        Log.d(TAG,"BANMI===>"+banmi.toString());
        banmiAdapter.getData(banmi);
    }

    @Override
    public void showError(String error) {

    }
}
