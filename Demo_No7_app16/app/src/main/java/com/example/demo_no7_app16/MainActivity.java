package com.example.demo_no7_app16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String[] name = new String[]{ "应用商城","相册","成人考试","问卷调查","标准体重","飞向太空","生日贺卡","语言播放",
            "QQ","微信","支付宝","网易音乐","腾讯视频","百度","静态加载","动态添加",
    };
    int[] images = new int[]{R.drawable.store,R.drawable.photo,R.drawable.kaoshi,R.drawable.wenjuan,
            R.drawable.tizhong,R.drawable.taikong,R.drawable.heka,R.drawable.yuyin,
            R.drawable.qq,R.drawable.wechat,R.drawable.zhifubao,R.drawable.wangyiyun,
            R.drawable.tengxunshipin,R.drawable.baidu,R.drawable.jingtai,R.drawable.dongtai,
    };
    private ArrayList<Map<String, Object>> list;
    private SimpleAdapter adapter;
    private GridView gridview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        list = new ArrayList<>();

        for (int i=0;i<images.length;i++){
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("001",images[i]);
            map.put("002",name[i]);

            list.add(map);
        }


        adapter = new SimpleAdapter(getApplicationContext(), list, R.layout.bg, new String[]{"001", "002"},
                new int[]{R.id.imageView, R.id.textView});

        gridview = findViewById(R.id.gridview);

      gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              if(i==0){
                  //商城
                    Intent itent = new Intent();
                    itent.setClassName("com.example.demo_app15","com.example.demo_app15.MainActivity");
                    startActivity(itent);

              }
              if (i==1){
                  //相册
                  Intent itent = new Intent();
                  itent.setClassName("com.example.demo_9","com.example.demo_9.Main3Activity");
                  startActivity(itent);
              }
              if (i==2){
                  //成人考试
                  Intent itent = new Intent();
                  itent.setClassName("com.example.demo_app14","com.example.demo_app14.Main5Activity");
                  startActivity(itent);
              }
              if (i==3){
                  //问卷调查
                  Intent itent = new Intent();
                  itent.setClassName("com.example.demo_app12","com.example.demo_app12.MainActivity");
                  startActivity(itent);
              }
              if (i==4){
                  //标准体重计算
                  Intent itent = new Intent();
                  itent.setClassName("com.example.demo_app13","com.example.demo_app13.Main3Activity");
                  startActivity(itent);
              }
              if (i==5){
                  //飞向太空
                  Intent itent = new Intent();
                  itent.setClassName("com.example.demo_app10","com.example.demo_app10.MainActivity");
                  startActivity(itent);
              }
              if (i==6){
                  //生日贺卡
                  Intent itent = new Intent();
                  itent.setClassName("com.example.demo_app11","com.example.demo_app11.MainActivity");
                  startActivity(itent);
              }
              if(i==7){
                  //语言播放
                  Intent intent = new Intent(getApplicationContext(),Main4Activity.class);
                  startActivity(intent);

              }

              if(i==8){
//                  跳转网页
                  Intent intent = new Intent();
                  intent.setAction(Intent.ACTION_VIEW);

                  Uri uri = Uri.parse("https://im.qq.com/");
                  intent.setData(uri);
                  startActivity(intent);

              }
              if(i==9){
//                  跳转网页
                  Intent intent = new Intent();
                  intent.setAction(Intent.ACTION_VIEW);
                  Uri uri = Uri.parse("https://weixin.qq.com/");
                  intent.setData(uri);
                  startActivity(intent);
              }
              if(i==10){
//                  跳转网页
                  Intent intent = new Intent();
                  intent.setAction(Intent.ACTION_VIEW);
                  Uri uri = Uri.parse("https://www.alipay.com/");
                  intent.setData(uri);
                  startActivity(intent);
              }
              if(i==11){
//                  跳转网页
                  Intent intent = new Intent();
                  intent.setAction(Intent.ACTION_VIEW);
                  Uri uri = Uri.parse("https://music.163.com/");
                  intent.setData(uri);
                  startActivity(intent);
              }
              if(i==12){
//                  跳转网页
                  Intent intent = new Intent();
                  intent.setAction(Intent.ACTION_VIEW);
                  Uri uri = Uri.parse("https://v.qq.com/");
                  intent.setData(uri);
                  startActivity(intent);
              }
              if(i==13){

                  //    自制百度跳转
                  Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
                  startActivity(intent);
              }
              if (i==14){
                  //静态加载
                  Intent itent = new Intent();
                  itent.setClassName("com.example.demo_no7","com.example.demo_no7.MainActivity");
                  startActivity(itent);
              }
              if (i==15){
                  //动态添加
                  Intent itent = new Intent();
                  itent.setClassName("com.example.demo_no7_1","com.example.demo_no7_1.MainActivity");
                  startActivity(itent);
              }

          }
      });

        gridview.setAdapter(adapter);
    }
}
