package com.example.demo_no7;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button btn;

    String [] items=new String[]{"网易云音乐","QQ","微信","百度","支付宝","腾讯视频","网易云音乐","QQ","微信","百度","支付宝","腾讯视频"};
    String [] items1=new String[]{"懂你的音乐器","扣扣9亿人的选择","交友需谨慎","度娘都知道","支付就用支付宝","独家播放xxx",
            "懂你的音乐器","扣扣9亿人的选择","交友需谨慎","度娘都知道","支付就用支付宝","独家播放xxx"};
    int[] images = new int[]{R.drawable.wangyiyun,R.drawable.qq,R.drawable.wechat, R.drawable.baidu, R.drawable.zhifubao,R.drawable.tengxunshipin,
            R.drawable.wangyiyun,R.drawable.qq,R.drawable.wechat, R.drawable.baidu, R.drawable.zhifubao,R.drawable.tengxunshipin,
    };
    private ArrayList<Map<String, Object>> list;
    private SimpleAdapter adapter;
    private ListView listview;
    private ArrayAdapter<String> adapter1;
    private Myfrgment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listView);
        adapter1 = new ArrayAdapter<String>(MainActivity.this,R.layout.bg,items);
        listview.setAdapter(adapter1);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragment = (Myfrgment) fragmentManager.findFragmentById(R.id.ly_fragment);
                TextView txt = fragment.getView().findViewById(R.id.textView2);
                txt.setText(items1[i]);

                ImageView imageview = fragment.getView().findViewById(R.id.imageView);
                imageview.setImageResource(images[i]);
                Button btn1 = fragment.getView().findViewById(R.id.button);
                btn1.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        TextView txt1 = findViewById(R.id.textView2);
                        txt1.setText("BACK TO U");
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setIcon(R.drawable.love);
                        builder.setTitle("提示");
                        builder.setMessage("已返回");
                        final AlertDialog dialog = builder.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(dialog!=null && dialog.isShowing())
                                    dialog.dismiss();
                            }
                        },1500);
                    }
                });
            }
        });
    }

}
