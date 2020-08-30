package com.example.demo_no7_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private Button btn2;
    private LinearLayout left;

    String [] items=new String[]{"网易云音乐","QQ","微信","百度","支付宝","腾讯视频","网易云音乐","QQ","微信","百度","支付宝","腾讯视频"};
    String [] items1=new String[]{"懂你的音乐器","扣扣9亿人的选择","交友需谨慎","度娘都知道","支付就用支付宝","独家播放xxx",
            "懂你的音乐器","扣扣9亿人的选择","交友需谨慎","度娘都知道","支付就用支付宝","独家播放xxx"};
    int[] images = new int[]{R.drawable.wangyiyun,R.drawable.qq,R.drawable.wechat, R.drawable.baidu, R.drawable.zhifubao,R.drawable.tengxunshipin,
            R.drawable.wangyiyun,R.drawable.qq,R.drawable.wechat, R.drawable.baidu, R.drawable.zhifubao,R.drawable.tengxunshipin,
    };
    private ListView listview;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> adapter1;
    int flag=-1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        left = findViewById(R.id.left);

        listview = findViewById(R.id.listView);
        adapter1 = new ArrayAdapter<String>(MainActivity.this, R.layout.bg, items);
        listview.setAdapter(adapter1);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                    //***********  传递数据（文字，图片）*****************************
                    Myfrgament myfrgament =new Myfrgament();
                    Bundle bundle = new Bundle();
                    bundle.putString("data",items1[i]);
                    bundle.putInt("data2",images[i]);
                    myfrgament.setArguments(bundle);

                    left.setLayoutParams(new LinearLayout.LayoutParams(getWindowManager().getDefaultDisplay().getWidth()*2/5,getWindowManager().getDefaultDisplay().getHeight()));
                    FragmentManager supportFragmentManager = getSupportFragmentManager();


                    //动态添加
                    FragmentTransaction transaction = supportFragmentManager.beginTransaction();
                    transaction.replace(R.id.main,myfrgament);

                    transaction.addToBackStack(null); //实现点击返回后，不用全部退出app



                    transaction.commit();



            }
        });

//       btn2 = findViewById(R.id.button3);
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                left.setLayoutParams(new LinearLayout.LayoutParams(getWindowManager().getDefaultDisplay().getWidth()*2/5,getWindowManager().getDefaultDisplay().getHeight()));
//                FragmentManager supportFragmentManager = getSupportFragmentManager();
//               //动态添加
//                 FragmentTransaction transaction = supportFragmentManager.beginTransaction();
//               transaction.replace(R.id.main,new Myfrgament());
//               transaction.addToBackStack(null); //实现点击返回后，不用全部退出app
//               transaction.commit();
//           }
//       });


    }
}
