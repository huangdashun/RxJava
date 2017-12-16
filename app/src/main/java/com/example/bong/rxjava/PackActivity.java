package com.example.bong.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.meituan.android.walle.WalleChannelReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PackActivity extends AppCompatActivity {

    @BindView(R.id.tv_envType)
    TextView mTvEnvType;
    @BindView(R.id.tv_channel)
    TextView mChannel;
    @BindView(R.id.tv_package)
    TextView mPackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pack);
        ButterKnife.bind(this);
        String channel = WalleChannelReader.getChannel(getApplicationContext());
        int envType = BuildConfig.ENV_TYPE;
        String packageName = getPackageName();

        if (envType == 1) {
            mTvEnvType.setText("开发环境");
        } else if (envType == 2) {
            mTvEnvType.setText("测试环境");
        } else if (envType == 3) {
            mTvEnvType.setText("正式环境");
        }
        mChannel.setText(channel);
        mPackage.setText(packageName);
    }
}
