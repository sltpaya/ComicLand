package org.sltpaya.comiclands.fragment.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.tab.TabAdapter;

/**
 * Author: SLTPAYA
 * Date: 2017/2/19
 */
public abstract class BaseTabFragment<N> extends Fragment {

    protected RecyclerView mRecyclerView;
    protected N mEntry;
    protected TabAdapter<N> adapter;
    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.layout_recycler, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        init();
        return mRootView;
    }

    private void init() {
        setRecyclerAttribute();
        if (mEntry == null) {
            requestNetWork();
            processNetEvent();
            return;
        }
        adapter.notifyDataChanged(mEntry);
    }

    protected abstract void setRecyclerAttribute();

    protected abstract void requestNetWork();

    protected abstract void processNetEvent();

}
