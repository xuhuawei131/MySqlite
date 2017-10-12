package com.xuhuawei.mysqlite.helper;

import com.xuhuawei.mysqlite.beans.ChatBean;
import com.xuhuawei.mysqlite.daos.ChatDao;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class QuerryDBHelper {
    private static QuerryDBHelper instance;
    private ChatDao chatDao;
    private QuerryDBHelper(){
        chatDao=new ChatDao();
    }
    public static QuerryDBHelper getInstance(){
        if (instance==null){
            instance=new QuerryDBHelper();
        }
        return instance;
    }

    public ChatBean getLastChatBean(){
       return chatDao.getLastChatBean();
    }
    public int getTotalNum(){
        return chatDao.getTotalNum();
    }

}
