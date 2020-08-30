package com.example.demo_no10_app23;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyProvide extends ContentProvider {

    private Context mContext;
    DbHelper mDbHelper = null;
    SQLiteDatabase db = null;

    // 设置ContentProvider的唯一标识
    public static final String AUTOHORITY = "com.example.demo_no10_app23";

    public static final int User_Code = 1;

    // UriMatcher类使用:在ContentProvider 中注册URI
    private static final UriMatcher mMatcher;

    static{
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // 初始化C
        // 若URI资源路径 = com.example.demo_no10_app23/user ，则返回注册码User_Code

        mMatcher.addURI(AUTOHORITY,"user", User_Code);


    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        // 在ContentProvider创建时对数据库进行初始化
        // 运行在主线程，故不能做耗时操作,此处仅作展示
        mDbHelper = new DbHelper(getContext());
        db = mDbHelper.getWritableDatabase();

//        清空表
//       db.execSQL("delete from user");

        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s,
                        @Nullable String[] strings1, @Nullable String s1) {
        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        // 该方法在最下面
        String table = getTableName(uri);

//        // 通过ContentUris类从URL中获取ID
//        long personid = ContentUris.parseId(uri);
//        System.out.println(personid);

        // 查询数据
        return db.query(table,strings,s,strings1,null,null,s1,null);

//        query( table, columns, selection, selectionArgs, groupBy, having, orderBy, limit );
//        table：表名。
//        columns：要查询出来的列名。
//        selection：查询条件子句。
//        selectionArgs：对应于selection语句中占位符的值。
//        groupBy：分组。相当于select语句group by关键字后面的部分。
//        having：分组后聚合的过滤条件。相当于select语句having关键字后面的部分。
//        orderBy：排序。相当于select语句order by关键字后面的部分 ASC或DESC。
//        limit：指定偏移量和获取的记录数。


    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }



//   *********************** 添加数据
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        // 该方法在最下面
        String table = getTableName(uri);

        // 向该表添加数据
        db.insert(table, null, values);

        // 当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        mContext.getContentResolver().notifyChange(uri, null);

//        // 通过ContentUris类从URL中获取ID
//        long personid = ContentUris.parseId(uri);
//        System.out.println(personid);

        return uri;
    }


//   **************** 删除数据
    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        int count = 0;
        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        // 该方法在最下面
        String table = getTableName(uri);

        // 删除表中的数据
         count = db.delete(table, s, strings);

        // 当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        mContext.getContentResolver().notifyChange(uri, null);

//        // 通过ContentUris类从URL中获取ID
//        long personid = ContentUris.parseId(uri);
//        System.out.println(personid);

        return count;
    }

//   ******************* 修改数据
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {

        // 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
        // 该方法在最下面
        String table = getTableName(uri);

        // 修改数据
        db.update(table, contentValues,s,strings);

        // 当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        mContext.getContentResolver().notifyChange(uri, null);

        return 0;
    }

    /**
     * 根据URI匹配 URI_CODE，从而匹配ContentProvider中相应的表名
     */
    private String getTableName(Uri uri){
        String tableName = null;
        switch (mMatcher.match(uri)) {
            case User_Code:
                tableName = DbHelper.USER_TABLE_NAME;
                break;

        }
        return tableName;
    }
}
