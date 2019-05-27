package com.example.lenovo.banmi.mywallet.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.banmi.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWalletReAdapter extends RecyclerView.Adapter<MyWalletReAdapter.ViewHolder> {

    private ArrayList<String> strings;
    private ArrayList<String> times;
    private Context context;

    public MyWalletReAdapter(ArrayList<String> strings,ArrayList<String> times, Context context) {
        this.strings = strings;
        this.times =times;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.activity_my_wallet_list, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(strings.get(i));
        viewHolder.tvTime.setText(times.get(i));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
