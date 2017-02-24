package org.sltpaya.comiclands.holder.state;

import android.view.View;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.holder.recommend.ComicHorizontalHolder;

import static org.sltpaya.comiclands.consts.Consts.COMIC_TYPE_FIRST;
import static org.sltpaya.comiclands.consts.Consts.COMIC_TYPE_ONE;

/**
 * Author: SLTPAYA
 * Date: 2017/2/23
 */
 class BannerState extends BaseState {

    @Override
    public void work(HolderManager manager) {
        super.work(manager);
        if (mType == COMIC_TYPE_ONE || mType == COMIC_TYPE_FIRST) {
            View inflate = inflate(R.layout.recommend_type_banner);
            ComicHorizontalHolder holder = new ComicHorizontalHolder(inflate);
            manager.setHolder(holder);
            return;
        }
        new PreviewState().work(manager);
    }

}
