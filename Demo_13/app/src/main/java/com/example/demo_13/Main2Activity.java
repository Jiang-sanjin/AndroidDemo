package com.example.demo_13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    private EditText edit1;
    private Button btn1;
    private TextView jieshou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        edit1 = findViewById(R.id.editText1);
        btn1 = findViewById(R.id.button1);
        jieshou = findViewById(R.id.jieshou);
        jieshou.setText(getIntent().getStringExtra("001"));

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("002",edit1.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
