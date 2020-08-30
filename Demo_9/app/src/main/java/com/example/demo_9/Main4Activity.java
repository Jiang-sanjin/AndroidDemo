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
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    private Uri uri_user;

    private Cursor cursor;
    private TextView id,password,year,email;
    private Button btn1,btn2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        // 设置URI
        uri_user = Uri.parse("content://com.example.demo_no10_app23/user");
        id = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        year = findViewById(R.id.editText3);
        email = findViewById(R.id.editText4);
        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!id.getText().toString().equals("")&&!password.getText().toString().equals("")
                        &&!year.getText().toString().equals("")&&!email.getText().toString().equals("")) {
                    ContentValues values = new ContentValues();

                    values.put("密码", Integer.parseInt(password.getText().toString()));
                    values.put("年龄", Integer.parseInt(year.getText().toString()));
                    values.put("邮箱", email.getText().toString());
                    // 获取ContentResolver
                    ContentResolver resolver3 =  getContentResolver();
                    resolver3.update(uri_user, values, "用户名=?", new String[]{id.getText().toString()});
                    Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
                    id.setText("");
                    password.setText("");
                    year.setText("");
                    email.setText("");
                }
                else Toast.makeText(getApplicationContext(),"请输入完整信息",Toast.LENGTH_SHORT).show();
            }
        });

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
