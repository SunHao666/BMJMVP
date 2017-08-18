package com.ht.bmj.utils;

import android.util.Log;

import com.ht.bmj.BuildConfig;

/**
 * Created by jpwen on 2017/8/18.
 */

public class LogUtils {

    public static final boolean isDebug = BuildConfig.DEBUG;

    /**
     * 打印一个debug等级的 log
     */
    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d("jiemo_" + tag, msg);
        }
    }

    /**
     * 打印一个debug等级的 log
     */
    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e("jiemo_" + tag, msg);
        }
    }

    /**
     * 打印一个debug等级的 log
     */
    public static void e(Class cls, String msg) {
        if (isDebug) {
            Log.e("jiemo_" + cls.getSimpleName(), msg);
        }
    }


}