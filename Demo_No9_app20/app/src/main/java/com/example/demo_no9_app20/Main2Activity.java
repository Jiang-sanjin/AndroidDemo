package com.example.demo_no9_app20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.widget.Toast;

import java.util.Locale;


public class Main2Activity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {
    Button bt_0, bt_1, bt_2, bt_3, bt_4, bt_5, bt_6, bt_7, bt_8, bt_9;
    Button bt_clear, bt_divide, bt_times, bt_delete, bt_add, bt_sub, bt_equal, bt_remainder, bt_point;
    private EditText et_input;

//    StringBuilder类也代表可变字符串对象。   append()、insert()、reverse()、setCharAt()、setLength()等方法可以改变这个字符串对象的字符序列
    private StringBuilder fomula = new StringBuilder();
    private TextToSpeech textToSpeech;


    private void initP() {
        bt_0 = (Button) findViewById(R.id.btn_0);
        bt_1 = (Button) findViewById(R.id.btn_1);
        bt_2 = (Button) findViewById(R.id.btn_2);
        bt_3 = (Button) findViewById(R.id.btn_3);
        bt_4 = (Button) findViewById(R.id.btn_4);
        bt_5 = (Button) findViewById(R.id.btn_5);
        bt_6 = (Button) findViewById(R.id.btn_6);
        bt_7 = (Button) findViewById(R.id.btn_7);
        bt_8 = (Button) findViewById(R.id.btn_8);
        bt_9 = (Button) findViewById(R.id.btn_9);
        bt_clear = (Button) findViewById(R.id.btn_clear);
        bt_divide = (Button) findViewById(R.id.btn_divide);
        bt_times = (Button) findViewById(R.id.btn_times);
        bt_delete = (Button) findViewById(R.id.btn_delete);
        bt_add = (Button) findViewById(R.id.btn_add);
        bt_sub = (Button) findViewById(R.id.btn_sub);
        bt_equal = (Button) findViewById(R.id.btn_equal);
        bt_point = (Button) findViewById(R.id.btn_point);
        bt_remainder = (Button) findViewById(R.id.btn_remainder);
        et_input = (EditText) findViewById(R.id.et_input);
        et_input.setKeyListener(null);
        bt_0.setOnClickListener(this);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_clear.setOnClickListener(this);
        bt_divide.setOnClickListener(this);
        bt_times.setOnClickListener(this);
        bt_delete.setOnClickListener(this);
        bt_sub.setOnClickListener(this);
        bt_remainder.setOnClickListener(this);
        bt_point.setOnClickListener(this);
        bt_equal.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initP();
        textToSpeech = new TextToSpeech(this, this);
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.CHINA);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "数据丢失或不支持", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public void onClick(View view) {
        int latest = 0;
        if (fomula.length() != 0) {
            latest = fomula.codePointAt(fomula.length() - 1);   //codePointAt()——提取索引字符代码点  即Unicode码
        }
        else
            latest = '.';
        switch (view.getId()) {
            case R.id.btn_0:
                fomula = fomula.append("0");
                et_input.setText(fomula);
                textToSpeech.speak("0",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_1:
                fomula = fomula.append("1");
                et_input.setText(fomula);
                textToSpeech.speak("1",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_2:
                fomula = fomula.append("2");
                et_input.setText(fomula);
                textToSpeech.speak("2",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_3:
                fomula = fomula.append("3");
                et_input.setText(fomula);
                textToSpeech.speak("3",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_4:
                fomula = fomula.append("4");
                et_input.setText(fomula);
                textToSpeech.speak("4",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_5:
                fomula = fomula.append("5");
                et_input.setText(fomula);
                textToSpeech.speak("5",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_6:
                fomula = fomula.append("6");
                et_input.setText(fomula);
                textToSpeech.speak("6",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_7:
                fomula = fomula.append("7");
                et_input.setText(fomula);
                textToSpeech.speak("7",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_8:
                fomula = fomula.append("8");
                et_input.setText(fomula);
                textToSpeech.speak("8",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_9:
                fomula = fomula.append("9");
                et_input.setText(fomula);
                textToSpeech.speak("9",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_sub:
                if (latest >= '0' && latest <= '9')
                    fomula = fomula.append("-");
                else {
                    if(latest != '.')
                        fomula.delete(fomula.length()-1, fomula.length());   // 开始（包含） fomula.delete(fomula.length()-1  结束（不包含） fomula.length());     //

                    fomula = fomula.append("-");
                }
                et_input.setText(fomula);
                textToSpeech.speak("减",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_add:
                if (latest >= '0' && latest <= '9')
                    fomula = fomula.append("+");
                else {
                    if(latest != '.')
                        fomula.delete(fomula.length()-1, fomula.length());
                    fomula = fomula.append("+");
                }
                et_input.setText(fomula);
                textToSpeech.speak("加",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_times:
                if (latest >= '0' && latest <= '9')
                    fomula = fomula.append("*");
                else {
                    if(latest != '.')
                        fomula = fomula.delete(fomula.length()-1,fomula.length());
                    fomula = fomula.append("*");
                }
                et_input.setText(fomula);
                textToSpeech.speak("乘",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_divide:
                if (latest >= '0' && latest <= '9')
                    fomula = fomula.append("/");
                else {
                    if(latest != '.')
                        fomula.delete(fomula.length()-1, fomula.length());
                    fomula = fomula.append("/");
                }
                et_input.setText(fomula);
                textToSpeech.speak("除",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_delete:
                if (fomula.length() > 0)
                    fomula = fomula.delete(fomula.length() - 1, fomula.length());
                et_input.setText(fomula);
                break;
            case R.id.btn_clear:
                fomula = fomula.delete(0, fomula.length());
                et_input.setText(fomula);
                textToSpeech.speak("归零",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_equal:
                String ans="0";
                if(fomula.length()>1){
                    InfixInToDuffix inf = new InfixInToDuffix();
                    try{
                        String a = inf.toSuffix(fomula);
                        System.out.println("out:");
                        System.out.println(a);
                        ans = inf.dealEquation(a);
                        fomula = fomula.delete(0,fomula.length());
                        fomula = fomula.append(ans);
                        //输出语音
                        textToSpeech.speak("等于"+ans,TextToSpeech.QUEUE_FLUSH, null);
                    }catch (Exception ex){
                        ans = "error";
                        fomula = fomula.delete(0,fomula.length());
                        textToSpeech.speak(ans,TextToSpeech.QUEUE_FLUSH, null);
                    }
                }
                et_input.setText(ans);


                break;
            case R.id.btn_point:
                fomula = fomula.append(".");
                et_input.setText(fomula);
                textToSpeech.speak("点",TextToSpeech.QUEUE_FLUSH, null);
                break;
            case R.id.btn_remainder:
                if(latest >= '0' && latest <= '9'){
                    fomula.append("%");
                }
                else
                {
                    if(latest != '.')
                        fomula.delete(fomula.length()-1,fomula.length());
                    fomula.append("%");
                }
                et_input.setText(fomula);
                textToSpeech.speak("求余",TextToSpeech.QUEUE_FLUSH, null);
                break;
        }
    }


}
