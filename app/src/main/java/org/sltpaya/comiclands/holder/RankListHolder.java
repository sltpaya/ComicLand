package org.sltpaya.comiclands.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.net.entry.RankListEntry;

/**
 * Author: SLTPAYA
 * Date: 2017/2/17
 */
public class RankListHolder extends RecyclerView.ViewHolder {

    public RankListHolder(View itemView) {
        super(itemView);
    }

    public void setData(RankListEntry.Special special) {
        ImageView img = (ImageView) itemView.findViewById(R.id.rank_list_img);
        TextView title = (TextView) itemView.findViewById(R.id.rank_list_title);
        TextView des = (TextView) itemView.findViewById(R.id.rank_list_des);

        String imgUrl = special.getCoverurl();
        String name = special.getName();
        String description = special.getDescription();
        title.setText(name);
        des.setText(description);

        Picasso.with(itemView.getContext()).load(imgUrl)
                .error(R.drawable.loading_bookrack).into(img);
    }

}
