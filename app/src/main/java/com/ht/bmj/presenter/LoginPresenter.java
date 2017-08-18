package com.ht.bmj.presenter;

import com.ht.bmj.activity.LoginActivity;
import com.ht.bmj.base.BasePresenter;
import com.ht.bmj.contract.LoginContract;
import com.ht.bmj.model.LoginModel;
import com.ht.bmj.mvp.IModel;

import java.util.HashMap;

/**
 * Created by jpwen on 2017/8/17.
 */

public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.Presenter{

    @Override
    public void login(String phone, String pwd) {
        if(getView() != null){
            ((LoginModel) getiModelMap().get("login")).login(phone, pwd, new LoginContract.Event() {
                @Override
                public void loginSuccess(String suc) {
                    getView().toMainTabs();
                }

                @Override
                public void loginFail(String fail) {
                    getView().showError(fail);
                }
            });
        }
    }

    @Override
    public HashMap<String, IModel> getiModelMap() {
        return loadModelMap(new LoginModel());
    }

    @Override
    public HashMap<String, IModel> loadModelMap(IModel... models) {
        HashMap<String,IModel> map = new HashMap<String, IModel>();
        map.put("login",models[0]);
        return map;
    }
}
