package com.result.homepage.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.result.homepage.R;
import com.result.homepage.adapter.AboutRecyclerAdapter;

/**
 * Created by 贾焕雪 on 2016-12-15.
 */
public class About extends Fragment{
    private View view;
    ImageView aboutImg;
    RecyclerView aboutRecycler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.about,null);
        aboutRecycler= (RecyclerView) view.findViewById(R.id.about_recycler);
        aboutImg = (ImageView) view.findViewById(R.id.about_img);
        LinearLayoutManager mManager = new LinearLayoutManager(getActivity());
        mManager.setOrientation(LinearLayoutManager.VERTICAL);
        //为recyclerview添加布局管理器
        aboutRecycler.setLayoutManager(mManager);
        AboutRecyclerAdapter adapter = new AboutRecyclerAdapter(getActivity());
        aboutRecycler.setAdapter(adapter);
        return view;
    }
}
