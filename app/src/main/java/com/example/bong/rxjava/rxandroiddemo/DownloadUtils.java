package com.example.bong.rxjava.rxandroiddemo;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by hs on 2017/2/24.
 */

public class DownloadUtils {
    private OkHttpClient client;

    public DownloadUtils() {
        client = new OkHttpClient();
    }

    public Observable<byte[]> downloadImage(String path) {
      return  Observable.create(new Observable.OnSubscribe<byte[]>() {
            @Override
            public void call(Subscriber<? super byte[]> subscriber) {
                if (!subscriber.isUnsubscribed()) {

                    Request request = new Request.Builder().url(path).build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            subscriber.onError(e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful()) {
                                byte[] data = response.body().bytes();
                                if (data != null) {
                                    subscriber.onNext(data);
                                }
                                subscriber.onCompleted();
                            }
                        }
                    });
                }
            }
        });
    }
}
