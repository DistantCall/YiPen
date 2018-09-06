package com.example.administrator.yipen.mvp.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.yipen.bean.OrderForm;
import com.example.administrator.yipen.bean.StagesBean;
import com.example.administrator.yipen.mvp.presenter.Presenter;
import com.example.myapplication.R;

public class PayActivity extends BaseActivity {

    private ImageView back;
    private ImageView img1;
    private ImageView img2;
    private Presenter presenter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {
        back = (ImageView) findViewById(R.id.pay_back);
        img1 = (ImageView) findViewById(R.id.aaaa);
        img2 = (ImageView) findViewById(R.id.bbbb);
    }

    @Override
    protected void initData() {
        back.setColorFilter(Color.WHITE);
        img1.setColorFilter(Color.GRAY);
        img2.setColorFilter(Color.GRAY);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = application.getPresenter(this);
        presenter.orderFormPre(phone, token);
    }

    @Override
    public void Scuess(Object o, int requestCode) {
        if (requestCode == 10006) {
            StagesBean stagesBean= (StagesBean) o;
            if(stagesBean.getStatus()==1){
                Log.e("tag",stagesBean.getRes().get(0).toString());
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
}
