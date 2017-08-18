package com.ht.bmj.exception;

/**
 * Created by jpwen on 2017/8/18.
 *
 * 自定义的异常,处理解析网络时的错误
 */

public class ApiException extends RuntimeException {
    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }


}
