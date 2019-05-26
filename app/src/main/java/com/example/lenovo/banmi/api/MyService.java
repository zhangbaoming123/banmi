package com.example.lenovo.banmi.api;

import com.example.lenovo.banmi.bean.MainDataBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

import com.example.lenovo.banmi.bean.BanMiBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface MyService {

    String api= "http://api.banmi.com/";


    //首页
    //http://api.banmi.com/api/3.0/content/routesbundles?page=1
    @GET("api/3.0/content/routesbundles")
    Observable<MainDataBean> getMainData(@Query("page") int page, @Header("token")String token);

    String url = "http://api.banmi.com/";
    //伴米
    @GET("api/3.0/banmi")
    Observable<BanMiBean> getBanmiBean(@Header("token") String token);

}
