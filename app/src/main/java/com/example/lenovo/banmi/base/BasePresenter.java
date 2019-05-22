package com.example.lenovo.banmi.base;

import java.util.ArrayList;

public abstract class BasePresenter<V extends BaseView> {
    public V mView;
    public ArrayList<BaseModel> mMolde = new ArrayList<>();

    public void bind(V view) {
        this.mView = view;
    }

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();
}
