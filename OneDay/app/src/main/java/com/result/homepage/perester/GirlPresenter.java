package com.result.homepage.perester;

import android.os.Handler;

import com.result.homepage.bean.GrilBean;
import com.result.homepage.modle.GirlModle;
import com.result.homepage.modle.ICallBack;
import com.result.homepage.modle.IGirlModle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 贾焕雪 on 2016-12-17.
 */
public class GirlPresenter implements GrilUpdate.IGirlPresenter{

    private IGirlModle iGirlModle;
    private GrilUpdate.IGirlView iGirlView;
    //更新数据
    private Handler handler=new Handler();
    //获得GrilBean.ResultsBean集合
    private List<GrilBean.ResultsBean> resultsBeanList=new ArrayList<>();
    //分页加载
    private int page;
    public GirlPresenter(GrilUpdate.IGirlView iGirlView) {
        this.iGirlView = iGirlView;
        this.iGirlModle=new GirlModle();
    }
    @Override
    public void ShowData() {
        page=iGirlView.get_Id();
        if(page==1){
            resultsBeanList.clear();
        }
        String url="http://gank.io/api/data/福利/10/"+page;
        iGirlModle.getData(url, new ICallBack<List<GrilBean.ResultsBean>>() {
            @Override
            public void success(final List<GrilBean.ResultsBean> resultsBeen) {
                //handler.post直接交给主线程
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        resultsBeanList.addAll(resultsBeen);
                        iGirlView.showData(resultsBeanList);
                    }
                });
            }
            @Override
            public void fail() {

            }

            });
    }
}
