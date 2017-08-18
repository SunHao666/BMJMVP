package com.ht.bmj.base;

/**
 * Created by jpwen on 2017/8/18.
 */

public class BaseHttpResult<T> {

    private int result_code;
    private String result_message;
    private T result;

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public String getResult_message() {
        return result_message;
    }

    public void setResult_message(String result_message) {
        this.result_message = result_message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
