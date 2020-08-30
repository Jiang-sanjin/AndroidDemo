package com.example.demo_no7;

import com.example.demo_no7.MainActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
public class Myfrgment extends Fragment {

    private Button btn1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment, container, false);
//        btn1 = view.findViewById(R.id.button);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                TextView txt1 = getActivity().findViewById(R.id.textView2);
//                txt1.setText("BACK TO U");
////                Toast.makeText(getContext(),"yifanhui",Toast.LENGTH_LONG).show();
//
//            }
//        });

        return view;
    }
}
