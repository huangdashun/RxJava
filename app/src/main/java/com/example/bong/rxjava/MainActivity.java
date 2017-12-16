package com.example.bong.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.bong.rxjava.customview.customdownview.CustomDownViewAct;
import com.example.bong.rxjava.customview.datapick.DatePickActivity;
import com.example.bong.rxjava.customview.view.TouchEventActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_countdown_view)
    Button mBtnCountdownView;
    @BindView(R.id.activity_main)
    LinearLayout mActivityMain;
    @BindView(R.id.btn_date_pick)
    Button mBtnDatePick;
    @BindView(R.id.btn_touch_event)
    Button mBtnTouchEvent;
    @BindView(R.id.btn_pack)
    Button mBtnPack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_countdown_view, R.id.btn_date_pick, R.id.btn_touch_event
            , R.id.btn_pack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_countdown_view:
                goActivity(CustomDownViewAct.class);
                break;
            case R.id.btn_date_pick:
                goActivity(DatePickActivity.class);
                break;
            case R.id.btn_touch_event:
                goActivity(TouchEventActivity.class);
                break;
            case R.id.btn_pack:
                goActivity(PackActivity.class);
                break;
            default:
                break;
        }
    }

    private void goActivity(Class clazz) {
        Intent intent = new Intent(MainActivity.this, clazz);
        startActivity(intent);
    }
}
