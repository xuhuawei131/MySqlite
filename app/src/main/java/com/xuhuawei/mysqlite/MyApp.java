package com.xuhuawei.mysqlite;

import android.app.Application;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class MyApp extends Application{
    public static MyApp instance=null;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}
