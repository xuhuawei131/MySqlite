package com.xuhuawei.mysqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xuhuawei.mysqlite.beans.ChatBean;
import com.xuhuawei.mysqlite.helper.InsertDBHelper;
import com.xuhuawei.mysqlite.helper.QuerryDBHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_insert;
    private Button btn_querry;
    private Button btn_querry_sum;
    private TextView text_result_insert;
    private TextView text_result_querry;
    private TextView text_result_sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_insert=(Button)findViewById(R.id.btn_insert);
        btn_querry=(Button)findViewById(R.id.btn_querry);
        btn_querry_sum=(Button)findViewById(R.id.btn_querry_sum);

        text_result_insert=(TextView)findViewById(R.id.text_result_insert) ;
        text_result_querry=(TextView)findViewById(R.id.text_result_querry) ;
        text_result_sum=(TextView)findViewById(R.id.text_result_sum) ;

        btn_insert.setOnClickListener(this);
        btn_querry.setOnClickListener(this);
        btn_querry_sum.setOnClickListener(this);

        InsertDBHelper.getInstance().start();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btn_insert){
            addTask();
        }else if(view.getId()==R.id.btn_querry){
            ChatBean bean=QuerryDBHelper.getInstance().getLastChatBean();
            text_result_querry.setText(bean.uid+":"+bean.content);
        }else if(view.getId()==R.id.btn_querry_sum){
            int sum=QuerryDBHelper.getInstance().getTotalNum();
            text_result_sum.setText("total:"+sum);
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
                for (int i=0;i<10000;i++){
                    ChatBean bean= ChatBean.obtain();
                    bean.content="MsgOne"+i;
                    bean.date=System.nanoTime();
                    bean.groupid=0;
                    bean.uid=i;
                    Log.v("xhw",bean.uid+"-"+bean.content);
                    InsertDBHelper.getInstance().addWriteTask(0,bean);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10000;i++){
                    ChatBean bean= ChatBean.obtain();
                    bean.content="MsgTwo"+i;
                    bean.date=System.nanoTime();
                    bean.groupid=0;
                    bean.uid=i;
                    Log.v("xhw",bean.uid+"-"+bean.content);
                    InsertDBHelper.getInstance().addWriteTask(0,bean);
                }
            }
        }).start();
    }
}
