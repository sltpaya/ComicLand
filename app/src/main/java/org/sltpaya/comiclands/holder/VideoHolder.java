package org.sltpaya.comiclands.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.net.entry.RecommendEntry;
import org.sltpaya.comiclands.net.entry.VideoEntry;
import org.sltpaya.tool.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: SLTPAYA
 * Date: 2017/2/18
 */
public class VideoHolder extends RecyclerView.ViewHolder {

    private ArrayList<View> mItems;
    private TextView mColumnTitle;
    private ImageView mColumnIcon;

    public VideoHolder(View itemView) {
        super(itemView);
        initHolderView();
    }

    /**
     * 获取所有的View，初始化控件
     */
    private void initHolderView() {
        mItems = new ArrayList<>();
        View mItem_one = itemView.findViewById(R.id.item_video_1);
        View mItem_two = itemView.findViewById(R.id.item_video_2);
        View mItem_three = itemView.findViewById(R.id.item_video_3);
        View mItem_four = itemView.findViewById(R.id.item_video_4);
        mColumnTitle = (TextView) itemView.findViewById(R.id.item_title_text);
        mColumnIcon = (ImageView) itemView.findViewById(R.id.item_title_img);
        View columnMore = itemView.findViewById(R.id.item_title_more);
        mItems.add(mItem_one);
        mItems.add(mItem_two);
        mItems.add(mItem_three);
        mItems.add(mItem_four);
        columnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(itemView.getContext(), "将会加载更多哦！", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setData(VideoEntry.Info info) {
        //设置标题栏
        String titleName = info.getName();
        mColumnTitle.setText(titleName);
        mColumnIcon.setImageResource(R.drawable.vedio_icon_title);

        List<VideoEntry.Vediolist> vediolist = info.getVediolist();
        //在adapter已经做过了数据检验
        for (int i = 0; i < mItems.size(); i++) {
            processData(mItems.get(i), vediolist.get(i));
        }

    }

    /**
     * 详细的数据处理类
     * @param view ItemView
     * @param vediolist VedioList
     */
    private void processData(View view, VideoEntry.Vediolist vediolist) {
        ImageView img = (ImageView) view.findViewById(R.id.video_img);
        TextView title = (TextView) view.findViewById(R.id.video_title);
        TextView des = (TextView) view.findViewById(R.id.video_des);
        TextView count = (TextView) view.findViewById(R.id.video_count);

        String name = vediolist.getName();
        String desContent = vediolist.getBrief();
        String playcount = vediolist.getPlaycount();
        String imgUrl = vediolist.getCoverhorizontal();

        title.setText(name);
        des.setText(desContent);
        count.setText(formatCount(playcount));

        Picasso.with(itemView.getContext()).load(imgUrl)
                .error(R.drawable.loading_bookrack).into(img);

    }

    /**
     * 格式化播放人数
     */
    private String formatCount(String count) {
        Long counts;
        try {
            counts = Long.parseLong(count);
        }catch (NumberFormatException e) {
            return null;
        }
        int length = 0;
        long tmp = counts;
        while (tmp > 0) {
            tmp = tmp / 10;
            length++;
        }
        if (length > 8) {
            double oFloat = ((counts / 10000000) / 10.0);
            System.out.println(oFloat);
            return oFloat + "亿";
        } else if (length > 4) {
            double oFloat = ((counts / 1000) / 10.0);
            return oFloat + "万";
        } else if (length > 3) {
            double oFloat = ((counts / 100) / 10.0);
            return oFloat + "千";
        }
        return null;
    }


}
