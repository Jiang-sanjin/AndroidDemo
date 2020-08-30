package com.example.demo_no7_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Myfrgament extends Fragment {

    private LinearLayout left;
    private Button btn2;

    int flag=-1;
    String [] items1=new String[]{"懂你的音乐器","扣扣9亿人的选择","交友需谨慎","度娘都知道","支付就用支付宝","独家播放xxx",
            "懂你的音乐器","扣扣9亿人的选择","交友需谨慎","度娘都知道","支付就用支付宝","独家播放xxx"};
    int[] images = new int[]{R.drawable.wangyiyun,R.drawable.qq,R.drawable.wechat, R.drawable.baidu, R.drawable.zhifubao,R.drawable.tengxunshipin,
            R.drawable.wangyiyun,R.drawable.qq,R.drawable.wechat, R.drawable.baidu, R.drawable.zhifubao,R.drawable.tengxunshipin,
    };
    private TextView txt2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        left = getActivity().findViewById(R.id.left);



//        Toast.makeText(getContext(),bundle.getString("DATA"),Toast.LENGTH_LONG).show();

//        加载动态布局
        View view = inflater.inflate(R.layout.fragment, container, false);

        Bundle bundle =this.getArguments();

       ImageView imageview = view.findViewById(R.id.imageView);
       imageview.setImageResource(bundle.getInt("data2"));

        txt2 = view.findViewById(R.id.textView2);
         String mess = null;
         if(bundle!=null){
            mess = bundle.getString("data");
         }
         txt2.setText(mess);



        btn2 = view.findViewById(R.id.button);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.remove(Myfrgament.this);
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        maxScreen();
    }

    private void maxScreen() {
    left.setLayoutParams(new LinearLayout.LayoutParams(getActivity().getWindowManager().getDefaultDisplay().getWidth(),getActivity().getWindowManager().getDefaultDisplay().getHeight()));

    }
}
