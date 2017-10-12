package com.xuhuawei.mysqlite.daos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.xuhuawei.mysqlite.beans.ChatBean;
import com.xuhuawei.mysqlite.database.MyDataBaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class ChatDao extends BaseDao {
    private static final String tableName="chat";
    public static final String CREATTABLE = "CREATE TABLE "+tableName+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "uid  LONG," +
            "groudid LONG," +
            "date LONG,"+
            "content VARCHAR(200))";
    public static final String UPDATETABLE = "DROP TABLE IF EXISTS  "+tableName+");";
    private SQLiteDatabase db;
    public ChatDao(){
        db = MyDataBaseHelper.getInstance().openDatabase();
    }
    public  long insertChatInfo(ChatBean bean) {
        ContentValues values = new ContentValues();
        values.put("uid", bean.uid);
        values.put("groudid", bean.groupid);
        values.put("date", bean.date);
        values.put("content", bean.content);
        long i = db.insert(tableName, "_id", values);
        Log.v("xhw","insert:"+bean.content);
        bean.recycle();
        return i;
    }
    public ChatBean getLastChatBean() {
        String sql = "SELECT * FROM " + tableName + "  ORDER BY date DESC limit 0,1";
        Cursor c = db.rawQuery(sql, new String[] { });
        ChatBean info=null ;
        if (c != null&&c.moveToNext()) {
                info= ChatBean.obtain();
                info.uid = c.getInt(c.getColumnIndex("uid"));
                info.content = c.getString(c.getColumnIndex("content"));
                info.date = c.getLong(c.getColumnIndex("date"));
                info.groupid = c.getInt(c.getColumnIndex("groudid"));
            c.close();
        }
        return info;
    }
    public int getTotalNum() {
        String sql = "SELECT count(_id) FROM " + tableName;
        Cursor c = db.rawQuery(sql, new String[] { });
        int count=0;
        if (c != null&&c.moveToNext()) {
            count = c.getInt(0);
            c.close();
        }
        return count;
    }
}
