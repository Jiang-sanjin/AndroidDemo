package com.example.demo_app10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private Button b_diqiu;
    private TextView x_s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b_diqiu = (Button) findViewById(R.id.button3);
        x_s2 = (TextView) findViewById(R.id.textView);
        x_s2.setText(getIntent().getStringExtra("001"));
        b_diqiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("003","月球来的信息：地球的盆友，你好，我是来自月球的兔兔");
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
