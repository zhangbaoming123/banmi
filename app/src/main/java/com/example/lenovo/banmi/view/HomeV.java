package com.example.lenovo.banmi.view;

import com.example.lenovo.banmi.base.BaseView;
import com.example.lenovo.banmi.bean.MainDataBean;

public interface HomeV extends BaseView {
    void onSucces(MainDataBean.ResultEntity dataBean);
    void onFails(String s);
}
