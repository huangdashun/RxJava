package com.example.bong.rxjava.customview.datapick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.bong.rxjava.R;
import com.example.bong.rxjava.utils.DisplayUtil;

import java.util.Calendar;
import java.util.Date;

import static android.content.ContentValues.TAG;


/**
 * @author rqg
 * @date 10/28/15.
 */
public class CalendarView extends View {
    private static final String TAG = "CalendarView";
    private Paint mWeekPaint, mDividerPaint, mDayPaint, mSelectedPaint;

    private int mWeekColor, mDividerColor, mDayColor, mSelectedColor;

    private int mTextSize;

    private Calendar mCalendar;

    private int mYear, mMonth;


    private float mCellWidth, mCellHeight;//一行有七个日历点,日历点的宽高

    private Date mSelectedDate = new Date(0);

    private String[] mWeekName;//日--六

    private float mOffsetX, mOffsetY;
    private int mToday = 40;

    private OnDaySelectedListener mListener;

    public CalendarView(Context context) {
        super(context);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        final int widthMode = MeasureSpec.getMode(widthSpec);
        final int heightMode = MeasureSpec.getMode(heightSpec);
        final int widthSize = MeasureSpec.getSize(widthSpec);
        final int heightSize = MeasureSpec.getSize(heightSpec);

        int width = 0;
        int height = 0;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                width = ViewCompat.getMinimumWidth(this);
                break;
        }

        mCellWidth = width / 7f;

        mCellHeight = mWeekPaint.getTextSize() * 2f;

        height = (int) (mCellHeight * 7);

        mOffsetX = 0.5f * mCellWidth;

        mOffsetY = -0.7f * mWeekPaint.getTextSize();

