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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.bean.LoginBean;
import com.example.administrator.yipen.constance.ConstanceClass;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.administrator.yipen.mvp.view.Iview;
import com.example.administrator.yipen.mvp.view.RegActivity;
import com.example.administrator.yipen.server.LoginInter;
import com.example.administrator.yipen.server.LoginServerce;
import com.example.administrator.yipen.utils.OnPopListener;
import com.example.administrator.yipen.utils.RewritePopwindow;
import com.example.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.List;

public class MineFragment extends Fragment implements Iview,LoginInter {
    private File tempFile;
    private View view;
    private SimpleDraweeView userIcon;
    private ImageView msg;
    private TextView userPhone;
    private TextView userName;
    private LoginBean.ResultBean resultBean;
    private RewritePopwindow finishProjectPopupWindows;
    private Presenter presenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_layout, container, false);

        EventBus.getDefault().register(this);

        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getResult(List<LoginBean.ResultBean> resultList) {
        resultBean = resultList.get(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        LoginServerce.init(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (resultBean != null) {
            String phone = resultBean.getTelephone();
            String username = resultBean.getUsername();
            userName.setText(username);
            userPhone.setText(phone);
            userIcon.setImageURI(ConstanceClass.LOCTIONPATH + resultBean.getCode_url());
        }

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
        userName.setText("注册/登录");




    }

    private void showPopwindow() {
        finishProjectPopupWindows = new RewritePopwindow(getActivity(), new OnPopListener() {
            @Override
            public void comera() {
                File file = new File("/mnt/sdcard/Pictures/a.jpg");
                Log.e("path", "" + file.exists());
                presenter.fileMultPart(file);

            }

            @Override
            public void compture() {

            }

            @Override
            public void back() {
                finishProjectPopupWindows.dismiss();
            }
        });
        finishProjectPopupWindows.showAtLocation(view,
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }



//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == PHOTO_REQUEST_GALLERY) {
//            // 从相册返回的数据
//            if (data != null) {// 得到图片的全路径
//                Uri uri = data.getData();
//                fileMultpart.crop(uri);
//            }
//        } else if (requestCode == PHOTO_REQUEST_CAREMA) {//从相机返回的数据
//            if (fileMultpart.hasSdcard()) {
//                fileMultpart.crop(Uri.fromFile(tempFile));
//            } else {
//                Toast.makeText(App.getApplication(), "未找到存储卡，无法存储照片！", Toast.LENGTH_LONG).show();
//            }
//        } else if (requestCode == PHOTO_REQUEST_CUT) {//从剪切图片返回的数据
//            if (data != null) {
//                Bitmap bitmap = data.getParcelableExtra("data");
//                //将bitmap转换为Uri
//                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, null, null));
//                //对非正确的Uri处理，这类Uri存在手机的external.db中，可以查询_data字段查出对应文件的uri
//                if (uri.getPath().contains("external")) {
//                    uri = fileMultpart.external(uri.getPath());
//                }
//                //在这可以拿到裁剪后的图片Uri,然后进行你想要的操作
//                userIcon.setImageURI(uri);
//            }
//            try {
//                tempFile.delete();//将临时文件删除
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

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


    @Override
    public void login() {
        userIcon.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                showPopwindow();
            }
        });

        userPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void notLogin() {
        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(),RegActivity.class),1);
            }
        });
        userPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(),RegActivity.class),1);
            }
        });
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(),RegActivity.class),1);
            }
        });
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(),RegActivity.class),1);
            }
        });
    }
}
