package org.sltpaya.comiclands.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.net.entry.BookListEntry;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class BookListHolder extends RecyclerView.ViewHolder {

    public BookListHolder(View itemView) {
        super(itemView);
    }

    public void setData(BookListEntry.Special special) {
        ImageView img = (ImageView) itemView.findViewById(R.id.book_list_img);
        TextView title = (TextView) itemView.findViewById(R.id.book_list_title);
        TextView count = (TextView) itemView.findViewById(R.id.book_list_count);

        int viewCount = special.getCollectcount();
        String name = special.getName();
        String imgUrl = special.getCoverurl();

        title.setText(name);
        count.setText(formatViewCount(viewCount));

        Picasso.with(itemView.getContext()).load(imgUrl)
                .error(R.drawable.loading_bookrack).into(img);
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
            return oFloat + "亿人收藏";
        } else if (length > 4) {
            double oFloat = ((count / 1000) / 10.0);
            return oFloat + "万人收藏";
        } else if (length > 3) {
            double oFloat = ((count / 100) / 10.0);
            return oFloat + "千人收藏";
        }
        return null;
    }

}
