package com.example.demo_app15;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private Button btn2;
    private Button btn3;
    private TextView txt2;
    private ImageView image2;
    private TextView txt3;
    private String s1;
    private String s2;
    int[] images=new int[]{R.drawable.wangyiyun,R.drawable.qq,R.drawable.wechat,R.drawable.baidu,R.drawable.zhifubao,R.drawable.tengxunshipin};
    private int im;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

       /* 加载进度条对话框*/
        final ProgressDialog prodialog;
        prodialog = new ProgressDialog(this);
        prodialog.setTitle("进度条");
        prodialog.setIcon(R.drawable.love);
        prodialog.setMessage("正在下载请等待...");
        prodialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);  /*圆形对话框*/



        s1=getIntent().getStringExtra("011");
        s2=getIntent().getStringExtra("013");


        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        im=Integer.parseInt(getIntent().getStringExtra("012"));

        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                prodialog.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        prodialog.dismiss();
                        Toast.makeText(getApplicationContext(),s1+"下载成功",Toast.LENGTH_SHORT).show();
                    }
                },5000);


            }
        });

        txt2 = (TextView) findViewById(R.id.txt2);
        txt2.setText(s1);

        image2 = (ImageView) findViewById(R.id.image2);

        image2.setImageResource(images[im]);

        txt3 = (TextView) findViewById(R.id.txt3);
        txt3.setText(s2);

    }
}
