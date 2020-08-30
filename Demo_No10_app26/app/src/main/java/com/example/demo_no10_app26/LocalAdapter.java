package com.example.demo_no10_app26;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.LocalViewHolder>{
    Context context;

    List<LocalBean> mDatas;

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public void OnItemClick(View view, int position);
    }
    public LocalAdapter(Context context, List<LocalBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }


    @NonNull
    @Override
    public LocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        LocalViewHolder holder = new LocalViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocalViewHolder holder, final int position) {
        LocalBean bean = mDatas.get(position);
        holder.name.setText(bean.getName());
        holder.number.setText(bean.getNumber());


    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class LocalViewHolder extends RecyclerView.ViewHolder {
        TextView name,number;
        public LocalViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_phone_name);
            number = itemView.findViewById(R.id.item_phone_number);
        }
    }
}
