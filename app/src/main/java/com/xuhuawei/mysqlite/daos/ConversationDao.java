package com.xuhuawei.mysqlite.daos;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class ConversationDao extends BaseDao {
    public static final String CREATTABLE = "CREATE TABLE conversation (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "groudid  LONG," +
            "avatar VARCHAR(200)," +
            "date LONG,"+
            "content VARCHAR(200))";
    public static final String UPDATETABLE = "DROP TABLE IF EXISTS  conversation);";
}
