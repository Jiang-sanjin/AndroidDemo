package com.example.demo_app13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private TextView txt1;
    private TextView txt2;
    private Button btn1;
    String sex;
    double biaozhun;
    double flag;
    int weight;
    private TextView txt3;
    private ImageView image1;
    private ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initview();

        sex = getIntent().getStringExtra("001");
        biaozhun = getIntent().getDoubleExtra("002",biaozhun);
        weight = getIntent().getIntExtra("003",weight);
        txt1.setText("您的性别是"+sex);
        txt2.setText("您的标准体重是"+biaozhun+"kg");

        if(sex.equals("男")){
            image1.setImageResource(R.drawable.k);
        }
        else if(sex.equals("女")){
            image1.setImageResource(R.drawable.j);
        }
        show();  //体重是否正常的提示及相应页面变化




        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



    public void initview() {
        txt1 = findViewById(R.id.textView1);
        txt2 = findViewById(R.id.textView2);
        txt3 = findViewById(R.id.textView3);
        btn1 = findViewById(R.id.button1);
        image1 = findViewById(R.id.imageView1);
        image2 = findViewById(R.id.imageView2);

    }

    public void show() {

        if( (weight)< (biaozhun*0.9)){
            txt3.setText("偏瘦哦，多吃点啊(。・∀・)ノ");
            if(sex.equals("男")){
                image2.setImageResource(R.drawable.boy);
            }
            else if(sex.equals("女")){
                image2.setImageResource(R.drawable.girl);
            }
        }
        else if( (weight)>= (biaozhun*0.9) && (weight)<=(biaozhun*1.1)){
            txt3.setText("您的体重基本正常，抽空可以增加适量运动(。・∀・)ノ");
            if(sex.equals("男")){
                image2.setImageResource(R.drawable.boy);
            }
            else if(sex.equals("女")){
                image2.setImageResource(R.drawable.girl);
            }
        }
        else if(weight>biaozhun*1.1 && weight<=biaozhun*1.2){
            txt3.setText("您的体重有点超重啦，要抽空可以增加适量运动哦(。・∀・)ノ");
            if(sex.equals("男")){
                image2.setImageResource(R.drawable.boy);
            }
            else if(sex.equals("女")){
                image2.setImageResource(R.drawable.girl);
            }
        }
        else if(weight>biaozhun*1.2 && weight<=biaozhun*1.3){
            txt3.setText("您已经轻度肥胖了哦，要多多运动哦╰（‵□′）╯");
            if(sex.equals("男")){
                image2.setImageResource(R.drawable.boy2);
            }
            else if(sex.equals("女")){
                image2.setImageResource(R.drawable.girl2);
            }
        }
        else if(weight>biaozhun*1.3 && weight<=biaozhun*1.5){
            txt3.setText("要多多运动哦╰（‵□′）╯,现在还是中度可爱胖，再下去就。。。 ＞﹏＜");
            if(sex.equals("男")){
                image2.setImageResource(R.drawable.boy2);
            }
            else if(sex.equals("女")){
                image2.setImageResource(R.drawable.girl2);
            }
        }
        else {
            txt3.setText("宝贝，重度就不是可爱胖了，快去运动╰（‵□′）╯");
            if(sex.equals("男")){
                image2.setImageResource(R.drawable.boy3);
            }
            else if(sex.equals("女")){
                image2.setImageResource(R.drawable.girl3);
            }
        }

    }
}
