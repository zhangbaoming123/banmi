package com.example.lenovo.banmi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.banmi.R;
import com.example.lenovo.banmi.bean.BanMiBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BanmiAdapter extends RecyclerView.Adapter {
    private List<BanMiBean.ResultBean.BanmiBean> list;
    private Context context;

    public BanmiAdapter(List<BanMiBean.ResultBean.BanmiBean> list, Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.banmi_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        Glide.with(context).load(list.get(i).getPhoto()).into(holder.banmiImg);

        holder.banmiName.setText(list.get(i).getName());

        holder.banmiAbout.setText(list.get(i).getFollowing()+"人关注");

        holder.address.setText(list.get(i).getLocation());

        holder.banmiTitle.setText(list.get(i).getOccupation());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void getData(List<BanMiBean.ResultBean.BanmiBean> banmi) {
        list.addAll(banmi);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.banmi_img)
        ImageView banmiImg;
        @BindView(R.id.banmi_name)
        TextView banmiName;
        @BindView(R.id.banmi_about)
        TextView banmiAbout;
        @BindView(R.id.img)
        ImageView img;
        @BindView(R.id.banmi_title)
        TextView banmiTitle;
        @BindView(R.id.banmi_collect)
        CheckBox banmiCollect;
        @BindView(R.id.address)
        TextView address;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
