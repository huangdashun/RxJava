package com.example.bong.rxjava.rxandroiddemo2;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hs on 2017/2/24.
 */

public class LoginUtils {
    static OkHttpClient okHttpClient;

    public LoginUtils() {
        okHttpClient = new OkHttpClient();
    }

    public static Observable<String> login(String path, Map<String, String> params) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    FormBody.Builder builder = new FormBody.Builder();
                    if (params != null && !params.isEmpty()) {
                        for (Map.Entry<String, String> param : params.entrySet()) {

                            builder.add(param.getKey(), param.getValue());
                        }
                    }

                    RequestBody body = builder.build();
                    Request requset = new Request.Builder().url(path).post(body).build();
                    okHttpClient.newCall(requset).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                subscriber.onNext(response.body().string());
                                subscriber.onCompleted();
                            }
                        }
                    });
                }

            }
        });
    }
}
