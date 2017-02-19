package org.sltpaya.comiclands.holder.recommend;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.net.entry.RecommendEntry;
import org.sltpaya.tool.Toast;

/**
 * Author: SLTPAYA
 * Date: 2017/2/14
 */
public abstract class BaseHolder<N> extends RecyclerView.ViewHolder {

    TextView mColumnTitle;
    ImageView mColumnIcon;
    private final LayoutInflater mInflater;

    BaseHolder(View itemView) {
        super(itemView);
        mInflater = (LayoutInflater) itemView.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

    public abstract void setData(RecommendEntry.Info infoEntry);

    public abstract void processData(View view, N list);

    /**
     * 格式化观看人数
     */
    String formatViewCount(int count) {
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

    LayoutInflater getLayoutInflater() {
        return mInflater;
    }

}
