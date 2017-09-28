package com.example.bong.rxjava.customview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by hs on 2017/8/21.
 * 只在圆形里触发点击事件的view
 */

public class CircleLayout extends LinearLayout {
    private int lastX;
    private int lastY;
    private static final String TAG = "CircleLayout";


    public CircleLayout(Context context) {
        super(context);
    }

    public CircleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                //获取控件在屏幕的位置
                int[] location = new int[2];
                this.getLocationOnScreen(location);
                //控件相对于屏幕的x与y坐标
                int x = location[0];
                int y = location[1];
                //圆半径通过左右坐标计算获得getLeft
                int radius = (this.getRight() - this.getLeft()) / 2;
                //圆心坐标
                int centerX = x + radius;
                int centerY = y + radius;
                //点击位置x坐标与圆心的x坐标的距离
                int distanceX = Math.abs(centerX - lastX);
                int distanceY = Math.abs(centerY - lastY);
                //点击位置到圆心的直线距离
                int distanceZ = (int) Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
                if (distanceZ > radius) {
                    return false;
                } else {
                    if (mListener != null) {
                        mListener.onCircleLayoutClickListener();
                    }
                }
            default:
                break;
        }
        return true;
    }

    public interface CircleLayoutClickListener {
        void onCircleLayoutClickListener();
    }

    CircleLayoutClickListener mListener;

    public void setOnCircleLayoutClickListener(CircleLayoutClickListener listener) {
        mListener = listener;
    }
}
