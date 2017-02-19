package org.sltpaya.comiclands.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.VideoAdapter;
import org.sltpaya.comiclands.net.BannerHttp;
import org.sltpaya.comiclands.net.NetListener;
import org.sltpaya.comiclands.net.NetObserver;
import org.sltpaya.comiclands.net.RankListHttp;
import org.sltpaya.comiclands.net.VideoHttp;
import org.sltpaya.comiclands.net.entry.BannerEntry;
import org.sltpaya.comiclands.net.entry.VideoEntry;
import org.sltpaya.tool.Toast;

import retrofit2.Call;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class VideoTabFragment extends Fragment {

    private View mRootView;
    private VideoAdapter adapter;
    private VideoEntry mEntry;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.layout_recycler, container, false);
        init();
        return mRootView;
    }

    private void init() {
        RecyclerView recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new VideoAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        if (mEntry == null) {
            System.out.println("正在请求正常数据！");
            VideoHttp.requestVideoJson();/*联网请求*/
            initNetEvent();
            return;
        }
        adapter.notifyDataChanged(mEntry);
    }

    private void initNetEvent() {

        NetObserver<VideoEntry> observer = VideoHttp.getVideoObserver();
        observer.registerNetObserver(new NetListener<VideoEntry>() {
            @Override
            public void onResponseSuccessed(VideoEntry entry) {
                if (entry.getCode() == 200) {
                    mEntry = entry;
                    adapter.notifyDataChanged(entry);
                }
            }

            @Override
            public void onFailed(Call<VideoEntry> call, Throwable t) {
                Toast.makeText(getActivity(), "动画网络连接失败了...", Toast.LENGTH_LONG).show();
            }
        });
    }

}
