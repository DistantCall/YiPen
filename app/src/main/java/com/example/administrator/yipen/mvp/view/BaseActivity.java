package com.example.administrator.yipen.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;

import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.constance.ConstanceClass;
import com.example.administrator.yipen.constance.SharePUtils;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.administrator.yipen.server.LoginServerce;


public abstract class BaseActivity extends AppCompatActivity implements Iview {

    public static App application;
    public static SharePUtils user;
    public static String token = "8a23d1e8c41df3318b3a8058a0b85870";
    public static String phone = "18810415234";
    public static String bis_id = 1 + "";
    public static String meM_id=1+"";
    public static String user_Icon;
    public static String codeUrl = "604709";
    public static String username = "用户";
    public static String nickName ="user";

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

        if (user.query("login").equals("err")) {

            LoginServerce.reflag = false;
        } else if (user.query("login").equals("scuess")) {
            token = user.query("token");
            phone = user.query("phone");
            meM_id = user.query("meM_id");
            codeUrl = ConstanceClass.LOCTIONPATH + "/img/" + user.query("user_icon");
            bis_id=user.query("bis_id");
            LoginServerce.reflag = true;

        }
    }

    private void NotLoginOr() {
        user = new SharePUtils(this, "user");
    }

    private void closeTitleBar() {
        App.setTitFlag(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Presenter.DestoryView();
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
