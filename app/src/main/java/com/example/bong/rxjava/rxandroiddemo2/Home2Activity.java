package com.example.bong.rxjava.rxandroiddemo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.bong.rxjava.R;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

public class Home2Activity extends AppCompatActivity {
    private Button btn;
    private LoginUtils utils;
    private EditText username;
    private EditText password;
    String path = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        btn = (Button) findViewById(R.id.btn);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utils = new LoginUtils();
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username.getText().toString());
                params.put("password", password.getText().toString());
                utils.login(path, params).subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });
            }
        });
    }
}
