package com.example.demo_no10_app23;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    // 数据库名
    private static final String DATABASE_NAME = "finch.db";
    // 表名
    public static final String USER_TABLE_NAME = "user";

    //数据库版本号
    private static final int DATABASE_VERSION = 1;

    public DbHelper(@Nullable Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String sql = "CREATE TABLE user(用户名 TEXT, 密码 INTEGER, 年龄 INTEGER, 邮箱 varchar)";
        db.execSQL(sql);
//        db.execSQL("CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME +
//                "(用户名 TEXT PRIMARY KEY AUTOINCREMENT," + " mima INTEGER,"+ "year INTEGER,"
//        +"邮箱 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
