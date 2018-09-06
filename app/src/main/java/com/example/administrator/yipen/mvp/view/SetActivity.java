package com.example.administrator.yipen.mvp.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.bean.FileBean;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.administrator.yipen.utils.OnPopListener;
import com.example.administrator.yipen.utils.PhotoUtils;
import com.example.administrator.yipen.utils.RewritePopwindow;

import com.example.administrator.yipen.utils.UserInfo;
import com.example.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.List;

public class SetActivity extends BaseActivity implements View.OnClickListener {
    private RewritePopwindow finishProjectPopupWindows;

    Presenter presenter;
    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;
    private File fileUri = new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");
    private File fileCropUri = new File(Environment.getExternalStorageDirectory().getPath() + "/crop_photo.jpg");
    private Uri imageUri;
    private Uri cropImageUri;
    private View view;
    private SimpleDraweeView userIcon;
    private TextView nickName;
    private TextView userNmae;
    private TextView up_phone;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUserInfo();
    }

    private void initUserInfo() {
        presenter = App.getPresenter(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        presenter.userInfoPre(BaseActivity.phone, BaseActivity.token);

    }

    @Override
    protected int getContentViewId() {
        view = View.inflate(this, R.layout.activity_set, null);
        return R.layout.activity_set;
    }

    @Override
    protected void initView() {
        userIcon = (SimpleDraweeView) findViewById(R.id.up_user_icon);
        nickName = (TextView) findViewById(R.id.nickName);
        userNmae = (TextView) findViewById(R.id.up_userNmae);
        up_phone = (TextView) findViewById(R.id.up_phone);
    }

    @Override
    protected void initData() {
        intent = new Intent(this, UpUserInfoActivity.class);
        userIcon.setOnClickListener(this);
        nickName.setOnClickListener(this);
        userNmae.setOnClickListener(this);
    }


    @Override
    public void Scuess(Object o, int requestCode) {
        if (requestCode == 10000) {
            UserInfo userInfo = (UserInfo) o;
            if (userInfo.getStatus() == 1) {
                Log.e("token",userInfo.toString());
                userIcon.setImageURI(userInfo.getRes().get(0).getCode_url());

                if (userInfo.getRes().get(0).getNickname() == null) {
                    nickName.setText("用户");
                } else {
                    nickName.setText((String) userInfo.getRes().get(0).getNickname());
                }
                up_phone.setText(phone);
                if (userInfo.getRes().get(0).getTruename() == null) {
                    userNmae.setText("Neo");
                } else {
                    userNmae.setText(userInfo.getRes().get(0).getTruename());
                }
            }
        } else if (requestCode == 10001) {
            FileBean fileBean = (FileBean) o;
            Log.e("fileBean", fileBean.toString());
            Toast.makeText(SetActivity.this, fileBean.toString(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void Error(Throwable e) {
    }
    /**
     * 拍照
     *
     * @param view
     */
    public void takePhoto(View view) {
        if (hasSdcard()) {
            imageUri = Uri.fromFile(fileUri);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                //通过FileProvider创建一个content类型的Uri
                imageUri = FileProvider.getUriForFile(this, "com.MainActivity.provider", fileUri);
            PhotoUtils.takePicture(this, imageUri, CODE_CAMERA_REQUEST);
        } else {
            Toast.makeText(this, "设备没有SD卡！", Toast.LENGTH_SHORT).show();
            Log.e("asd", "设备没有SD卡");
        }
    }
    /**
     * 调用系统相册
     *
     * @param view
     */
    public void getPhoto(View view) {
        PhotoUtils.openPic(this, CODE_GALLERY_REQUEST);
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
                        Uri newUri = Uri.parse(PhotoUtils.getPath(this, data.getData()));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                            newUri = FileProvider.getUriForFile(this, "com.MainActivity.provider", new File(newUri.getPath()));
                        PhotoUtils.cropImageUri(this, newUri, cropImageUri, 1, 1, output_X, output_Y, CODE_RESULT_REQUEST);
                    } else {
                        Toast.makeText(this, "设备没有SD卡!", Toast.LENGTH_SHORT).show();
                    }

                    break;
                case CODE_RESULT_REQUEST:
                    Bitmap bitmap = PhotoUtils.getBitmapFromUri(cropImageUri, this);
                    if (bitmap != null) {
                        showImages(bitmap);
                    }
                    break;
                case 20000:
                    presenter.userInfoPre(BaseActivity.phone, BaseActivity.token);
                    break;
            }
        }
    }

    /**
     * 展示图片
     *
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

    private void showPopwindow() {
        finishProjectPopupWindows = new RewritePopwindow(this, new OnPopListener() {
            @Override
            public void comera() {
//                takePhoto(userIcon);

                File file = new File("/storage/emulated/0/Pictures/a.jpg");
                Log.e("path", "" + file.exists());
                presenter.fileMultPart(phone, file, token);
            }

            @Override
            public void compture() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, CODE_GALLERY_REQUEST);
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
    public void LoginScuess(Object o) {

    }

    @Override
    public void LoginErr(Throwable t) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.up_user_icon:
                showPopwindow();
                break;
            case R.id.nickName:
                intent.putExtra("phone", phone);
                intent.putExtra("token", token);
                intent.putExtra("data","nickname");
                startActivityForResult(intent,20000);
                break;
            case R.id.up_userNmae:
                intent.putExtra("phone", phone);
                intent.putExtra("token", token);
                intent.putExtra("data","truename");
                startActivityForResult(intent,20000);
                break;
        }
    }
}
