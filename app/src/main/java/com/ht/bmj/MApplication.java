package com.ht.bmj;

import android.app.Application;
import android.content.Context;

/**
 * Created by jpwen on 2017/8/17.
 */

public class MApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    /**
     * @return
     * 全局的上下文
     */
    public static Context getmContext() {
        return mContext;
    }
}
