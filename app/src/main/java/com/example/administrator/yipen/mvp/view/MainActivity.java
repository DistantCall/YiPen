package com.example.administrator.yipen.mvp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;

import com.example.administrator.yipen.mvp.view.frag.HomeFragment;
import com.example.administrator.yipen.mvp.view.frag.MineFragment;
import com.example.administrator.yipen.mvp.view.frag.ShopFragment;
import com.example.myapplication.R;
import com.hjm.bottomtabbar.BottomTabBar;


public class MainActivity extends BaseActivity {
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }

    //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                System.exit(0);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void initData() {
        int argen = Color.parseColor("#F59A24");
        BottomTabBar mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);
        mBottomTabBar.init(getSupportFragmentManager())
                .setChangeColor(argen, Color.BLACK)
                .addTabItem("首页", R.mipmap.mainchenge, R.mipmap.main, HomeFragment.class)
                .addTabItem("商城", R.mipmap.shopchenge, R.mipmap.shop, ShopFragment.class)
                .addTabItem("我的", R.mipmap.minechenge, R.mipmap.mine, MineFragment.class);
    }


    @Override
    public void Scuess(Object o, int requestCode) {

    }

    @Override
    public void Error(Throwable e) {

    }

    @Override
    public void LoginScuess(Object o) {

    }

    @Override
    public void LoginErr(Throwable t) {

    }
}
