package com.example.lenovo.banmi.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter> extends Fragment implements BaseView {
    private Unbinder bind;
    protected P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayoutId(), null);
        bind = ButterKnife.bind(this, inflate);
        presenter = initPenster();
        if (presenter != null) {
            presenter.bind(this);
        }
        initView();
        initData();
        initListerer();
        return inflate;
    }

    protected void initListerer() {

    }

    protected void initData() {

    }

    protected void initView() {

    }

    protected abstract P initPenster();

    protected abstract int getLayoutId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();

    }
}
