package com.ht.bmj.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ht.bmj.mvp.IView;

import butterknife.ButterKnife;

/**
 * activity 父类
 * 1.view presenter 绑定
 * 2.activity 通用方法设置
 * 3.activity 压栈、出栈
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity
        implements IView, View.OnClickListener{

    public P presenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = creatPresenter();
        setContentView(getLayoutId());
        initView();
        initPresenter();
        initData();
    }

    /**
     * presenter绑定view
     */
    private void initPresenter() {
        if(presenter != null){
            presenter.attachView(this);
        }
    }

    /**
     * butterknife 初始化控件
     */
    protected abstract void initView();
    /**
     * presenter获取
     * @return
     */
    protected abstract P creatPresenter();

    /**
     * 初始化界面数据
     */
    protected abstract void initData();

    /**
     * view点击事件处理
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 点击事件处理
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
         default:
             otherViewClick(v);
             break;
        }
    }

    protected abstract void otherViewClick(View v);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null)
            presenter.deachView();
    }
}
