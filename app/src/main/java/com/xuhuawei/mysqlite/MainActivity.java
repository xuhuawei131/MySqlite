package com.xuhuawei.mysqlite;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xuhuawei.mysqlite.beans.ChatBean;
import com.xuhuawei.mysqlite.beans.TaskBean;
import com.xuhuawei.mysqlite.helper.InsertDBHelper;
import com.xuhuawei.mysqlite.helper.QuerryDBHelper;
import com.xuhuawei.mysqlite.task.MyTask;

import java.util.ArrayList;
import java.util.List;

import dalvik.system.PathClassLoader;

import static com.xuhuawei.mysqlite.beans.TaskBean.TYPE_IMSERT;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_insert;
    private Button btn_insert_stop;
    private Button btn_querry;
    private Button btn_querry_sum;
    private TextView text_result_insert;
    private TextView text_result_querry;
    private TextView text_result_sum;

    private MyTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btn_insert=(Button)findViewById(R.id.btn_insert);
        btn_insert_stop=(Button)findViewById(R.id.btn_insert_stop);
        btn_querry=(Button)findViewById(R.id.btn_querry);
        btn_querry_sum=(Button)findViewById(R.id.btn_querry_sum);

        text_result_insert=(TextView)findViewById(R.id.text_result_insert) ;
        text_result_querry=(TextView)findViewById(R.id.text_result_querry) ;
        text_result_sum=(TextView)findViewById(R.id.text_result_sum) ;

        btn_insert.setOnClickListener(this);
        btn_insert_stop.setOnClickListener(this);
        btn_querry.setOnClickListener(this);
        btn_querry_sum.setOnClickListener(this);

        InsertDBHelper.getInstance().start();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_insert){
            addTask2();
        }else if(view.getId()==R.id.btn_querry){
            ChatBean bean=QuerryDBHelper.getInstance().getLastChatBean();
            if(bean!=null){
                text_result_querry.setText(bean.uid+":"+bean.content);
            }
        }else if(view.getId()==R.id.btn_querry_sum){
            int sum=QuerryDBHelper.getInstance().getTotalNum();
            text_result_sum.setText("total:"+sum);
        }else if (view==btn_insert_stop){
            task.cancel(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        InsertDBHelper.getInstance().destory();
    }

    private void addTask(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int index=0;;
                for (int j=0;j<100;j++){
                    TaskBean task=new TaskBean();
                    task.index=j;
                    List<ChatBean> arrayList=new ArrayList<ChatBean>(100);
                    for (int i=0;i<100;i++){
                        ChatBean bean= ChatBean.obtain();
                        bean.content="MsgOne"+i;
                        bean.date=System.nanoTime();
                        bean.groupid=0;
                        bean.uid=index++;
                        arrayList.add(bean);

                    }
                    task.arrayList=arrayList;
                    task.operate=TYPE_IMSERT;
                    InsertDBHelper.getInstance().addWriteTask(task);
                }
            }
        }).start();
    }

    private void addTask2(){
        task=new MyTask();
        task.execute("");
    }
    private void addTask1(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10000;i++){
                    ChatBean bean= ChatBean.obtain();
                    bean.content="MsgTwo"+i;
                    bean.date=System.nanoTime();
                    bean.groupid=0;
                    bean.uid=i;
                    bean.index=i;
                    InsertDBHelper.getInstance().addWriteTask(0,bean);
                }
            }
        }).start();
    }

}
