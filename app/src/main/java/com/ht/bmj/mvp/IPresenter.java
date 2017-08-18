package com.ht.bmj.mvp;

/**
 * Presenter基类接口
 */

public interface IPresenter<V extends IView> {
    /**
     * 绑定view
     * @param view
     */
    void attachView(V view);

    /**
     * 防止内存泄漏 关闭activity 解除绑定
     */
    void deachView();

    /**
     * 获取绑定View
     */
    V getView();
}

