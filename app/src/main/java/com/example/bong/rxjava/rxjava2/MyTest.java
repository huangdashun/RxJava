package com.example.bong.rxjava.rxjava2;

/**
 * Created by hs on 2017/2/24.
 */

public class MyTest {
    public static void main(String[] args) {
        //创建一个被观察者
        SimpleObservable observable = new SimpleObservable();
        //创建一个观察者
        SimpleObserver observer = new SimpleObserver(observable);
        observable.setData(2);
        observable.setData(3);
        observable.setData(3);
        observable.setData(4);

    }
}
