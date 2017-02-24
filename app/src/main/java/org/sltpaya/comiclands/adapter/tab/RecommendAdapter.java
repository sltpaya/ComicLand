package org.sltpaya.comiclands.adapter.tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.consts.Consts;
import org.sltpaya.comiclands.holder.recommend.AdViewHolder;
import org.sltpaya.comiclands.holder.recommend.BaseHolder;
import org.sltpaya.comiclands.holder.recommend.ComicHorizontalHolder;
import org.sltpaya.comiclands.holder.recommend.ComicVerticalHolder;
import org.sltpaya.comiclands.holder.recommend.PreviewHolder;
import org.sltpaya.comiclands.holder.recommend.VideoViewHolder;
import org.sltpaya.comiclands.holder.state.HolderManager;
import org.sltpaya.comiclands.net.entry.RecommendEntry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/13
 */
public class RecommendAdapter extends TabAdapter<RecommendEntry> {

    private final String TAG = "RecommendAdapter";
    private boolean DEBUG = true;

    private final int COMIC_TYPE_FIRST = 1;
    private final int COMIC_TYPE_SECONDE = 2;
    private final int COMIC_TYPE_ONE = 3;
    private final int COMIC_TYPE_TWO = 4;
    private final int AD_VIEW = 5;
    private final int VIDEO_VIEW = 6;

    private LayoutInflater mInflater;
    private List<RecommendEntry.Info> info;

    public RecommendAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    /**
     * 通知数据改变
     *
     * @param entry RecommendEntry
     */
    @Override
    public void notifyDataChanged(RecommendEntry entry) {
        if (entry != null) {
            this.info = entry.getInfo();
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HolderManager manager = new HolderManager(viewType, parent, mInflater);
        manager.work();
        return manager.getHolder();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ComicHorizontalHolder) {
            ComicHorizontalHolder holder1 = (ComicHorizontalHolder) holder;
            if (getItemViewType(position) == COMIC_TYPE_ONE) {
                holder1.setData(false, 0, info.get(position));
            } else {
                holder1.setData(true, Consts.RECOMMEND_ID, info.get(position));
            }
            return;
        }
        ((BaseHolder)holder).setData(info.get(position));
    }

    @Override
    public int getItemCount() {
        return info == null ? 0 : info.size();
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 0;

        if (position == 0) {
            return COMIC_TYPE_FIRST;
        } else if (position == 1) {
            return COMIC_TYPE_SECONDE;
        }

        RecommendEntry.Info info = this.info.get(position);
        int comicsViewtype = info.getComicsviewtype();
        int videoViewtype = info.getVedioviewtype();
        int adViewtype = info.getAdviewtype();
        int reviewtype = info.getReviewtype();

        if (comicsViewtype == 1) {
            viewType = COMIC_TYPE_ONE;
        } else if (comicsViewtype == 2) {
            viewType = COMIC_TYPE_TWO;
        } else if (adViewtype == 2 || adViewtype == 3 || comicsViewtype == 3) {
            return AD_VIEW;
        } else if (reviewtype == 1) {
            return AD_VIEW;//return COMIC_TYPE_ONE;
        } else if (videoViewtype == 1) {
            return VIDEO_VIEW;
        }

        return viewType;
    }

    @SuppressWarnings("unused")
    private void logger(String des) {
        if (DEBUG) {
            Log.d(TAG, des);
        }
    }

}
