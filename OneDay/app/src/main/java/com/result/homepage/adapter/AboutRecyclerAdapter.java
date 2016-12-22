package com.result.homepage.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.result.homepage.R;

import java.util.ArrayList;
import java.util.List;
public class AboutRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
//    List<Integer> list;
    LayoutInflater inflater;
    List<String> list=new ArrayList();

    public AboutRecyclerAdapter(Context context) {
        inflater=LayoutInflater.from(context);
        this.context = context;
        list.add("作者:RaphetS");
        list.add("联系作者:raphets@126.com");
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=inflater.inflate(R.layout.about_recycler_item,parent,false);
        MyViewHolder VH=new MyViewHolder(mView);
        return VH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            ((MyViewHolder) holder).text.setText(list.get(position)+"");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private class MyViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        public MyViewHolder(View itemView) {
            super(itemView);
            text= (TextView) itemView.findViewById(R.id.about_item_text);
        }
    }
}
