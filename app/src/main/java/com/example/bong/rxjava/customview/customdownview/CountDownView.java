package com.example.bong.rxjava.customview.customdownview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.bong.rxjava.R;
import com.example.bong.rxjava.utils.DisplayUtil;

/**
 * Created by hs on 2017/8/13.
 * 倒计时view
 */

public class CountDownView extends View {
    private Paint mPaintBackGround;//背景画笔
    private Paint mPaintArc;//圆弧画笔
    private Paint mPaintText;//文字画笔
    private int mRetreatType;//圆弧绘制方式（增加和减少）
    private float mPaintArcWidth;//最外层圆弧的宽度
    private int mCircleRadius;//圆圈的半径
    private int mPaintArcColor = Color.parseColor("#3C3F41");//初始值
    private int mPaintBackGroundColor = Color.parseColor("#55B2E5");//初始值
    private int mLoadingTime;//时间，单位秒
    private String mLoadingTimeUnit = "";//时间单位
    private int mTextColor = Color.BLACK;//字体颜色
    private int mTextSize;//字体大小
    private int location;//从哪个位置开始
    private float startAngle;//开始角度
    private float mmSweepAngleStart;//起点
    private float mmSweepAngleEnd;//终点
    private float mSweepAngle;//扫过的角度
    private String mText = "";//要绘制的文字
    private int mWidth;
    private int mHeight;

    public CountDownView(Context context) {
        super(context);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CountDownView);
        mRetreatType = array.getInt(R.styleable.CountDownView_cd_retreat_type, 1);
        location = array.getInt(R.styleable.CountDownView_cd_location, 1);
        mCircleRadius = (int) array.getDimension(R.styleable.CountDownView_cd_circle_radius, DisplayUtil.dp2Px(context, 25));//默认25dp
        mPaintArcWidth = array.getDimension(R.styleable.CountDownView_cd_arc_width, DisplayUtil.dp2Px(context, 3));//默认3dp
        mPaintArcColor = array.getColor(R.styleable.CountDownView_cd_arc_color, mPaintArcColor);
        mTextSize = (int) array.getDimension(R.styleable.CountDownView_cd_text_size, DisplayUtil.dp2Px(context, 14));//默认14dp
        mTextColor = array.getColor(R.styleable.CountDownView_cd_text_color, mTextColor);
        mPaintBackGroundColor = array.getColor(R.styleable.CountDownView_cd_bg_color, mPaintBackGroundColor);
        mLoadingTime = array.getInteger(R.styleable.CountDownView_cd_animator_time, 3);//默认3秒
        mLoadingTimeUnit = array.getString(R.styleable.CountDownView_cd_animator_time_unit);//时间单位
        if (TextUtils.isEmpty(mLoadingTimeUnit)) {
            mLoadingTimeUnit = "";
        }
        array.recycle();
        init();
    }


    private void init() {
//    背景圆弧的画笔
        mPaintBackGround = new Paint();
        mPaintBackGround.setAntiAlias(true);//抗锯齿
        mPaintBackGround.setStrokeWidth(mPaintArcWidth);
        mPaintBackGround.setStyle(Paint.Style.STROKE);
        mPaintBackGround.setColor(Color.GRAY);
        //进度圆弧
        mPaintArc = new Paint();
        mPaintArc.setAntiAlias(true);
        mPaintArc.setStrokeWidth(mPaintArcWidth);
        mPaintArc.setStyle(Paint.Style.STROKE);
        mPaintArc.setColor(mPaintArcColor);
        //文字
        mPaintText = new Paint();
        mPaintText.setStyle(Paint.Style.STROKE);
        mPaintText.setAntiAlias(true);
        mPaintText.setColor(mTextColor);
        mPaintText.setTextSize(mTextSize);

        if (mLoadingTime < 3) {
            mLoadingTime = 3;
        }
        if (location == 1) {
            startAngle = -180;
        } else if (location == 2) {
            startAngle = -90;
        } else if (location == 3) {
            startAngle = 0;
        } else if (location == 4) {
            startAngle = 90;
        }
        if (mRetreatType == 1) {
            mmSweepAngleStart = 0f;
            mmSweepAngleEnd = 360f;
        } else {
            mmSweepAngleStart = 360f;
            mmSweepAngleEnd = 0f;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //此测量该圆形的view
        setMeasuredDimension(mCircleRadius * 2, mCircleRadius * 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //首先画背景图
        canvas.drawCircle(mWidth / 2, mHeight / 2, mWidth / 2 - mPaintArcWidth, mPaintBackGround);
        //画圆弧
        RectF rectF = new RectF(0 + mPaintArcWidth / 2, 0 + mPaintArcWidth / 2
                , mWidth - mPaintArcWidth / 2, mHeight - mPaintArcWidth / 2);
        canvas.drawArc(rectF, startAngle, mSweepAngle, false, mPaintArc);
        //画文字
        float mTextWidth = mPaintText.measureText(mText, 0, mText.length());
        float dx = mWidth / 2 - mTextWidth / 2;
        Paint.FontMetricsInt fontMetricsInt = mPaintText.getFontMetricsInt();
        float dy = fontMetricsInt.bottom - (fontMetricsInt.bottom - fontMetricsInt.top) / 2;
        float baseLine = mHeight / 2 - dy;
        canvas.drawText(mText, dx, baseLine, mPaintText);
    }

    public void start() {
        //圆弧的度数
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(mmSweepAngleStart, mmSweepAngleEnd);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSweepAngle = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        //时间
        ValueAnimator timeAnimator = ValueAnimator.ofInt(mLoadingTime, 0);
        timeAnimator.setInterpolator(new LinearInterpolator());
        timeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int time = (int) animation.getAnimatedValue();
                mText = time + mLoadingTimeUnit;
            }
        });
        AnimatorSet set = new AnimatorSet();
        set.playTogether(valueAnimator, timeAnimator);
        set.setDuration(mLoadingTime * 1000);
        set.setInterpolator(new LinearInterpolator());
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                clearAnimation();
                if (loadingFinishListener != null) {
                    loadingFinishListener.finish();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    interface LoadingFinishListener {
        void finish();
    }

    LoadingFinishListener loadingFinishListener;

    public void setLoadingFinishListener(LoadingFinishListener loadingFinishListener) {
        this.loadingFinishListener = loadingFinishListener;
    }
}

