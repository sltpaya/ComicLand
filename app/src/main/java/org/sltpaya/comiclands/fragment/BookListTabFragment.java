package org.sltpaya.comiclands.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.BookListAdapter;
import org.sltpaya.comiclands.net.BannerHttp;
import org.sltpaya.comiclands.net.BookListHttp;
import org.sltpaya.comiclands.net.NetListener;
import org.sltpaya.comiclands.net.NetObserver;
import org.sltpaya.comiclands.net.entry.BannerEntry;
import org.sltpaya.comiclands.net.entry.BookListEntry;
import org.sltpaya.tool.Toast;
import org.sltpaya.tool.Utils;

import retrofit2.Call;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class BookListTabFragment extends Fragment {

    private View mRootView;
    private BookListAdapter adapter;
    private BookListEntry mEntry;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.layout_recycler, container, false);
        init();
        return mRootView;
    }

    private void init() {
        RecyclerView recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);
        recyclerView.setPadding(0, Utils.dp2px(10), 0, 0);/*设置recyclerView的上padding*/
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new BookListAdapter(getContext());
        recyclerView.setAdapter(adapter);
        if (mEntry == null) {
            BookListHttp.requestBookListJson();/*请求网络数据--书单*/
            processNetEvent();
            return;
        }
        adapter.notifyDataChanged(mEntry);
    }

    private void processNetEvent() {
        NetObserver<BookListEntry> observer = BookListHttp.getBookListObserver();
        observer.registerNetObserver(new NetListener<BookListEntry>() {
            @Override
            public void onResponseSuccessed(BookListEntry entry) {
                mEntry = entry;
                adapter.notifyDataChanged(entry);
            }

            @Override
            public void onFailed(Call<BookListEntry> call, Throwable t) {
                Toast.makeText(getContext(), "书单联网失败了...", Toast.LENGTH_LONG).show();
            }
        });

    }

}
