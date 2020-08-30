package com.example.demo_no8_app18;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class Broadcast1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Toast.makeText(context,"你要的花",Toast.LENGTH_SHORT).show();


        Toast toast = new Toast(context);
        ImageView img = new ImageView(context);
        //图片
        img.setImageResource(R.drawable.flower1);
        //设置图片
        toast.setView(img);
        toast.show();

        Toast toast2 = new Toast(context);
        ImageView img2 = new ImageView(context);
        //图片
        img2.setImageResource(R.drawable.flower3);
        //设置图片
        toast2.setView(img2);
        toast2.show();


        Toast toast3 = new Toast(context);
        ImageView img3 = new ImageView(context);
        //图片
        img3.setImageResource(R.drawable.flower2);
        //设置图片
        toast3.setView(img3);
        toast3.show();

    }




}
