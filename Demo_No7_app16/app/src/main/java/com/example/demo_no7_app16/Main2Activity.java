package com.example.demo_no7_app16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    String[] digit = new String[]{"1","2","3","4","5","6","7","8","9","紧急呼叫","0","返回"};
    private GridView gridview;
    private EditText edit;
    private ArrayList<Map<String, Object>> list;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        gridview = findViewById(R.id.gridView2);
        edit = findViewById(R.id.editText);

        list = new ArrayList<>();
        for(int i=0; i<digit.length; i++){
            Map<String,Object> map = new HashMap<>();
            map.put("digit",digit[i]);
            list.add(map);

        }
        adapter = new SimpleAdapter(getApplicationContext(), list, R.layout.digit,
                new String[]{"digit"}, new int[]{R.id.textView3});

        gridview.setAdapter(adapter);

        edit.append("");
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i==11 ){
                    if (!edit.getText().toString().equals(""))
                        edit.setText(edit.getText().toString().substring(0,edit.getText().length()-1));
                }else {
                    edit.append(digit[i]);

                    if (edit.getText().toString().equals("123456")) {
                        Intent intent = new Intent();
                        intent.setClass(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    } else if (edit.getText().length() == 6) {
                        Toast.makeText(getApplicationContext(), "输入密码错误", Toast.LENGTH_SHORT).show();
                        edit.setText("");
                    }
                }
            }
        });

    }
}
