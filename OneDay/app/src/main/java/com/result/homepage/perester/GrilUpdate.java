package com.result.homepage.perester;

import com.result.homepage.bean.GrilBean;

import java.util.List;

/**
 * Created by 贾焕雪 on 2016-12-17.
 */
public class GrilUpdate {

    public interface IGirlPresenter{
        void ShowData();
    }
    public interface IGirlView{
        int get_Id();
        void showData(List<GrilBean.ResultsBean> resultsBeen);
    }

}
