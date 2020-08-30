package com.ccce.demo6_4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {


    private ListView listView;
    String [] items=new String[]{"百度","QQ","微信","支付宝","腾讯视频","网易云音乐"};
    String [] items1=new String[]{"新闻头条热点视频","乐在沟通21年，聊天欢乐9亿人","微信，是一个生活方式","支付就用支付宝","安家独播","优异口碑，千万曲库免费下载"};
    int[] images=new int[]{R.drawable.baidu,R.drawable.qq,R.drawable.wechat,R.drawable.zhifubao,R.drawable.tengxunshipin,R.drawable.wangyiyun};
    String btn11="安装";
    private ArrayList<Map<String, Object>> list;
    private SimpleAdapter adapter;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            listView = (ListView) findViewById(R.id.listView);

            list = new ArrayList<>();
            for (int i=0;i<items.length;i++){
                Map<String,Object> map=new HashMap<>();
                map.put("001",images[i]);
                map.put("002",items[i]);
                map.put("003",items1[i]);
                map.put("004",btn11);
                list.add(map);
            }

            adapter = new SimpleAdapter(getApplicationContext(), list, R.layout.bg, new String[]{"001","002","003","004"},
                    new int[]{R.id.imageView, R.id.textView,R.id.tx2,R.id.button}){
                @Override
                public View getView(final int position, View convertView, ViewGroup parent) {
                    if (convertView==null){
                        convertView=View.inflate(MainActivity.this,R.layout.bg,null);
                    }

                   final Button button=(Button) convertView.findViewById(R.id.button);
                    button.setTag(position);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String btnname=(String)button.getText();
                            Toast.makeText(getApplicationContext(),"正在安装"+items[position],Toast.LENGTH_SHORT).show();
                        }
                    });
                    return super.getView(position, convertView, parent);
                }
            };



            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //Toast.makeText(getApplicationContext(),items[position]+images[position],Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getApplicationContext(),Main2Activity.class);
                    intent.putExtra("011",items[position]);
                    intent.putExtra("012",String.valueOf(position));
                    intent.putExtra("013",items1[position]);
                    startActivity(intent);





                }
            });









        }

}
