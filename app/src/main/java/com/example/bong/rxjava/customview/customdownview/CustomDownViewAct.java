package com.example.bong.rxjava.customview.customdownview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.bong.rxjava.R;
import com.example.bong.rxjava.customview.view.CircleLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomDownViewAct extends AppCompatActivity {

    @BindView(R.id.view1)
    CountDownView mView1;
    @BindView(R.id.view2)
    CountDownView mView2;
    @BindView(R.id.view3)
    CountDownView mView3;
    @BindView(R.id.view4)
    CountDownView mView4;
    @BindView(R.id.circle_layout)
    CircleLayout mCircleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_down_view);
        ButterKnife.bind(this);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView1.start();
                mView2.start();
                mView3.start();
                mView4.start();
            }
        });

        mView1.setLoadingFinishListener(new CountDownView.LoadingFinishListener() {
            @Override
            public void finish() {
                Toast.makeText(getApplicationContext(), "动画结束", Toast.LENGTH_SHORT).show();
            }
        });
        mCircleLayout.setOnCircleLayoutClickListener(new CircleLayout.CircleLayoutClickListener() {
            @Override
            public void onCircleLayoutClickListener() {
                Toast.makeText(getApplicationContext(), "你点击了我", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
