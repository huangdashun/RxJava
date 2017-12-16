package com.example.bong.rxjava.customview.datapick;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.bong.rxjava.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author rqg
 * @date 10/29/15.
 */
public class DatePickFragment extends DialogFragment {
    private static final String TAG = "DatePickFragment";
    @BindView(R.id.date_bar_left)
    IconTextView mDateBarLeft;//上个月
    @BindView(R.id.date_bar_right)
    IconTextView mDateBarRight;//下个月
    @BindView(R.id.date_bar_text)
    TextView mDateBarText;//日期
    @BindView(R.id.date_bar)
    RelativeLayout mDateBar;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;
    @BindView(R.id.empty)
    View mEmpty;


    private DatePickActionListener mListener;

    private Date mSelectedDate;
    private SimpleDateFormat mMonthSdf;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_pick, container, false);
        // TODO: 10/29/15 setBackgroundRes not work ????? why????
        mMonthSdf = new SimpleDateFormat(getString(R.string.month_sdf_new), Locale.getDefault());

        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mViewpager.setAdapter(new CalendarPagerAdapter());
        mViewpager.getLayoutParams().height = measureViewPagerHeight(getContext());//设置viewpager的高度为cardView的高度
        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                onChangeMonth(mViewpager.getCurrentItem() - TimeLineFragment.MAX_BONG_DAY_PAGE_NUMBER + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mDateBarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextMonth();
            }
        });


        mDateBarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousMonth();
            }
        });


        // TODO: 10/29/15 setBackgroundRes not work ????? why????

        mViewpager.setBackgroundColor(getResources().getColor(R.color.common_bg));


        mEmpty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onEmptyClick();
            }
        });


    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
//        dialog.setTitle(R.string.found_new_version);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return dialog;
    }

    @Override
    public void onResume() {

        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            Point size = new Point();
            // Store dimensions of the screen in `size`
            Display display = window.getWindowManager().getDefaultDisplay();
            display.getSize(size);
            // Set the width of the dialog proportional to 75% of the screen width
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setWindowAnimations(R.style.DateSelectAnimation);

            window.setGravity(Gravity.CENTER);
        }

        if (mSelectedDate != null) {
            Calendar calendar = Calendar.getInstance();
            int cMonth = calendar.get(Calendar.MONTH);
            int cYear = calendar.get(Calendar.YEAR);

            calendar.setTime(mSelectedDate);

            int sMonth = calendar.get(Calendar.MONTH);
            int sYear = calendar.get(Calendar.YEAR);

            Log.d(TAG, "onResume diff:" + ((cYear - sYear) * 12 + cMonth - sMonth));
            mViewpager.setCurrentItem(mViewpager.getAdapter().getCount() - ((cYear - sYear) * 12 + cMonth - sMonth) - 1, false);

            Log.d(TAG, "onActivityCreated curr:" + mViewpager.getCurrentItem() + " o:" + mViewpager);
        } else {
            mViewpager.setCurrentItem(mViewpager.getAdapter().getCount() - 1, false);
        }


        super.onResume();
    }

    /**
     * 测量viewpager的高度
     *
     * @param context
     * @return
     */
    private int measureViewPagerHeight(Context context) {
        // TODO: 10/28/15 　暂行方案，寻找其他解决方案
        CalendarView calendarView = new CalendarView(context);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        return calendarView.getDatePickViewHeight(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }


    public void setDatePickActionListener(DatePickActionListener listener) {
        mListener = listener;
    }

    public void setSelectedDate(Date date) {
        mSelectedDate = date;
    }

    private CalendarView.OnDaySelectedListener mDaySelectedListener = new CalendarView.OnDaySelectedListener() {
        @Override
        public void onDaySelected(Date date) {
            if (mListener != null)
                mListener.onDateSelected(date);
        }
    };


    /**
     * @return 当前是否是第一页
     */
    private void nextMonth() {
        mViewpager.setCurrentItem(mViewpager.getCurrentItem() - 1, true);
    }

    /**
     * @return 当前是否是第一页
     */
    private void previousMonth() {
        mViewpager.setCurrentItem(mViewpager.getCurrentItem() + 1, true);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private class CalendarPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return TimeLineFragment.MAX_BONG_DAY_PAGE_NUMBER;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            CalendarView v = getItem(container.getContext(), position);

            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -TimeLineFragment.MAX_BONG_DAY_PAGE_NUMBER + position + 1);
            v.setDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH));
            v.setOnDaySelectedListener(mDaySelectedListener);
            v.setSelectedDate(mSelectedDate);
            container.addView(v);
            return v;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
        }

        @Override
        public void finishUpdate(ViewGroup container) {
        }

        @Override
        public Parcelable saveState() {
            return super.saveState();
        }

        @Override
        public void restoreState(Parcelable state, ClassLoader loader) {
            super.restoreState(state, loader);
        }

        private CalendarView getItem(Context context, int position) {
            CalendarView calendarView = new CalendarView(context);

            return calendarView;
        }
    }

    public interface DatePickActionListener {
        void onEmptyClick();

        void onDateSelected(Date date);

    }

    void onChangeMonth(int monthOffset) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, monthOffset);
        mDateBarText.setText(mMonthSdf.format(c.getTime()));

        if (mViewpager.getCurrentItem() == 0) {
            mDateBarLeft.setVisibility(View.INVISIBLE);
        } else {
            mDateBarLeft.setVisibility(View.VISIBLE);
        }


        if (mViewpager.getCurrentItem() == mViewpager.getAdapter().getCount() - 1) {
            mDateBarRight.setVisibility(View.INVISIBLE);
        } else {
            mDateBarRight.setVisibility(View.VISIBLE);
        }
    }


}
