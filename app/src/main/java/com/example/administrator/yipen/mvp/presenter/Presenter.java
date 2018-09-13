package com.example.administrator.yipen.mvp.presenter;


import android.util.Log;

import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.mvp.model.Model;
import com.example.administrator.yipen.mvp.view.Iview;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Map;

public class Presenter implements Ipre {
    public static Iview iview;
    private final Model model;

    public static void DestoryView() {
        if (iview != null) {
            WeakReference<Iview> iviewWeakReference = new WeakReference<>(iview);
        }
    }

    public Presenter(Iview iview) {
        this.iview = iview;
        model = new Model(this);
    }
public void wxMoneyPre(String parms){
        model.wxMoneyModel(parms);
}
    public void RegPre(String phone) {
        model.RegModle(phone);
    }

    public void LoginPre(String phone, String smsCode) {
        model.LoginModle(phone, smsCode);
    }

    public void fileMultPart(String phone, File f, String token) {
        model.upDataIcon(phone, f, token);
    }
        public void preMaryModel(String phone,String token){
        model.preMary(phone,token);
        }
    public void updateUserInfo(String phone, String token, Map<String, String> map) {
        model.updateUserInfo(phone, token, map);
    }

    public void userInfoPre(String phone, String token) {
        model.userInfoModel(phone, token);
    }

    public void selectInfo(String phone, String member_id, String token) {
        model.seletInfo(phone, member_id, token);
    }

    public void priceCount(String phone, String member_id, String token) {
        model.priceCount(phone, member_id, token);
    }

    public void HistoryPay(String phone, String member_id, String token) {
        model.HistoryPay(phone, member_id, token);
    }

    public void bannerPre(String bis_id) {
        model.bannerModel(bis_id);
    }

    public void orderFormPre(String phone,  String token) {
        model.OrderForm(phone,token);
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
