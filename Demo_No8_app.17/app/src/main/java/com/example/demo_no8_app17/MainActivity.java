package com.example.demo_no8_app17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements MyCalendarView.ClickDateListener{

    private MyCalendarView myCalendarView;

    private final String TAG = MainActivity.class.getName();
    private TextView txt;
    private String time;

    //    传输子线程与主线程信息
    Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0x01){
                time = (String) msg.obj;
                txt.setText(time);
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.textView);



        //        子线程
        new Thread(){

            private String timeString;

            @Override
            public void run() {
                super.run();
                while (true) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
                    timeString = simpleDateFormat.format(System.currentTimeMillis());
                    Message message = new Message();
                    message.what = 0x01;
                    message.obj = timeString;
                    handler.sendMessage(message);

                    try {
                        Thread.sleep(1000); //隔一秒刷新
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();

        myCalendarView = (MyCalendarView) findViewById(R.id.mycalendrview);
        myCalendarView.setOnClickDateListener(this);
        try {
            myCalendarView.setVacationDate(2015, 10, 1, 7);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clickDate(String formatterTime) {
        Log.i(TAG, "clickDate time[" + formatterTime +  "]");
    }
}
