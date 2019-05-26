package com.example.lenovo.banmi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.banmi.R;
import com.example.lenovo.banmi.bean.MainDataBean;
import com.example.lenovo.banmi.utils.GlideUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<MainDataBean.ResultEntity.BannersEntity> bannerlist;
    List<MainDataBean.ResultEntity.RoutesEntity> listitem;
    Context context;

    final int BANNER_STYLE = 0;
    final int ITEM_STYLE = 1;
    final int BUNDLE_STYLE = 2;

    public HomeAdapter(List<MainDataBean.ResultEntity.BannersEntity> bannerlist, List<MainDataBean.ResultEntity.RoutesEntity> listitem, Context context) {
        this.bannerlist = bannerlist;
        this.listitem = listitem;
        this.context = context;
    }

    public void setBannerlist(List<MainDataBean.ResultEntity.BannersEntity> bannerlist) {
        this.bannerlist = bannerlist;
        notifyDataSetChanged();
    }

    public void setListitem(List<MainDataBean.ResultEntity.RoutesEntity> listitem) {
        this.listitem = listitem;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == BANNER_STYLE) {
            return new MyBanner(View.inflate(context, R.layout.home_banner, null));
        } else if (i == ITEM_STYLE) {
            return new MyItem(View.inflate(context, R.layout.home_item, null));
        } else {
            return new BundleHolder(View.inflate(context, R.layout.item_bundle, null));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int viewType = getItemViewType(i);
        if (viewType == BANNER_STYLE) {
            MyBanner banner = (MyBanner) viewHolder;
            banner.itembanner.setImages(bannerlist)
                    .setDelayTime(3000)
                    .setBannerStyle(BannerConfig.NOT_INDICATOR)
                    .setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            MainDataBean.ResultEntity.BannersEntity imagepath = (MainDataBean.ResultEntity.BannersEntity) path;
                            Glide.with(context).load(imagepath.getImageURL()).into(imageView);
                        }
                    }).start();

        } else if (viewType == ITEM_STYLE) {
            int index = i;
            if (bannerlist.size() > 0) {
                index -= 1;
            }
            MyItem item = (MyItem) viewHolder;
            MainDataBean.ResultEntity.RoutesEntity routesEntity = listitem.get(index);


            item.tv3.setText(routesEntity.getIntro());
            if (routesEntity.isIsPurchased()) {
               // item.btn.setBackgroundColor(context.getResources().getColor(R.color.cecece));
                item.btn.setText("已购买");
            }
            item.tv1.setText(routesEntity.getCity());
            item.tv2.setText(routesEntity.getTitle());
            RequestOptions options = new RequestOptions()
                    .placeholder(R.mipmap.zhanweitu_home_kapian)
                    .error(R.mipmap.zhanweitu_home_kapian);
            Glide.with(context).load(routesEntity.getCardURL()).apply(options).into(item.bgimg);
            final int finalIndex = index;
            item.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener!= null){
                        onItemClickListener.onClick(v,finalIndex);
                    }
                }
            });

        }else {
            int index = i;
            if (bannerlist.size()>0){
                index-=1;
            }
            final MainDataBean.ResultEntity.RoutesEntity entity = listitem.get(index);
            BundleHolder bundle = (BundleHolder) viewHolder;
            GlideUtil.loadUrlImage(R.mipmap.zhanweitu_home_kapian,R.mipmap.zhanweitu_home_kapian,
                    entity.getCardURL(),bundle.bigImg,context);
            bundle.bigImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onBundleClickListener!= null){
                        onBundleClickListener.onClick(entity.getContentURL(),entity.getTitle());
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (bannerlist.size()>0){
            return listitem.size()+1;
        }else {
            return listitem.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position ==0 && bannerlist.size()>0){
            return BANNER_STYLE;
        }else {
            int index = position;
            if (bannerlist.size()>0){
                index -= 1;
            }
            if (!TextUtils.isEmpty(listitem.get(index).getType()) && listitem.get(index).getType().equals("bundle")){
                return BUNDLE_STYLE;
            }else {
                return ITEM_STYLE;
            }
        }
    }

    public void getBannerList(List<MainDataBean.ResultEntity.BannersEntity> banners) {
        bannerlist.addAll(banners);
        notifyDataSetChanged();
    }

    public void getData(List<MainDataBean.ResultEntity.RoutesEntity> routes) {
        listitem.addAll(routes);
        notifyDataSetChanged();
    }

    class MyBanner extends RecyclerView.ViewHolder {
        @BindView(R.id.home_banner)
        Banner itembanner;

        public MyBanner(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class MyItem extends RecyclerView.ViewHolder {
        @BindView(R.id.ih_btn)
        Button btn;
        @BindView(R.id.ih_tv1)
        TextView tv1;
        @BindView(R.id.ih_tv2)
        TextView tv2;
        @BindView(R.id.ih_tv3)
        TextView tv3;
        @BindView(R.id.bgg_img)
        ImageView bgimg;

        public MyItem(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class BundleHolder extends RecyclerView.ViewHolder {

        ImageView bigImg;

        public BundleHolder(View itemView) {
            super(itemView);
            bigImg = itemView.findViewById(R.id.bg_img);
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(View view, int position);
    }

    private OnBundleClickListener onBundleClickListener;

    public void setOnBundleClickListener(OnBundleClickListener onBundleClickListener) {
        this.onBundleClickListener = onBundleClickListener;
    }

    public interface OnBundleClickListener{
        void onClick(String url,String title);
    }
}
