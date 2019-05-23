package com.example.lenovo.banmi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.banmi.R;

import java.util.ArrayList;

public class MainReAdapter extends RecyclerView.Adapter<MainReAdapter.ViewHodler> {

    private ArrayList<String> strings;
    private Context context;

    public MainReAdapter(ArrayList<String> strings, Context context) {
        this.strings = strings;
        this.context = context;
    }

    @NonNull
    @Override
    public MainReAdapter.ViewHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.main_list, null);
        ViewHodler viewHodler = new ViewHodler(inflate);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(@NonNull MainReAdapter.ViewHodler viewHodler, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHodler extends RecyclerView.ViewHolder {
        public ViewHodler(@NonNull View itemView) {
            super(itemView);
        }
    }
}
