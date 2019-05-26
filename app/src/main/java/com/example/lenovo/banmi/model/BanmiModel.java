package com.example.lenovo.banmi.model;

import android.widget.Toast;

import com.example.lenovo.banmi.api.MyService;
import com.example.lenovo.banmi.base.BaseModel;
import com.example.lenovo.banmi.base.ResultBack;
import com.example.lenovo.banmi.bean.BanMiBean;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class BanmiModel extends BaseModel {

    public void getData(final ResultBack<BanMiBean> callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MyService.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyService myService = retrofit.create(MyService.class);
        Observable<BanMiBean> banmiBean = myService.getBanmiBean("JVy0IvZamK7f7FBZLKFtoniiixKMlnnJ6dWZ6NlsY4HGsxcAA9qvFo8yacHCKHE8YAcd0UF9L59nEm7zk9AUixee0Hl8EeWA880c0ikZBW0KEYuxQy5Z9NP3BNoBi3o3Q0g");
        banmiBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BanMiBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BanMiBean banMiBean) {
                        if (banMiBean!=null){
                            callback.onSucces(banMiBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
