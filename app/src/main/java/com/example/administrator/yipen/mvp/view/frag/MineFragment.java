package com.example.administrator.yipen.mvp.view.frag;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (reflag) {
            if(resultBean==null){
                phone = BaseActivity.phone;
                token = BaseActivity.token;
                icon = BaseActivity.codeUrl;
                username = BaseActivity.username;
            }else{
                phone=resultBean.getTelephone();
                token=resultBean.getToken();
                icon= ConstanceClass.LOCTIONPATH + "/img/" +resultBean.getCode_url();
                username=resultBean.getUsername();
            }


            userName.setText(username);
            userPhone.setText(phone);
            userName.setText("手机号");
            setIcon(icon);
            login();
        } else {
            notLogin();
        }


    }

    private void setIcon(String url) {
        int alpha = Color.parseColor("#00FFFFFF");
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                //图片地址
                .setUri(url)
                //播放gif 图片
                //     .setAutoPlayAnimations(true)
                //点击重新加载时 可以重新加载4 次
                .setTapToRetryEnabled(true)
                .build();
        RoundingParams rp = new RoundingParams();
//设置边框颜色 宽度
        rp.setBorder(alpha, 2);
//设置圆角
        rp.setRoundAsCircle(true);
        GenericDraweeHierarchy build = GenericDraweeHierarchyBuilder.newInstance(getActivity().getResources())
                 .setRoundingParams(RoundingParams.asCircle()) //直接设置圆角
                .setRoundingParams(rp)
                .build();
//图片
        userIcon.setController(controller);
        userIcon.setHierarchy(build);
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
                user.delete("login");
                getActivity().finish();
                getActivity().startActivity(new Intent(getActivity(), RegActivity.class));

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
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getData(List<LoginBean.ResultBean> resultBeanList) {
        resultBean = resultBeanList.get(0);

    }

    public void login() {
        userName.setText(username);
        userPhone.setText(phone);
        userIcon.setImageURI(icon);
        final Intent intent = new Intent(getActivity(), SetActivity.class);

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
