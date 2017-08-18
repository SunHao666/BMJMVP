package com.ht.bmj.contract;

/**
 * view 契约接口
 */

public interface LoginContract {

    interface View {
        void showLoading();

        void hideLoading();

        void toMainTabs();

        void showError(String error);
    }

    interface Presenter {
        void login(String phone, String pwd);
    }

    interface Event {
        void loginSuccess(String suc);

        void loginFail(String fail);
    }
}
