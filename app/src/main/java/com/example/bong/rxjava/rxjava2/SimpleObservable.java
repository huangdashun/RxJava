package com.example.bong.rxjava.rxjava2;

import java.util.Observable;

/**
 * Created by hs on 2017/2/24.
 * 创建一个被观察者
 */

public class SimpleObservable extends Observable {
    private int data = 0;

    public void setData(int data) {
        if (this.data != data) {
            this.data = data;
            setChanged();//发生了改变
            notifyObservers();//通知观察者
        }
    }

    public int getData() {
        return data;
    }
}
