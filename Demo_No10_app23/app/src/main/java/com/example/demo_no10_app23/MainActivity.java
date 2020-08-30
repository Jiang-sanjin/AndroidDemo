package com.example.demo_no10_app23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Uri uri_user;
    private View view;
    private EditText id,password,year,find_id,email;
    private TextView txt;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                id.setText("");
                password.setText("");
                year.setText("");
                email.setText("");
            }
            else{
                Toast.makeText(getApplicationContext(),"该用户已存在，请重新输入",Toast.LENGTH_SHORT).show();
            }

        }
        else Toast.makeText(getApplicationContext(),"请输入完整信息",Toast.LENGTH_SHORT).show();

        cursor.close();
       // 关闭游标


    }

    public void delete(View view){


        //        删除
        if (!id.getText().toString().equals("")) {
            int flag = 0;
            ContentResolver resolver2 =  getContentResolver();

            flag = resolver2.delete(uri_user,  "用户名=?", new String[]{id.getText().toString()});
            if(flag!=0){
                Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "该用户不存在", Toast.LENGTH_SHORT).show();
            }

            id.setText("");
            password.setText("");
            year.setText("");
            email.setText("");
        }
        else Toast.makeText(getApplicationContext(),"请输入完整信息",Toast.LENGTH_SHORT).show();




    }

    public void updata(View view){
//        修改

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

    public void find_id(View view){

        txt.setText("");
        // 获取ContentResolver
        ContentResolver resolver =  getContentResolver();
        // 通过ContentResolver 向ContentProvider中查询数据
        cursor = resolver.query(uri_user,new String[]{"用户名","密码","年龄","邮箱"},"用户名=?",new String[]{find_id.getText().toString()},null, null);
        while (cursor.moveToNext()){
            String id2 = cursor.getString(0);
//            int password2 = cursor.getInt(1);
            int year2 = cursor.getInt(2);
            String email2 = cursor.getString(3);

            txt.append("用户名:"+id2+"\t\t\t\t年龄:"+year2+"\n"+"邮箱:"+email2+"\n"+"\n");

        }
        find_id.setText("");


    }

    public void quanbu(View view) {
        txt.setText("");
        // 获取ContentResolver
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri_user, new String[]{"用户名", "密码", "年龄", "邮箱"}, null, null, null);
        while (cursor.moveToNext()){
            String id2 = cursor.getString(0);
//            int password2 = cursor.getInt(1);
            int year2 = cursor.getInt(2);
            String email2 = cursor.getString(3);

            txt.append("用户名:"+id2+"\t\t\t\t年龄:"+year2+"\n"+"邮箱:"+email2+"\n"+"\n");

        }
        find_id.setText("");
    }


    private void inint(){

//        加载控件
        id = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        year = findViewById(R.id.editText3);
        email = findViewById(R.id.editText4);
        find_id = findViewById(R.id.editText5);
        txt = findViewById(R.id.textView6);
    }


}
