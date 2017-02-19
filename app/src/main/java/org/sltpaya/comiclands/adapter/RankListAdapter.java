package org.sltpaya.comiclands.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.holder.ranklist.BannerHolder;
import org.sltpaya.comiclands.holder.ranklist.RankListHolder;
import org.sltpaya.comiclands.net.BannerHttp;
import org.sltpaya.comiclands.net.NetListener;
import org.sltpaya.comiclands.net.NetObserver;
import org.sltpaya.comiclands.net.entry.BannerEntry;
import org.sltpaya.comiclands.net.entry.RankListEntry;
import org.sltpaya.comiclands.net.entry.RecommendEntry;

import java.util.List;

import retrofit2.Call;

/**
 * Author: SLTPAYA
 * Date: 2017/2/17
 */
public class RankListAdapter extends RecyclerView.Adapter {

    private LayoutInflater mInflater;
    private List<RankListEntry.Special> special;

    private final int BANNER_TYPE = 1;
    private final int NORMAL_TYPE = 2;

    public RankListAdapter(Context context) {
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 通知数据改变
     * @param entry RecommendEntry
     */
    public void notifyDataChanged(RankListEntry entry) {
        if (entry != null) {
            this.special = entry.getInfo().getSpecials();
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == BANNER_TYPE) {
            view = mInflater.inflate(R.layout.banner_layout, parent, false);
            return new BannerHolder(view);
        }
        if (viewType == NORMAL_TYPE) {
            view = mInflater.inflate(R.layout.item_rank_list, parent, false);
            return new RankListHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerHolder) {
            System.out.println("设置中！");
            ((BannerHolder)holder).setData(BannerHolder.RANK_LIST_ID);
            return;
        }
        if (holder instanceof RankListHolder) {
            ((RankListHolder)holder).setData(special.get(position-1));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER_TYPE;
        }
        return NORMAL_TYPE;
    }

    @Override
    public int getItemCount() {
        return special == null ? 0 : special.size() + 1;
    }


}
