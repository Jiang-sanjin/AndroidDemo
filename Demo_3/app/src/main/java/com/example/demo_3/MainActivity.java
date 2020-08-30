package com.example.demo_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1; //全局变量
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(this);

        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"HELLO ANDROID",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"HELLO JAVA",Toast.LENGTH_SHORT).show();
    }

    public void click1(View view){
        Toast.makeText(getApplicationContext(),"明人不说暗话",Toast.LENGTH_SHORT).show();
    }
    public void click2(View view){
        Toast.makeText(getApplicationContext(),"想回校上程老师的课",Toast.LENGTH_SHORT).show();
    }


}
