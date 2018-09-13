package com.example.administrator.yipen.mvp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.KeyEvent;
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
import com.example.administrator.yipen.constance.ConstanceClass;
import com.example.administrator.yipen.server.LoginServerce;
import com.example.administrator.yipen.utils.SmartroInter;
import com.example.myapplication.R;

//import org.greenrobot.eventbus.EventBus;


public class RegActivity extends BaseActivity implements View.OnClickListener, SmartroInter {


    private Button reg;
    private EditText telephone;
    private App application;

    private Button login;

    private EditText regCode;

    CountTimer countTimer = new CountTimer(3000, 1000);


    @Override
    protected int getContentViewId() {
        return R.layout.activity_reg;
    }


    @Override
    protected void initView() {
        reg = (Button) findViewById(R.id.regmax);
        telephone = (EditText) findViewById(R.id.telephone);
        login = (Button) findViewById(R.id.login_btn);
        regCode = (EditText) findViewById(R.id.regCode);

    }


    @Override
    protected void initData() {
        application = (App) App.getApplication();
        reg.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    //登录注册逻辑
    @Override
    public void Scuess(Object o, int requestCode) {

        RegBean regBean = (RegBean) o;


        if (regBean.getStatus() == 0) {
            //失败
            application.toastLong(regBean.getMessage() + ",请检查您的手机号");
            LoginServerce.reflag = false;
        }
        if (regBean.getStatus() == 2) {
            application.toastLong("短信已发送");
            countTimer.start();
            LoginServerce.reflag = false;
        }
        if (regBean.getStatus() == 1) {
            application.toastLong("短信已发送");
            countTimer.start();
            LoginServerce.reflag = false;
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
        if (loginBean.getStatus() == 0) {
            //失败

            user.add("login", "err");
            LoginServerce.reflag = false;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setButton(1);
                    application.toastLong(loginBean.getMessage());
                }
            });


        } else if (loginBean.getStatus() == 1) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //成功
                    application.toastLong("登陆成功,跳转登录页面");
//                    EventBus.getDefault().postSticky(loginBean.getResult());
                    user.add("token", loginBean.getResult().get(0).getToken());
                    user.add("phone", loginBean.getResult().get(0).getTelephone());
                    user.add("user_icon", loginBean.getResult().get(0).getCode_url());
                    user.add("username", loginBean.getResult().get(0).getUsername());
                    user.add("nickName", loginBean.getResult().get(0).getUsername());
                    user.add("bis_id", loginBean.getResult().get(0).getBis_id() + "");
                    user.add("meM_id", loginBean.getResult().get(0).getMem_id() + "");
                    BaseActivity.phone = loginBean.getResult().get(0).getTelephone();
                    BaseActivity.token = loginBean.getResult().get(0).getToken();
                    BaseActivity.bis_id = loginBean.getResult().get(0).getBis_id() + "";
                    BaseActivity.meM_id = loginBean.getResult().get(0).getMem_id() + "";
                    BaseActivity.codeUrl = ConstanceClass.LOCTIONPATH + "/img/" + loginBean.getResult().get(0).getCode_url();
                    BaseActivity.username = loginBean.getResult().get(0).getUsername();
                    BaseActivity.nickName = loginBean.getResult().get(0).getUsername();
                    finishLogin("scuess");
                }
            });

        }
    }

    @Override
    public void LoginErr(Throwable t) {

    }

    //点击注册/登录按钮响应事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                setButton(0);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        App.getPresenter(RegActivity.this).LoginPre(telephone.getText().toString().trim(), regCode.getText().toString().trim());

                    }
                }.start();
                break;
            case R.id.regmax:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        App.getPresenter(RegActivity.this).RegPre(telephone.getText().toString().trim());
                    }
                }.start();
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
        Log.e("login", function);
        user.add("login", function);
        if (function.equals("scuess")) {
            LoginServerce.reflag = true;
        } else if (function.equals("err")) {
            LoginServerce.reflag = false;
        }
        Log.e("login", LoginServerce.reflag + "");
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
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    reg.setText("重新验证");
                    int color = Color.parseColor("#FFFF0088");
                    reg.setBackgroundColor(color);
                    reg.setClickable(true);
                }
            });

        }

        @SuppressLint("Range")
        @Override
        public void onTick(long millisUntilFinished) {// 计时过程显示
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    reg.setClickable(false);
                    reg.setAlpha(150);
                    reg.setBackgroundColor(Color.GRAY);
                    reg.setText((millisUntilFinished / 1000 + "秒后发送验证码"));
                }
            });

        }

    }


}
