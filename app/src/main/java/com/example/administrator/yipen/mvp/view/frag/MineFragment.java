package com.example.administrator.yipen.mvp.view.frag;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.constance.ConstanceClass;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.administrator.yipen.mvp.view.BaseActivity;
import com.example.administrator.yipen.mvp.view.Iview;

import com.example.administrator.yipen.mvp.view.RegActivity;

import com.example.administrator.yipen.mvp.view.SetActivity;
import com.example.administrator.yipen.utils.OnPopListener;
import com.example.administrator.yipen.utils.PhotoUtils;
import com.example.administrator.yipen.utils.RewritePopwindow;
import com.example.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.administrator.yipen.server.LoginServerce.reflag;

public class MineFragment extends Fragment implements Iview {

    private View view;
    private SimpleDraweeView userIcon;
    private ImageView msg;
    private TextView userPhone;
    private TextView userName;
    private LoginBean.ResultBean resultBean;

    private Presenter presenter;
    private String code_url;
    private String phone;
    private String username;
    private String token;
    private String no_addres;


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
           login();
        } else {
            notLogin();
        }
        if (resultBean != null) {
            phone = resultBean.getTelephone();
            code_url = resultBean.getCode_url();
            token = resultBean.getToken();
            no_addres = resultBean.getCreate_time();

            username = (
                    String) resultBean.getUsername();
            if (username != null) {
                userName.setText(username);
            } else {
                userName.setText("用户");
            }
            if (phone != null) {
                userPhone.setText(phone);
            } else {
                userName.setText("手机号");
            }
            if (code_url != null) {
                userIcon.setImageURI(ConstanceClass.LOCTIONPATH + code_url);
            } else {
                userIcon.setImageResource(R.drawable.user_icon);
            }


        }
        login();
    }

    private void initView() {


        userIcon = view.findViewById(R.id.user_icon);
        userPhone = view.findViewById(R.id.user_phone);
        userName = view.findViewById(R.id.user_Namne);
        msg = view.findViewById(R.id.user_msg);

    }


    @SuppressLint("ResourceType")
    private void initData() {
        presenter = App.getPresenter(this);
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
        final Intent intent = new Intent(getActivity(), SetActivity.class);
        userIcon.setImageURI(BaseActivity.phone);
        userName.setText(BaseActivity.username);
        BaseActivity.user.add("user_icon", ConstanceClass.LOCTIONPATH + code_url);
        BaseActivity.user.add("addres", no_addres);
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
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }


    public void notLogin() {

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
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), RegActivity.class));
            }
        });
    }
}
