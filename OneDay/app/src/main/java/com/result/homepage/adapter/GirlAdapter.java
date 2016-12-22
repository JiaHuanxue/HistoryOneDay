package com.result.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.result.homepage.R;
import com.result.homepage.bean.GrilBean;

import java.util.List;

/**
 * Created by 贾焕雪 on 2016-12-17.
 */
public class GirlAdapter extends RecyclerView.Adapter<GirlAdapter.GirlViewHolder>{
    private Context context;
    private List<GrilBean.ResultsBean> resultsBeen;

    public GirlAdapter(Context context, List<GrilBean.ResultsBean> resultsBeen,RecyclerView list) {
        this.context = context;
        this.resultsBeen = resultsBeen;
    }
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }
    private OnItemClickLitener mOnItemClickLitener;
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    @Override
    public GirlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.recyclerview_item,parent,false);
        GirlViewHolder vh=new GirlViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(final GirlViewHolder holder, final int position) {
        Glide.with(context)
                .load(resultsBeen.get(position).getUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓冲全尺寸
                .centerCrop() //设置居中
                .placeholder(R.mipmap.ic_launcher)
                .into((holder).imageView);

        holder.itemView.setTag(resultsBeen.get(position).getUrl());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickLitener.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsBeen.size();
    }

    class GirlViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public GirlViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.grilimageView);
        }
    }
}

