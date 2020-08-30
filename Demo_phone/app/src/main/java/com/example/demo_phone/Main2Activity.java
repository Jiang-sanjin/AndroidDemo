package com.example.demo_phone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private ListView listview;

    String[] items  = new String[]{"大数据","云计算","数据挖掘","区块链","爬虫","大数据","云计算","数据挖掘","区块链","爬虫","数据挖掘","区块链","爬虫"};
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listview = findViewById(R.id.listview);


        adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item, items);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                builder.setIcon(R.drawable.love);
                builder.setTitle("提示");
                builder.setMessage("你选择的是"+items[i]);

                /* 定义dialog 并用Handler实现延迟关闭dialog*/
                final AlertDialog dialog = builder.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(dialog!=null && dialog.isShowing())
                            dialog.dismiss();
                    }
                },3000);
                /*Toast.makeText(getApplicationContext(),items[i],Toast.LENGTH_LONG).show();*/
            }
        });
    }
}
