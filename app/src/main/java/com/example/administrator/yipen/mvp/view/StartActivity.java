package com.example.administrator.yipen.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.example.administrator.yipen.app.App;
import com.example.myapplication.R;

import java.lang.ref.WeakReference;

public class StartActivity extends AppCompatActivity {

    private TextView mTextView;
    CountTime mc = new CountTime(5000, 1000);//第一个参数总计时时间，第二个是间隔时间，单位都是毫秒值
    private Intent intent;

    //初始化控件、数据
    private void init() {
        mTextView = (TextView) findViewById(R.id.msg);
        intent = new Intent(this, MainActivity.class);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        WeakReference<CountTime> countTimeWeakReference = new WeakReference<>(mc);
        if (mc != null) {

            mc = null;
        }

    }

    private class CountTime extends CountDownTimer {

        public CountTime(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {   ////开始计时调用的函数
            mTextView.setText("倒计时:" + millisUntilFinished / 1000 + "");
        }

        @Override
        public void onFinish() {    //完成计时调用的函数
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_start);
        try {

            closeTitleBar();


            init();

            mc.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void closeTitleBar() {
        App.setTitFlag(this);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }



}

