package com.example.administrrtor.yipen.utils;


import com.example.administrrtor.yipen.VersionOne.bean.BannerDataBean;
import com.example.administrrtor.yipen.VersionOne.bean.CountPriceBena;
import com.example.administrrtor.yipen.VersionOne.bean.FenQiBean;
import com.example.administrrtor.yipen.VersionOne.bean.FileBean;
import com.example.administrrtor.yipen.VersionOne.bean.HistoryPayBean;
import com.example.administrrtor.yipen.VersionOne.bean.JiaoFBean;
import com.example.administrrtor.yipen.VersionOne.bean.LoginBean;
import com.example.administrrtor.yipen.VersionOne.bean.PreMaryBean;
import com.example.administrrtor.yipen.VersionOne.bean.RegBean;
import com.example.administrrtor.yipen.VersionOne.bean.SelectInfo;
import com.example.administrrtor.yipen.VersionOne.bean.UserUpdateInfo;
import com.example.administrrtor.yipen.VersionOne.bean.WeiXin;
import java.util.Map;

import com.example.administrrtor.yipen.constance.ConstanceClass;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.*;
import rx.Observable;

import static com.example.administrrtor.yipen.constance.ConstanceClass.FENQIPATH;
import static com.example.administrrtor.yipen.constance.ConstanceClass.YIJIAOFEI;


public interface RetrofitAPI {
    //验证码

    @FormUrlEncoded
    @POST(ConstanceClass.REGPATH)
    Observable<RegBean> requestReg(@Field("telephone") String parms);

    //登录

    @FormUrlEncoded
    @POST(ConstanceClass.LOGINPATH)
    Observable<LoginBean> requestLogin(@Field("telephone") String phoneCode, @Field("newcode") String smsCode);

    //修改用户信息

    @POST(ConstanceClass.UPDATEUSERINFO)
    @FormUrlEncoded
    Observable<UserUpdateInfo> updateUserInfo(@Field("telephone") String phone, @Field("token") String token, @FieldMap Map<String, String> map);

    //上传头像

    @Multipart
    @POST(ConstanceClass.MULTPARTPATH)
    Observable<FileBean> upload(@Part("token") RequestBody token, @Part("telephone") RequestBody telephone, @Part MultipartBody.Part part);

    @POST(ConstanceClass.SUNPARY)
    @FormUrlEncoded
    Observable<CountPriceBena> sumPay(@FieldMap Map<String, String> map);

    @POST(ConstanceClass.SELECTINFO)
    @FormUrlEncoded
    Observable<SelectInfo> selsectInfo(@FieldMap Map<String, String> map);

    @POST(ConstanceClass.HISTORYPATH)
    @FormUrlEncoded
    Observable<HistoryPayBean> historyPay(@FieldMap Map<String, String> map);

    @GET(ConstanceClass.BANNERPATH)
    Observable<BannerDataBean> banner(@Query("bis_id") String bis_id);

    @POST(ConstanceClass.USERINFO)
    @FormUrlEncoded
    Observable<UserInfo> userinfo(@Field("telephone") String phoneCode, @Field("token") String token);

    @POST(ConstanceClass.PREMARY)
    @FormUrlEncoded
    Observable<PreMaryBean> preMary(@Field("telephone") String phoneCode, @Field("token") String token);
    @POST(ConstanceClass.WXMONEY)
    @FormUrlEncoded
    Observable<WeiXin> wxMoney(@Field("order_no") String order_no);

    //分期
    @POST(FENQIPATH)
    @FormUrlEncoded
    Observable<FenQiBean> fenQi(@Field("telephone") String phoneCode, @Field("token") String token);

    //已缴费
    @POST(YIJIAOFEI)
    @FormUrlEncoded
    Observable<JiaoFBean> YiJiao(@Field("telephone") String phoneCode, @Field("token") String token);

}
