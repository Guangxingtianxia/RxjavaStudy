// (c)2016 Flipboard Inc, All Rights Reserved.

package com.rengwuxian.rxjavasamples.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rengwuxian.rxjavasamples.R;
import com.rengwuxian.rxjavasamples.model.ZhuangbiImage;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 装逼适配器
 */
public class ZhuangbiListAdapter extends RecyclerView.Adapter
{
    List<ZhuangbiImage> images;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        // parent 可以调用getContext()......这个，没见过!值得学习。。。。
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new DebounceViewHolder(view);
    }

    /**
     * 使用父类的ViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        DebounceViewHolder debounceViewHolder = (DebounceViewHolder) holder;
        ZhuangbiImage image = images.get(position);
        Glide.with(holder.itemView.getContext()).load(image.image_url).into(debounceViewHolder.imageIv);
        debounceViewHolder.descriptionTv.setText(image.description);
    }

    @Override
    public int getItemCount()
    {
        return images == null ? 0 : images.size();
    }

    /**
     * 设置images 数据源，Fragment层调用
     * @param images
     */
    public void setImages(List<ZhuangbiImage> images)
    {
        this.images = images;
        // 观察者模式的调用--通知其他使用此数据的观察者发生改变
        notifyDataSetChanged();
    }

    /**
     * 自定义ViewHolder
     */
    static class DebounceViewHolder extends RecyclerView.ViewHolder
    {
        @Bind(R.id.imageIv)
        ImageView imageIv;

        @Bind(R.id.descriptionTv)
        TextView descriptionTv;

        public DebounceViewHolder(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
