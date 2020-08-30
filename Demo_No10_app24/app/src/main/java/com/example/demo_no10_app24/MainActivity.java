package com.example.demo_no10_app24;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Intent intent;
    private ServiceConnection serviceConnection;
    private Myservice.MyBind bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent(MainActivity.this, Myservice.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                bind = (Myservice.MyBind) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    public void bind(View view){
        bindService(intent,serviceConnection,BIND_AUTO_CREATE);
        Toast.makeText(getApplicationContext(),"绑定成功",Toast.LENGTH_SHORT).show();
    }
    public void unbind(View view){

        unbindService(serviceConnection);
        Toast.makeText(getApplicationContext(),"解绑成功",Toast.LENGTH_SHORT).show();
    }

    public void getV(View view){

        Toast.makeText(getApplicationContext(),"当前值是："+bind.getcount()+"",Toast.LENGTH_SHORT).show();
    }
    public void setV(View view){

        bind.setcount(0);
        Toast.makeText(getApplicationContext(),"已经清零！！",Toast.LENGTH_SHORT).show();
    }
}
