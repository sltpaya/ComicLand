package org.sltpaya.comiclands.holder.state;

import android.view.View;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.holder.recommend.AdViewHolder;

import static org.sltpaya.comiclands.consts.Consts.AD_VIEW;

/**
 * Author: SLTPAYA
 * Date: 2017/2/24
 */
 class AdViewState extends BaseState {

    @Override
    public void work(HolderManager manager) {
        super.work(manager);
        if (mType == AD_VIEW) {
            View inflate = inflate(R.layout.recycler_item);
            AdViewHolder holder = new AdViewHolder(inflate);
            manager.setHolder(holder);
            return;
        }
        new VideoViewState().work(manager);
    }

}
