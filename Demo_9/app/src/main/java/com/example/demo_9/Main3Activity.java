package com.example.demo_9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {

    private Uri uri_user;
    private EditText id,password,year,email;
    private Button btn2;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        inint();
        // 设置URI
        uri_user = Uri.parse("content://com.example.demo_no10_app23/user");
    }
    public void insert(View view){
        // 插入表中数据

        // 获取ContentResolver
        ContentResolver resolver =  getContentResolver();
        if (!id.getText().toString().equals("")&&!password.getText().toString().equals("")
                &&!year.getText().toString().equals("")&&!email.getText().toString().equals("")){
//            String sql = "select * from user where 编号=?";
            //cursor  是游标
            cursor = resolver.query(uri_user,new String[]{"用户名","密码","年龄","邮箱"},"用户名=?",new String[]{id.getText().toString()},null, null);
            if (cursor.getCount() ==0 ){
                ContentValues values = new ContentValues();
                values.put("用户名", id.getText().toString());
                values.put("密码", Integer.parseInt(password.getText().toString()));
                values.put("年龄", Integer.parseInt(year.getText().toString()));
                values.put("邮箱", email.getText().toString());
                resolver.insert(uri_user,values);
                Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
                id.setText("");
                password.setText("");
                year.setText("");
                email.setText("");
            }
            else{
                Toast.makeText(getApplicationContext(),"该用户已存在，请重新输入",Toast.LENGTH_SHORT).show();
            }

            cursor.close();
            // 关闭游标
        }
        else Toast.makeText(getApplicationContext(),"请输入完整信息",Toast.LENGTH_SHORT).show();




    }

    private void inint(){

//        加载控件
        id = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        year = findViewById(R.id.editText3);
        email = findViewById(R.id.editText4);
        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
