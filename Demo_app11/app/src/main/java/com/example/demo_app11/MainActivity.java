package com.example.demo_app11;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer1;
    private RelativeLayout relay1;
    private Button btn1;
    private ImageView imageview;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageview = findViewById(R.id.imageview);
        relay1 = findViewById(R.id.relay1);
        btn1 = findViewById(R.id.happy);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview.setImageResource( R.drawable.love);
            }
        });
        relay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==0) {
                    mediaPlayer1 = MediaPlayer.create(getApplicationContext(), R.raw.happy);
                    mediaPlayer1.start();
                    flag=1;
                }
            }
        });
    }

    public void stop(View view){
        if(flag==1){
            mediaPlayer1.stop();
            flag=0;
        }
        imageview.setImageBitmap(null);
    }
}
