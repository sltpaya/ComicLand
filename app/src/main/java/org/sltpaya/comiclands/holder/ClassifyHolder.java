package org.sltpaya.comiclands.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.net.entry.ClassifyEntry;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class ClassifyHolder extends RecyclerView.ViewHolder {

    public ClassifyHolder(View itemView) {
        super(itemView);
    }

    public void setData(ClassifyEntry.Adlistjson adlistjson) {
        ImageView img = (ImageView) itemView.findViewById(R.id.classify_img);
        TextView title = (TextView) itemView.findViewById(R.id.classify_title);
        String name = adlistjson.getName();
        String imgUrl = adlistjson.getImageurl();

        title.setText(name);
        Picasso.with(itemView.getContext()).load(imgUrl)
                .error(R.drawable.loading_bookrack).into(img);
    }

}
