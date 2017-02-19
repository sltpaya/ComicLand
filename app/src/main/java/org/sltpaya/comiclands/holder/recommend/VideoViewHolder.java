package org.sltpaya.comiclands.holder.recommend;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.net.entry.RecommendEntry;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/14
 */
public class VideoViewHolder extends BaseHolder<RecommendEntry.Vediolist> {

    private ArrayList<View> mItems;

    public VideoViewHolder(View itemView) {
        super(itemView);
        initHolderView();
    }

    private void initHolderView() {
        mItems = new ArrayList<>();
        View mItem_one = itemView.findViewById(R.id.item_videoview_one_1);
        View mItem_two = itemView.findViewById(R.id.item_videoview_one_2);
        View mItem_three = itemView.findViewById(R.id.item_videoview_one_3);
        View mItem_four = itemView.findViewById(R.id.item_videoview_one_4);
        mItems.add(mItem_one);
        mItems.add(mItem_two);
        mItems.add(mItem_three);
        mItems.add(mItem_four);
    }

    @Override
    public void setData(RecommendEntry.Info infoEntry) {
        String iconUrl = infoEntry.getIcon();
        String titleName = infoEntry.getName();
        List<RecommendEntry.Vediolist> comicslist = infoEntry.getVediolist();
        mColumnTitle.setText(titleName);

        Picasso.with(itemView.getContext()).load(iconUrl)
                .error(R.drawable.bookshelf_hot).into(mColumnIcon);

        for (int i = 0; i < 4; i++) {
            processData(mItems.get(i), comicslist.get(i));
        }
    }

    @Override
    public void processData(View view, RecommendEntry.Vediolist list) {
        ImageView imgDes = (ImageView) view.findViewById(R.id.videoview_one_img);
        TextView comicTitle = (TextView) view.findViewById(R.id.videoview_one_title);

        String bigcoverurl = list.getCoverhorizontal().replace("\t", "");
        String name = list.getName();

        imgDes.setImageResource(R.drawable.loading_bookrack);
        if (!bigcoverurl.isEmpty()) {
            Picasso.with(itemView.getContext()).load(bigcoverurl).into(imgDes);
        }
        comicTitle.setText(name);
    }

}
