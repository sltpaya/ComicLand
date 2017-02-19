package org.sltpaya.comiclands.holder.recommend;

import android.support.v4.view.ViewPager;
import android.util.Log;
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

/**
 * Author: SLTPAYA
 * Date: 2017/2/14
 */
public class BannerHolder extends ComicHorizontalHolder {

    private ViewPager mBanner;
    private View selectedDot;
    private ViewGroup dotSet;
    private int dotWidth;
    private int dotRightMargin;
    private int pagerCount;
    private int pagerStartIndex;//设置Banner最开始的索引值
    private boolean mBannerFlag = false;//设置布局Banner的显隐

    public BannerHolder(View itemView, boolean bannerFlag) {
        super(itemView);
        BannerHttp http = new BannerHttp(9);
        http.requestBannerJson();
//        BannerHttp.requestBannerJson(9);/*推荐-轮播图groupId = 9*/
        mBannerFlag = bannerFlag;
        initHolderView();
    }

    @Override
    protected void initHolderView() {
        View bannerLayout = itemView.findViewById(R.id.banner_layout);
        if (mBannerFlag) {
            Log.d("BannerHolder", "Banner初始化了！");
            mBanner = (ViewPager) itemView.findViewById(R.id.recommend_banner);
            bannerLayout.setVisibility(View.VISIBLE);
            selectedDot = itemView.findViewById(R.id.banner_select_dot);
            dotSet = (ViewGroup) itemView.findViewById(R.id.point_set);
            selectedDot.setVisibility(View.GONE);
            bindBannerEvent();
        } else {
            Log.d("BannerHolder", "橫向布局初始化了！");
            bannerLayout.setVisibility(View.GONE);
        }
        super.initHolderView();
    }

    @Override
    public void setData(RecommendEntry.Info entry) {
        super.setData(entry);
        if (mBannerFlag) {
            /*成功获取到网络数据后，为Banner设置数据适配器，创建固定指示点*/
            BannerObserver observer = BannerHttp.getBannerObserver();
            observer.registerNetObserver(new BannerListener() {
                @Override
                public void onResponseSuccessed(BannerEntry entry, int groupId) {
                    pagerCount = entry.getInfo().getAdlistjson().size();
                    pagerStartIndex = 10000 * pagerCount;
                    setBannerData(entry);
                    createDotView();
                }

                @Override
                public void onFailed(int groupId) {

                }
            }, 9);

        }
    }

    /**
     * 设置Banner数据，为其设置适配器
     *
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
