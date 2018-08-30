package com.example.administrator.yipen.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.myapplication.R;

import java.util.List;



public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HisHolder> {
    private Context context;
    private List<UserBean> list;

    public HistoryAdapter(Context context, List<UserBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HisHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.historica_child_layout,parent,false);
        return new HisHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HisHolder holder, int position) {
            holder.time.setText(list.get(position).getTime());
            holder.domicile.setText("地址:"+list.get(position).getDomicile());
            holder.userName.setText("业主:"+list.get(position).getName());
            holder.sum.setText("待缴金额:"+list.get(position).getSun());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HisHolder extends RecyclerView.ViewHolder{
        TextView time;
        TextView domicile;
        TextView userName;
        TextView sum;
    public HisHolder(@NonNull View itemView) {
        super(itemView);
        time=itemView.findViewById(R.id.historytime);
        domicile=itemView.findViewById(R.id.historydomicile);
        userName=itemView.findViewById(R.id.historyuserName);
        sum=itemView.findViewById(R.id.historysum);
    }
}
}
