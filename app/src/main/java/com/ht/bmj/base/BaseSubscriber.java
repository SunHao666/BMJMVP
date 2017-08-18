package com.ht.bmj.base;

import com.ht.bmj.exception.ApiException;

import rx.Subscriber;

/**
 * Created by jpwen on 2017/8/18.
 */


public abstract class BaseSubscriber<T> extends Subscriber<T> {

    @Override
    public void onError(Throwable e) {
        ApiException apiException = (ApiException) e;
        onError(apiException);
    }


    /**
     * @param e 错误的一个回调
     */
    protected abstract void onError(ApiException e);

}