package com.example.administrator.yipen.mvp.model;


import android.util.Log;

import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.bean.BannerDataBean;
import com.example.administrator.yipen.bean.CountPriceBena;
import com.example.administrator.yipen.bean.FileBean;
import com.example.administrator.yipen.bean.HistoryPayBean;
import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.bean.RegBean;
import com.example.administrator.yipen.bean.SelectInfo;
import com.example.administrator.yipen.bean.UserUpdateInfo;
import com.example.administrator.yipen.constance.ConstanceClass;
import com.example.administrator.yipen.mvp.presenter.Ipre;
import com.example.administrator.yipen.server.LoginServerce;
import com.example.administrator.yipen.utils.RetrofitAPI;
import com.example.administrator.yipen.utils.RetrofitUtils;
import com.example.administrator.yipen.utils.UserInfo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Model {
    public Ipre presenter;
    private final RetrofitAPI retrofitAPI;
    Map<String, String> map;

    public Model(Ipre presenter) {
        this.presenter = presenter;
        retrofitAPI = RetrofitUtils.retrofitInstance().initRetrofit(ConstanceClass.LOCTIONPATH).create(RetrofitAPI.class);


    }

    public void RegModle(String phone) {

        retrofitAPI.requestReg(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RegBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("err", e.getMessage());
                        presenter.Error(e);
                    }

                    @Override
                    public void onNext(RegBean regBean) {

                        try {
                            Log.e("view", regBean.toString());
                            presenter.Scuess(regBean, 9999);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    public void LoginModle(String phone, String smsCode) {

        retrofitAPI.requestLogin(phone, smsCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.LoginErr(e);

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        presenter.LoginScuess(loginBean);
                        if (loginBean.getStatus() == 1) {
                            LoginServerce.reflag = true;
                        } else {
                            LoginServerce.reflag = false;
                        }

                    }
                });

    }

    public void upDataIcon(String phone,File file,String token) {
        Map<String, RequestBody> params = new HashMap<>();

        RequestBody tokenBody = App.toRequestBody(token);
        RequestBody phoneBody= App.toRequestBody(phone);
        RequestBody photoRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part photoPart = MultipartBody.Part.createFormData("code_url", file.getName(), photoRequestBody);
        retrofitAPI.upload(tokenBody,phoneBody,photoPart).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<FileBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FileBean fileBean) {
                        Log.e("file_Bean",fileBean.toString());
                        if (fileBean.getStatus() == 1) {
                            presenter.Scuess(fileBean, 10001);
                        }
                    }
                });

    }

    public void userInfoModel(String phone, String token) {
        retrofitAPI.userinfo(phone, token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserInfo userInfo) {
                        Log.i("User_log", userInfo.toString());
                        presenter.Scuess(userInfo, 10000);
                    }
                });
    }

    public void updateUserInfo(String phone, String requestCode, String token) {
        map = new HashMap<>();
        map.put("telephone", phone);
        map.put("code", requestCode);
        map.put("token", token);

        retrofitAPI.updateUserInfo(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserUpdateInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(UserUpdateInfo userUpdateInfo) {
                        presenter.Scuess(userUpdateInfo, 10002);
                    }
                });

    }

    public void priceCount(String phone, String member_id, String token) {
        map = new HashMap<>();
        map.put("telephone", phone);
        map.put("member_id", member_id);
        map.put("token", token);
        retrofitAPI.sumPay(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CountPriceBena>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CountPriceBena historyPayBean) {
                        Log.e("data", historyPayBean.toString());
                        presenter.Scuess(historyPayBean, 10003);

                    }

                });

    }

    public void HistoryPay(String phone, String member_id, String token) {
        map = new HashMap<>();
        map.put("telephone", phone);
        map.put("member_id", member_id);
        map.put("token", token);
        retrofitAPI.historyPay(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HistoryPayBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HistoryPayBean historyPayBean) {
                        Log.e("HistoryPay", historyPayBean.toString());
                        presenter.Scuess(historyPayBean, 10005);

                    }

                });

    }

    public void seletInfo(String phone, String member_id, String token) {
        map = new HashMap<>();
        map.put("telephone", phone);
        map.put("member_id", member_id);
        map.put("token", token);
        Log.e("data", phone + "-----" + member_id + "----" + token);
        retrofitAPI.selsectInfo(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SelectInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SelectInfo selectInfo) {
                        Log.e("data", selectInfo.toString());
                        presenter.Scuess(selectInfo, 10004);
                    }

                });

    }

    public void bannerModel(String bis_id) {
        retrofitAPI.banner(bis_id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BannerDataBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BannerDataBean bannerDataBean) {
                        Log.e("banner", bannerDataBean.toString());
                        presenter.Scuess(bannerDataBean, 9998);
                    }
                });
    }
}
