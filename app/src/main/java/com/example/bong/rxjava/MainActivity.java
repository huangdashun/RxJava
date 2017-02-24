package com.example.bong.rxjava;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.bong.rxjava.rxandroid.RxUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 第一种方法
     *
     * @param view
     */
    public void createObservable(View view) {
        RxUtils.createObservable();
    }

    /**
     * 第二种方法
     *
     * @param view
     */
    public void createObservable2(View view) {
        RxUtils.createObservable2();
    }

    /**
     * 使用from形式
     *
     * @param view
     */
    public void from(View view) {
        RxUtils.from();
    }

    /**
     * 使用just
     */
    public void just(View view) {
        RxUtils.just();
    }

    /**
     * 使用过滤
     */
    public void filter(View view) {
        RxUtils.filter();
    }
    /**
     * 使用定时
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void interval(View view){
        RxUtils.interval();
    }
}
