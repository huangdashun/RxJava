package com.example.bong.rxjava.rxjava;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hs on 2017/2/24.
 * 被观察者的具体实现类
 */

public class WatchedImp implements Watched {
    private List<Watcher> list = new ArrayList<>();

    @Override
    public void addWatcher(Watcher watcher) {
        list.add(watcher);
    }

    @Override
    public void removedWatcher(Watcher watcher) {
        list.remove(watcher);
    }

    @Override
    public void notificationWatcher(String str) {
        for (Watcher watcher : list) {
            watcher.UpdateMsg(str);
        }
    }
}
