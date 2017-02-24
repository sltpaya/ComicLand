package org.sltpaya.comiclands.holder.state;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: SLTPAYA
 * Date: 2017/2/24
 */
 class BaseState implements State {

    int mType;
    private ViewGroup mParent;
    private LayoutInflater mInflater;

    @Override
    public void work(HolderManager manager) {
        manager.setState(this);
        mType = manager.getType();
        mParent = manager.getParent();
        mInflater = manager.getInflater();
    }

    View inflate(@LayoutRes int id) {
        return mInflater.inflate(id, mParent, false);
    }

}
