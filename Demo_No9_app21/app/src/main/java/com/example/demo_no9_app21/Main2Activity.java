package com.example.demo_no9_app21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import static com.example.demo_no9_app21.R.layout.activity_main2;

public class Main2Activity extends AppCompatActivity {

    private Button btn3;
    private EditText editTxt;
    private EditText editTxt2;
    String filename = "sanjin.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_main2);
        btn3 = findViewById(R.id.button3);
        editTxt = findViewById(R.id.editText);
        editTxt2 = findViewById(R.id.editText2);





        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    public void save(View view) throws FileNotFoundException {
//        有输入或者输出流程序就会产生异常    需要抛出异常 例如  throws FileNotFoundException
        FileOutputStream fileOutputStream = getApplicationContext().openFileOutput(filename, MODE_APPEND);//  MODE_APPEND追加在后面
        PrintStream printStream = new PrintStream(fileOutputStream);
        printStream.println(editTxt.getText().toString());

    }


    public void read(View view) throws IOException {

        FileInputStream fileInputStream = openFileInput(filename);
//        通过bufferreader  读取
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        String temp = "";
        editTxt2.setText("文件路径："+getApplicationContext().getFilesDir());
        while ((temp = bufferedReader.readLine())!=null){
            editTxt2.append("\n"+temp);

        }

    }
}
