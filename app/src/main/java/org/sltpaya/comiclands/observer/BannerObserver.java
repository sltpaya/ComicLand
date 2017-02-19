package org.sltpaya.comiclands.observer;

import android.util.Log;

import org.sltpaya.comiclands.net.entry.BannerEntry;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class BannerObserver implements BannerListener {

    private Map<Integer, BannerListener> observer = new HashMap<>();

    @Override
    public void onResponseSuccessed(BannerEntry entry, int groupId) {
        BannerListener listener = observer.get(groupId);
        if (listener != null) {
            logger("不为空，正在开始回调："+groupId+"监听器为："+listener);
            listener.onResponseSuccessed(entry, groupId);
            return;
        }
        logger("为空，不回调："+groupId);
    }

    @Override
    public void onFailed(int groupId) {
        BannerListener listener = observer.get(groupId);
        if (listener != null) {
            listener.onFailed(groupId);
        }
    }

    public void registerNetObserver(BannerListener listener,int groupId) {
        if (!observer.containsKey(groupId)) {
            logger("监听添加"+listener);
            observer.put(groupId, listener);
        }
    }

    public void unRegisterNetObserver(BannerListener listener,int groupId) {
        if (observer.containsKey(groupId)) {
            observer.remove(groupId);
        }
    }

    public void clearRegisterNetObserver() {
       observer.clear();
    }

    private void logger(String des) {
        Log.d("BannerObserver", des);
    }

}
