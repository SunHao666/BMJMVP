package com.ht.bmj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.ht.bmj.MApplication;
import com.ht.bmj.R;
import com.ht.bmj.base.BaseActivity;
import com.ht.bmj.base.BasePresenter;
import com.ht.bmj.contract.LoginContract;
import com.ht.bmj.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View  {

    @BindView(R.id.te_phone)
    TextInputEditText tePhone;
    @BindView(R.id.te_pwd)
    TextInputEditText tePwd;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;

    @Override
    protected LoginPresenter creatPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void otherViewClick(View v) {
        presenter.login("1352253131","qwe123");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toMainTabs() {
        startActivity(new Intent(MApplication.getmContext(),MainActivity.class));
        finish();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(MApplication.getmContext(),error,Toast.LENGTH_SHORT).show();
    }
}
