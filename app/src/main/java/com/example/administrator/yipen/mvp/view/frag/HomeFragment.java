package com.example.administrator.yipen.mvp.view.frag;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.yipen.adapter.HomeAdapter;
import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.bean.BannerDataBean;
import com.example.administrator.yipen.bean.CountPriceBena;
import com.example.administrator.yipen.bean.HistoryPayBean;
import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.bean.SelectInfo;
import com.example.administrator.yipen.constance.IMG;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.administrator.yipen.mvp.view.BaseActivity;
import com.example.administrator.yipen.mvp.view.Iview;
import com.example.administrator.yipen.server.LoginServerce;
import com.example.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements Iview {

    List<String> imgs = new ArrayList<>();

    List<SelectInfo.ResultBean> userBeans = new ArrayList<>();
    private View view;
    private RecyclerView homeView;

    List<HistoryPayBean.ResBean> resultBean = new ArrayList<>();
    private Presenter presenter;

    private HomeAdapter homeAdapter;
    //    private LoginBean.ResultBean o;
    private String result;
    private HistoryPayBean historyPayBean;
    private CountPriceBena countPriceBena;
    private SwipeRefreshLayout mSwiperefresh;
    private LoginBean.ResultBean o;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            view = inflater.inflate(R.layout.home_layout, container, false);
            presenter = App.getPresenter(this);
            EventBus.getDefault().register(this);
            initView(view);
            initData();
            setData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (LoginServerce.reflag = true) {
            homeAdapter = new HomeAdapter(getActivity(), imgs, userBeans, result, resultBean, true);
            homeView.setAdapter(homeAdapter);
        }

        if (LoginServerce.reflag = false) {
            homeAdapter = new HomeAdapter(getActivity(), imgs, false);
            homeView.setAdapter(homeAdapter);
        }
        if (LoginServerce.reflag) {
            if (imgs.size() == 0) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        presenter.bannerPre(BaseActivity.bis_id);
                        presenter.selectInfo(BaseActivity.phone, BaseActivity.bis_id, BaseActivity.token);
                        presenter.priceCount(BaseActivity.phone, BaseActivity.bis_id, BaseActivity.token);
                        presenter.HistoryPay(BaseActivity.phone, BaseActivity.bis_id, BaseActivity.token);

                    }
                }.start();
            }
            homeAdapter.notifyDataSetChanged();

        } else {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    presenter.bannerPre("1");
                }
            }.start();


        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getData(List<LoginBean.ResultBean> resultBeanList) {
        o = resultBeanList.get(0);

    }

    public void setData() {
        mSwiperefresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwiperefresh.setProgressViewOffset(true, 0, 100);//设置加载圈是否有缩放效果，后两个参数是展示的位置y轴坐标
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        homeView.setLayoutManager(linearLayoutManager);

    }


    public void initView(View view) {
        homeView = view.findViewById(R.id.home_recy);
        mSwiperefresh = (SwipeRefreshLayout) view.findViewById(R.id.sf_swiperefresh);
    }

    public void initData() {

        refreshData();
    }

    @Override
    public void Scuess(Object o, int requestCode) {
        if (requestCode == 9998) {
            imgs = (List<String>) o;


        } else if (requestCode == 10004) {

            SelectInfo selectInfo = (SelectInfo) o;
            if (((SelectInfo) o).getStatus() == 1) {
                userBeans = selectInfo.getResult();
            } else {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), selectInfo.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        } else if (requestCode == 10003) {

            countPriceBena = (CountPriceBena) o;
            if (countPriceBena.getStatus() == 1) {
                result = countPriceBena.getResult();

            } else {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), countPriceBena.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        } else if (requestCode == 10005) {
            historyPayBean = (HistoryPayBean) o;
            if (historyPayBean.getStatus() == 1) {
                resultBean = historyPayBean.getRes();
            } else {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), historyPayBean.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                homeAdapter.notifyDataSetChanged();
            }
        });
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

    /**
     * 刷新以及加载更多
     */
    private void refreshData() {
        //下拉刷新
        mSwiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            presenter.bannerPre(BaseActivity.bis_id);
                            presenter.selectInfo(BaseActivity.phone, BaseActivity.bis_id, BaseActivity.token);
                            presenter.priceCount(BaseActivity.phone, BaseActivity.bis_id, BaseActivity.token);
                            presenter.HistoryPay(BaseActivity.phone, BaseActivity.bis_id, BaseActivity.token);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //inData(); //初始化数据
                                //listAdapter.notifyDataSetChanged();//更新adapter
                                mSwiperefresh.setRefreshing(false);//刷新结束，隐藏刷新进度条

                            }
                        });
                    }
                }).start();
            }
        });

    }
}