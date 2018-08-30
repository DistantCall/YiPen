package com.example.administrator.yipen.utils;

import android.util.Log;


import com.example.administrator.yipen.app.App;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;

    public static RetrofitUtils retrofitInstance() {

        if (retrofitUtils == null) {

            if (null == retrofitUtils) {
                retrofitUtils = new RetrofitUtils();
            }

        }
        return retrofitUtils;
    }

    public static OkHttpClient OkHttpInstance(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient().newBuilder()
                    //设置连接时间
                    .connectTimeout(500, TimeUnit.MILLISECONDS)
                    //设置读时间
                    .readTimeout(1000, TimeUnit.MILLISECONDS)
                    //设置写时间
                    .writeTimeout(1000, TimeUnit.MILLISECONDS)
                    //设置缓存
                    .cache(new Cache(App.getApplication().getExternalCacheDir(), 10 * 1024 * 1024))
                    .build();
            return okHttpClient;
        } else {
            return okHttpClient;
        }
    }

    public Retrofit initRetrofit(String url) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new inter()).build();
        OkHttpClient okHttpClient = OkHttpInstance(client);


        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    class inter implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            HttpUrl url = chain.request().url();
            Log.e("url", url.toString());
            return chain.proceed(chain.request());
        }
    }
}
