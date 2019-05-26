package com.example.lenovo.banmi.presenter;

import com.example.lenovo.banmi.base.BasePresenter;
import com.example.lenovo.banmi.base.ResultBack;
import com.example.lenovo.banmi.bean.BanMiBean;
import com.example.lenovo.banmi.model.BanmiModel;
import com.example.lenovo.banmi.view.BanmiView;

public class BanmiPresenter extends BasePresenter<BanmiView> {

    private BanmiModel banmiModel;

    @Override
    protected void initModel() {
        banmiModel=new BanmiModel();
        mMolde.add(banmiModel);
        banmiModel.getData(new ResultBack<BanMiBean>(){
            @Override
            public void onSucces(BanMiBean bean) {
                mView.showBanMi(bean);
            }

            @Override
            public void onFails(String s) {
                mView.showError(s);
            }
        });
    }


}
