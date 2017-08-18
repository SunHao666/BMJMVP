package com.ht.bmj.http;

import com.ht.bmj.BuildConfig;
import com.ht.bmj.MApplication;
import com.ht.bmj.url.URLHelper;
import com.ht.bmj.utils.NetworkUtil;
import com.ht.bmj.utils.SpUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit、okhttp 配置类
 */

public class Http {


    private static Retrofit retrofit;
    private static HttpService httpService;

    public static HttpService getHttpService() {
        if(httpService == null){
            httpService = getRetrofit().create(HttpService.class);
        }
        return httpService;
    }

    private static Retrofit getRetrofit() {
        //单例
        if(retrofit == null){
            synchronized (Http.class){
                if (retrofit == null){
                    //设置log拦截器
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    httpLoggingInterceptor.setLevel(BuildConfig.DEBUG? HttpLoggingInterceptor.Level.BODY:
                            HttpLoggingInterceptor.Level.NONE);

                    //设置 请求的缓存的大小跟位置
//                    File cacheFile = new File(MApplication.getmContext().getCacheDir(), "cache");
//                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小


                    //OkHttpClient
                    OkHttpClient client = new OkHttpClient.Builder()
                            .addInterceptor(addQueryParameterInterceptor())  //参数添加
                            .addInterceptor(addHeaderInterceptor()) // token过滤
                            .addInterceptor(httpLoggingInterceptor)//日志查看
//                            .cache(cache)
                            .connectTimeout(60l, TimeUnit.SECONDS)
                            .readTimeout(60l, TimeUnit.SECONDS)
                            .writeTimeout(60l, TimeUnit.SECONDS)
                            .build();


                    retrofit = new Retrofit
                            .Builder()
                            .client(client)
                            .baseUrl(URLHelper.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

    private static Interceptor addHeaderInterceptor() {

        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        // Provide your custom header here
                        .header("citoken", (String) SpUtils.get("token", ""))
                        .method(originalRequest.method(), originalRequest.body());
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        return headerInterceptor;
    }

    private static Interceptor addQueryParameterInterceptor() {
        Interceptor addQueryParameterInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request request;
                HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                        // Provide your custom parameter here
                        .addQueryParameter("terminalType", "1")
                        .addQueryParameter("cimac", "9c:fb:d5:80:5c:7a")
                        .build();
                request = originalRequest.newBuilder().url(modifiedUrl).build();
                return chain.proceed(request);
            }
        };
        return addQueryParameterInterceptor;
    }

    /**
     * 设置缓存
     */
    private static Interceptor addCacheInterceptor() {
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetworkUtil.isNetworkAvailable(MApplication.getmContext())) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (NetworkUtil.isNetworkAvailable(MApplication.getmContext())) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周  只对get有用,post没有缓冲
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" +
                                    maxStale)
                            .removeHeader("nyn")
                            .build();
                }
                return response;
            }
        };
        return cacheInterceptor;
    }
}
