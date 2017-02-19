package org.sltpaya.comiclands.holder.recommend;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.holder.*;
import org.sltpaya.comiclands.net.entry.RecommendEntry;
import org.sltpaya.tool.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Author: SLTPAYA
 * Date: 2017/2/14
 */
public class ComicHorizontalHolder extends org.sltpaya.comiclands.holder.BannerHolder {

    private ArrayList<View> mItems;
    private TextView mColumnTitle;
    private ImageView mColumnIcon;

    public ComicHorizontalHolder(View itemView) {
        super(itemView);
        initTitleView();
    }

    private void initTitleView() {
        mColumnTitle = (TextView) itemView.findViewById(R.id.item_title_text);
        mColumnIcon = (ImageView) itemView.findViewById(R.id.item_title_img);
        View columnMore = itemView.findViewById(R.id.item_title_more);
        columnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "将会加载更多哦！", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
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

    public void setData(boolean bannerFlag, int groupId,RecommendEntry.Info infoEntry) {
        super.setBannerData(bannerFlag, groupId);
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

    private void processData(final View view, RecommendEntry.Comicslist comic) {
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

    /**
     * 格式化观看人数
     */
    private String formatViewCount(int count) {
        int length = 0;
        int tmp = count;
        while (tmp > 0) {
            tmp = tmp / 10;
            length++;
        }
        if (length > 8) {
            double oFloat = ((count / 10000000) / 10.0);
            System.out.println(oFloat);
            return oFloat + "亿";
        } else if (length > 4) {
            double oFloat = ((count / 1000) / 10.0);
            return oFloat + "万";
        } else if (length > 3) {
            double oFloat = ((count / 100) / 10.0);
            return oFloat + "千";
        }
        return null;
    }

}
