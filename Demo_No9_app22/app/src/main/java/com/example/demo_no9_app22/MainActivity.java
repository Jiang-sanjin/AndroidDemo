package com.example.demo_no9_app22;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText number,name,year,find_number,find_name;
    private TextView txt;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inint();
        Mydatabase mydatabase = new Mydatabase(MainActivity.this);
        database = mydatabase.getReadableDatabase();


    }

    public void insert(View view){
////        第一种插入数据 //////////////////////////
//        String sql = "insert into user(编号,姓名,年龄)values(?,?,?)";
//        database.execSQL(sql,new Object[]{Integer.parseInt(number.getText().toString()),
//        name.getText().toString(),Integer.parseInt(year.getText().toString())});


//////////////////  第二种插入数据方法////////////////////////////
        if (!number.getText().toString().equals("")&&!name.getText().toString().equals("")&&!year.getText().toString().equals("")){
            String sql = "select * from user where 编号=?";
            Cursor cursor = database.rawQuery(sql,new String[]{number.getText().toString()});   //cursor  是游标
            if (cursor.getCount() ==0 ){
                ContentValues contentValues = new ContentValues();
                contentValues.put("编号",Integer.parseInt(number.getText().toString()));
                contentValues.put("姓名",name.getText().toString());
                contentValues.put("年龄",Integer.parseInt(year.getText().toString()));
                database.insert("user",null,contentValues);
                Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                number.setText("");
                name.setText("");
                year.setText("");
            }
            else{
                Toast.makeText(getApplicationContext(),"数据已经存在，请重新输入编号",Toast.LENGTH_SHORT).show();
            }

        }
        else Toast.makeText(getApplicationContext(),"请输入完整信息",Toast.LENGTH_SHORT).show();
    }

    public void delete(View view){
        if (!number.getText().toString().equals("")) {
            String sql = "delete from user where 编号=?";
            database.execSQL(sql, new Object[]{Integer.parseInt(number.getText().toString())});
            Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
            number.setText("");
            name.setText("");
            year.setText("");
        }
        else Toast.makeText(getApplicationContext(),"请输入完整信息",Toast.LENGTH_SHORT).show();
    }

    public void updata(View view){
        if (!number.getText().toString().equals("")&&!name.getText().toString().equals("")&&!year.getText().toString().equals("")) {
            String sql = "update  user set 姓名=?,年龄=? where 编号=?";
            database.execSQL(sql,new Object[]{name.getText().toString(),Integer.parseInt(year.getText().toString()),
                    Integer.parseInt(number.getText().toString())   });
            Toast.makeText(getApplicationContext(),"修改成功",Toast.LENGTH_SHORT).show();
            number.setText("");
            name.setText("");
            year.setText("");
        }
        else Toast.makeText(getApplicationContext(),"请输入完整信息",Toast.LENGTH_SHORT).show();
    }

    public void find_number(View view){

        txt.setText("");
        String sql = "select * from user where 编号=?";
        Cursor cursor = database.rawQuery(sql, new String[]{find_number.getText().toString()});

        while (cursor.moveToNext()){
            String name2 = cursor.getString(cursor.getColumnIndex("姓名"));
            int number2 = cursor.getInt(cursor.getColumnIndex("编号"));
            int year2 = cursor.getInt(cursor.getColumnIndex("年龄"));

            txt.append("编号:"+number2+"\t\t姓名:"+name2+"\t\t年龄:"+year2+"\n");

        }
        find_number.setText("");


    }

    public void find_name(View view){
        txt.setText("");
        String sql = "select * from user where 姓名=?";
        Cursor cursor = database.rawQuery(sql, new String[]{find_name.getText().toString()});

        while (cursor.moveToNext()){
            String name2 = cursor.getString(cursor.getColumnIndex("姓名"));
            int number2 = cursor.getInt(cursor.getColumnIndex("编号"));
            int year2 = cursor.getInt(cursor.getColumnIndex("年龄"));

            txt.append("编号:"+number2+"\t\t姓名:"+name2+"\t\t年龄:"+year2+"\n");

        }
        find_name.setText("");
    }

    public void quanbu(View view){
        txt.setText("");
        String sql="select * from user";
        Cursor cursor = database.rawQuery(sql,null);
        while (cursor.moveToNext()){
            int bh = cursor.getInt(cursor.getColumnIndex("编号"));
            String xm = cursor.getString(cursor.getColumnIndex("姓名"));
            int nl = cursor.getInt(cursor.getColumnIndex("年龄"));
            txt.append("编号:" + bh + "\t\t姓名:" + xm + "\t\t年龄:" + nl+"\n");
        }
    }

    private void inint() {
        number = findViewById(R.id.editText);
        name = findViewById(R.id.editText2);
        year = findViewById(R.id.editText3);
        find_number = findViewById(R.id.editText4);
        find_name = findViewById(R.id.editText5);
        txt = findViewById(R.id.textView6);
    }

}


