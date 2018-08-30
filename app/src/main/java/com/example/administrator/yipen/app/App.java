package com.example.administrator.yipen.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.administrator.yipen.constance.SharePUtils;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.administrator.yipen.mvp.view.Iview;
import com.example.administrator.yipen.server.LoginServerce;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;


public class App extends Application {
   private static App  app;
   private static Presenter presenter;
    private static SharePUtils user;
    List<Iview> list=new ArrayList<>();
    private Intent intent;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;

        Fresco.initialize(this);
    }

        //判断登陆后下次是否需要重新登录的操作
    public void NotLoginOr() {
        user = new SharePUtils(this, "user");
        intent = new Intent(this, LoginServerce.class);

        String login = user.query("login");
        if(login.equals("scuess")){
            LoginServerce.reflag=true;
        }else if(login.equals("err")){
            LoginServerce.reflag=false;
        }


        startService(intent);
    }


    public static SharePUtils getSharedP(){
        return user;
    }

    public static Application getApplication(){
        return app;
    }

    public static void setTitFlag(Activity activity) {
        // Android4.4的沉浸式状态栏写法
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        // 底部导航栏也可以弄成透明的
        int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
        attributes.flags |= flagTranslucentStatus;
        attributes.flags |= flagTranslucentNavigation;
        window.setAttributes(attributes);
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            ViewGroup contentView = window.getDecorView().findViewById(Window.ID_ANDROID_CONTENT);
//            contentView.getChildAt(0).setFitsSystemWindows(false);
//        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        stopService(intent);
    }

    public void toastLong(String msg){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
}
    public void toastShow(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public static Presenter getPresenter(Iview iview){


             presenter=new Presenter(iview);


        return presenter;
    }
}





