package com.xuhuawei.mysqlite.beans;

import java.util.List;

/**
 * Created by lingdian on 2017/11/3.
 */

public class TaskBean {
    public static final byte TYPE_IMSERT=0;
    public static final byte TYPE_UPDATE=1;
    public static final byte TYPE_DELETE=2;

    public int index=0;
    public byte operate=TYPE_IMSERT;

    public List<ChatBean> arrayList;
}
