package org.sltpaya.comiclands.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.consts.Consts;
import org.sltpaya.comiclands.holder.BannerHolder;
import org.sltpaya.comiclands.holder.VideoHolder;
import org.sltpaya.comiclands.net.entry.VideoEntry;

import java.util.Iterator;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class VideoAdapter extends TabAdapter<VideoEntry> {

    private final LayoutInflater mInflater;
    private List<VideoEntry.Info> infos;

    private final int BANNER_TYPE = 1;
    private final int NORMAL_TYPE = 2;

    public VideoAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void notifyDataChanged(VideoEntry entry) {
        infos = entry.getInfo();
        checkedData();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == BANNER_TYPE) {
            view = mInflater.inflate(R.layout.item_banner, parent, false);
            return new BannerHolder(view);
        }
        if (viewType == NORMAL_TYPE) {
            View inflate = mInflater.inflate(R.layout.group_video, parent, false);
            return new VideoHolder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == BANNER_TYPE) {
            ((BannerHolder)holder).setBannerData(true, Consts.VIDEO_LIST_ID);
        }else {
            ((VideoHolder)holder).setData(infos.get(position - 1));
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
        return infos == null ? 0 : infos.size() + 1;
    }

    /**
     * 检查数据完整性
     */
    private void checkedData() {
        System.out.println("数据之前的大小为："+infos.size());
        Iterator<VideoEntry.Info> iterator = infos.iterator();
        while (iterator.hasNext()) {
            VideoEntry.Info info = iterator.next();
            if (info != null && info.getVediolist() == null) {
                iterator.remove();
            }
        }
        System.out.println("经过严格检查，现在的大小为："+infos.size());
    }
}
