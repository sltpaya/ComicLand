package org.sltpaya.comiclands.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.net.entry.BannerEntry;

import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/17
 */
public class BannerPagerAdapter extends PagerAdapter {

    private List<BannerEntry.Adlistjson> list;

    public BannerPagerAdapter(List<BannerEntry.Adlistjson> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        position %= list.size();

        LayoutInflater inflater = (LayoutInflater) container.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.banner_content, container, false);
        ImageView img = (ImageView) item.findViewById(R.id.banner_img);

        String imgUrl = list.get(position).getImageurl();
        Picasso.with(container.getContext()).load(imgUrl)
                .error(R.drawable.loading_bookrack).into(img);

        container.addView(item);
        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
    }


}
