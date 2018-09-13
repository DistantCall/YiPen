package com.example.administrator.yipen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yipen.bean.HistoryPayBean;
import com.example.administrator.yipen.bean.SelectInfo;
import com.example.myapplication.R;

import java.util.List;


public class PayAdapter extends RecyclerView.Adapter<PayAdapter.HisHolder> {
    private List<SelectInfo.ResultBean> resultBeans;
    private Context context;





    public PayAdapter(Context context, List<SelectInfo.ResultBean> resultBeans) {
        this.context = context;
        this.resultBeans = resultBeans;
    }

    @NonNull
    @Override
    public HisHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.hischild, parent, false);



        return new HisHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HisHolder holder, int position) {

            holder.time.setText("截至日期:" + resultBeans.get(position).getArea_name() + ":" + resultBeans.get(position).getStart_time() + "—" + resultBeans.get(position).getStop_time());
            holder.domicile.setText("地址：" + resultBeans.get(position).getArea_name() + " " + resultBeans.get(position).getFloor() + "单元 " + resultBeans.get(position).getUnit() + "号楼 " + resultBeans.get(position).getRoom() + "室");
            holder.userName.setText("业主:" + resultBeans.get(position).getTruename());
            holder.historyArea.setText("面积:" + resultBeans.get(position).getArea() + "平方");
            holder.sum.setText("待缴金额: " + resultBeans.get(position).getAssessment() + "元");

    }

    @Override
    public int getItemCount() {
        return  resultBeans.size();
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
