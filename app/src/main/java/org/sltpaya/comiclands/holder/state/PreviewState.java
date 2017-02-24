package org.sltpaya.comiclands.holder.state;

import android.view.View;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.holder.recommend.PreviewHolder;

import static org.sltpaya.comiclands.consts.Consts.COMIC_TYPE_SECONDE;

/**
 * Author: SLTPAYA
 * Date: 2017/2/24
 */
 class PreviewState extends BaseState {

    @Override
    public void work(HolderManager manager) {
        super.work(manager);
        if (mType == COMIC_TYPE_SECONDE) {
            View inflate = inflate(R.layout.recommend_type_second);
            PreviewHolder holder = new PreviewHolder(inflate);
            manager.setHolder(holder);
            return;
        }
        new ComicVerticalState().work(manager);
    }



}
