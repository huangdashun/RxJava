package com.example.bong.rxjava.customview.datapick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bong.rxjava.R;

import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.bong.rxjava.customview.datapick.TimeLineFragment.MAX_BONG_DAY_PAGE_NUMBER;

public class DatePickActivity extends AppCompatActivity {

    @Bind(R.id.btn_alter)
    Button mBtnAlter;
    private DatePickFragment mDatePickFragment;
    private static final String TAG = "DatePickActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_pick);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mDatePickFragment = new DatePickFragment();
        mDatePickFragment.setDatePickActionListener(new DatePickFragment.DatePickActionListener() {
            @Override
            public void onEmptyClick() {
                setDateSelectMode(false);
            }

            @Override
            public void onDateSelected(Date date) {

            }
        });


        mBtnAlter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateSelectMode(mDatePickFragment == null || !mDatePickFragment.isAdded());
            }
        });
    }

    private void setDateSelectMode(boolean isSelectDate) {
        if (isSelectDate) {

            if (!mDatePickFragment.isRemoving()) {
                Calendar calendar = Calendar.getInstance();


                calendar.add(Calendar.DAY_OF_MONTH, -MAX_BONG_DAY_PAGE_NUMBER + 1);


                mDatePickFragment.setSelectedDate(calendar.getTime());
                mDatePickFragment.show(getSupportFragmentManager(), null);


            } else {
                Log.e(TAG, "setDateSelectMode on removing");
            }

        } else {
            mDatePickFragment.dismiss();

//            //set date bar text
//            mDateBarText.setText(mDaySdf.format(
//                    new Date(
//                            System.currentTimeMillis() - (MAX_BONG_DAY_PAGE_NUMBER - mViewpager.getCurrentItem() - 1) * Constant.ONE_DAY_TIME_MILLISECONDS)
//                    )
//            );

//            mDatePickFragment = null;
            //set date bar background alpha
        }

    }
}
