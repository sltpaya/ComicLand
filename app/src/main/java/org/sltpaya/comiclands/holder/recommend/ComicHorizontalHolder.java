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
import java.util.regex.Pattern;

/**
 * Author: SLTPAYA
 * Date: 2017/2/14
 */
class ComicHorizontalHolder extends BaseHolder<RecommendEntry.Comicslist> {

    private ArrayList<View> mItems;

    ComicHorizontalHolder(View itemView) {
        super(itemView);
        initHolderView();
    }

    protected void initHolderView() {
        mItems = new ArrayList<>();
        int[] layoutIds = {
                R.id.item_comicview_one_1,
                R.id.item_comicview_one_2,
                R.id.item_comicview_one_3,
                R.id.item_comicview_one_4
        };
        for (int id : layoutIds) {
            View tmpView = itemView.findViewById(id);
            mItems.add(tmpView);
        }
    }


    @Override
    public void setData(RecommendEntry.Info infoEntry) {
        String iconUrl = infoEntry.getIcon();
        String titleName = infoEntry.getName();
        mColumnTitle.setText(titleName);
        Picasso.with(itemView.getContext()).load(iconUrl)
                .error(R.drawable.bookshelf_hot).into(mColumnIcon);

        List<RecommendEntry.Comicslist> comicslist = infoEntry.getComicslist();
        for (int i = 0; i < mItems.size(); i++) {
            processData(mItems.get(i), comicslist.get(i));
        }
    }

    @Override
    public void processData(final View view, RecommendEntry.Comicslist comic) {
        ImageView imgDes = (ImageView) view.findViewById(R.id.comicview_one_img);
        TextView comicTitle = (TextView) view.findViewById(R.id.comicview_one_title);
        TextView comicDes = (TextView) view.findViewById(R.id.comicview_one_des);
        TextView comicViewCount = (TextView) view.findViewById(R.id.comicview_one_viewcount);

        String bigbookName = comic.getBigbookName();
        String lastpartname = comic.getLastpartname();
        int bigbookview = comic.getBigbookview();
        comicTitle.setText(bigbookName);
        comicDes.setText(String.format("更新至%s", lastpartname));
        comicViewCount.setText(formatViewCount(bigbookview));

        final String extensionUrl = formatExtensionUrl(comic.getExtension());
        if (!extensionUrl.isEmpty()) {
            Picasso.with(itemView.getContext()).load(extensionUrl)
                    .error(R.drawable.loading_bookrack).into(imgDes);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("设置URL："+extensionUrl+"本View为："+v);
                Toast.makeText(view.getContext(), "设置URL："+extensionUrl,Toast.LENGTH_LONG).show();
            }
        });
    }

    private String formatExtensionUrl(String extension) {
        String all = extension.replaceAll("\\\\", "");
        String[] split = all.split(",");
        Pattern pattern = Pattern.compile("scfk344_202");
        String extensionUrl = null;
        String tmp;
        for (String aSplit : split) {
            tmp = aSplit;
            boolean b = pattern.matcher(tmp).find();
            if (b) {
                extensionUrl = tmp.replace("\"", "")
                        .replace("scfk344_202:", " ")
                        .replaceAll("[\\{,\\}]", "")
                        .replaceAll(" ", "");
            }
        }
        return extensionUrl;
    }

}
