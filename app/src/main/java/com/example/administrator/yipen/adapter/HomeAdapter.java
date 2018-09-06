package com.example.administrator.yipen.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.example.administrator.yipen.bean.HistoryPayBean;
import com.example.administrator.yipen.bean.SelectInfo;
import com.example.administrator.yipen.mvp.view.DidivActivity;
import com.example.administrator.yipen.mvp.view.PayActivity;
import com.example.administrator.yipen.mvp.view.RegActivity;
import com.example.administrator.yipen.ui.CustomLinearLayoutManager;
import com.example.administrator.yipen.utils.ChatDetailItemDecoration;
import com.example.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.List;


public class HomeAdapter extends RecyclerView.Adapter {
    private List<HistoryPayBean.ResBean> historyPayList;
    private List<String> imgs;
    private List<SelectInfo.ResultBean> resultBean;
    private Context context;
    private List<String> list;
    private String ResultPrice;
    private boolean reflag;

    public HomeAdapter(Context context, List<String> list, List<String> imgs, List<SelectInfo.ResultBean> resultBean, String ResultPrice, List<HistoryPayBean.ResBean> historyPayList, boolean reflag) {
        this.context = context;
        this.list = list;
        this.imgs = imgs;
        this.resultBean = resultBean;
        this.ResultPrice = ResultPrice;
        this.historyPayList = historyPayList;
        this.reflag = reflag;
    }

    public HomeAdapter(Context context, List<String> list, List<String> imgs, boolean reflag) {
        this.context = context;
        this.list = list;
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

            if (holder instanceof FunctionHolder) {

                    ((FunctionHolder) holder).img1.setImageURI(list.get(0));
                    ((FunctionHolder) holder).img2.setImageURI(list.get(1));
                    ((FunctionHolder) holder).img3.setImageURI(list.get(2));
                    ((FunctionHolder) holder).img4.setImageURI(list.get(3));

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
                if (((BannerHolder) holder).banner != null) {
                    ((BannerHolder) holder).banner.removeAllViews();
                }
                setBanner(((BannerHolder) holder).banner);
            } else if (holder instanceof HistoricaHolder) {
                    for (int i = 0; i < resultBean.size(); i++) {
                        ((HistoricaHolder) holder).time.setText("截止日期:"+resultBean.get(i).getArea_name()+":"+resultBean.get(i).getStart_time()+"—"+resultBean.get(i).getStop_time());
                        ((HistoricaHolder) holder).domicile.setText("地址: " + resultBean.get(i).getArea_name()+" "+resultBean.get(i).getFloor()+"单元 "+resultBean.get(i).getUnit()+"号楼 "+resultBean.get(i).getRoom()+"室");
                        ((HistoricaHolder) holder).userName.setText("业主:" + resultBean.get(i).getTruename());
                        ((HistoricaHolder) holder).area.setText("面积:"+resultBean.get(i).getArea()+"平方");
                        ((HistoricaHolder) holder).sum.setText("待缴金额: " + resultBean.get(i).getAssessment()+"元");
                    }


            } else if (holder instanceof ShouldHolder) {

                HistoryAdapter historyAdapter = new HistoryAdapter(context, historyPayList);
               ((ShouldHolder) holder).shouldRecy.addItemDecoration(new ChatDetailItemDecoration(30));
                ((ShouldHolder) holder).shouldRecy.setLayoutManager(new CustomLinearLayoutManager(context));
                ((ShouldHolder) holder).shouldRecy.setAdapter(historyAdapter);
            }
        } else {

            if (holder instanceof FunctionHolder) {

                ((FunctionHolder) holder).img1.setImageURI(list.get(0));
                ((FunctionHolder) holder).img2.setImageURI(list.get(1));
                ((FunctionHolder) holder).img3.setImageURI(list.get(2));
                ((FunctionHolder) holder).img4.setImageURI(list.get(3));
            } else if (holder instanceof PayHolder) {

                ((PayHolder) holder).textView2.setText("0.00元");
                ((PayHolder) holder).btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"未登陆", Toast.LENGTH_SHORT).show();

                    }
                });
                ((PayHolder) holder).btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"未登陆", Toast.LENGTH_SHORT).show();
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

        return reflag ? 5 : 4;
    }

    private void setBanner(XBanner banner) {
        // 为XBanner绑定数据


        banner.setData(imgs, null);
        // XBanner适配数据


        banner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(context).load(imgs.get(position)).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效
        banner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        banner.setPageChangeDuration(1000);

    }

    class BannerHolder extends RecyclerView.ViewHolder {

        XBanner banner;

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
            area=itemView.findViewById(R.id.area);
        }
    }
}
