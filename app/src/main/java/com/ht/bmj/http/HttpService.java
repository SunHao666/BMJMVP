package com.ht.bmj.http;

import com.ht.bmj.base.BaseHttpResult;
import com.ht.bmj.bean.LoginBean;
import com.ht.bmj.url.URLHelper;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 网络请求的接口都在这里
 */

public interface HttpService {

    //登录接口
    @GET(URLHelper.LOGIN + "{loginName}")
    Observable<BaseHttpResult<LoginBean>> login(@Path("loginName") String loginName,
                                                @Query("password") String password);

}
