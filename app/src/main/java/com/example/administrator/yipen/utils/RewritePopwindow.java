package com.example.administrator.yipen.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.myapplication.R;


/**
 * 自定义popupWindow
 */

public class RewritePopwindow extends PopupWindow implements View.OnClickListener {
    private View mView;
    private OnPopListener onPopListener;
    public RewritePopwindow(Activity context, OnPopListener onPopListener) {
        super(context);
        this.onPopListener=onPopListener;
        initView(context);
    }

    private void initView(final Activity context) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = mInflater.inflate(R.layout.picture_upload_layout, null);
        TextView compture = (TextView) mView.findViewById(R.id.compture);
        TextView comera = (TextView) mView.findViewById(R.id.comera);
        TextView blak = (TextView) mView.findViewById(R.id.upload_blak);



        //设置按钮监听
        compture.setOnClickListener(this);
        comera.setOnClickListener(this);
        blak.setOnClickListener(this);

        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置PopupWindow可触摸
        this.setTouchable(true);
        //设置非PopupWindow区域是否可触摸
//        this.setOutsideTouchable(false);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
        //实例化一个ColorDrawable颜色为半透明

        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(new ColorDrawable(Color.CYAN));
        backgroundAlpha(context, 0.5f);//0.0-1.0
        this.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss() {
                // TODO Auto-generated method stub
                backgroundAlpha(context, 1f);
            }
        });
    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.compture:
                onPopListener.compture();
                break;
            case R.id.comera:
                onPopListener.comera();
                break;
            case R.id.upload_blak:
                onPopListener.back();
                break;
        }
    }
}
