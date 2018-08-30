package com.example.administrator.yipen.mvp.presenter;


import android.util.Log;

import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.mvp.model.Model;
import com.example.administrator.yipen.mvp.view.Iview;

import java.io.File;
import java.lang.ref.WeakReference;

public class Presenter implements Ipre {
    public Iview iview;
    private final Model model;

    public void DestoryView() {
        if (iview != null) {
            WeakReference<Iview> iviewWeakReference = new WeakReference<>(iview);
        }
    }

    public Presenter(Iview iview) {
        this.iview = iview;
        model = new Model(this);
    }

    public void RegPre(String phone) {
        model.RegModle(phone);
    }

    public void LoginPre(String phone, String smsCode) {
        model.LoginModle(phone, smsCode);
    }

    public void fileMultPart(File f) {
        model.upDataIcon(f);
    }

    @Override
    public void Scuess(Object o, int requestCode) {
        iview.Scuess(o, requestCode);
    }

    @Override
    public void Error(Throwable e) {
        iview.Error(e);
    }

    @Override
    public void LoginScuess(Object o) {
        LoginBean bean = (LoginBean) o;
        Log.e("msg", bean.toString());
        iview.LoginScuess(o);
    }

    @Override
    public void LoginErr(Throwable t) {
        iview.LoginErr(t);
    }
}
