package com.example.administrator.yipen.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.constance.SharePUtils;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.administrator.yipen.server.LoginServerce;
import com.zhy.autolayout.AutoLayoutActivity;

public abstract class BaseActivity extends AutoLayoutActivity implements Iview {

    public static App application;
    public static SharePUtils user;
    public static String token;
    public static String phone;
    public static String bis_id;
    public static String meM_id;
    public static String username;

    //布局文件ID
    protected abstract int getContentViewId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        try {
            closeTitleBar();
            NotLoginOr();
            loginInfo();
            initView();
            initData();
            application = (App) App.getApplication();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loginInfo() {
        String login = user.query("login");

        if (login.equals("scuess")) {
        if(user.query("token")!=null&&user.query("phone")!=null) {
             token= user.query("token");
             phone= user.query("phone");
            bis_id = user.query("bis_id");
            meM_id = user.query("meM_id");
            username = user.query("userName");
            LoginServerce.reflag=true;

        }else{

                LoginServerce.reflag = true;
            }
        }else if (login.equals("err")) {
            LoginServerce.reflag = false;
        }
    }

    private void NotLoginOr() {
        user = new SharePUtils(this, "user");
    }
    private void closeTitleBar() {
        App.setTitFlag(this);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
