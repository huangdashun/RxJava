package com.example.bong.rxjava.rxjava2;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by hs on 2017/2/24.
 */

public class SimpleObserver implements Observer {
    public SimpleObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object arg) {
        System.out.println(((SimpleObservable)observable).getData());
    }
}
