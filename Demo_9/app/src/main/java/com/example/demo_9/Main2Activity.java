package com.example.demo_9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1;
    private Button btn2;
    private TextView mtxt;
    private ImageView image1;
    private String[] titles;
    private int[] images;
    private int index;
    private int alpha;
    private Button btn3;
    private Button btn4;
    private Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn5 = findViewById(R.id.btnexit);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        inintView();
        inintData();
    }
    /*初始化数据*/
    private void inintData() {
        titles = new String[]{"第一张图片","第二张图片","第三张图片","第四张图片","第五张图片","第六张图片"};
        images = new int[]{R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5, R.drawable.cat };
        image1.setImageResource(images[0]);//初始化图片
        mtxt.setText(titles[0]);//初始化名称

        index = 0;
        alpha = 255;

    }

    /*初始化界面*/
    private void inintView() {
        btn1 = findViewById(R.id.btnLast);
        btn2 = findViewById(R.id.btnNext);
        btn3 = findViewById(R.id.btnPlus);
        btn4 = findViewById(R.id.btnMinus);

        mtxt = findViewById(R.id.txt);
        image1 = findViewById(R.id.image1);


        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLast:
                if( index==0 ){
                    Toast.makeText(getApplicationContext(),"已经到顶了",Toast.LENGTH_SHORT).show();
                    //index=titles.length-1; //实现循环 跳至最后一张

                }else{
                    index--;
                }
                break;

            case R.id.btnNext:
                if( index==titles.length-1 ){
                    Toast.makeText(getApplicationContext(),"已经到底了",Toast.LENGTH_SHORT).show();
                    //index=0; //实现循环  跳至第一张
                }else{
                    index++;
                }
                break;
        }
        if( view==btn4 ) alpha+=20;
        if( view==btn3 ) alpha-=20;
        if( alpha>=255 ) alpha=255;
        if( alpha<=0 ) alpha=0;
        image1.setImageAlpha(alpha);  //设置透明度

        updateImadeAndTitle();

    }

    private void updateImadeAndTitle() {
        image1.setImageResource(images[index]);
        mtxt.setText(titles[index]);
    }

}
