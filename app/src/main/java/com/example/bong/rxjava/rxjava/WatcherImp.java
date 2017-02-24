package com.example.bong.rxjava.rxjava;

/**
 * Created by hs on 2017/2/24.
 */

public class WatcherImp implements Watcher {
    @Override
    public void UpdateMsg(String str) {
        System.out.println("我抓到了小偷");
    }
}
