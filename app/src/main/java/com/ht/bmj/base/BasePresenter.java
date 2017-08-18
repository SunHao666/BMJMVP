package com.ht.bmj.base;

import com.ht.bmj.mvp.IModel;
import com.ht.bmj.mvp.IPresenter;
import com.ht.bmj.mvp.IView;

import java.lang.ref.WeakReference;
import java.util.HashMap;

/**
 *  mvp presenter 父类
 *  1.解决mvp泄漏问题
 */

public abstract class BasePresenter<V extends IView> implements IPresenter {

    private WeakReference weakReference;

    public abstract HashMap<String, IModel> getiModelMap();
    /**
     * 绑定view
     * @param view
     */
    @Override
    public void attachView(IView view) {
        weakReference = new WeakReference(view);
    }

    /**
     * 解除绑定view
     */
    @Override
    public void deachView() {
        if(weakReference != null){
            weakReference.clear();
            weakReference = null;
        }
    }

    /**
     * 获取当前view
     * @return
     */
    @Override
    public V getView() {
        return (V) weakReference.get();
    }

    /**
     * @param models
     * @return
     * 添加多个model,如有需要
     */
    public abstract HashMap<String, IModel> loadModelMap(IModel... models);
}
