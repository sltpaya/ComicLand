package org.sltpaya.comiclands.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.holder.FragmentRecyclerHolder;

/**
 * Author: SLTPAYA
 * Date: 2017/2/11
 */
public class FragmentRecyclerAdapter extends RecyclerView.Adapter{

    private LayoutInflater mInflater;

    public FragmentRecyclerAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mInflater.inflate(R.layout.recycler_item, parent, false);
        return new FragmentRecyclerHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
