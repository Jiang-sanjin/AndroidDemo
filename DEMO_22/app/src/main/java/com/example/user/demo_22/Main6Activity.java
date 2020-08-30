package com.example.user.demo_22;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Main6Activity extends AppCompatActivity {

    private TextView txth;
    private Button shouye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        txth = (TextView) findViewById(R.id.txth);
        int rad =new Random().nextInt(99999999);
        txth.setText("您的报名号:"+rad);

        shouye = (Button) findViewById(R.id.shouye);
        shouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
