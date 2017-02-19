package org.sltpaya.comiclands.holder.recommend;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.BannerPagerAdapter;
import org.sltpaya.comiclands.consts.Consts;
import org.sltpaya.comiclands.net.BannerHttp;
import org.sltpaya.comiclands.net.BannerListener;
import org.sltpaya.comiclands.net.BannerObserver;
import org.sltpaya.comiclands.net.entry.BannerEntry;
import org.sltpaya.comiclands.net.entry.RecommendEntry;
import org.sltpaya.tool.Utils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
    private int pagerStartIndex;         //设置Banner最开始的索引值
    private boolean mBannerFlag = false;//设置布局Banner的显隐

    public BannerHolder(View itemView, boolean bannerFlag) {
        super(itemView);
        requestBannerJson();
        mBannerFlag = bannerFlag;
        initHolderView();
    }

    private void requestBannerJson() {
        BannerHttp http = new BannerHttp(Consts.RECOMMEND_ID);
        http.requestBannerJson();
    }

    @Override
    protected void initHolderView() {
        View bannerLayout = itemView.findViewById(R.id.banner_layout);
        if (mBannerFlag) {
            initBanner(bannerLayout);
        } else {
            Log.d("BannerHolder", "橫向布局初始化了！");
            bannerLayout.setVisibility(View.GONE);
        }
        super.initHolderView();
    }

    @Override
    public void setData(RecommendEntry.Info entry) {
        if (mBannerFlag) {
            processNetData();
        }
        super.setData(entry);
    }

    /**
     * 初始化Banner相关View
     *
     * @param bannerLayout BannerLayout
     */
    private void initBanner(View bannerLayout) {
        Log.d("BannerHolder", "Banner初始化了！");
        mBanner = (ViewPager) itemView.findViewById(R.id.recommend_banner);
        bannerLayout.setVisibility(View.VISIBLE);
        selectedDot = itemView.findViewById(R.id.banner_select_dot);
        dotSet = (ViewGroup) itemView.findViewById(R.id.point_set);
        selectedDot.setVisibility(View.GONE);
        bindBannerEvent();
    }

    /**
     * 获取Banner Json网络回调数据
     * @see #requestBannerJson()
     * {@link Consts}
     * {@link BannerHttp}
     */
    private void processNetData() {
        /*成功获取到网络数据后，为Banner设置数据适配器，创建固定指示点*/
        BannerObserver observer = BannerHttp.getBannerObserver();
        observer.registerNetObserver(new BannerListener() {
            @Override
            public void onResponseSuccessed(BannerEntry entry, int groupId) {
                setBannerData(entry);
                createDotView();
            }

            @Override
            public void onFailed(int groupId) {
            }
        }, Consts.RECOMMEND_ID);
    }

    /**
     * <p>获取到Entry后设置Banner数据，为其设置适配器</p>
     * <p>获取Banner需要展示的总数量{@link #pagerCount}，
     * 设置StartIndex{@link #pagerStartIndex}</p>
     * @param entry {@link BannerEntry}
     */
    private void setBannerData(BannerEntry entry) {
        if (entry.getCode() == 200) {
            pagerCount = entry.getInfo().getAdlistjson().size();
            pagerStartIndex = 10000 * pagerCount;
            List<BannerEntry.Adlistjson> list = entry.getInfo().getAdlistjson();
            mBanner.setAdapter(new BannerPagerAdapter(list));
            mBanner.setCurrentItem(pagerStartIndex, false);
//            timingRoll();//自动滚动
        }
    }

    /**
     * 创建Banner下方所有固定小白点
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
     * 为Banner绑定事件监听，在其中处理选中指示点的移动
     * <p>原理为根据{@link ViewPager}滑动的百分值，动态设置选中点的Margin值</p>
     * @see #mBanner
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
