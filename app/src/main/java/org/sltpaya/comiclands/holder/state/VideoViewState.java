package org.sltpaya.comiclands.holder.state;

import android.view.View;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.holder.recommend.VideoViewHolder;
import static org.sltpaya.comiclands.consts.Consts.VIDEO_VIEW;

/**
 * Author: SLTPAYA
 * Date: 2017/2/24
 */
 class VideoViewState extends BaseState {

    @Override
    public void work(HolderManager manager) {
        super.work(manager);
        if (mType == VIDEO_VIEW) {
            View inflate = inflate(R.layout.group_video_one);
            VideoViewHolder holder = new VideoViewHolder(inflate);
            manager.setHolder(holder);
        }
    }

}
