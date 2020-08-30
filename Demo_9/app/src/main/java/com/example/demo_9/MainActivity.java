package com.example.demo_9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edit1;
    private EditText edit2;
    private Button btn1;
    private Button btn2;
    private Button btn3;

    private Uri uri_user;
    private Cursor cursor,cursor2;
    private TextView btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        uri_user = Uri.parse("content://com.example.demo_no10_app23/user");


        edit1 = (EditText) findViewById(R.id.editText1);

        edit2 = (EditText) findViewById(R.id.editText2);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = findViewById(R.id.textView4);

        btn1.setOnClickListener(
                new View.OnClickListener() {

                    private String name2;
                    private int password2;

                    @Override
            public void onClick(View view) {
                String name = edit1.getText().toString();
                String password = edit2.getText().toString();

                // 获取ContentResolver
                ContentResolver resolver =  getContentResolver();
                // 通过ContentResolver 向ContentProvider中查询数据

                cursor = resolver.query(uri_user,new String[]{"用户名","密码"},"用户名=?",new String[]{name},null, null);

                while (cursor.moveToNext()){
                    name2 = cursor.getString(0);
                    System.out.println("query book:" + cursor.getString(0) +" "+ cursor.getInt(1));
                    password2 = cursor.getInt(1);
                    if(name2.equals(name) ){
                        if(String.valueOf(password2).equals(password) ){
                            Toast.makeText(getApplicationContext(),"欢迎您,已经成功登录！！！",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent();
                            intent.setClass(getApplicationContext(),Main2Activity.class);
                            startActivity(intent);
                            edit1.setText(""); //执行清空edit1的数据
                            edit2.setText("");
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"密码错误",Toast.LENGTH_SHORT).show();
                            edit2.setText("");
                        }
                    }


                }


            }
        });



        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),Main3Activity.class);
                startActivity(intent);
                finish();
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),Main4Activity.class);
                startActivity(intent);
            }
        });
    }
}
