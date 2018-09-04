package com.example.administrator.yipen.utils;


import com.example.administrator.yipen.bean.BannerDataBean;
import com.example.administrator.yipen.bean.CountPriceBena;
import com.example.administrator.yipen.bean.FileBean;
import com.example.administrator.yipen.bean.HistoryPayBean;
import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.bean.RegBean;
import com.example.administrator.yipen.bean.SelectInfo;
import com.example.administrator.yipen.bean.UserUpdateInfo;

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
import static com.example.administrator.yipen.constance.ConstanceClass.REGPATH;
import static com.example.administrator.yipen.constance.ConstanceClass.SELECTINFO;
import static com.example.administrator.yipen.constance.ConstanceClass.SUNPARY;
import static com.example.administrator.yipen.constance.ConstanceClass.UPDATEUSERINFO;
import static com.example.administrator.yipen.constance.ConstanceClass.USERINFO;


public interface RetrofitAPI {
    @FormUrlEncoded
    @POST(REGPATH)
    Observable<RegBean> requestReg(@Field("telephone") String parms);

    @FormUrlEncoded
    @POST(LOGINPATH)
    Observable<LoginBean> requestLogin(@Field("telephone") String phoneCode, @Field("newcode") String smsCode);



    @POST(UPDATEUSERINFO)
    @FormUrlEncoded
    Observable<UserUpdateInfo> updateUserInfo(@FieldMap Map<String, String> map);

    @Multipart
    @POST(MULTPARTPATH)
    Observable<FileBean> upload(@Part("token") RequestBody token,@Part("telephone")RequestBody telephone,
                                                @Part MultipartBody.Part part);
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
}
