package com.example.bong.rxjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.bong.rxjava.customview.customdownview.CustomDownViewAct;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_countdown_view)
    Button mBtnCountdownView;
    @Bind(R.id.activity_main)
    LinearLayout mActivityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_countdown_view})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_countdown_view:
                    goActivity(CustomDownViewAct.class);
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
