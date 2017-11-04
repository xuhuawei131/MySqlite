package com.xuhuawei.mysqlite.helper;

import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.xuhuawei.mysqlite.beans.ChatBean;
import com.xuhuawei.mysqlite.beans.TaskBean;
import com.xuhuawei.mysqlite.daos.ChatDao;
import com.xuhuawei.mysqlite.database.MyDataBaseHelper;

import static android.R.attr.type;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class InsertDBHelper extends Thread {
    private static InsertDBHelper instance;
    private Handler handler;

    private Looper looper;
    private ChatDao chatDao;
    private InsertDBHelper() {
        chatDao=new ChatDao();
    }

    @Override
    public synchronized void start() {
            super.start();
    }

    public static InsertDBHelper getInstance() {
        if (instance == null) {
            instance = new InsertDBHelper();
        }
        return instance;
    }

    public void addWriteTask(int type, ChatBean bean){
        Message message=handler.obtainMessage();
        message.obj=bean;
        message.what=type;
        handler.sendMessage(message);
    }
    public void addWriteTask(TaskBean bean){
        Message message=handler.obtainMessage();
        message.obj=bean;
        handler.sendMessage(message);
    }
    @Override
    public void run() {
        Looper.prepare();
        looper = Looper.myLooper();
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                doTask(msg);
            }
        };
        Looper.loop();
    }

    private void doTask(Message msg){
        if (msg.obj instanceof ChatBean){//插入chat
            ChatBean bean=(ChatBean)(msg.obj);
            chatDao.insertChatInfo(bean);
        }else if(msg.obj instanceof TaskBean){//插入conversation
            TaskBean bean=(TaskBean)(msg.obj);
            chatDao.patchInsertChatInfo(bean);
        }
    }

    public void destory() {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        looper.quit();
        instance = null;
    }
}
