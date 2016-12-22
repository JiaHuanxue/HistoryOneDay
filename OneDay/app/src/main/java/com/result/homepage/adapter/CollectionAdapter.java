package com.result.homepage.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.result.homepage.R;
import com.result.homepage.bean.CollectionBean;

import java.util.List;

public class CollectionAdapter extends
        RecyclerView.Adapter<CollectionAdapter.MyViewHolder>  {

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setmOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }


    private List<CollectionBean> data;
    private Context mContext;
    private LayoutInflater inflater;


    public CollectionAdapter(List<CollectionBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
        inflater=LayoutInflater.from(mContext);
    }

    //创建新View，被LayoutManager所调用
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.conllection_list_item,parent,false);
        MyViewHolder holder= new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.textView_date.setText(data.get(position).getDate());
        holder.textView_title.setText(data.get(position).getTitle());
        ImageLoader.getInstance().displayImage(data.get(position).getImage_url(),holder.imageView_url);
/*        Glide.with(mContext)
                .load(data.get(position).getImage_url())
                .diskCacheStrategy(DiskCacheStrategy.ALL) //缓冲全尺寸
                .centerCrop() //设置居中
                .placeholder(R.mipmap.ic_launcher)
                .into((holder).imageView_url);*/
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(data.get(position));

        //判断是否设置了监听器
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
                }
            });
        }
        if(mOnItemLongClickListener != null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemLongClickListener.onItemLongClick(holder.itemView,position);
                    //返回true 表示消耗了事件 事件不会继续传递
                    return true;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView_date;
        TextView textView_title;
        ImageView imageView_url;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView_date = (TextView) itemView.findViewById(R.id.textView_date);
            textView_title = (TextView) itemView.findViewById(R.id.textView_title);
            imageView_url = (ImageView) itemView.findViewById(R.id.image_url);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener{
        void onItemLongClick(View view, int position);
    }
}
