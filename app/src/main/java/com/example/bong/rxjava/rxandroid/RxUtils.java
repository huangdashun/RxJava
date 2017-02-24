package com.example.bong.rxjava.rxandroid;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hs on 2017/2/24.
 */

public class RxUtils {
    public static final String TAG = RxUtils.class.getSimpleName();

    /**
     * 第一种方式
     */
    public static void createObservable() {
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (!subscriber.isUnsubscribed()) {//如果被观察者和观察者已经订阅
                    subscriber.onNext("hello");
                    subscriber.onNext("world");
                    subscriber.onNext(downloadToHttp());
                    subscriber.onNext("over");
                    subscriber.onCompleted();
                }
            }
        });
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onNext(String s) {
                Log.i(TAG, s);
            }
        };
        observable.subscribe(subscriber);//关联被观察者
    }

    /**
     * 第二种方式
     */
    public static void createObservable2() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    for (int i = 0; i <= 10; i++) {
                        subscriber.onNext(i);
                    }
                    subscriber.onCompleted();
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError");
            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, integer + "");
            }
        });
    }

    private static String downloadToHttp() {
        return "{name:hs}";
    }

    /**
     * 使用from形式
     */
    public static void from() {
        Integer[] items = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        final int[] n = {0};
        Observable.from(items).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, n[0] + "");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                n[0]++;
                Log.i(TAG, integer.toString() + n[0]);
            }
        });
    }

    /**
     * 处理数据集合
     */
    public static void just() {
        Integer[] item1 = {1, 3, 5, 7, 9};
        Integer[] item2 = {2, 4, 6, 8, 10};
        Observable.just(item1, item2).subscribe(new Subscriber<Integer[]>() {
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer[] integers) {
                for (int i : integers) {
                    Log.i(TAG, i + "");
                }
            }
        });
    }

    /**
     * 过滤
     */
    public static void filter() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5, 6);
        observable.filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer < 4;
            }
        }).subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        Log.i(TAG, integer + "");
                    }
                });
    }

    /**
     * 定时
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void interval() {
        Observable<Long> observable = Observable.interval(1, 1, TimeUnit.SECONDS);
        observable.subscribe(new Action1<Long>() {
            @Override
            public void call(Long aLong) {
                Log.i(TAG, aLong + "");
            }
        });
    }
}
