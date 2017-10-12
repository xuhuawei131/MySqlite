package com.xuhuawei.mysqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xuhuawei.mysqlite.MyApp;
import com.xuhuawei.mysqlite.daos.ChatDao;
import com.xuhuawei.mysqlite.daos.ConversationDao;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private static final int version=1;
    private SQLiteDatabase database = null ;
    private static MyDataBaseHelper instance=null;
    public static MyDataBaseHelper getInstance(){
        if (instance==null){
            instance=new MyDataBaseHelper();
        }
        return instance;
    }

    public  SQLiteDatabase openDatabase() {
        if(database == null||!database.isOpen()){
            database = getWritableDatabase();
        }
        return database;
    }

    public MyDataBaseHelper() {
        super(MyApp.instance, "test.db", null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ChatDao.CREATTABLE);
        sqLiteDatabase.execSQL(ConversationDao.CREATTABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ChatDao.UPDATETABLE);
        sqLiteDatabase.execSQL(ConversationDao.UPDATETABLE);
        onCreate(sqLiteDatabase);
    }
}
