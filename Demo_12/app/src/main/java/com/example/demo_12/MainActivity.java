package com.example.demo_12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private CustomDialog customDialog;
    private int count = 0;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customDialog = new CustomDialog(this);
        final EditText edit1 = findViewById(R.id.editText1);
        final EditText edit2 = findViewById(R.id.editText2);
        Button btn1 = findViewById(R.id.button1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //使用Handler的postDelayed方法实现延时操作
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                        intent.putExtra("001",edit1.getText().toString());
                        intent.putExtra("002",edit2.getText().toString());

                        startActivity(intent);
                        edit1.setText("");
                        edit2.setText("");
                    }
                }, 5800);//5.5秒后执行Runnable中的run方法

                customDialog.show();
                final Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        count += 10;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (customDialog != null && customDialog.isShowing()) {
                                    customDialog.setProgress(count);
                                }
                            }
                        });
                        if (count >= 100) {
                            timer.cancel();

                        }
                    }
                }, 0, 500);


                customDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        if (timer != null) timer.cancel();
                        count = 0;
                    }
                });



            }


        });

    }





}
