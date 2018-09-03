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
    private File tempFile;
    private View view;
    private SimpleDraweeView userIcon;
    private ImageView msg;
    private TextView userPhone;
    private TextView userName;
    private LoginBean.ResultBean resultBean;
    private RewritePopwindow finishProjectPopupWindows;
    private Presenter presenter;

    private static final int  CODE_GALLERY_REQUEST = 0xa0;
    private static final int  CODE_CAMERA_REQUEST  = 0xa1;
    private static final int  CODE_RESULT_REQUEST  = 0xa2;
    private              File fileUri              = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private              File fileCropUri          = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.mine_layout, container, false);

        EventBus.getDefault().register(this);

        return view;
    }

    /**
     * 拍照
     * @param view
     */
    public void takePhoto(View view) {
        if (hasSdcard()) {
            imageUri = Uri.fromFile(fileUri);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                //通过FileProvider创建一个content类型的Uri
                imageUri = FileProvider.getUriForFile(getActivity(), "com.MainActivity.provider", fileUri);
           PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
        } else {
            Toast.makeText(getActivity(), "设备没有SD卡！", Toast.LENGTH_SHORT).show();
            Log.e("asd", "设备没有SD卡");
        }
    }
    /**
     * 调用系统相册
     * @param view
     */
    public void getPhoto(View view) {
        PhotoUtils.openPic(getActivity(), CODE_GALLERY_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        int output_X = 480, output_Y = 480;
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CODE_CAMERA_REQUEST://拍照完成回调
                    cropImageUri = Uri.fromFile(fileCropUri);
                    PhotoUtils.cropImageUri(this, imageUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    break;
                case CODE_GALLERY_REQUEST://访问相册完成回调
                    if (hasSdcard()) {
                        cropImageUri = Uri.fromFile(fileCropUri);
                        Uri newUri = Uri.parse(PhotoUtils.getPath(getActivity(), data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(getActivity(), "com.MainActivity.provider", new File(newUri.getPath()));
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    } else {
                        Toast.makeText(getActivity(), "设备没有SD卡!", Toast.LENGTH_SHORT).show();
                    }

                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, getActivity());
                    if (bitmap != null) {
                        showImages(bitmap);
                    }
                    break;
            }
        }
    }

    /**
     * 展示图片
     * @param bitmap
     */
    private void showImages(Bitmap bitmap) {
        userIcon.setImageBitmap(bitmap);
    }


    /**
     * 检查设备是否存在SDCard的工具方法
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
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


    }

    @Override
    public void onResume() {
        super.onResume();
//        if (reflag) {
            login();
//        } else {
//            notLogin();
//        }
        if (resultBean != null) {
            String phone = resultBean.getTelephone();
            String code_url = resultBean.getCode_url();
            String username = (String) resultBean.getUsername();
            if (username!=null){
                userName.setText(username);
            }else{
                userName.setText("用户");
            }
            if(phone!=null) {
                userPhone.setText(phone);
            }else{
                userName.setText("手机号");
            }
            if(code_url!=null) {
                userIcon.setImageURI(ConstanceClass.LOCTIONPATH + code_url);
            }else{
                userIcon.setImageResource(R.drawable.user_icon);
            }


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



    }

    private void showPopwindow() {
        finishProjectPopupWindows = new RewritePopwindow(getActivity(), new OnPopListener() {
            @Override
            public void comera() {
                takePhoto(userIcon);

                File file = new File("/mnt/sdcard/Pictures/a.jpg");
                Log.e("path", "" + file.exists());
                presenter.fileMultPart(file);
            }

            @Override
            public void compture() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                getActivity().startActivityForResult(intent, CODE_GALLERY_REQUEST);
            }

            @Override
            public void back() {
                finishProjectPopupWindows.dismiss();
            }
        });
        finishProjectPopupWindows.showAtLocation(view,
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
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
        userIcon.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetActivity.class));
            }
        });

        userPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(), SetActivity.class));
                }
        });
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetActivity.class));
            }
        });
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SetActivity.class));
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
