package com.xuhuawei.mysqlite;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class LookSourceCode {

    PathClassLoader pathClassLoader;
    DexClassLoader dexClassLoader;

    ThreadPoolExecutor threadPoll=new ThreadPoolExecutor(0,Integer.MAX_VALUE,30, TimeUnit.SECONDS,null);

    AsyncTask asyncTask=new AsyncTask<String,String,String>() {
        @Override
        protected String doInBackground(String... strings) {
            return null;
        }


    }.execute("");

    SharedPreferences sd;
    Handler handler;
}
