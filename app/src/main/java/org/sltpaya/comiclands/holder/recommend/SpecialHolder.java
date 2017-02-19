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
public class SpecialHolder extends BaseHolder<RecommendEntry.Speciallist> {

    private ArrayList<View> mItems;

    public SpecialHolder(View itemView) {
        super(itemView);
        initHolderView();
    }

    private void initHolderView() {
        mItems = new ArrayList<>();
        View mItem_one = itemView.findViewById(R.id.item_comicview_one_1);
        View mItem_two = itemView.findViewById(R.id.item_comicview_one_2);
        View mItem_three = itemView.findViewById(R.id.item_comicview_one_3);
        View mItem_four = itemView.findViewById(R.id.item_comicview_one_4);
        mItems.add(mItem_one);
        mItems.add(mItem_two);
        mItems.add(mItem_three);
        mItems.add(mItem_four);
    }

    @Override
    public void setData(RecommendEntry.Info infoEntry) {
        String iconUrl = infoEntry.getIcon();
        String titleName = infoEntry.getName();
        List<RecommendEntry.Speciallist> speciallist = infoEntry.getSpeciallist();
        mColumnTitle.setText(titleName);

        Picasso.with(itemView.getContext()).load(iconUrl)
                .error(R.drawable.bookshelf_hot).into(mColumnIcon);

        for (int i = 0; i < 4; i++) {
            processData(mItems.get(i), speciallist.get(i));
        }
    }

    @Override
    public void processData(View view, RecommendEntry.Speciallist list) {
        ImageView imgDes = (ImageView) view.findViewById(R.id.comicview_one_img);
        TextView comicTitle = (TextView) view.findViewById(R.id.comicview_one_title);
        TextView comicDes = (TextView) view.findViewById(R.id.comicview_one_des);
        TextView comicViewCount = (TextView) view.findViewById(R.id.comicview_one_viewcount);

        comicDes.setVisibility(View.GONE);
        comicViewCount.setVisibility(View.GONE);

        String bigcoverurl = list.getCoverurl2();
        String bigbookName = list.getName();

        Picasso.with(itemView.getContext()).load(bigcoverurl)
                    .error(R.drawable.loading_bookrack).into(imgDes);

        comicTitle.setText(bigbookName);
    }
}
