package com.example.demo_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private EditText edit1;
    private EditText edit2;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit1 = (EditText) findViewById(R.id.editText1);
        edit2 = (EditText) findViewById(R.id.editText2);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit1.getText().toString();
                String password = edit2.getText().toString();

                if(name.equals("sanjin")&&password.equals("666666")){
                    Toast.makeText(getApplicationContext(),"欢迎您,已经成功登录！！！",Toast.LENGTH_SHORT).show();
                    flag = 1;
                }
                if(!name.equals("sanjin")){
                    Toast.makeText(getApplicationContext(),"用户名错误，请重新输入！！",Toast.LENGTH_SHORT).show();
                    edit1.setText(""); //执行清空edit1的数据
                    edit2.setText("");
                }
                if(!password.equals("666666")){
                    Toast.makeText(getApplicationContext(),"密码错误，请重新输入！！",Toast.LENGTH_SHORT).show();
                    edit2.setText("");
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( flag==1 ){
                    Toast.makeText(getApplicationContext(),"退出成功",Toast.LENGTH_SHORT).show();
                    flag = 0;
                    edit1.setText("");
                    edit2.setText("");
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"暂未开放此功能",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
