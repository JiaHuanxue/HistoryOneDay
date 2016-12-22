package com.result.homepage.modle;

import com.result.homepage.bean.GrilBean;

import java.util.List;

/**
 * Created by 贾焕雪 on 2016-12-17.
 */
public interface IGirlModle {

    void getData(String url, final ICallBack<List<GrilBean.ResultsBean>> callBack);

}