        setMeasuredDimension(width, height);
    }

    public int getDatePickViewHeight(int widthSpec, int heightSpec) {
        // TODO: 10/28/15 　暂行方案，寻找其他解决方案


        final int widthMode = MeasureSpec.getMode(widthSpec);
        final int heightMode = MeasureSpec.getMode(heightSpec);
        final int widthSize = MeasureSpec.getSize(widthSpec);
        final int heightSize = MeasureSpec.getSize(heightSpec);

        int width = 0;
        int height = 0;

        switch (widthMode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                width = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                width = ViewCompat.getMinimumWidth(this);
                break;
        }

        mCellWidth = width / 7f;

        mCellHeight = mWeekPaint.getTextSize() * 2f;

        height = (int) (mCellHeight * 7);

        return height;
    }


    private void init() {
        mTextSize = (int) DisplayUtil.dp2Px(getContext(), 17);//文字的大小为17dp
        mWeekName = getResources().getStringArray(R.array.week_name);

        mCalendar = Calendar.getInstance();

        mWeekColor = getResources().getColor(R.color.white_9);//画星期的颜色
        mDividerColor = getResources().getColor(R.color.white_c);//画线的颜色
        mDayColor = getResources().getColor(R.color.black);//画日期的颜色
        mSelectedColor = getResources().getColor(R.color.green_1);//画当前查看的日期

        //星期画笔
        mWeekPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mWeekPaint.setColor(mWeekColor);
        mWeekPaint.setTextAlign(Paint.Align.CENTER);
        mWeekPaint.setStyle(Paint.Style.FILL);
        mWeekPaint.setTextSize(mTextSize);
        //线的画笔
        mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDividerPaint.setColor(mDividerColor);
        mDividerPaint.setStrokeWidth(2);
        //日期的画笔
        mDayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDayPaint.setColor(mDayColor);
        mDayPaint.setTextAlign(Paint.Align.CENTER);
        mDayPaint.setStyle(Paint.Style.FILL);
        mDayPaint.setTextSize(mTextSize);
        //当前查看的日期的画笔
        mSelectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSelectedPaint.setColor(mSelectedColor);
        mSelectedPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawWeek(canvas);
        drawSelectedCircle(canvas);
        drawDay(canvas);
    }

    private void drawSelectedCircle(Canvas canvas) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mSelectedDate);

        if (calendar.get(Calendar.YEAR) == mCalendar.get(Calendar.YEAR)
                && calendar.get(Calendar.MONTH) == mCalendar.get(Calendar.MONTH)) {
            float cx = (calendar.get(Calendar.DAY_OF_WEEK) - 0.5f) * mCellWidth;
            float cy = (calendar.get(Calendar.WEEK_OF_MONTH) + 0.5f) * mCellHeight;
            canvas.drawCircle(cx, cy, Math.min(mCellWidth / 2f, mCellHeight / 2f), mSelectedPaint);
        }
    }

    private void drawWeek(Canvas canvas) {
        float x = mOffsetX;
        float y = mCellHeight + mOffsetY;
        for (int i = 0; i < 7; i++) {
            canvas.drawText(mWeekName[i], x, y, mWeekPaint);
            x += mCellWidth;
        }

        canvas.drawLine(0, mCellHeight, getWidth(), mCellHeight, mDividerPaint);
//        canvas.drawLine(1, 0, getWidth(), 1, mDividerPaint);
    }

    private void drawDay(Canvas canvas) {
        float x;
        float y = 2 * mCellHeight + mOffsetY;

        int dc = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);//当前月的最大天数
        int week = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;//星期几

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mSelectedDate);
        Log.i(TAG, "选中的day:" + calendar.get(Calendar.DAY_OF_MONTH));

        x = week * mCellWidth + mOffsetX;

        mDayPaint.setColor(mDayColor);

        for (int i = 1; i <= dc; i++) {


            canvas.drawText(String.valueOf(i), x, y, mDayPaint);

            //绘制不可选择的天数时设置颜色
            if (i == mToday) {
                mDayPaint.setColor(Color.GRAY);
                Log.i(TAG, "mToday:" + mToday);
            }
//            if (i == calendar.get(Calendar.DAY_OF_MONTH)) {//将选中的颜色设置为白色
//                mDayPaint.setColor(Color.WHITE);
//            }

            if (week % 7 == 6) {
                x = mOffsetX;
                y += mCellHeight;
            } else {
                x += mCellWidth;
            }

            week++;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction() & MotionEventCompat.ACTION_MASK;
        switch (action) {

            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_UP:
                Date d = getSelectedDateBy(event.getX(), event.getY());
                if (mListener != null && d != null)
                    mListener.onDaySelected(d);
                return true;

        }
        return false;
    }

    private Date getSelectedDateBy(float ex, float ey) {
        Date d = null;
        int x = (int) (ex / mCellWidth);
        int y = (int) (ey / mCellHeight);
        int week = mCalendar.get(Calendar.DAY_OF_WEEK) - 1;
        int maxDay = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);//获取当月的总天数
        maxDay = Math.min(maxDay, mToday);
        int today = x + (y - 1) * 7 - week + 1;
        if (today > 0 && today <= maxDay) {
            Calendar c = Calendar.getInstance();
            c.setTime(mCalendar.getTime());
            c.set(Calendar.DAY_OF_MONTH, today);

            d = c.getTime();
        }


        return d;
    }

    public void setTextSize(int textSize) {
        mTextSize = textSize;
        mWeekPaint.setTextSize(mTextSize);
        mDayPaint.setTextSize(mTextSize);

        requestLayout();
    }

    public void setDate(int year, int month) {
        Log.d(TAG, "setDate() called with " + "year = [" + year + "], month = [" + month + "]");

        mYear = year;
        mMonth = month;

        mCalendar = Calendar.getInstance();
        //test if is today
        if (mCalendar.get(Calendar.YEAR) == mYear && mCalendar.get(Calendar.MONTH) == mMonth) {
            mToday = mCalendar.get(Calendar.DAY_OF_MONTH);
        } else {
            mToday = 40;
        }
        mCalendar.set(Calendar.YEAR, mYear);
        mCalendar.set(Calendar.MONTH, mMonth);
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);

        invalidate();
    }


    public void setOnDaySelectedListener(OnDaySelectedListener listener) {
        mListener = listener;
    }

    //设置选中的日期
    public void setSelectedDate(Date selectedDate) {
        if (selectedDate != null)
            this.mSelectedDate.setTime(selectedDate.getTime());
    }


    public interface OnDaySelectedListener {
        void onDaySelected(Date date);
    }
}
