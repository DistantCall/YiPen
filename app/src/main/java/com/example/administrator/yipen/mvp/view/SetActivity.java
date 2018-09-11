package com.example.administrator.yipen.mvp.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;

import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.bean.FileBean;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.administrator.yipen.utils.OnPopListener;

import com.example.administrator.yipen.utils.UserInfo;
import com.example.myapplication.R;
import com.facebook.drawee.view.SimpleDraweeView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class SetActivity extends BaseActivity implements View.OnClickListener {

    Presenter presenter;
    private static final int CAMERA_REQUEST_CODE = 0xa0;
    private static final int ALBUM_REQUEST_CODE = 0xa1;
    private static final int CROP_REQUEST_CODE = 0xa2;

    private View view;
    private SimpleDraweeView userIcon;
    private TextView nickName;
    private TextView userNmae;
    private TextView up_phone;
    private Intent intent;
    private RelativeLayout up_nickame;
    private RelativeLayout up_trueName;
    private RelativeLayout up_icon;
    private UserInfo userInfo;
   private File tempFile;
    private ImageView back;

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
        up_nickame = (RelativeLayout) findViewById(R.id.update_nickName);
        up_trueName = (RelativeLayout) findViewById(R.id.up_trueName);
        up_icon = (RelativeLayout) findViewById(R.id.set_nickName);
        back = (ImageView) findViewById(R.id.set_back);
    }

    @Override
    protected void initData() {
        intent = new Intent(this, UpUserInfoActivity.class);
        up_icon.setOnClickListener(this);
        up_nickame.setOnClickListener(this);
        up_trueName.setOnClickListener(this);
        back.setOnClickListener(this);
    }


    @Override
    public void Scuess(Object o, int requestCode) {
        if (requestCode == 10000) {
            userInfo = (UserInfo) o;
            if (userInfo.getStatus() == 1) {
                Log.e("data", userInfo.toString());
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
            BaseActivity.user.add("user_icon",userInfo.getRes().get(0).getCode_url());
            BaseActivity.user.add("bis_id",userInfo.getRes().get(0).getUsername());
        } else if (requestCode == 10001) {
            FileBean fileBean = (FileBean) o;
            Log.e("fileBean", fileBean.toString());
            userIcon.setImageURI(fileBean.getResult());
            user.add("user_icon",fileBean.getResult());
            Toast.makeText(SetActivity.this, fileBean.toString(), Toast.LENGTH_LONG).show();
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
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.set_nickName:
                alertDiaLogFouction();
                break;
            case R.id.update_nickName:
                intent.putExtra("data", "nickname");
                startActivityForResult(intent, 20000);
                break;
            case R.id.up_trueName:
                intent.putExtra("data", "truename");
                startActivityForResult(intent, 20000);
                break;
            case  R.id.set_back:
                finish();
                break;
        }
    }

    /**
     * 从相机获取图片
     */
    private void getPicFromCamera() {
        //用于保存调用相机拍照后所生成的文件
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), System.currentTimeMillis() + ".jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, "com.example.administrator.yipen.fileprovider", tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            Log.e("dasd", contentUri.toString());
        } else {    //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    /**
     * 从相册获取图片
     */
    private void getPicFromAlbm() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }


    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case CAMERA_REQUEST_CODE:   //调用相机后返回
                if (resultCode == RESULT_OK) {
                    //用相机返回的照片去调用剪裁也需要对Uri进行处理
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(this, "com.hansion.chosehead", tempFile);
                        cropPhoto(contentUri);
                    } else {
                        cropPhoto(Uri.fromFile(tempFile));
                    }
                }
                break;
            case ALBUM_REQUEST_CODE:    //调用相册后返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    cropPhoto(uri);
                }
                break;
            case CROP_REQUEST_CODE:     //调用剪裁后返回
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    //设置到ImageView上

                    //也可以进行一些保存、压缩等操作后上传
                    String  path = saveImage("crop",image);



                    File file = new File(path);
                    Log.e("isnull",file.exists()+"");
                    Log.e("isnull",phone);
                    Log.e("isnull",token);
                    presenter.fileMultPart(phone,file,token);
                }
                break;
            case 20000:
               presenter.userInfoPre(phone,token);
                break;
        }
    }
public void alertDiaLogFouction(){
        String path="storage/emulated/0/a.jpg";
    AlertDialog.Builder builder=new AlertDialog.Builder(this)
            .setIcon(R.mipmap.ic_launcher)
            .setTitle("选择图片：")
            //设置两个item
            .setItems(new String[]{"相机","图库"}, new android.content.DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
//                            getPicFromCamera();
                            File Camera = new File(path);
                            Log.e("isnull",Camera.exists()+"");
                            Log.e("isnull",phone);
                            Log.e("isnull",token);
                            presenter.fileMultPart(phone,Camera,token);

                            break;
                        case 1:
//                            getPicFromAlbm();
                            File Albm = new File(path);
                            Log.e("isnull",Albm.exists()+"");
                            Log.e("isnull",phone);
                            Log.e("isnull",token);
                            presenter.fileMultPart(phone,Albm,token);
                            break;
                        default:

                            break;
                    }

                }});
    builder.create().show();
}


    public String saveImage(String name, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
