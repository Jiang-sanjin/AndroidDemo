package com.example.user.demo_22;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    private EditText user1;
    private EditText password;
    private Button denglu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        user1 = (EditText) findViewById(R.id.uers1);

        password = (EditText) findViewById(R.id.password);
        denglu = (Button) findViewById(R.id.denglu);

        denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user1.getText().toString().equals("wz")&&password.getText().toString().equals("123456")){
                    Intent intent=new Intent(getApplicationContext(),Main3Activity.class);

                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
