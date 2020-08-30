package com.example.demo_app14;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main6Activity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        name = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        btn1 = findViewById(R.id.button1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("sanjin")&&password.getText().toString().equals("666666")){
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);

                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
