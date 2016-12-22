package com.result.homepage.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.result.homepage.R;
import com.result.homepage.activity.GrilDetailActivity;
import com.result.homepage.adapter.GirlAdapter;
import com.result.homepage.bean.GrilBean;
import com.result.homepage.perester.GirlPresenter;
import com.result.homepage.perester.GrilUpdate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 贾焕雪 on 2016-12-15.
 */
public class Girl extends Fragment implements GrilUpdate.IGirlView{

    private View view;
    private RecyclerView mRecyclerView;
    private GirlPresenter mGirlPresenter=new GirlPresenter(this);
    private int index=1;
    private SwipeRefreshLayout swipeRefreshLayout;
    private GirlAdapter girlAdapter;
    private GridLayoutManager gridLayoutManager;
    private List<GrilBean.ResultsBean> list=new ArrayList<>();
    private FloatingActionButton floatActionBtn;
    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.girl,null);
        mRecyclerView=(RecyclerView)view.findViewById(R.id.girl_id_recyclerview);
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_girl);
        mGirlPresenter.ShowData();
        floatActionBtn=(FloatingActionButton)view.findViewById(R.id.fab);
        floatActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });
        UpdataData();
        initView(savedInstanceState);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visible  = gridLayoutManager.getChildCount();//显示条目数
                int total = gridLayoutManager.getItemCount();//显示数据总数
                int past=gridLayoutManager.findFirstCompletelyVisibleItemPosition();
                if ((visible + past) >= total){
                    Toast.makeText(getActivity(),"慢点滑呦！",Toast.LENGTH_SHORT).show();
                    index++;
                    get_Id();
                    mGirlPresenter.ShowData();
                }
            }
        });
        return view;
    }

    public void initView(Bundle savedInstanceState) {
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_girl);
        mGirlPresenter.ShowData();
        floatActionBtn=(FloatingActionButton)view.findViewById(R.id.fab);
        floatActionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecyclerView.smoothScrollToPosition(0);
            }
        });

    }
    //刷新数据
    public void UpdataData() {
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                index++;
                get_Id();
                mGirlPresenter.ShowData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public int get_Id() {
        return index;
    }

    @Override
    public void showData(List<GrilBean.ResultsBean> resultsBeen) {
        list.clear();
        list.addAll(resultsBeen);
        girlAdapter=new GirlAdapter(getActivity(),list,mRecyclerView);
        gridLayoutManager=new GridLayoutManager(getActivity(),2);
        gridLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(girlAdapter);

        girlAdapter.setOnItemClickLitener(new GirlAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(),GrilDetailActivity.class);
                intent.putExtra("url",list.get(position).getUrl());
                startActivity(intent);
            }
        });
    }
}