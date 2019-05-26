package com.example.lenovo.banmi.presenter;

import com.example.lenovo.banmi.base.BasePresenter;
import com.example.lenovo.banmi.base.ResultBack;
import com.example.lenovo.banmi.bean.MainDataBean;
import com.example.lenovo.banmi.model.HomeM;
import com.example.lenovo.banmi.view.HomeV;

public class HomeP extends BasePresenter<HomeV> {

    private HomeM model;

    @Override
    protected void initModel() {
        model = new HomeM();
        mMolde.add(model);
    }

    public void getHomeData(int page) {
        model.getMainList(page, new ResultBack<MainDataBean>() {
            @Override
            public void onSucces(MainDataBean bean) {
                mView.onSucces(bean.getResult());
            }

            @Override
            public void onFails(String s) {
                mView.onFails(s);
            }
        });

    }
}
