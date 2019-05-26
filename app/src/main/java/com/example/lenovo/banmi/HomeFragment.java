package com.example.lenovo.banmi;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.lenovo.banmi.activity.MainInfoActivity;
import com.example.lenovo.banmi.adapter.HomeAdapter;
import com.example.lenovo.banmi.base.BaseFragment;
import com.example.lenovo.banmi.base.Constants;
import com.example.lenovo.banmi.bean.MainDataBean;
import com.example.lenovo.banmi.presenter.HomeP;
import com.example.lenovo.banmi.view.HomeV;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

import static android.support.constraint.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeV, HomeP> implements HomeV {


    @BindView(R.id.home_re)
    RecyclerView homeRe;
    @BindView(R.id.srl)
    SmartRefreshLayout srl;
    Unbinder unbinder;
    private List<MainDataBean.ResultEntity.RoutesEntity> list;
    private List<MainDataBean.ResultEntity.BannersEntity> bannerlist;
    private int page = 1;
    private HomeAdapter homeAdapter;

    @Override
    protected HomeP initPenster() {
        return new HomeP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        homeRe.setLayoutManager(manager);
        bannerlist = new ArrayList<>();
        list = new ArrayList<>();
        homeAdapter = new HomeAdapter(bannerlist,list,getContext());
        homeRe.setAdapter(homeAdapter);
    }

    @Override
    protected void initData() {
        presenter.getHomeData(page);

    }

    @Override
    public void onSucces(MainDataBean.ResultEntity dataBean) {
       /* if (page == 1) {
            bannerlist.clear();
            list.clear();
        }
        bannerlist.addAll(dataBean.getBanners());
        list.addAll(dataBean.getRoutes());
        homeAdapter.setBannerlist(bannerlist);
        homeAdapter.setListitem(list);*/
        homeAdapter.getBannerList(dataBean.getBanners());
        homeAdapter.getData(dataBean.getRoutes());
        srl.finishLoadMore();
        srl.finishRefresh();


    }

    @Override
    protected void initListerer() {
        srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                initData();
            }
        });
    }

    @Override
    public void onFails(String s) {
        Log.i(TAG, "onFails: " + s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
