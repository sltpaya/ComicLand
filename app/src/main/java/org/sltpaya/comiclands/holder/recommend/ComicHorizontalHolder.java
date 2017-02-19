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
public class ComicHorizontalHolder extends BaseHolder<RecommendEntry.Comicslist> {

    private ArrayList<View> mItems;

    public ComicHorizontalHolder(View itemView) {
        super(itemView);
        initHolderView();
    }

    protected void initHolderView() {
        mItems = new ArrayList<>();
        //从布局文件中重复的获取，优化办法，通过代码重复填充成新布局
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
