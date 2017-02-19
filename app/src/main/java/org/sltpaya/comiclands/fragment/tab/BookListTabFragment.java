package org.sltpaya.comiclands.fragment.tab;

import android.support.v7.widget.GridLayoutManager;

import org.sltpaya.comiclands.adapter.tab.BookListAdapter;
import org.sltpaya.comiclands.net.BookListHttp;
import org.sltpaya.comiclands.observer.NetListener;
import org.sltpaya.comiclands.observer.NetObserver;
import org.sltpaya.comiclands.net.entry.BookListEntry;
import org.sltpaya.tool.Toast;
import org.sltpaya.tool.Utils;

import retrofit2.Call;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class BookListTabFragment extends BaseTabFragment<BookListEntry> {

    @Override
    protected void setRecyclerAttribute() {
        mRecyclerView.setPadding(0, Utils.dp2px(10), 0, 0);/*设置recyclerView的上padding*/
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new BookListAdapter(getContext());
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void requestNetWork() {
        BookListHttp.requestBookListJson();/*请求网络数据--书单*/
    }

    @Override
    protected void processNetEvent() {
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
