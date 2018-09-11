package com.example.administrator.yipen.mvp.view.frag;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.constance.ConstanceClass;
import com.example.administrator.yipen.constance.SharePUtils;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.administrator.yipen.mvp.view.BaseActivity;
import com.example.administrator.yipen.mvp.view.DidivActivity;
import com.example.administrator.yipen.mvp.view.Iview;

import com.example.administrator.yipen.mvp.view.PayActivity;
import com.example.administrator.yipen.mvp.view.RegActivity;

import com.example.administrator.yipen.mvp.view.SetActivity;
import com.example.administrator.yipen.mvp.view.StartActivity;
import com.example.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import static com.example.administrator.yipen.server.LoginServerce.reflag;

public class MineFragment extends Fragment implements Iview {

    private View view;
    private SimpleDraweeView userIcon;
    private TextView userPhone;
    private TextView userName;
    private LoginBean.ResultBean resultBean;

    private Presenter presenter;

    private String phone;
    private String username;
    private String token;
    private String no_addres;
    private String icon;
    private LinearLayout jiaofie;
    private LinearLayout fenqi;
    private TextView finish;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_layout, container, false);

        EventBus.getDefault().register(this);
        initView();
        initData();
        return view;
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getData(List<LoginBean.ResultBean> resultBeanList) {
        resultBean = resultBeanList.get(0);
        Log.e("Mine_data", resultBean.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (reflag) {
            if (resultBean != null) {
                phone = resultBean.getTelephone();
                token = resultBean.getToken();
                icon = resultBean.getCode_url();
                username = (String) resultBean.getUsername();
            } else {
                phone = BaseActivity.user.query("phone");
                token = BaseActivity.user.query("token");
                icon = BaseActivity.user.query("user_icon");
                username = BaseActivity.user.query("username");
            }
            if (username != null) {

                String username1;
                if(username.equals("err")){
                    username1="用户名";
                }else{
                 username1=username;
                }
                Log.e("err", username1);
                userName.setText(username1);
            } else {
                userName.setText("用户");
            }
            if (phone != null) {
                userPhone.setText(phone);
            } else {
                userName.setText("手机号");
            }
            if (icon != null) {
                userIcon.setImageURI(ConstanceClass.LOCTIONPATH + icon);
            } else {
                userIcon.setImageResource(R.drawable.user_icon);
            }
            login();
        } else {
            notLogin();
        }


    }


    private void initView() {

        jiaofie = view.findViewById(R.id.jiaofei);
        fenqi = view.findViewById(R.id.fenqi);
        userIcon = view.findViewById(R.id.user_icon);
        userPhone = view.findViewById(R.id.user_phone);
        userName = view.findViewById(R.id.user_Namne);

        finish = (TextView) view.findViewById(R.id.freishLogin);

    }


    @SuppressLint("ResourceType")
    private void initData() {
        presenter = App.getPresenter(this);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseActivity.username = null;
                BaseActivity.user = null;
                BaseActivity.bis_id = null;
                BaseActivity.codeUrl = null;
                BaseActivity.meM_id = null;
                BaseActivity.phone = null;
                BaseActivity.token = null;
                SharePUtils user = new SharePUtils(getActivity(), "user");
                user.add("login", null);
                user.add("token", null);
                user.add("phone", null);
                user.add("user_icon", null);
                user.add("bis_id", null);
                user.add("mem_id", null);
                getActivity().finish();
                getActivity().startActivity(new Intent(getActivity(), RegActivity.class).putExtra("requestCode", "22"));

            }
        });
    }

    @Override
    public void Scuess(Object o, int requestCode) {

        App application = (App) App.getApplication();
        if (requestCode == 3) {
            String msg = (String) o;

            application.toastLong(msg);
        }

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


    public void login() {
        userName.setText(username);
        userPhone.setText(phone);
        userIcon.setImageURI(icon);
        final Intent intent = new Intent(getActivity(), SetActivity.class);
        intent.putExtra("requestCode", "22");
        userIcon.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        userPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        jiaofie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DidivActivity.class));

            }
        });
        fenqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PayActivity.class));
            }
        });
    }


    public void notLogin() {
        userName.setText("手机号");
        userPhone.setText("用户");

        userIcon.setImageResource(R.mipmap.user_icon);
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegActivity.class));
            }
        });
        userPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegActivity.class));
            }
        });
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegActivity.class));
            }
        });

        jiaofie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegActivity.class));

            }
        });
        fenqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegActivity.class));
            }
        });
    }
}
