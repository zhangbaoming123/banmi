package com.example.lenovo.banmi.app;

import android.app.Application;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class MyApp extends Application {

    public static MyApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        initUmeng();
    }
    private void initUmeng() {
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(this, "5c81df033fc195af990007cb", "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0

        // 这儿的两个参数需要替换成用你自己公司账号在qq开放平台申请的
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");

    }
    public static MyApp getInstance(){
        return app;
    }
}
