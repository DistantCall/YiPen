package com.example.administrator.yipen.utils;


import com.example.administrator.yipen.bean.FileBean;
import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.bean.RegBean;
import com.example.administrator.yipen.bean.UserUpdateInfo;

import okhttp3.MultipartBody;
import retrofit2.http.*;
import rx.Observable;

import static com.example.administrator.yipen.constance.ConstanceClass.LOGINPATH;
import static com.example.administrator.yipen.constance.ConstanceClass.MULTPARTPATH;
import static com.example.administrator.yipen.constance.ConstanceClass.REGPATH;
import static com.example.administrator.yipen.constance.ConstanceClass.UPDATEUSERINFO;


public interface RetrofitAPI {
    @FormUrlEncoded
    @POST(REGPATH)
    Observable<RegBean> requestReg(@Field("telephone") String parms);
    @FormUrlEncoded
    @POST(LOGINPATH)
    Observable<LoginBean> requestLogin(@Field("telephone") String phoneCode, @Field("newcode") String smsCode);
    @POST(MULTPARTPATH)
    @Multipart
    Observable<FileBean> upload(@Part MultipartBody.Part file);
    @POST(UPDATEUSERINFO)
    @FormUrlEncoded
    Observable<UserUpdateInfo> updateUserInfo(@Field("telephone") String phone);
}
