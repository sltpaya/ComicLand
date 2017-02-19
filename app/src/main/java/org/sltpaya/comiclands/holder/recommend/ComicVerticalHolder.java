package org.sltpaya.comiclands.holder.recommend;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.net.entry.RecommendEntry;
import org.sltpaya.tool.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/14
 */
public class ComicVerticalHolder extends BaseHolder<RecommendEntry.Comicslist> {

    ArrayList<View> mItems;

    public ComicVerticalHolder(View itemView) {
        super(itemView);
        initHolderView();
    }

    protected void initHolderView() {
        mItems = new ArrayList<>();
        System.out.println("重走了该方法了！！");
        View mItem_one = itemView.findViewById(R.id.item_comicview_two_1);
        View mItem_two = itemView.findViewById(R.id.item_comicview_two_2);
        View mItem_three = itemView.findViewById(R.id.item_comicview_two_3);
        mItems.add(mItem_one);
        mItems.add(mItem_two);
        mItems.add(mItem_three);
    }

    @Override
    public void setData(RecommendEntry.Info infoEntry) {
        String iconUrl = infoEntry.getIcon();
        String titleName = infoEntry.getName();
        List<RecommendEntry.Comicslist> comicslist = infoEntry.getComicslist();
        mColumnTitle.setText(titleName);

        Picasso.with(itemView.getContext()).load(iconUrl)
                .error(R.drawable.bookshelf_hot).into(mColumnIcon);

        System.out.println("我的大小是：" + comicslist.size());
        for (int i = 0; i < mItems.size(); i++) {
            processData(mItems.get(i), comicslist.get(i));
        }
    }

    @Override
    public void processData(final View view, RecommendEntry.Comicslist comic) {
        ImageView imgDes = (ImageView) view.findViewById(R.id.comicview_two_img);
        TextView comicTitle = (TextView) view.findViewById(R.id.comicview_two_title);

        final String coverurl = comic.getCoverurl();
        String bigbookName = comic.getBigbookName();

        Picasso.with(itemView.getContext()).load(coverurl)
                .error(R.drawable.loading_bookrack).into(imgDes);

        comicTitle.setText(bigbookName);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("设置URL："+coverurl+"本View为："+v);
                Toast.makeText(v.getContext(), "设置URL："+coverurl,Toast.LENGTH_LONG).show();
            }
        });
    }

}
