package com.example.administrator.yipen.mvp.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;


import com.example.myapplication.R;


public class DidivActivity extends BaseActivity {


    private TextView odernumber;
    private TextView time;
    private TextView pat;
    private TextView address;
    private RecyclerView recyclerView;
    private TabHost tabhost;
    private ImageView back;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_didiv;
    }

    @Override
    protected void initView() {
        back = (ImageView) findViewById(R.id.history_back);

    }

    @Override
    protected void initData() {
        back.setColorFilter(Color.WHITE);
        //初始化TabHost组件
        tabhost = (TabHost) findViewById(R.id.tthost);
        tabhost.setup();
        //声明并实例化一个LayoutInflater对象
        LayoutInflater inflater = LayoutInflater.from(this);
        inflater.inflate(R.layout.lishi_layout, tabhost.getTabContentView());
        inflater.inflate(R.layout.lishi_layout1, tabhost.getTabContentView());
        //添加第一个标签页
        tabhost.addTab(tabhost.newTabSpec("tab").setIndicator("近期记录").setContent(R.id.linearLayout01));
        //添加第二个标签页
        tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("历史记录").setContent(R.id.linearLayout02));

        //设置tabhost大小
        TabWidget tabWidget = tabhost.getTabWidget();
        for (int i = 0; i < tabWidget.getTabCount(); i++) {
            tabWidget.getChildAt(i).getLayoutParams().height = 50;
            tabWidget.getChildAt(i).getLayoutParams().width = 85;
        }

        odernumber = tabhost.findViewById(R.id.histopry_odernumber);

        recyclerView = tabhost.findViewById(R.id.didiv_recycleView);
        recyclerView = tabhost.findViewById(R.id.didiv_recycleView1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void Scuess(Object o, int requestCode) {

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
