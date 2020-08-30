package com.example.demo_app14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    int[] images =new int[]{R.drawable.kaoshi,R.drawable.kaoshi2,R.drawable.kaoshi3,R.drawable.kaoshi4,R.drawable.kaoshi5,R.drawable.kaoshi6};
    String[] items =  new String[]{"成人高考","成人中专自学考试","成人大专自学考试","在职研究生","在职博士生","在职博士后"};
    String[] items1 =  new String[]{"已报名555人","已报名660人","已报名1000人","已报名6000人","已报名200人","已报名50人"};
    private ArrayList<Map<String, Object>> list;
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listview);


      /*  1.实现list中的具体数据*/
        list = new ArrayList<>();
        for( int i=0; i<items.length; i++ ){
             Map<String,Object> map = new HashMap<>();
             map.put("001",images[i]);
             map.put("002",items[i]);
             map.put("003",items1[i]);
             list.add(map);
        }

       /* 2.设置布局文件*/
        adapter = new SimpleAdapter(getApplicationContext(), list, R.layout.item,
                new String[]{"001", "002","003"}, new int[]{R.id.imageView, R.id.textView1,R.id.textView2}){


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
              if (convertView==null){
                 convertView=View.inflate(MainActivity.this,R.layout.item,null);
             }
                final Button button = (Button) convertView.findViewById(R.id.button);
                button.setTag(position);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                        startActivity(intent);
                    }
                });


                return super.getView(position, convertView, parent);
            }

        };





        /*3.设置adapter适配器*/
        listview.setAdapter(adapter);
        /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), items[i], Toast.LENGTH_SHORT).show();

            }
        });*/

    }
}
