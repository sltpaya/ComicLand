package org.sltpaya.comiclands.holder.ranklist;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.BannerPagerAdapter;
import org.sltpaya.comiclands.net.BannerHttp;
import org.sltpaya.comiclands.net.BannerListener;
import org.sltpaya.comiclands.net.BannerObserver;
import org.sltpaya.comiclands.net.NetListener;
import org.sltpaya.comiclands.net.NetObserver;
import org.sltpaya.comiclands.net.entry.BannerEntry;
import org.sltpaya.comiclands.net.entry.RecommendEntry;
import org.sltpaya.tool.Utils;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import static org.sltpaya.tool.Utils.getLayoutInflater;

/**
 * Author: SLTPAYA
 * Date: 2017/2/17
 */
public class BannerHolder extends RecyclerView.ViewHolder{

    private ViewPager mBanner;
    private View selectedDot;
    private ViewGroup dotSet;
    private int dotWidth;
    private int dotRightMargin;
    private int pagerCount;
    private int pagerStartIndex;//设置Banner最开始的索引值

    public static final int RANK_LIST_ID = 19;
    public static final int BOOK_LIST_ID = 58;//书单轮播图
    public static final int VIDEO_LIST_ID = 112;//动画轮播图

    public BannerHolder(View itemView) {
        super(itemView);
        initHolderView();
    }

    private void initHolderView() {
        mBanner = (ViewPager) itemView.findViewById(R.id.recommend_banner);
        selectedDot = itemView.findViewById(R.id.banner_select_dot);
        dotSet = (ViewGroup) itemView.findViewById(R.id.point_set);
        selectedDot.setVisibility(View.GONE);
        bindBannerEvent();
    }

    public void setData(int groupId) {
//        BannerHttp.requestBannerJson(groupId);
        BannerHttp http = new BannerHttp(groupId);
        http.requestBannerJson();
        BannerObserver observer = BannerHttp.getBannerObserver();
        observer.registerNetObserver(new BannerListener() {
            @Override
            public void onResponseSuccessed(BannerEntry entry, int groupId) {
                System.out.println("成功获取到："+groupId+"的Banner");
                pagerCount = entry.getInfo().getAdlistjson().size();
                pagerStartIndex = 10000 * pagerCount;
                setBannerData(entry);
                createDotView();
            }

            @Override
            public void onFailed(int groupId) {
            }
        }, groupId);
    }

    /**
     * 设置Banner数据，为其设置适配器
     * @param entry BannerEntry
     */
    private void setBannerData(BannerEntry entry) {
        if (entry.getCode() == 200) {
            List<BannerEntry.Adlistjson> list = entry.getInfo().getAdlistjson();
            mBanner.setAdapter(new BannerPagerAdapter(list));
            mBanner.setCurrentItem(pagerStartIndex, false);
//            timingRoll();//自动滚动
        }
    }

    /**
     * 创建所有固定点
     */
    private void createDotView() {
        for (int i = 0; i < pagerCount; i++) {
            View normalDot = getLayoutInflater()
                    .inflate(R.layout.dot_normal, dotSet, false);
            dotSet.addView(normalDot);
            LinearLayout.LayoutParams params
                    = (LinearLayout.LayoutParams) normalDot.getLayoutParams();
            dotWidth = params.width;
            dotRightMargin = params.rightMargin;
        }
        selectedDot.setVisibility(View.VISIBLE);
    }

    /**
     * 为Banner绑定事件监听，在其中处理指示点的移动
     */
    private void bindBannerEvent() {
        mBanner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                RelativeLayout.LayoutParams params
                        = (RelativeLayout.LayoutParams) selectedDot.getLayoutParams();
                position %= pagerCount;
                int max = (dotWidth + dotRightMargin) * (pagerCount - 1);
                int tmp = (int) ((dotWidth + dotRightMargin) * (position + positionOffset) + 0.5);
                if (tmp > max) tmp = 0;
                params.leftMargin = tmp;
                selectedDot.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    /**
     * Banner自动滚动
     */
    private void timingRoll() {
        final int[] nowPosition = {pagerStartIndex};
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                nowPosition[0] += 1;
                runOnMainThread(nowPosition[0]);
            }
        }, 5000, 5000);
    }

    private void runOnMainThread(final int position) {
        Utils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBanner.setCurrentItem(position, false);
            }
        });
    }

}
