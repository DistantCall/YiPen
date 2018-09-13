package com.example.administrator.yipen.adapter;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.bean.HistoryPayBean;
import com.example.administrator.yipen.bean.PreMaryBean;
import com.example.administrator.yipen.bean.SelectInfo;
import com.example.administrator.yipen.bean.WeiXin;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.administrator.yipen.mvp.view.BaseActivity;
import com.example.administrator.yipen.mvp.view.Iview;
import com.example.administrator.yipen.mvp.view.PayStagesActivity;
import com.example.administrator.yipen.mvp.view.RegActivity;

import com.example.administrator.yipen.ui.CustomLinearLayoutManager;
import com.example.administrator.yipen.utils.ChatDetailItemDecoration;
import com.example.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter implements OnBannerListener, Iview {
    private List<HistoryPayBean.ResBean> historyPayList;
    private List<String> imgs;
    private List<SelectInfo.ResultBean> resultBean;
    private Context context;
    private String ResultPrice;
    private boolean reflag;
    Presenter presenter;

    public HomeAdapter(Context context, List<String> imgs, List<SelectInfo.ResultBean> resultBean, String ResultPrice, List<HistoryPayBean.ResBean> historyPayList, boolean reflag) {
        this.context = context;

        this.imgs = imgs;
        this.resultBean = resultBean;
        this.ResultPrice = ResultPrice;
        this.historyPayList = historyPayList;
        this.reflag = reflag;
    }

    public HomeAdapter(Context context, List<String> imgs, boolean reflag) {
        this.context = context;
        this.imgs = imgs;
        this.reflag = reflag;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (reflag) {
                if (viewType == 0) {
                    view = LayoutInflater.from(context).inflate(R.layout.banner_layout, parent, false);
                    BannerHolder bannerHolder = new BannerHolder(view);
                    return bannerHolder;
                } else if (viewType == 1) {
                    view = LayoutInflater.from(context).inflate(R.layout.fouction_layout, parent, false);
                    FunctionHolder functionHolder = new FunctionHolder(view);
                    return functionHolder;
                } else if (viewType == 2) {
                        view = LayoutInflater.from(context).inflate(R.layout.want_layout, parent, false);
                        PayHolder payHolder = new PayHolder(view);
                        return payHolder;
                } else if (viewType == 3) {
                    view = LayoutInflater.from(context).inflate(R.layout.historica_layout, parent, false);
                    HistoricaHolder historicaHolder = new HistoricaHolder(view);
                    return historicaHolder;
                } else if (viewType == 4) {
                    view = LayoutInflater.from(context).inflate(R.layout.should_layout, parent, false);
                    ShouldHolder shouldHolder = new ShouldHolder(view);
                    return shouldHolder;
                }

        } else {
            if (viewType == 0) {
                view = LayoutInflater.from(context).inflate(R.layout.banner_layout, parent, false);
                BannerHolder bannerHolder = new BannerHolder(view);
                return bannerHolder;
            } else if (viewType == 1) {
                view = LayoutInflater.from(context).inflate(R.layout.fouction_layout, parent, false);
                FunctionHolder functionHolder = new FunctionHolder(view);
                return functionHolder;
            } else if (viewType == 2) {

                    view = LayoutInflater.from(context).inflate(R.layout.want_layout, parent, false);
                    PayHolder payHolder = new PayHolder(view);
                    return payHolder;

            } else if (viewType == 3) {
                view = LayoutInflater.from(context).inflate(R.layout.noogin, parent, false);
                ButtonHolder buttonHolder = new ButtonHolder(view);
                return buttonHolder;
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        if (reflag) {

            if (holder instanceof BannerHolder) {

                setBanner(((BannerHolder) holder).banner);
            }
             else if (holder instanceof FunctionHolder) {
                ((FunctionHolder) holder).img1.setImageResource(R.mipmap.guarantee);
                ((FunctionHolder) holder).img2.setImageResource(R.mipmap.purifier);
                ((FunctionHolder) holder).img3.setImageResource(R.mipmap.notice);
                ((FunctionHolder) holder).img4.setImageResource(R.mipmap.haow);

            } else if (holder instanceof PayHolder) {

                ((PayHolder) holder).textView2.setText(ResultPrice);
                ((PayHolder) holder).btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e(BaseActivity.phone, BaseActivity.token);
                        presenter.preMaryModel(BaseActivity.phone, BaseActivity.token);

                    }
                });
                ((PayHolder) holder).btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            } else if (holder instanceof HistoricaHolder) {
//                    for (int i = 0; i < resultBean.size(); i++) {
//                        ((HistoricaHolder) holder).time.setText("截止日期:" + resultBean.get(i).getArea_name() + ":" + resultBean.get(i).getStart_time() + "—" + resultBean.get(i).getStop_time());
//                        ((HistoricaHolder) holder).domicile.setText("地址: " + resultBean.get(i).getArea_name() + " " + resultBean.get(i).getFloor() + "单元 " + resultBean.get(i).getUnit() + "号楼 " + resultBean.get(i).getRoom() + "室");
//                        ((HistoricaHolder) holder).userName.setText("业主:" + resultBean.get(i).getTruename());
//                        ((HistoricaHolder) holder).area.setText("面积:" + resultBean.get(i).getArea() + "平方");
//                        ((HistoricaHolder) holder).sum.setText("待缴金额: " + resultBean.get(i).getAssessment() + "元");
                HistoryAdapter historyAdapter = new HistoryAdapter(context, historyPayList);
                    ((HistoricaHolder) holder).recyclerView.addItemDecoration(new ChatDetailItemDecoration(30));
                    ((HistoricaHolder) holder).recyclerView.setLayoutManager(new CustomLinearLayoutManager(context));
                       ((HistoricaHolder) holder).recyclerView.setAdapter(historyAdapter);
//                    }
                } else if (holder instanceof ShouldHolder) {

                    PayAdapter payAdapter = new PayAdapter(context,resultBean);
                    ((ShouldHolder) holder).shouldRecy.addItemDecoration(new ChatDetailItemDecoration(30));
                    ((ShouldHolder) holder).shouldRecy.setLayoutManager(new CustomLinearLayoutManager(context));
                    ((ShouldHolder) holder).shouldRecy.setAdapter(payAdapter);
                }

        } else {

            if (holder instanceof FunctionHolder) {
                ((FunctionHolder) holder).img1.setImageResource(R.mipmap.guarantee);
                ((FunctionHolder) holder).img2.setImageResource(R.mipmap.purifier);
                ((FunctionHolder) holder).img3.setImageResource(R.mipmap.notice);
                ((FunctionHolder) holder).img4.setImageResource(R.mipmap.haow);
            } else if (holder instanceof PayHolder) {

                ((PayHolder) holder).textView2.setText("0.00元");
                ((PayHolder) holder).btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "未登陆", Toast.LENGTH_SHORT).show();
                    }
                });
                ((PayHolder) holder).btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "未登陆", Toast.LENGTH_SHORT).show();
                    }
                });

            } else if (holder instanceof BannerHolder) {
                setBanner(((BannerHolder) holder).banner);
            } else if (holder instanceof ButtonHolder) {
                if (((ButtonHolder) holder).NoLogin != null) {
                    ((ButtonHolder) holder).NoLogin.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context, RegActivity.class));
                        }
                    });
                }
            }
        }
    }


    //根据条件返回条目的类型
    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }else  if(position==1){
            return 1;
        }else if(position==2){
            return 2;
        }else if(position==3){
            return 3;
        }else if(position==4){
            return 4;
        }

        return position;
    }

    @Override
    public int getItemCount() {

        presenter = new Presenter(HomeAdapter.this);
        if (reflag) {
                return 5;
        } else {
            return 4;
        }
    }

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第" + position + "张轮播图");
    }

    private void setBanner(Banner mBanner) {
        List<String> list_title = new ArrayList<>();

        for (int i = 0; i < imgs.size(); i++) {
            Log.e(imgs.get(i), "msg");
            list_title.add("");
        }

        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new MyImageLoader());
        //设置轮播的动画效果,里面有很多种特效,可以都看看效果。
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //轮播图片的文字
        mBanner.setBannerTitles(list_title);
        //设置轮播间隔时间
        mBanner.setDelayTime(3000);
        //设置是否为自动轮播，默认是true
        mBanner.isAutoPlay(true);
        //设置指示器的位置，小点点，居中显示
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载地址
        mBanner.setImages(imgs)
                //轮播图的监听
                .setOnBannerListener(this)
                //开始调用的方法，启动轮播图。
                .start();

    }
    /**
     * 图片加载类
     */
    private class MyImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load(path)
                    .into(imageView);
        }
    }

    @Override
    public void Scuess(Object o, int requestCode) {
        if (requestCode == 10007) {
            PreMaryBean preMaryBean = (PreMaryBean) o;
            if (preMaryBean.getStatus() == 1) {
                Log.e("message", preMaryBean.getResult().getOrder_no());
                presenter.wxMoneyPre(preMaryBean.getResult().getOrder_no());
            }
        } else if (requestCode == 10008) {
            WeiXin weiXin = (WeiXin) o;
            Log.e("tag", weiXin.getMessage());
            if (weiXin.getStatus() == 1) {
                Log.e("tag", weiXin.getMessage());
                App application = (App) App.getApplication();
                application.get(weiXin);
            }
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


}

    class BannerHolder extends RecyclerView.ViewHolder {

        Banner banner;

        public BannerHolder(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    class ButtonHolder extends RecyclerView.ViewHolder {
        Button NoLogin;

        public ButtonHolder(View itemView) {
            super(itemView);
            NoLogin = itemView.findViewById(R.id.goto_login);
        }
    }

    class FunctionHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img1;
        SimpleDraweeView img2;
        SimpleDraweeView img3;
        SimpleDraweeView img4;


        public FunctionHolder(@NonNull View itemView) {
            super(itemView);
            img1 = itemView.findViewById(R.id.textImg1);
            img2 = itemView.findViewById(R.id.textImg2);
            img3 = itemView.findViewById(R.id.textImg3);
            img4 = itemView.findViewById(R.id.textImg4);

        }

    }

    class PayHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView textView2;
        Button btn1;
        Button btn2;

        public PayHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text_pay1);
            textView2 = itemView.findViewById(R.id.text_pay2);
            btn1 = itemView.findViewById(R.id.btn_pay1);
            btn2 = itemView.findViewById(R.id.btn_pay2);
        }
    }

    class ShouldHolder extends RecyclerView.ViewHolder {

        RecyclerView shouldRecy;

        public ShouldHolder(@NonNull View itemView) {
            super(itemView);
            shouldRecy = itemView.findViewById(R.id.should_recy);
        }
    }

    class HistoricaHolder extends RecyclerView.ViewHolder {
       RecyclerView recyclerView;

//        TextView time;
//        TextView domicile;
//        TextView userName;
//        TextView sum;
//        TextView area;

        public HistoricaHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recycleView);
//            time = itemView.findViewById(R.id.time);
//            domicile = itemView.findViewById(R.id.domicile);
//            userName = itemView.findViewById(R.id.userName);
//            sum = itemView.findViewById(R.id.sum);
//            area = itemView.findViewById(R.id.area);

        }

}