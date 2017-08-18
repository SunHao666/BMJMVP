package com.ht.bmj.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.ht.bmj.MApplication;
import com.ht.bmj.R;
import com.ht.bmj.base.BaseActivity;
import com.ht.bmj.base.BasePresenter;

/**
 *  欢迎界面
 *  2秒后自动跳转到主页
 */

public class WelcomeAvtivity extends BaseActivity {

    private long sleepTime = 2*1000;

    @Override
    protected void initView() {

    }

    @Override
    protected BasePresenter creatPresenter() {
        return null;
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //跳转到主页
                startActivity(new Intent(WelcomeAvtivity.this,MainActivity.class));
                finish();
            }
        }).start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void otherViewClick(View v) {

    }
}
