package com.example.lenovo.banmi.mycoupon.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.banmi.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiftReAdapter extends RecyclerView.Adapter<GiftReAdapter.ViewHolder> {

    private ArrayList<String> strings;
    private Context context;

    public GiftReAdapter(ArrayList<String> strings, Context context) {
        this.strings = strings;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.gift_list, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.voucherName.setText(strings.get(i));
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.voucher_img)
        ImageView voucherImg;
        @BindView(R.id.voucher_name)
        TextView voucherName;
        @BindView(R.id.voucher_time)
        TextView voucherTime;
        @BindView(R.id.voucher_money)
        TextView voucherMoney;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
