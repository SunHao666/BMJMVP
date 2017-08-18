package com.ht.bmj.model;

import com.ht.bmj.MApplication;
import com.ht.bmj.base.BaseHttpResult;
import com.ht.bmj.base.BaseModel;
import com.ht.bmj.bean.LoginBean;
import com.ht.bmj.contract.LoginContract;
import com.ht.bmj.exception.ApiException;
import com.ht.bmj.subscriber.CommonSubscriber;
import com.ht.bmj.transformer.CommonTransformer;

import rx.Observable;

/**
 * 登录业务处理类
 * 主要做一些数据处理,网路请求呀
 */

public class LoginModel extends BaseModel {

    public void login(String phone, String pwd, final LoginContract.Event event) {

        if (event == null) {
            throw new RuntimeException("event 不能为空");
        }

        httpService.login(phone, pwd)
                .compose(new CommonTransformer<LoginBean>())
                .subscribe(new CommonSubscriber<LoginBean>(MApplication.getmContext()) {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        event.loginSuccess(loginBean.getToken());
                    }

                    @Override
                    protected void onError(ApiException e) {
                        super.onError(e);
                        event.loginFail(e.message);
                    }
                });
    }

}
