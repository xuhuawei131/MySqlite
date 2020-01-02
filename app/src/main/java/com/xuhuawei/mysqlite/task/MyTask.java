package com.xuhuawei.mysqlite.task;

import android.os.AsyncTask;
import android.util.Log;

import com.xuhuawei.mysqlite.beans.ChatBean;
import com.xuhuawei.mysqlite.helper.InsertDBHelper;

public class MyTask extends AsyncTask<String, Integer, String> {
    private static final String TAG = "xhw";

    //onPreExecute方法用于在执行后台任务前做一些UI操作
    @Override
    protected void onPreExecute() {
        Log.i(TAG, "onPreExecute() called");
    }

    //doInBackground方法内部执行后台任务,不可在此方法内修改UI
    @Override
    protected String doInBackground(String... params) {
        Log.i(TAG, "doInBackground(Params... params) called");
        for (int i=10;i>0;i++){
            ChatBean bean= ChatBean.obtain();
            bean.content="MsgTwo"+i;
            bean.date=System.nanoTime();
            bean.groupid=0;
            bean.uid=i;
            bean.index=i;
            if (i%10==0){
                Log.v("xhw","signal 10");
            }

            InsertDBHelper.getInstance().addWriteTask(0,bean);
        }
        return null;
    }

    //onProgressUpdate方法用于更新进度信息
    @Override
    protected void onProgressUpdate(Integer... progresses) {
        Log.i(TAG, "onProgressUpdate(Progress... progresses) called");
    }

    //onPostExecute方法用于在执行完后台任务后更新UI,显示结果
    @Override
    protected void onPostExecute(String result) {
        Log.i(TAG, "onPostExecute(Result result) called");
    }

    //onCancelled方法用于在取消执行中的任务时更改UI
    @Override
    protected void onCancelled() {
        Log.e(TAG, "onCancelled() called");
    }
}
