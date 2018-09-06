package com.example.administrator.yipen.mvp.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.yipen.app.App;
import com.example.administrator.yipen.bean.UserUpdateInfo;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.myapplication.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.example.administrator.yipen.constance.ConstanceClass.LOCTIONPATH;
import static com.example.administrator.yipen.constance.ConstanceClass.UPDATEUSERINFO;

public class UpUserInfoActivity extends BaseActivity implements View.OnClickListener {

    private EditText data_content;
    private ImageView back;
    private Button edit;
    private String data;
    private Presenter presenter;
    private String phone;
    private String token;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_up_user_info;
    }

    @Override
    protected void initView() {
        presenter = App.getPresenter(this);
        edit = (Button) findViewById(R.id.edit_up);
        back = (ImageView) findViewById(R.id.up_icback);
        data_content = (EditText) findViewById(R.id.Updata_Content);
        back.setColorFilter(Color.WHITE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getIntent().getStringExtra("phone") != null) {
            data = getIntent().getStringExtra("data");
            phone = getIntent().getStringExtra("phone");
            token = getIntent().getStringExtra("token");
        } else {
            data = user.query("data");
            phone = user.query("phone");
            token = user.query("token");
        }

    }

    @Override
    protected void initData() {

        data_content.setText(data);
        back.setOnClickListener(this);
        edit.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void Scuess(Object o, int requestCode) {
        if (requestCode == 10002) {

            UserUpdateInfo updateInfo = (UserUpdateInfo) o;
            if (updateInfo.getStatus()==1){
                application.toastLong(updateInfo.getMessage());
                finish();
            }else{
                Log.e("update",updateInfo.getResult().get(0).toString());
                application.toastLong(updateInfo.getMessage());
            }
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
            case R.id.up_icback:
                finish();
                break;
            case R.id.edit_up:
                Map<String,String> map = new HashMap<>();
                map.put(data,data_content.getText().toString().trim());
//                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
//                    @Override
//                    public void log(String message) {
//                        Log.e("msg",message);
//                    }
//                });
//                OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
//
//                FormBody body = new FormBody.Builder()
//                        .add("telephone", phone)
//                        .add("token", token)
//                        .add(data, data_content.getText().toString().trim())
//                        .build();
//
//                Request request= new Request.Builder().url(LOCTIONPATH+UPDATEUSERINFO).post(body).build();
//
//                okHttpClient.newCall(request).enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                        String string = response.body().string();
//                        Log.e("str",string);
//                    }
//                });
                presenter.updateUserInfo(phone,token,map);
                break;
        }
    }
}
