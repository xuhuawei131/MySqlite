package com.xuhuawei.mysqlite.beans;

import android.support.v4.util.Pools;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class ChatBean {
    public int index=0;
    public String content;
    public int groupid;
    public long date;
    public int uid;

    private ChatBean(){

    }

    private static final Pools.SynchronizedPool<ChatBean> sPool =
            new Pools.SynchronizedPool(10);

    public static ChatBean obtain() {
        ChatBean instance = sPool.acquire();
        return (instance != null) ? instance : new ChatBean();
    }

    public void recycle() {
        // Clear state if needed.
        sPool.release(this);
    }
}
