package com.example.demo_app10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button t_huoxing;
    private Button t_yuqiu;
    private TextView x_s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //to 火星
        t_huoxing = (Button) findViewById(R.id.button);
        //to 月球
        t_yuqiu = (Button) findViewById(R.id.button2);
        //显示
        x_s1 = (TextView) findViewById(R.id.x_s);

        t_yuqiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("001","地球来的信息：月球的盆友，你好，我是来自地球的sanjin");
                startActivityForResult(intent,0x00);
            }
        });
        t_huoxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Main3Activity.class);
                intent.putExtra("002","地球来的信息：火星的盆友，你好，我是来自地球的sanjin");
                startActivityForResult(intent,0x01);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0x00){
            String extra = data.getStringExtra("003");//月球来的信息
            x_s1.setText(extra);
        }
        else if (requestCode==0x01){
            String extra = data.getStringExtra("004");//火星来的信息
            x_s1.setText(extra);
        }
    }
}
