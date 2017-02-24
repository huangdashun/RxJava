package com.example.bong.rxjava.rxjava;

/**
 * Created by hs on 2017/2/24.
 * 被观察者的接口
 */

public interface Watched {
    //添加观察者
    void addWatcher(Watcher watcher);

    //移除观察者
    void removedWatcher(Watcher watcher);

    //通知观察者
    void notificationWatcher(String str);
}
