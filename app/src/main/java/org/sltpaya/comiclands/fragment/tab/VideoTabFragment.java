package org.sltpaya.comiclands.fragment.tab;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.tab.VideoAdapter;
import org.sltpaya.comiclands.observer.NetListener;
import org.sltpaya.comiclands.observer.NetObserver;
import org.sltpaya.comiclands.net.VideoHttp;
import org.sltpaya.comiclands.net.entry.VideoEntry;
import org.sltpaya.tool.Toast;

import retrofit2.Call;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class VideoTabFragment extends BaseTabFragment<VideoEntry> {

    @Override
    protected void setRecyclerAttribute() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new VideoAdapter(getActivity());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void requestNetWork() {
        VideoHttp.requestVideoJson();/*联网请求*/
    }

    @Override
    protected void processNetEvent() {
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
