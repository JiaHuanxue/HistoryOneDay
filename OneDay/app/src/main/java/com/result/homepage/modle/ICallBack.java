package com.result.homepage.modle;

/**
 * Created by 贾焕雪 on 2016-12-17.
 */
public interface ICallBack<T>{

    void success(T t);
    void fail();

}
