package org.sltpaya.comiclands.observer;

import org.sltpaya.comiclands.net.entry.BannerEntry;


/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public interface BannerListener {

    void onResponseSuccessed(BannerEntry entry, int groupId);
    void onFailed(int groupId);

}
