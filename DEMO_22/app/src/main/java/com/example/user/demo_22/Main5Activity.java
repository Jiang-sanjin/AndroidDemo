package com.example.user.demo_22;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {

    private Button fanhui;
    private Button queding;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt1.setText("您选择的学校是："+getIntent().getStringExtra("001"));

        txt2 = (TextView) findViewById(R.id.txt2);
        txt2.setText("您选择的专业是："+getIntent().getStringExtra("002"));

        txt3 = (TextView) findViewById(R.id.txt3);
        txt3.setText("您选择的老师是："+getIntent().getStringExtra("003"));

        fanhui = (Button) findViewById(R.id.fanhui);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        queding = (Button) findViewById(R.id.queding);
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Main6Activity.class);
                startActivity(intent);
            }
        });

    }
}
