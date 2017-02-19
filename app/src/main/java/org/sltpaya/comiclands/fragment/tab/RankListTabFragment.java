package org.sltpaya.comiclands.fragment.tab;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
public class RankListTabFragment extends BaseTabFragment<RankListEntry> {

    private final String TAG = "RankListTabFragment";
    private boolean DEBUG = true;

    @Override
    protected void setRecyclerAttribute() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RankListAdapter(getActivity());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void requestNetWork() {
        RankListHttp.requestRankListJson();/*联网请求*/
    }

    @Override
    protected void processNetEvent() {
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
