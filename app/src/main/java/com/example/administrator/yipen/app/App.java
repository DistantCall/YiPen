package com.example.administrator.yipen.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.administrator.yipen.bean.WeiXin;
import com.example.administrator.yipen.constance.SharePUtils;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.administrator.yipen.mvp.view.Iview;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import okhttp3.MediaType;
import okhttp3.RequestBody;


public class App extends Application {
    private static App app;
    private static Presenter presenter;
    public static String WXAPPID;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SharePUtils user = new SharePUtils(this, "user");

        if (user.query("login") == null) {
            user.add("login", "err");
        }
        Fresco.initialize(this);

    }

    //判断登陆后下次是否需要重新登录的操作


    public static Application getApplication() {
        return app;
    }

    public static RequestBody toRequestBody(String value) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), value);
        return requestBody;
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


    public void toastLong(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void toastShow(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public static Presenter getPresenter(Iview iview) {


        presenter = new Presenter(iview);


        return presenter;
    }

public  void get(WeiXin bean){
    IWXAPI wxapi = WXAPIFactory.createWXAPI(this, null);  //应用ID 即微信开放平台审核通过的应用APPID
    wxapi.registerApp(bean.getResult().getAppid());    //应用ID
    PayReq payReq = new PayReq();
    payReq.appId =bean.getResult().getAppid();        //应用ID
    payReq.partnerId = bean.getResult().getPartnerid();      //商户号 即微信支付分配的商户号
    payReq.prepayId =bean.getResult().getPrepayid();        //预支付交易会话ID
    payReq.packageValue =bean.getResult().getPackageX();    //扩展字段
    payReq.nonceStr =bean.getResult().getNoncestr();        //随机字符串不长于32位。
    payReq.timeStamp =bean.getResult().getTimestamp()+""; //时间戳
    payReq.sign = bean.getResult().getSign();             //签名
    wxapi.sendReq(payReq);
}

}





