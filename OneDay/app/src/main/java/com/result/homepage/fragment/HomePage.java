package com.result.homepage.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.result.homepage.R;
import com.result.homepage.activity.DetailsActivity;
import com.result.homepage.activity.MaterialCalendarViewActivity;
import com.result.homepage.adapter.MyRecyclerAdapter;
import com.result.homepage.bean.FirstEvent;
import com.result.homepage.bean.HomeBean;
import com.result.homepage.custom.DateUtil;
import com.result.homepage.okhttp.OkHttp;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import okhttp3.Request;

/**
 * Created by 贾焕雪 on 2016-12-15.
 */
public class HomePage extends Fragment {

    private View view;
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter myRecyclerAdapter;
    public static final String url = "http://v.juhe.cn/todayOnhistory/queryEvent.php";
    public String surl = "http://v.juhe.cn/todayOnhistory/queryEvent.php?key=69a7eeba7869f8bdcdee7b2bc3bb5aa2&date=";
    //配置您申请的KEY
    public static final String APPKEY ="69a7eeba7869f8bdcdee7b2bc3bb5aa2";
    private String date;
    private String TAG = "TAG.HomePage";

    private SwipeRefreshLayout id_swipe_ly;
    private TextView home_title;
    private int intday;
    private int intmonth;
    private ImageView image_right;
    private ImageView image_left;
    private Date data;
    private SimpleDateFormat formatdata;
    private SimpleDateFormat formatyear;
    private SimpleDateFormat formatmonth;
    private SimpleDateFormat formatday;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        view = View.inflate(getActivity(), R.layout.fragment_homepage, null);
        image_right = (ImageView) view.findViewById(R.id.image_right);
        image_left = (ImageView) view.findViewById(R.id.image_left);
        home_title = (TextView) view.findViewById(R.id.home_title);
        //获取系统时间
        formatdata = new SimpleDateFormat("yyyy年MM月dd日");
        formatyear = new SimpleDateFormat("yyyy");
        formatmonth = new SimpleDateFormat("MM");
        formatday = new SimpleDateFormat("dd");
        //获得时间
        data = new Date(System.currentTimeMillis());
        //让标题加载系统当前时间
        date = formatdata.format(data);
        home_title.setText(date);
        image_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateUtil.num=-1;
                String str = DateUtil.getDateStr(home_title.getText().toString().trim(), DateUtil.num);
                home_title.setText(str);
                String data = surl+DateUtil.getMonth(str)+"/"+DateUtil.getMonthDate(str);
                initData(data);
            }
        });
        image_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateUtil.num=1;
                String str1 = DateUtil.getDateStr(home_title.getText().toString().trim(), DateUtil.num);
                home_title.setText(str1);
                String data1 = surl+DateUtil.getMonth(str1)+"/"+DateUtil.getMonthDate(str1);
                initData(data1);
            }
        });

        //给时间月日赋值
        intday = Integer.parseInt(formatday.format(data));
        intmonth =  Integer.parseInt(formatmonth.format(data));

        EventBus.getDefault().register(this);
        //停止刷新
        id_swipe_ly = (SwipeRefreshLayout) view.findViewById(R.id.id_swipe_ly);
        id_swipe_ly.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                id_swipe_ly.setRefreshing(false);
            }
        });
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MaterialCalendarViewActivity.class);
                startActivity(intent);
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //把时间出给URL
        initData(setMap(intmonth+"",intday+""));
        return view;
    }

    public void initData(String url) {
        Log.i(TAG+"url=",url);
        OkHttp.getAsync(url, new OkHttp.DataCallBack() {
            @Override
            public void requestFailure(Request request, IOException e) {

            }

            @Override
            public void requestSuccess(String result) throws Exception {
                Gson gson = new Gson();
                HomeBean homeBean = gson.fromJson(result, HomeBean.class);
                final List<HomeBean.ResultBean> result1 = homeBean.getResult();
                //Collections.reverse(result1);
                myRecyclerAdapter = new MyRecyclerAdapter(result1, getActivity());
                mRecyclerView.setAdapter(myRecyclerAdapter);
                myRecyclerAdapter.setmOnItemClickListener(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getActivity(), DetailsActivity.class);
                        intent.putExtra("e_id",result1.get(position).getE_id());
                        intent.putExtra("date",date);
                        intent.putExtra("hometitle",result1.get(position).getTitle());
                        startActivity(intent);
                    }
                });
            }
        });

    }
    public String  setMap(String month,String day){
        Map map = new HashMap<>();
        //拼接Map
        map.put("key",APPKEY);
        map.put("date",month+"/"+day);
        String str = setString(map);
        return  url+str;
    }
    public String setString(Map<String,Object> string) {
        StringBuilder stringBuilder = null;
        try {
            stringBuilder = new StringBuilder();
            stringBuilder.append("?");
            for (Map.Entry me : string.entrySet()){
                stringBuilder.append(me.getKey()).append("=").append(URLEncoder.encode(me.getValue()+"","UTF-8")).append("&");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
    //EventBus接收值
    @Subscribe
    public void onEventMainThread(FirstEvent event) {

        String eYear = event.geteYear();
        String eMonth = event.geteMonth();
        String eDay = event.geteDay();
        date = eYear+"年"+eMonth+"月"+eDay+"日";
        intday = Integer.parseInt(eDay);
        intmonth = Integer.parseInt(eMonth);
        //把时间出给URL
        initData(setMap(intmonth+"",intday+""));
        home_title.setText(date);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
