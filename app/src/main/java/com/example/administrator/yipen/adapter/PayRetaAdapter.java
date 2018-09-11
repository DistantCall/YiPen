package com.example.administrator.yipen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.yipen.bean.StagesBean;
import com.example.myapplication.R;

import java.util.List;

public class PayRetaAdapter extends RecyclerView.Adapter<PayRetaAdapter.MyHolder> {
    List<StagesBean.ResBean> imgs;
    Context context;

    public PayRetaAdapter( Context context,List<StagesBean.ResBean> imgs) {
        this.imgs = imgs;
        this.context = context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pay_retalayout, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
//        holder.num.setText(position + "");
//        holder.money.setText(imgs.get(position).getGeneral_finance());
//        holder.countPrice.setText(imgs.get(position).getApply_number());
//        holder.RetaModey.setText(imgs.get(position).getInterest_rate());
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView num;
        TextView money;
        TextView countPrice;
        TextView RetaModey;

        public MyHolder(View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.ratenum);
            money = itemView.findViewById(R.id.ratenum);
            countPrice = itemView.findViewById(R.id.count_reta_price);
            RetaModey = itemView.findViewById(R.id.ratenum);
        }
    }
}
