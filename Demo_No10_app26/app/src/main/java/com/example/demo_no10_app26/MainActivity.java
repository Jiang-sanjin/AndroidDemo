package com.example.demo_no10_app26;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView text;
    private Button button;
    private ArrayList<String> list;
    private List<LocalBean> mDatas;
    private LocalAdapter adapter;
    private RecyclerView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        phone = findViewById(R.id.phone);

        button = findViewById(R.id.button);
        mDatas = new ArrayList<>();
//创建适配器
        adapter = new LocalAdapter(this, mDatas);
        phone.setAdapter(adapter);
//        设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        phone.setLayoutManager(layoutManager);
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }




        button.setOnClickListener(new View.OnClickListener() {

            private String name;
            private String number;

            @Override
            public void onClick(View v) {

//  ********************  清空容器数据
                mDatas.clear();
//  ********************  清空容器数据

                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

                while (cursor.moveToNext()){

                    name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

                    int id=cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));

                    Cursor cursor1 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
                    while(cursor1.moveToNext()){

                        number = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


                    }

                    LocalBean bean = new LocalBean(name,number);

                    mDatas.add(bean);  //添加元素
                }
                adapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(),"获取成功",Toast.LENGTH_SHORT).show();

            }

        });


    }
}
