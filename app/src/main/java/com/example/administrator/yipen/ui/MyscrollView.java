
package com.example.administrator.yipen.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * @author Zhenhua on 2017/5/24 11:15.
 * @email zhshan@ctrip.com
 */

public class MyscrollView extends ScrollView{
    public MyscrollView(Context context) {
        super(context);
    }

    public MyscrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyscrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    View view;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed){
            LinearLayout v = (LinearLayout) getChildAt(0);
            if(v != null){
                for(int i=0;i<v.getChildCount();i++){
                    if(v.getChildAt(i).getTag() != null && ((String)v.getChildAt(i).getTag()).equals("aaa")){
                        view = v.getChildAt(i);
                        break;
                    }
                }
            }
        }
    }






}
