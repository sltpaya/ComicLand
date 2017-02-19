package org.sltpaya.comiclands.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.RankListAdapter;
import org.sltpaya.comiclands.net.NetListener;
import org.sltpaya.comiclands.net.NetObserver;
import org.sltpaya.comiclands.net.RankListHttp;
import org.sltpaya.comiclands.net.entry.RankListEntry;
import org.sltpaya.tool.Toast;

import retrofit2.Call;

/**
 * Author: SLTPAYA
 * Date: 2017/2/17
 */
public class RankListTabFragment extends Fragment {

    private final String TAG = "RankListTabFragment";
    private boolean DEBUG = true;

    private View mRootView;
    private RankListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.layout_recycler, container, false);
        init();
        return mRootView;
    }

    private void init() {
        RankListHttp.requestRankListJson();/*联网请求*/
        adapter = new RankListAdapter(getActivity());
        RecyclerView recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        initNetEvent();
    }

    private void initNetEvent() {
        NetObserver<RankListEntry> observer = RankListHttp.getRankListObserver();
        observer.registerNetObserver(new NetListener<RankListEntry>() {
            @Override
            public void onResponseSuccessed(RankListEntry entry) {
                adapter.notifyDataChanged(entry);
            }

            @Override
            public void onFailed(Call<RankListEntry> call, Throwable t) {
                Toast.makeText(getActivity(), "书单网络连接失败了...", Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressWarnings("unused")
    private void logger(String des) {
        if (DEBUG) {
            Log.d(TAG, des);
        }
    }
}
