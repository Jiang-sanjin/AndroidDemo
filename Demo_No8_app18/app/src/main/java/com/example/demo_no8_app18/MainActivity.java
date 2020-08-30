package com.example.demo_no8_app18;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        btn = findViewById(R.id.btn);



//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                TextView动态改变背景
////                btn.setBackgroundResource(R.drawable.btnbg2);
//                Toast.makeText(MainActivity.this, "实现点击TextView事件", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public void send1(View view){
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),Broadcast1.class);
        sendBroadcast(intent);


    }

    public void send2(View view){
        Intent intent = new Intent();
        intent.setAction("to2");
        sendBroadcast(intent);

    }

    public void send3(View view){
        Intent intent = new Intent();

        intent.setClass(getApplicationContext(),Broadcast1.class);
        sendBroadcast(intent);

        Intent intent2 = new Intent();
        intent2.setAction("to2");
        sendBroadcast(intent2);
    }

}
