package com.ht.bmj.subscriber;

import android.content.Context;

import com.ht.bmj.base.BaseSubscriber;
import com.ht.bmj.exception.ApiException;
import com.ht.bmj.utils.LogUtils;
import com.ht.bmj.utils.NetworkUtil;

/**
 * Created by jpwen on 2017/8/18.
 */

public abstract class CommonSubscriber<T> extends BaseSubscriber<T> {

    private Context context;

    public CommonSubscriber(Context context) {
        this.context = context;
    }

    private static final String TAG = "CommonSubscriber";

    @Override
    public void onStart() {

        if (!NetworkUtil.isNetworkAvailable(context)) {
            LogUtils.e(TAG, "网络不可用");
        } else {
            LogUtils.e(TAG, "网络可用");
        }
    }


    @Override
    protected void onError(ApiException e) {
        LogUtils.e(TAG, "错误信息为 " + "code:" + e.code + "   message:" + e.message);
    }

    @Override
    public void onCompleted() {
        LogUtils.e(TAG, "成功了");
    }
}