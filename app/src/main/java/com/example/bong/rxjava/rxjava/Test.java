package com.example.bong.rxjava.rxjava;

/**
 * Created by hs on 2017/2/24.
 */

public class Test {
    public static void main(String args[]) {
        //创建一个被观察者
        WatchedImp watched = new WatchedImp();
        WatcherImp xiaoming = new WatcherImp();
        WatcherImp xiaogang = new WatcherImp();
        WatcherImp xiaohuang = new WatcherImp();
        watched.addWatcher(xiaoming);
        watched.addWatcher(xiaogang);
        watched.addWatcher(xiaohuang);
        watched.notificationWatcher("我要开始偷东西了");

    }
}
