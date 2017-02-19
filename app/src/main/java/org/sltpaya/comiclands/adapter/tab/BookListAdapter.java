package org.sltpaya.comiclands.adapter.tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.holder.BookListHolder;
import org.sltpaya.comiclands.net.entry.BookListEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
//TODO:没有解决轮播图的问题，打算设置头布局来实现
public class BookListAdapter extends TabAdapter<BookListEntry> {

    private final int BANNER_TYPE = 1;
    private final int NORMAL_TYPE = 2;
    private LayoutInflater mInflater;
    private List<BookListEntry.Special> special;

    public BookListAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * 通知数据改变
     *
     * @param entry RecommendEntry
     */
    @Override
    public void notifyDataChanged(BookListEntry entry) {
        if (entry != null) {
            this.special = entry.getInfo().getSpecials();
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
//        if (viewType == BANNER_TYPE) {
//            view = mInflater.inflate(R.layout.banner_layout, parent, false);
//            return new BannerHolder(view);
//        }
        if (viewType == NORMAL_TYPE) {
            view = mInflater.inflate(R.layout.item_book_list, parent, false);
            return new BookListHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (holder instanceof BannerHolder) {
//            ((BannerHolder)holder).setBannerData(BannerHolder.BOOK_LIST_ID);
//            return;
//        }
        if (holder instanceof BookListHolder) {
            ((BookListHolder) holder).setData(special.get(position));
        }
    }


    @Override
    public int getItemViewType(int position) {
//        if (position == 0) {
//            return BANNER_TYPE;
//        }
        return NORMAL_TYPE;
    }

    @Override
    public int getItemCount() {
        return special == null ? 0 : special.size();
    }
}
