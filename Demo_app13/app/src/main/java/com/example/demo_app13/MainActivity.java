package com.example.demo_app13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private RadioGroup radiogroup;
    private EditText edit1;
    private EditText edit2;
    private RadioButton radiobtn;
    String sex;
    int height ;
    int weight ;
    double biaozhun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                if(!edit1.getText().toString().equals("")&&!edit2.getText().toString().equals("")){
                    height = new Integer(edit1.getText().toString());
                    weight = new Integer(edit2.getText().toString());

            /*    if(!edit2.getText().toString().equals("")){
                    weight = new Integer(edit2.getText().toString());
                }*/
                    for( int i=0; i<radiogroup.getChildCount(); i++ ){
                        radiobtn = (RadioButton) radiogroup.getChildAt(i);
                        sex = radiobtn.getText().toString();

                        if(radiobtn.isChecked()){
                            if(sex.equals("男")){
                                biaozhun = (height- 80)*0.7;
                                intent.putExtra("001",sex);
                                intent.putExtra("002",biaozhun);
                                intent.putExtra("003",weight);
                                startActivityForResult(intent,0x01);
                                edit1.setText(""); //执行清空edit1的数据
                                edit2.setText("");
                            }
                            if(sex.equals("女")){
                                biaozhun = (height- 70)*0.6;
                                intent.putExtra("001",sex);
                                intent.putExtra("002",biaozhun);
                                intent.putExtra("003",weight);
                                startActivityForResult(intent,0x01);
                                edit1.setText(""); //执行清空edit1的数据
                                edit2.setText("");
                            }
                        }
                    }
                }
            }
        });

    }

    public void initview() {
        btn1 = findViewById(R.id.button1);
        radiogroup = findViewById(R.id.radiogroup);
        edit1 = findViewById(R.id.editText1);
        edit2 = findViewById(R.id.editText2);

    }
}
