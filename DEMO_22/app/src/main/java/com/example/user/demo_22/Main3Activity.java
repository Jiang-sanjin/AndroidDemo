package com.example.user.demo_22;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main3Activity extends AppCompatActivity {

    private ListView listView;
    String [] item1=new String[]{"小学考试","初中考试","中专考试","高中考试","大专考试","大学考试"};
    String [] item2=new String[]{"已报名65123人","已报名12345人","已报名54321人","已报名465678人","已报名87654人","已报名43210人"};
    int [] images= new int[]{R.drawable.q1,R.drawable.q2,R.drawable.q3,R.drawable.q4,R.drawable.q5,R.drawable.q6};
    private ArrayList<Map<String, Object>> list;
    private SimpleAdapter adapter;
    private TextView user2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        listView = (ListView) findViewById(R.id.listView);

        list = new ArrayList<>();
        for (int i=0;i<item1.length;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("001",images[i]);
            map.put("002",item1[i]);
            map.put("003",item2[i]);
            list.add(map);
        }
        adapter = new SimpleAdapter(getApplicationContext(),list, R.layout.item,
                new String[]{"001","002","003"},new int[]{R.id.imageView,R.id.textView,R.id.tx2}){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView==null){
                    convertView=View.inflate(Main3Activity.this,R.layout.item,null);

                }
                final Button button = (Button) convertView.findViewById(R.id.button);
                button.setTag(position);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(),Main4Activity.class);
                        startActivity(intent);
                    }
                });


                return super.getView(position, convertView, parent);
            }
        };
        listView.setAdapter(adapter);


    }
}
