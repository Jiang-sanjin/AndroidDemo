package com.example.demo_no9_app21;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txt;
    private EditText edit;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private EditText edit2;
    private SharedPreferences.Editor editor2;
    private Button btn3;

    @SuppressLint({"WorldWriteableFiles", "CommitPrefEdits", "WrongConstant"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.textView2);
        edit = findViewById(R.id.editText);
        edit2 = findViewById(R.id.editText2);
        btn3 = findViewById(R.id.button3);
        sharedPreferences = getSharedPreferences("clf", Context.MODE_PRIVATE);

        editor = sharedPreferences.edit();   // 读 写操作

        editor2 = sharedPreferences.edit();
        int count = sharedPreferences.getInt("count", 1);
        Toast.makeText(getApplicationContext(),"访问了"+count+"次",Toast.LENGTH_SHORT).show();
        editor2.putInt("count",++count);
        editor2.commit();

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public  void save(View view){
        editor.putString("001",edit.getText().toString());
        editor.commit();
    }
    public  void read(View view){
        String string = sharedPreferences.getString("001", "");
        edit2.setText(string);
    }


}
