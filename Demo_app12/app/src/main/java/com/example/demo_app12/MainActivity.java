package com.example.demo_app12;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private RadioGroup raioGroup;
    private Button btn1;
    private CheckBox check1;
    private CheckBox check2;
    private CheckBox check3;
    private Button btn2;
    private String like;
    private ImageView image1;
    private ToggleButton togbtn;
    private Switch switch1;
    public String[] item = new String[]{"","",""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        btn1.setOnClickListener(new View.OnClickListener() {


            private RadioButton radiobtn;

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.cat);
                builder.setTitle("提示");
              for ( int i=0; i<raioGroup.getChildCount(); i++ ){
                    radiobtn = (RadioButton) raioGroup.getChildAt(i);
                    if(radiobtn.isChecked()){
                           builder.setMessage("你最喜欢的网课软件是："+radiobtn.getText().toString());
                        //*Toast.makeText(getApplicationContext(),"你最喜欢的网课软件是："+radiobtn.getText().toString(),Toast.LENGTH_SHORT).show();*//*
                    }
                }

              builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      Toast.makeText(getApplicationContext(),"okok",Toast.LENGTH_SHORT).show();
                  }
              });
              builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      Toast.makeText(getApplicationContext(),"nono",Toast.LENGTH_SHORT).show();
                  }
              });
                builder.show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                item = new String[]{"","",""};
                final CheckBox[] checks = new CheckBox[]{check1, check2, check3};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.cat);
                builder.setTitle("提示:请从中选择最好的一个方面");
                for (int i = 0; i < checks.length; i++) {
                    if (checks[i].isChecked()) {
                        item[i] += checks[i].getText().toString() ;
                    }

                }
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       Toast.makeText(getApplicationContext(),"选择是:"+item[i],Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();

 /*               for (int i = 0; i < checks.length; i++) {
                    if (checks[i].isChecked()) {
                        like += checks[i].getText().toString() + ";";
                    }
                }

                if (like != "") {
                    like = like.substring(0, like.length() - 1);
                    Toast.makeText(getApplicationContext(), "该软件做的不错的方面有：" + like, Toast.LENGTH_SHORT).show();

                }*/
            }
        });


        togbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    image1.setImageResource(R.drawable.h1_1);
                }else{
                    image1.setImageResource(R.drawable.h1);
                }
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    image1.setImageResource(R.drawable.h1_1);
                }else{
                    image1.setImageResource(R.drawable.h1);
                }
            }
        });
    }

    public void initView() {
        raioGroup = findViewById(R.id.RadioGroup);
        btn1 = findViewById(R.id.button1);
        check1 = findViewById(R.id.checkBox1);
        check2 = findViewById(R.id.checkBox2);
        check3 = findViewById(R.id.checkBox3);
        btn2 = findViewById(R.id.button2);
        image1 = findViewById(R.id.image1);
        togbtn = findViewById(R.id.toggleButton);
        switch1 = findViewById(R.id.switch1);
    }
}
