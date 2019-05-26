package com.example.lenovo.banmi.model;

import com.example.lenovo.banmi.api.MyService;
import com.example.lenovo.banmi.app.MyApp;
import com.example.lenovo.banmi.base.BaseModel;
import com.example.lenovo.banmi.base.ResultBack;
import com.example.lenovo.banmi.bean.MainDataBean;

import java.util.Date;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeM extends BaseModel {
    public void getMainList(int page, final ResultBack<MainDataBean> back) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MyService.api)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MyService myService = retrofit.create(MyService.class);
        Observable<MainDataBean> mainData = myService.getMainData(page, "JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g");
        mainData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(MainDataBean dataBean) {
                        back.onSucces(dataBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        back.onFails(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
