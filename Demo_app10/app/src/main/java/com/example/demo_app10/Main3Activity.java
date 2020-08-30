package com.example.demo_app10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private Button t_diqiu1;
    private TextView x_s3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        t_diqiu1 = (Button) findViewById(R.id.button4);
        x_s3 = (TextView) findViewById(R.id.textView2);
        x_s3.setText(getIntent().getStringExtra("002"));
        t_diqiu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("004","火星来的信息：地球的盆友，你好，我是来自火星的ET");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
