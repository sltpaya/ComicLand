package org.sltpaya.comiclands.holder.state;

import android.view.View;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.holder.recommend.ComicVerticalHolder;

import static org.sltpaya.comiclands.consts.Consts.COMIC_TYPE_TWO;

/**
 * Author: SLTPAYA
 * Date: 2017/2/24
 */
 class ComicVerticalState extends BaseState {

    @Override
    public void work(HolderManager manager) {
        super.work(manager);
        if (mType == COMIC_TYPE_TWO) {
            View inflate = inflate(R.layout.group_type_two);
            ComicVerticalHolder holder = new ComicVerticalHolder(inflate);
            manager.setHolder(holder);
            return;
        }
        new AdViewState().work(manager);
    }

}
