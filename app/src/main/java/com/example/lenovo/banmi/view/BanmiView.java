package com.example.lenovo.banmi.view;

import com.example.lenovo.banmi.base.BaseView;
import com.example.lenovo.banmi.bean.BanMiBean;

public interface BanmiView extends BaseView {

    void showBanMi(BanMiBean banmiBean);
    void showError(String error);
}
