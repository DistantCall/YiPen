package com.example.administrator.yipen.mvp.view.frag;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yipen.adapter.HomeAdapter;
import com.example.administrator.yipen.adapter.UserBean;
import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.bean.BannerDataBean;
import com.example.administrator.yipen.bean.CountPriceBena;
import com.example.administrator.yipen.bean.HistoryPayBean;
import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.bean.SelectInfo;
import com.example.administrator.yipen.constance.IMG;
import com.example.administrator.yipen.constance.SharePUtils;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.administrator.yipen.mvp.view.BaseActivity;
import com.example.administrator.yipen.mvp.view.Iview;
import com.example.administrator.yipen.mvp.view.RegActivity;
import com.example.administrator.yipen.server.LoginServerce;
import com.example.myapplication.R;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements Iview, View.OnClickListener {

    List<String> imgs = new ArrayList<>();
    List<String> beanList = new ArrayList<>();
    List<SelectInfo.ResultBean> userBeans = new ArrayList<>();
    private View view;
    private RecyclerView homeView;
    private Button button;
    private LinearLayout linearLayout;
    List<HistoryPayBean.ResBean> resultBean = new ArrayList<>();
    private Presenter presenter;

    private HomeAdapter homeAdapter;
    private LoginBean.ResultBean o;
    private String result;
    private HistoryPayBean historyPayBean;
    private CountPriceBena countPriceBena;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            view = inflater.inflate(R.layout.home_layout, container, false);
            linearLayout = view.findViewById(R.id.logintoo);
            button = view.findViewById(R.id.null_login);
            button.setOnClickListener(this);
            presenter = App.getPresenter(this);
            EventBus.getDefault().register(this);

            initView(view);
            initData();
            setData();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "诶呀·报错了", Toast.LENGTH_LONG).show();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (LoginServerce.reflag) {

            linearLayout.setVisibility(View.VISIBLE);
            button.setVisibility(View.INVISIBLE);
            presenter.bannerPre(BaseActivity.bis_id);
            presenter.selectInfo(BaseActivity.phone, BaseActivity.bis_id, BaseActivity.token);
            presenter.priceCount(BaseActivity.phone, BaseActivity.bis_id, BaseActivity.token);
            presenter.HistoryPay(BaseActivity.phone, BaseActivity.bis_id, BaseActivity.token);
        } else  {
            linearLayout.setVisibility(View.INVISIBLE);
            button.setVisibility(View.VISIBLE);


        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getData(List<LoginBean.ResultBean> resultBeanList) {
        o = resultBeanList.get(0);
        Log.e("Mine_data", resultBean.toString());
    }

    public void setData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homeView.setLayoutManager(linearLayoutManager);
    }



    public void initView(View view) {
        homeView = view.findViewById(R.id.home_recy);
    }

    public void initData() {

        beanList.add(IMG.LGO01);
        beanList.add(IMG.LGO02);
        beanList.add(IMG.LGO03);
        beanList.add(IMG.LOG04);
    }

    @Override
    public void Scuess(Object o, int requestCode) {
        if (requestCode == 9998) {
            BannerDataBean bannerDataBean = (BannerDataBean) o;
            if (bannerDataBean.getStatus() == 1) {
                List<BannerDataBean.ResultBean> dataBeanResult = bannerDataBean.getResult();
                for (int i = 0; i < dataBeanResult.size(); i++) {
                    imgs.add(dataBeanResult.get(i).getImage().replace("\\", "/"));
                }
            }
        } else if (requestCode == 10004) {

            SelectInfo selectInfo = (SelectInfo) o;
            if (((SelectInfo) o).getStatus() == 1) {
                userBeans = selectInfo.getResult();


            } else {
                Toast.makeText(getActivity(), selectInfo.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == 10003) {

            countPriceBena = (CountPriceBena) o;
            if (countPriceBena.getStatus() == 1) {
                result = countPriceBena.getResult();
                Log.e("result", countPriceBena.toString());
            } else {
                Toast.makeText(getActivity(), countPriceBena.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else if (requestCode == 10005) {
            historyPayBean = (HistoryPayBean) o;
            Log.e("history", historyPayBean.toString());
            if (historyPayBean.getStatus() == 1) {
                resultBean = historyPayBean.getRes();
            } else {
                Toast.makeText(getActivity(), historyPayBean.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

        homeAdapter = new HomeAdapter(getActivity(), beanList, imgs, userBeans, result, resultBean);
        homeView.setAdapter(homeAdapter);


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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.null_login:
                startActivity(new Intent(getActivity(), RegActivity.class));
                break;
        }
    }
}
