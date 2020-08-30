package com.example.demo_no10_app24;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;


public class Myservice extends Service {

    int count;
    boolean flag;

    public class MyBind extends Binder {

        public int getcount(){

            return count;
        }
        public void setcount(int args){
            count=args;
        }

    }

    MyBind myBind=new MyBind();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return myBind;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        flag=true;
        new Thread(){

            @Override
            public void run() {
                super.run();
                while (flag){

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }

            }
        }.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag=false;

    }
}
