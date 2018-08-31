package com.example.administrator.yipen.mvp.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.bean.RegBean;
import com.example.administrator.yipen.server.LoginServerce;
import com.example.administrator.yipen.utils.SmartroInter;
import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;


public class RegActivity extends BaseActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener, SmartroInter {
    String tel_uls[] = {"+86", "+886", "+852", "+853"};

    private Button reg;
    private EditText telephone;
    private Spinner spinner;
    private App application;
    private String phoneCode = null;
    private TextView reg_back;
    private Button login;

    private EditText regCode;

    private TextView title;
    CountTimer countTimer = new CountTimer(3000, 1000);

    @Override
    protected int getContentViewId() {
        return R.layout.activity_reg;
    }


    @Override
    protected void initView() {

        spinner = (Spinner) findViewById(R.id.spinner);
        reg = (Button) findViewById(R.id.regmax);
        telephone = (EditText) findViewById(R.id.telephone);
        reg_back = (TextView) findViewById(R.id.reg_blak);
        login = (Button) findViewById(R.id.login_btn);
        regCode = (EditText) findViewById(R.id.regCode);
        title = (TextView) findViewById(R.id.titleFlag);
    }


    @Override
    protected void initData() {

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, tel_uls);
        /*spDown加载适配器*/
        spinner.setAdapter(adapter);
        /*spDown设置监听事件*/
        spinner.setOnItemSelectedListener(this);
        application = (App) App.getApplication();
        reg.setOnClickListener(this);
        reg_back.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        phoneCode = tel_uls[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //登录注册逻辑
    @Override
    public void Scuess(Object o, int requestCode) {

        RegBean regBean = (RegBean) o;


        if (regBean.getStatus() == 0) {
            //失败
            application.toastLong(regBean.getMessage() + ",请检查您的手机号");
        }
        if (regBean.getStatus() == 2) {
            application.toastLong("短信已发送");
            countTimer.start();
        }
        if (regBean.getStatus() == 1) {
            application.toastLong("短信已发送");
            countTimer.start();
        }
    }


    @Override
    public void Error(Throwable e) {
        application.toastLong(e.getMessage());
    }

    //#cae2dbe2
    @Override
    public void LoginScuess(Object o) {
        LoginBean loginBean = (LoginBean) o;
        Log.e("ss", loginBean.toString());
        if (loginBean.getStatus() == 0) {
            //失败
            user.add("login", "err");
            setButton(1);
            application.toastLong(loginBean.getMessage());
        } else if (loginBean.getStatus() == 1) {
            //成功
            application.toastLong("登陆成功,跳转登录页面");
            EventBus.getDefault().post(loginBean.getResult());
            SystemClock.sleep(1000);
            finishLogin("scuess");
        }
    }

    @Override
    public void LoginErr(Throwable t) {
    }

    //点击注册/登录按钮响应事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reg_blak:
                finishLogin("err");
                break;
            case R.id.login_btn:
                setButton(0);
                App.getPresenter(this).LoginPre(telephone.getText().toString().trim(), regCode.getText().toString().trim());
                break;
            case R.id.regmax:
                App.getPresenter(this).RegPre(telephone.getText().toString().trim());
                break;
        }

    }

    public void setButton(int requestCode) {
        if (requestCode == 0) {
            this.getWindow().getDecorView().setBackgroundColor(Color.TRANSPARENT);
            login.setBackgroundColor(Color.GRAY);
        } else if (requestCode == 1) {
            int parseColor = Color.parseColor("#FFFF0088");
            login.setBackgroundColor(parseColor);
        }
    }

    private void finishLogin(String function) {

        user.add("login", function);
        if (function.equals("scuess")) {
            LoginServerce.reflag = true;
        } else if (function.equals("err")) {
            LoginServerce.reflag = false;
        }
        Log.e("log", "user:" + function + "....reFlag:" + LoginServerce.reflag);
        finish();

    }

    //下次登录判断
    @Override
    public void setFlag(boolean flag) {
    }

    // timer Util
    /* 定义一个倒计时的内部类 */
    class CountTimer extends CountDownTimer {
        public CountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);// 参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {// 计时完毕时触发
            reg.setText("重新验证");
            int color = Color.parseColor("#FFFF0088");
            reg.setBackgroundColor(color);
            reg.setClickable(true);
        }

        @SuppressLint("Range")
        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            reg.setClickable(false);
            reg.setAlpha(150);
            reg.setBackgroundColor(Color.GRAY);
            reg.setText((millisUntilFinished / 1000 + "秒后发送验证码"));
        }

    }


}