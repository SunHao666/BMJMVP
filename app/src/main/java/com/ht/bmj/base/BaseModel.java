package com.ht.bmj.base;

import com.ht.bmj.http.Http;
import com.ht.bmj.http.HttpService;
import com.ht.bmj.mvp.IModel;

/**
 * Model基类
 */

public class BaseModel implements IModel{

    protected static HttpService httpService;

    //初始化httpService
    static {
        httpService = Http.getHttpService();
    }


}
