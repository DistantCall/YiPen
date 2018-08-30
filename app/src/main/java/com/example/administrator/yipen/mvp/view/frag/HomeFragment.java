package com.example.administrator.yipen.mvp.view.frag;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.yipen.adapter.HomeAdapter;
import com.example.administrator.yipen.adapter.UserBean;
import com.example.administrator.yipen.constance.IMG;
import com.example.myapplication.R;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    List<String> imgs = new ArrayList<>();
    List<String> beanList = new ArrayList<>();
    List<UserBean> userBeans=new ArrayList<>();
    private View view;
    private RecyclerView homeView;
    private View headView;
    private XBanner banner;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_layout, container, false);
        initView(view);
        initData();
        return view;
    }

    public void initView(View view) {
        homeView = view.findViewById(R.id.home_recy);
    }

    public void initData() {
        imgs.add(IMG.BANNER_ONE);
        imgs.add(IMG.BANNER_TWO);
        imgs.add(IMG.BANNER_THREE);
        imgs.add(IMG.BANNER_FOUR);
        beanList.add(IMG.LGO01);
        beanList.add(IMG.LGO02);
        beanList.add(IMG.LGO03);
        beanList.add(IMG.LOG04);
        for (int i = 0; i <10 ; i++) {
            userBeans.add( new UserBean("2018年度应缴金额", "破溪镇崎岖小区美人住大街", "甄帅", (i*100)+""));
        }
        setXrecycleView();
    }
    @SuppressLint("WrongConstant")
    private void setXrecycleView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeView.setLayoutManager(linearLayoutManager);
        HomeAdapter homeAdapter = new HomeAdapter(getActivity(), beanList,imgs,userBeans);
        homeView.setAdapter(homeAdapter);

    }


}
