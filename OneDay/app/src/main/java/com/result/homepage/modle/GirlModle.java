package com.result.homepage.modle;

import com.result.homepage.bean.GrilBean;
import com.result.homepage.gson.Tools;
import com.result.homepage.okhttp.OkHttp;

import java.io.IOException;
import java.util.List;

import okhttp3.Request;

/**
 * Created by 贾焕雪 on 2016-12-17.
 */
public class GirlModle implements IGirlModle{
    @Override
    public void getData( final String url,final ICallBack<List<GrilBean.ResultsBean>> callBack) {

        new Thread(){
            @Override
            public void run() {
                OkHttp.getAsync(url, new OkHttp.DataCallBack() {
                    @Override
                    public void requestFailure(Request request, IOException e) {
                        callBack.fail();
                    }

                    @Override
                    public void requestSuccess(String result) throws Exception {
                        GrilBean girlBean = Tools.parseJsonWithGson(result, GrilBean.class);
                        callBack.success(girlBean.getResults());
                    }
                });
            }
        }.start();


    }
}
