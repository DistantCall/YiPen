package com.example.administrator.yipen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yipen.bean.BannerDataBean;
import com.example.administrator.yipen.bean.HistoryPayBean;
import com.example.administrator.yipen.bean.PreMaryBean;
import com.example.administrator.yipen.bean.SelectInfo;
import com.example.myapplication.R;

import java.util.List;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HisHolder> {
    private List<SelectInfo.ResultBean> resultBeans;
    private Context context;
    private List<HistoryPayBean.ResBean> historyPayList;
    private int first = 2;

    public HistoryAdapter(Context context, List<HistoryPayBean.ResBean> historyPayList, int first) {
        this.first = first;
        this.context = context;
        this.historyPayList = historyPayList;
    }

    public HistoryAdapter(Context context, List<SelectInfo.ResultBean> resultBeans) {
        this.context = context;
        this.resultBeans = resultBeans;
    }

    @NonNull
    @Override
    public HisHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.historica_child_layout, parent, false);
        LinearLayout linearLayout = v.findViewById(R.id.selecttt);
        if (first == 1) {
            linearLayout.setBackgroundResource(R.drawable.pay_bg);
        } else {
            linearLayout.setBackgroundResource(R.drawable.history_bg);
        }
        return new HisHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HisHolder holder, int position) {
        if (first == 1) {
            holder.time.setText("截至日期:" + historyPayList.get(position).getArea_name() + ":" + historyPayList.get(position).getStart_time() + "—" + historyPayList.get(position).getStop_time());
            holder.domicile.setText("地址：" + historyPayList.get(position).getArea_name() + " " + historyPayList.get(position).getFloor() + "单元 " + historyPayList.get(position).getUnit() + "号楼 " + historyPayList.get(position).getRoom() + "室");
            holder.userName.setText("业主:" + historyPayList.get(position).getTruename());
            holder.historyArea.setText("面积:" + historyPayList.get(position).getArea() + "平方");
            holder.sum.setText("待缴金额: " + historyPayList.get(position).getAssessment() + "元");
        } else {
            holder.time.setText("截至日期:" + resultBeans.get(position).getArea_name() + ":" + resultBeans.get(position).getStart_time() + "—" + resultBeans.get(position).getStop_time());
            holder.domicile.setText("地址：" + resultBeans.get(position).getArea_name() + " " + resultBeans.get(position).getFloor() + "单元 " + resultBeans.get(position).getUnit() + "号楼 " + resultBeans.get(position).getRoom() + "室");
            holder.userName.setText("业主:" + resultBeans.get(position).getTruename());
            holder.historyArea.setText("面积:" + resultBeans.get(position).getArea() + "平方");
            holder.sum.setText("待缴金额: " + resultBeans.get(position).getAssessment() + "元");
        }
    }

    @Override
    public int getItemCount() {
        return historyPayList.size();
    }

    class HisHolder extends RecyclerView.ViewHolder {
        TextView time;
        TextView domicile;
        TextView userName;
        TextView sum;
        TextView historyArea;

        public HisHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.historytime);
            domicile = itemView.findViewById(R.id.historydomicile);
            userName = itemView.findViewById(R.id.historyuserName);
            sum = itemView.findViewById(R.id.historysum);
            historyArea = itemView.findViewById(R.id.historyarea);
        }
    }
}
