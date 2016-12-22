package com.result.homepage.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.result.homepage.R;
import com.result.homepage.activity.DetailsActivity;
import com.result.homepage.adapter.CollectionAdapter;
import com.result.homepage.bean.CollectionBean;
import com.result.homepage.db.OneDayDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 贾焕雪 on 2016-12-15.
 */
public class Collection extends Fragment {

    private OneDayDao dao;
    private String e_id;
    private View view;
    private RecyclerView recyclerView_collection;
    private CollectionAdapter collectionAdapter;
    private List<CollectionBean> dblist;
    private SwipeRefreshLayout id_swipe_co;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.collection, null);
        recyclerView_collection = (RecyclerView) view.findViewById(R.id.recyclerView_collection);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        id_swipe_co = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_co);
        id_swipe_co.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                id_swipe_co.setRefreshing(false);
            }
        });
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView_collection.setLayoutManager(manager);
        dao = new OneDayDao(getActivity());
        dblist = new ArrayList<>();
        dblist = dao.selectSQL();
        collectionAdapter = new CollectionAdapter(dblist, getActivity());
        recyclerView_collection.setAdapter(collectionAdapter);
        //0则不执行拖动或者滑动
        ItemTouchHelper.Callback mCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {
            /**
             * @param recyclerView
             * @param viewHolder 拖动的ViewHolder
             * @param target 目标位置的ViewHolder
             * @return
             */
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                dao.delete(dblist.get(position).getTitle());
                dblist.remove(position);


                collectionAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    //左右滑动时改变Item的透明度
                    final float alpha = 1 - Math.abs(dX) / (float) viewHolder.itemView.getWidth();
                    viewHolder.itemView.setAlpha(alpha);
                    viewHolder.itemView.setTranslationX(dX);

                }
            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                //当选中Item时候会调用该方法，重写此方法可以实现选中时候的一些动画逻辑

            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                //当动画已经结束的时候调用该方法，重写此方法可以实现恢复Item的初始状态
            }
        };
        collectionAdapter.setmOnItemClickListener(new CollectionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("e_id",dblist.get(position).getE_id());
                intent.putExtra("isChecks",true);
                intent.putExtra("title",dblist.get(position).getTitle());
                startActivity(intent);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView_collection);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        dao = new OneDayDao(getActivity());
        dblist = new ArrayList<>();
        dblist = dao.selectSQL();
        collectionAdapter = new CollectionAdapter(dblist, getActivity());
        recyclerView_collection.setAdapter(collectionAdapter);
        collectionAdapter.setmOnItemClickListener(new CollectionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("e_id",dblist.get(position).getE_id());
                intent.putExtra("isChecks",true);
                intent.putExtra("title",dblist.get(position).getTitle());
                startActivity(intent);
            }
        });
    }
    public boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
