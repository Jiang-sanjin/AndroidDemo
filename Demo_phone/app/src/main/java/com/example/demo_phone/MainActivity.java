package com.example.demo_phone;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    String [] items = new String[]{"钉钉","中国大学mooc","雨课堂","学习通"};
    String content;
    String [] items2 = new String[]{"稳定 不经常崩溃","交作业方便","数据更新快","便于跟老师交流","稳定 不经常崩溃","交作业方便","数据更新快","便于跟老师交流"};
    boolean flag2[] = new boolean[]{false,false,false,false,false,false,false,false};
    String content2;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i =0;i<flag2.length;i++){
                    flag2[i]=false;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.love);
                builder.setTitle("多项选择");
                builder.setMultiChoiceItems(items2, flag2, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        flag2[i] = b;

                    }
                });

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        content2=" ";
                    for (int j=0; j<flag2.length; j++){

                        if(flag2[j]==true){
                            content2+=items2[j]+";";
                        }
                    }

                    if(content2.equals(" ")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setIcon(R.drawable.love);
                        builder.setTitle("提示");
                        builder.setMessage("你没有选择");
                        final AlertDialog dialog = builder.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(dialog!=null && dialog.isShowing())
                                    dialog.dismiss();
                            }
                        },3000);
                       /* Toast.makeText(getApplicationContext(),"你没有选择",Toast.LENGTH_LONG).show();*/
                       /* return;*/
                    }
                    else{
                    content2 = content2.substring(0,content2.length()-1);
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setIcon(R.drawable.love);
                        builder.setTitle("提示");
                        builder.setMessage("你选择了"+content2);

                       /* 定义dialog并用Handler实现延迟关闭dialog*/
                        final AlertDialog dialog = builder.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(dialog!=null && dialog.isShowing())
                                    dialog.dismiss();
                            }
                        },3000);

                   /* Toast.makeText(getApplicationContext(),"你选择了"+content2,Toast.LENGTH_LONG).show();*/
                    }
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setIcon(R.drawable.love);
                        builder.setTitle("提示");
                        builder.setMessage("你已取消");
                        final AlertDialog dialog = builder.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(dialog!=null && dialog.isShowing())
                                    dialog.dismiss();
                            }
                        },3000);
                    }
                });
                builder.show();

            }
        });



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(R.drawable.love);
                builder.setTitle("单选对话框");
                content = " ";
                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                            content = items[i];
                    }
                });

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(content.equals(" ")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setIcon(R.drawable.love);
                            builder.setTitle("提示");
                            builder.setMessage("你没有选择");
                            final AlertDialog dialog = builder.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(dialog!=null && dialog.isShowing())
                                        dialog.dismiss();
                                }
                            },3000);

                           /* Toast.makeText(getApplicationContext(),"你没有选择",Toast.LENGTH_LONG).show();*/
                        }
                        else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setIcon(R.drawable.love);
                            builder.setTitle("提示");
                            builder.setMessage("你选择的是"+content);
                            final AlertDialog dialog = builder.show();
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(dialog!=null && dialog.isShowing())
                                        dialog.dismiss();
                                }
                            },3000);
                            /*Toast.makeText(getApplicationContext(),"你选择的是"+content,Toast.LENGTH_LONG).show();*/
                        }
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setIcon(R.drawable.love);
                        builder.setTitle("提示");
                        builder.setMessage("你已取消");
                        final AlertDialog dialog = builder.show();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(dialog!=null && dialog.isShowing())
                                    dialog.dismiss();
                            }
                        },3000);
                        /*Toast.makeText(getApplicationContext(),"你已取消",Toast.LENGTH_LONG).show();*/
                    }
                });
                builder.show();

            }
        });



    }


}
