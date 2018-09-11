package com.example.administrator.yipen.mvp.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.example.administrator.yipen.adapter.HomeAdapter;
import com.example.administrator.yipen.adapter.PayRetaAdapter;
import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.bean.BannerDataBean;
import com.example.administrator.yipen.bean.StagesBean;
import com.example.administrator.yipen.mvp.presenter.Presenter;

import com.example.administrator.yipen.ui.RecycleViewDivider;
import com.example.myapplication.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;


import java.util.ArrayList;
import java.util.List;

public class PayStagesActivity extends BaseActivity implements OnBannerListener {

    private Banner banner;
    private RecyclerView recyclerView;

    private PayRetaAdapter payRetaAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pay_stages;

    }


    private void setBanner(List<BannerDataBean.ResultBean> list) {
        List<String> imgs = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            imgs.add(list.get(i).getImage());
            Log.e("j", list.get(i).getImage());
        }
        List<String> list_title = new ArrayList<>();

        for (int i = 0; i < imgs.size(); i++) {
            list_title.add("");
        }
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(imgs);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();


    }


    @Override
    protected void initView() {
        banner = (Banner) findViewById(R.id.pay_baner);
        recyclerView = (RecyclerView) findViewById(R.id.pay_rate_recy);

    }


    @Override
    protected void initData() {

        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));
        Presenter presenter = App.getPresenter(this);
        presenter.bannerPre(bis_id);
        presenter.orderFormPre(phone, token);

    }


    @Override
    public void Scuess(Object o, int requestCode) {
        if (requestCode == 9998) {
            BannerDataBean bannerDataBean = (BannerDataBean) o;

            setBanner(bannerDataBean.getResult());
        } else if (requestCode == 10006) {
            StagesBean stagesBean = (StagesBean) o;
            payRetaAdapter = new PayRetaAdapter(this, stagesBean.getRes());
            recyclerView.setAdapter(payRetaAdapter);
        }
    }

    @Override
    public void Error(Throwable e) {

    }

    @Override
    public void LoginScuess(Object o) {

    }

    @Override
    public void LoginErr(Throwable t) {

    }

    @Override
    public void OnBannerClick(int position) {

    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

}
