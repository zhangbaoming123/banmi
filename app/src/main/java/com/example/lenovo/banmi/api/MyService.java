package com.example.lenovo.banmi.api;

import com.example.lenovo.banmi.bean.BanMiBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface MyService {

    String url = "http://api.banmi.com/";
    //伴米
    @GET("api/3.0/banmi")
    Observable<BanMiBean> getBanmiBean(@Header("token") String token);

}
