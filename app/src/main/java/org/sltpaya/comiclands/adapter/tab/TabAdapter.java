package org.sltpaya.comiclands.adapter.tab;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Author: SLTPAYA
 * Date: 2017/2/19
 */
public abstract class TabAdapter<N> extends RecyclerView.Adapter{

    public abstract void notifyDataChanged(N entry);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
