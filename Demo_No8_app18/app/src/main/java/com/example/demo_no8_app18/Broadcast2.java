package com.example.demo_no8_app18;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

public class Broadcast2 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"你要的表情包",Toast.LENGTH_SHORT).show();


        Toast toast = new Toast(context);
        ImageView img = new ImageView(context);
        //图片
        img.setImageResource(R.drawable.a1);
        //设置图片
        toast.setView(img);
        toast.show();

        Toast toast2 = new Toast(context);
        ImageView img2 = new ImageView(context);
        //图片
        img2.setImageResource(R.drawable.a2);
        //设置图片
        toast2.setView(img2);
        toast2.show();


        Toast toast3 = new Toast(context);
        ImageView img3 = new ImageView(context);
        //图片
        img3.setImageResource(R.drawable.a4);
        //设置图片
        toast3.setView(img3);
        toast3.show();
    }
}
