package com.example.demo_no10_app25;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private ImageView image;
    Bitmap bitmap;

    int flag = -1;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0x01){
                image.setImageBitmap(bitmap);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.image);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag++;
                new Thread(){

                    @Override
                    public void run() {
                        super.run();

                        try {
//                            URL url=new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588840983610&di=5f4c1bebad28ff78a095ce7e82ceef92&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Forj480%2F7fd664e1gw1f7ry6olay9g20rs0rsqof.gif");
                            URL[] url2= new URL[]{new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588941902861&di=73601e620bc62174c2f33d339df4eb88&imgtype=0&src=http%3A%2F%2Ft9.baidu.com%2Fit%2Fu%3D1307125826%2C3433407105%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D5760%26h%3D3240"),
                                    new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1588941203068&di=8320f3b1eb67e6118f64348a6680963c&imgtype=0&src=http%3A%2F%2Ft8.baidu.com%2Fit%2Fu%3D2247852322%2C986532796%26fm%3D79%26app%3D86%26f%3DJPEG%3Fw%3D1280%26h%3D853"),
                                    new URL("http://img3.imgtn.bdimg.com/it/u=1681847228,2121748243&fm=26&gp=0.jpg"),
                                    new URL("http://img0.imgtn.bdimg.com/it/u=2534089435,3974188437&fm=26&gp=0.jpg"),
                                    new URL("http://img5.imgtn.bdimg.com/it/u=1507011887,661999872&fm=26&gp=0.jpg")
                            };
                            try{
                                if(flag<5) {
                                    URLConnection connection = url2[flag].openConnection();
                                    InputStream inputStream = connection.getInputStream();
                                    bitmap = BitmapFactory.decodeStream(inputStream);
                                    handler.sendEmptyMessage(0x01);
                                }
                            }catch (IOException e) {
                                e.printStackTrace();
                            }



                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }

                    }

                }.start();

                if(flag>=5)
                    Toast.makeText(getApplicationContext(),"已经是最后一张",Toast.LENGTH_SHORT).show();
            }


        });

    }
}
