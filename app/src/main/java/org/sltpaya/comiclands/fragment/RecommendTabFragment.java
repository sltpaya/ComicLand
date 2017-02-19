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
import org.sltpaya.comiclands.RecycleViewDivider;
import org.sltpaya.comiclands.adapter.RecommendAdapter;
import org.sltpaya.comiclands.net.NetObserver;
import org.sltpaya.comiclands.net.NetListener;
import org.sltpaya.comiclands.net.RecommendHttp;
import org.sltpaya.comiclands.net.entry.RecommendEntry;
import org.sltpaya.tool.Toast;

import retrofit2.Call;

/**
 * Author: SLTPAYA
 * Date: 2017/2/13
 */
public class RecommendTabFragment extends Fragment {

    private final String TAG = "RecommendTabFragment";
    private boolean DEBUG = true;

    private View mRootView;
    private RecommendAdapter adapter;
    private RecommendEntry mEntry;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.layout_recycler, container, false);
        init();
        return mRootView;
    }

    private void init() {
        adapter = new RecommendAdapter(getActivity());
        RecyclerView recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        initNetEvent();
        if (mEntry == null) {
            logger("RecommendEntry为空，正在联网请求数据....");
            RecommendHttp.requestRecommendJson();/*联网请求*/
            return;
        }
        logger("RecommendEntry不为空，正在设置数据....");
        adapter.notifyDataChanged(mEntry);
    }

    private void initNetEvent() {
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
