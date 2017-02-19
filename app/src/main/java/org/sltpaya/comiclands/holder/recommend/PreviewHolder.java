package org.sltpaya.comiclands.holder.recommend;

import android.view.View;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.net.entry.RecommendEntry;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/14
 */
public class PreviewHolder extends ComicVerticalHolder {

    private ImageView bigImg;

    public PreviewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void initHolderView() {
        bigImg = (ImageView) itemView.findViewById(R.id.type_second_bigimg);
        super.initHolderView();
    }

    @Override
    public void setData(RecommendEntry.Info infoEntry) {
        String iconUrl = infoEntry.getIcon();
        String titleName = infoEntry.getName();
        List<RecommendEntry.Comicslist> comicslist = infoEntry.getComicslist();
        mColumnTitle.setText(titleName);

        Picasso.with(itemView.getContext()).load(iconUrl)
                .error(R.drawable.bookshelf_hot).into(mColumnIcon);

        //设置大图
        Picasso.with(itemView.getContext()).load(comicslist.get(0).getBigcoverurl())
                .error(R.drawable.loading_bookrack).into(bigImg);

        for (int i = 0; i < mItems.size(); i++) {
            processData(mItems.get(i), comicslist.get(i+1));
        }
    }

    @Override
    public void processData(View view, RecommendEntry.Comicslist comic) {
        super.processData(view, comic);
    }

}
