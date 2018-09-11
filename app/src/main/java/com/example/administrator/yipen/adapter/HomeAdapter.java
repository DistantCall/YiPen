package com.example.administrator.yipen.adapter;


import android.content.Context;
import android.content.Intent;
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

import com.example.administrator.yipen.bean.HistoryPayBean;
import com.example.administrator.yipen.bean.SelectInfo;
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


public class HomeAdapter extends RecyclerView.Adapter implements OnBannerListener {
    private List<HistoryPayBean.ResBean> historyPayList;
    private List<String> imgs;
    private List<SelectInfo.ResultBean> resultBean;
    private Context context;

    private String ResultPrice;
    private boolean reflag;

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
            if (resultBean.size() == 0) {
                if (viewType == 0) {
                    view = LayoutInflater.from(context).inflate(R.layout.banner_layout, parent, false);
                    BannerHolder bannerHolder = new BannerHolder(view);
                    return bannerHolder;
                } else if (viewType == 1) {
                    view = LayoutInflater.from(context).inflate(R.layout.fouction_layout, parent, false);
                    FunctionHolder functionHolder = new FunctionHolder(view);
                    return functionHolder;
                } else if (viewType == 2) {
                    if (view == null) {
                        view = LayoutInflater.from(context).inflate(R.layout.want_layout, parent, false);
                        PayHolder payHolder = new PayHolder(view);
                        return payHolder;
                    }
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
                } else if (viewType == 3) {
                    view = LayoutInflater.from(context).inflate(R.layout.historica_layout, parent, false);
                    HistoricaHolder historicaHolder = new HistoricaHolder(view);
                    return historicaHolder;
                } else if (viewType == 2) {
                    if (view == null) {
                        view = LayoutInflater.from(context).inflate(R.layout.want_layout, parent, false);
                        PayHolder payHolder = new PayHolder(view);
                        return payHolder;
                    } else {

                    }
                } else if (viewType == 4) {
                    view = LayoutInflater.from(context).inflate(R.layout.should_layout, parent, false);
                    ShouldHolder shouldHolder = new ShouldHolder(view);
                    return shouldHolder;
                }
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
                if (view == null) {
                    view = LayoutInflater.from(context).inflate(R.layout.want_layout, parent, false);
                    PayHolder payHolder = new PayHolder(view);
                    return payHolder;
                } else {

                }
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
            if (resultBean.size() == 0) {

                if (holder instanceof BannerHolder) {

                    setBanner(((BannerHolder) holder).banner);
                } else if (holder instanceof FunctionHolder) {

                    ((FunctionHolder) holder).img1.setImageResource(R.mipmap.guarantee);
                    ((FunctionHolder) holder).img2.setImageResource(R.mipmap.purifier);
                    ((FunctionHolder) holder).img3.setImageResource(R.mipmap.notice);
                    ((FunctionHolder) holder).img4.setImageResource( R.mipmap.haow);

                } else if (holder instanceof PayHolder) {

                    ((PayHolder) holder).textView2.setText(ResultPrice);
                    ((PayHolder) holder).btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    ((PayHolder) holder).btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context, PayStagesActivity.class));
                        }
                    });

                }
            } else {


                if (holder instanceof FunctionHolder) {


                    ((FunctionHolder) holder).img1.setImageResource(R.mipmap.guarantee);
                    ((FunctionHolder) holder).img2.setImageResource(R.mipmap.purifier);
                    ((FunctionHolder) holder).img3.setImageResource(R.mipmap.notice);
                    ((FunctionHolder) holder).img4.setImageResource( R.mipmap.haow);

                } else if (holder instanceof PayHolder) {

                    ((PayHolder) holder).textView2.setText(ResultPrice);
                    ((PayHolder) holder).btn1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });
                    ((PayHolder) holder).btn2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    });

                } else if (holder instanceof BannerHolder) {

                    setBanner(((BannerHolder) holder).banner);
                } else if (holder instanceof HistoricaHolder) {
                    for (int i = 0; i < resultBean.size(); i++) {
                        ((HistoricaHolder) holder).time.setText("截止日期:" + resultBean.get(i).getArea_name() + ":" + resultBean.get(i).getStart_time() + "—" + resultBean.get(i).getStop_time());
                        ((HistoricaHolder) holder).domicile.setText("地址: " + resultBean.get(i).getArea_name() + " " + resultBean.get(i).getFloor() + "单元 " + resultBean.get(i).getUnit() + "号楼 " + resultBean.get(i).getRoom() + "室");
                        ((HistoricaHolder) holder).userName.setText("业主:" + resultBean.get(i).getTruename());
                        ((HistoricaHolder) holder).area.setText("面积:" + resultBean.get(i).getArea() + "平方");
                        ((HistoricaHolder) holder).sum.setText("待缴金额: " + resultBean.get(i).getAssessment() + "元");
                    }


                } else if (holder instanceof ShouldHolder) {

                    HistoryAdapter historyAdapter = new HistoryAdapter(context, historyPayList);
                    ((ShouldHolder) holder).shouldRecy.addItemDecoration(new ChatDetailItemDecoration(30));
                    ((ShouldHolder) holder).shouldRecy.setLayoutManager(new CustomLinearLayoutManager(context));
                    ((ShouldHolder) holder).shouldRecy.setAdapter(historyAdapter);
                }
            }
        } else {

            if (holder instanceof FunctionHolder) {


                ((FunctionHolder) holder).img1.setImageResource(R.mipmap.guarantee);
                ((FunctionHolder) holder).img2.setImageResource(R.mipmap.purifier);
                ((FunctionHolder) holder).img3.setImageResource(R.mipmap.notice);
                ((FunctionHolder) holder).img4.setImageResource( R.mipmap.haow);
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

                if (((BannerHolder) holder).banner != null) {
                    ((BannerHolder) holder).banner.removeAllViews();
                }
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

        return position;
    }

    @Override
    public int getItemCount() {
        if (reflag) {
            if (resultBean.size() >= 0) {
                return 3;
            } else {
                return 5;
            }
        } else {
            return 4;
        }

    }

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第" + position + "张轮播图");
    }

    private void setBanner(Banner banner) {
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

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
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

    TextView time;
    TextView domicile;
    TextView userName;
    TextView sum;
    TextView area;

    public HistoricaHolder(@NonNull View itemView) {
        super(itemView);
        time = itemView.findViewById(R.id.time);
        domicile = itemView.findViewById(R.id.domicile);
        userName = itemView.findViewById(R.id.userName);
        sum = itemView.findViewById(R.id.sum);
        area = itemView.findViewById(R.id.area);
    }
}

}