package org.sltpaya.comiclands.fragment.tab;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.FragmentRecyclerAdapter;

/**
 * Author: SLTPAYA
 * Date: 2017/2/11
 */
public class TabFragment extends Fragment {

    @LayoutRes
    private int mLayoutId;
    private View mRootView;
    public static final String LAYOUT_ID = "layout_id";

    @Override
    public void setArguments(Bundle args) {
        this.mLayoutId = args.getInt(LAYOUT_ID, -1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mLayoutId != -1) {
            mRootView = inflater.inflate(mLayoutId, container, false);
            init();
            return mRootView;
        }
        return null;
    }

    private void init() {
        RecyclerView recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new FragmentRecyclerAdapter(getActivity()));
    }

}
