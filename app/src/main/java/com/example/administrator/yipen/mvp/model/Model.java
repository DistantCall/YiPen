package com.example.administrator.yipen.mvp.model;


import android.util.Log;

import com.example.administrator.yipen.bean.FileBean;
import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.bean.RegBean;
import com.example.administrator.yipen.constance.ConstanceClass;
import com.example.administrator.yipen.mvp.presenter.Ipre;
import com.example.administrator.yipen.server.LoginServerce;
import com.example.administrator.yipen.utils.RetrofitAPI;
import com.example.administrator.yipen.utils.RetrofitUtils;

import java.io.File;

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

    public void upDataIcon(File file) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);

        MultipartBody.Part part = MultipartBody.Part.createFormData("code_url", file.getName(), requestBody);


        retrofitAPI.upload(part).subscribeOn(Schedulers.io())
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
                        Log.e("model", fileBean.toString());
                        if (fileBean.getStatus() == 1) {
                            presenter.Scuess(fileBean, 10001);
                        }
                    }
                });

    }

}
