package org.sltpaya.comiclands.holder.state;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Author: SLTPAYA
 * Date: 2017/2/23
 */
public class HolderManager {

    private State state;
    private int type;
    private ViewGroup parent;
    private LayoutInflater inflater;
    private RecyclerView.ViewHolder holder;

    public HolderManager(int type, ViewGroup parent, LayoutInflater inflater) {
        this.type = type;
        this.parent = parent;
        this.inflater = inflater;
    }

    public void work() {
        BannerState state = new BannerState();
        state.work(this);
    }

    public int getType() {
        return type;
    }

    LayoutInflater getInflater() {
        return inflater;
    }

    public ViewGroup getParent() {
        return parent;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public RecyclerView.ViewHolder getHolder() {
        return holder;
    }

    public void setHolder(RecyclerView.ViewHolder holder) {
        this.holder = holder;
    }

}
