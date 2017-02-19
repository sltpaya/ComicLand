package org.sltpaya.comiclands.fragment.tab;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.tab.RecommendAdapter;
import org.sltpaya.comiclands.observer.NetObserver;
import org.sltpaya.comiclands.observer.NetListener;
import org.sltpaya.comiclands.net.RecommendHttp;
import org.sltpaya.comiclands.net.entry.RecommendEntry;
import org.sltpaya.tool.Toast;

import retrofit2.Call;

/**
 * Author: SLTPAYA
 * Date: 2017/2/13
 */
public class RecommendTabFragment extends BaseTabFragment<RecommendEntry> {

    private final String TAG = "RecommendTabFragment";
    private boolean DEBUG = true;

    @Override
    protected void setRecyclerAttribute() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecommendAdapter(getActivity());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void requestNetWork() {
        RecommendHttp.requestRecommendJson();/*联网请求*/
    }

    @Override
    protected void processNetEvent() {
        NetObserver<RecommendEntry> observer = RecommendHttp.getRecommendObserver();
        observer.registerNetObserver(new NetListener<RecommendEntry>() {
            @Override
            public void onResponseSuccessed(RecommendEntry entry) {
                mEntry = entry;
                adapter.notifyDataChanged(entry);
            }

            @Override
            public void onFailed(Call<RecommendEntry> call, Throwable t) {
                Toast.makeText(getActivity(), "网络连接失败了...", Toast.LENGTH_LONG).show();
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
