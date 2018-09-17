package com.example.administrrtor.yipen.app;

import android.app.Activity;
import android.app.Application;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.administrrtor.yipen.VersionOne.bean.WeiXin;
import com.example.administrrtor.yipen.VersionOne.mvp.presenter.Presenter;
import com.example.administrrtor.yipen.VersionOne.mvp.view.Iview;
import com.example.administrrtor.yipen.constance.SharePUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import okhttp3.MediaType;
import okhttp3.RequestBody;


public class App extends Application {
    private static App app;
    private static Presenter presenter;
    public static final String WX_APP_ID ="wxb80a36968e113605";
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


    public static App getApplication() {
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

    public void get(Activity activity, WeiXin bean) {


        IWXAPI api = WXAPIFactory.createWXAPI(activity, null);//通过工厂创建对象
        api.registerApp(bean.getResult().getAppid());
        PayReq request = new PayReq();
        request.appId = bean.getResult().getAppid();
        request.partnerId = bean.getResult().getPartnerid().trim();
        request.prepayId = bean.getResult().getPrepayid().trim();
        request.nonceStr = bean.getResult().getNoncestr().trim();
        request.timeStamp = (bean.getResult().getTimestamp() + "").trim();
        request.packageValue = bean.getResult().getPackageX().trim();
        request.sign = bean.getResult().getSign().trim();
//                                request.extData = "app data"; // optional
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(request);
    }


}





