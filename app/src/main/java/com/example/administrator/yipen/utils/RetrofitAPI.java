package com.example.administrator.yipen.utils;


import com.example.administrator.yipen.bean.BannerDataBean;
import com.example.administrator.yipen.bean.CountPriceBena;
import com.example.administrator.yipen.bean.FileBean;
import com.example.administrator.yipen.bean.HistoryPayBean;
import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.bean.OrderForm;
import com.example.administrator.yipen.bean.PreMaryBean;
import com.example.administrator.yipen.bean.RegBean;
import com.example.administrator.yipen.bean.SelectInfo;
import com.example.administrator.yipen.bean.StagesBean;
import com.example.administrator.yipen.bean.UserUpdateInfo;
import com.example.administrator.yipen.bean.WeiXin;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;
import rx.Observable;

import static com.example.administrator.yipen.constance.ConstanceClass.BANNERPATH;
import static com.example.administrator.yipen.constance.ConstanceClass.HISTORYPATH;
import static com.example.administrator.yipen.constance.ConstanceClass.LOGINPATH;
import static com.example.administrator.yipen.constance.ConstanceClass.MULTPARTPATH;
import static com.example.administrator.yipen.constance.ConstanceClass.ORDERFORM;
import static com.example.administrator.yipen.constance.ConstanceClass.PREMARY;
import static com.example.administrator.yipen.constance.ConstanceClass.REGPATH;
import static com.example.administrator.yipen.constance.ConstanceClass.SELECTINFO;
import static com.example.administrator.yipen.constance.ConstanceClass.SUNPARY;
import static com.example.administrator.yipen.constance.ConstanceClass.UPDATEUSERINFO;
import static com.example.administrator.yipen.constance.ConstanceClass.USERINFO;
import static com.example.administrator.yipen.constance.ConstanceClass.WXMONEY;


public interface RetrofitAPI {
    //验证码

    @FormUrlEncoded
    @POST(REGPATH)
    Observable<RegBean> requestReg(@Field("telephone") String parms);

    //登录

    @FormUrlEncoded
    @POST(LOGINPATH)
    Observable<LoginBean> requestLogin(@Field("telephone") String phoneCode, @Field("newcode") String smsCode);

    //修改用户信息

    @POST(UPDATEUSERINFO)
    @FormUrlEncoded
    Observable<UserUpdateInfo> updateUserInfo(@Field("telephone") String phone, @Field("token") String token, @FieldMap Map<String, String> map);

    //上传头像

    @Multipart
    @POST(MULTPARTPATH)
    Observable<FileBean> upload(@Part("token") RequestBody token, @Part("telephone") RequestBody telephone, @Part MultipartBody.Part part);

    @POST(SUNPARY)
    @FormUrlEncoded
    Observable<CountPriceBena> sumPay(@FieldMap Map<String, String> map);

    @POST(SELECTINFO)
    @FormUrlEncoded
    Observable<SelectInfo> selsectInfo(@FieldMap Map<String, String> map);

    @POST(HISTORYPATH)
    @FormUrlEncoded
    Observable<HistoryPayBean> historyPay(@FieldMap Map<String, String> map);

    @GET(BANNERPATH)
    Observable<BannerDataBean> banner(@Query("bis_id") String bis_id);

    @POST(USERINFO)
    @FormUrlEncoded
    Observable<UserInfo> userinfo(@Field("telephone") String phoneCode, @Field("token") String token);

    @POST(ORDERFORM)
    @FormUrlEncoded
    Observable<StagesBean> OrderForm(@Field("telephone") String phoneCode, @Field("token") String token);
    @POST(PREMARY)
    @FormUrlEncoded
    Observable<PreMaryBean> preMary(@Field("telephone") String phoneCode, @Field("token") String token);
    @POST(WXMONEY)
    @FormUrlEncoded
    Observable<WeiXin> wxMoney(@Field("order_no") String order_no);

}
