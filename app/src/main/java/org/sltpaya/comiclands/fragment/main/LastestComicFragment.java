package org.sltpaya.comiclands.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.BaseFragmentPagerAdapter;
import org.sltpaya.comiclands.fragment.BaseTabFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Author: SLTPAYA
 * Date: 2017/2/11
 */
public class LastestComicFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mPager;
    private View mSearchButton;

    @Override
    public void initViews() {
        //动态替换成对应的AppBar
        View inflate = inflateActionBar(R.layout.bar_lastest);
        //初始化控件
        mPager = getViewPager();
        mTabLayout = (TabLayout) inflate.findViewById(R.id.top_navigation);
        mSearchButton = inflate.findViewById(R.id.search_lastest);
        init();
    }

    private void init() {
        initData();
//        initEvent();
    }

    private void initData() {
        String[] titles = handleWeekTitle();
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (String title : titles) {
            BaseTabFragment fragment = new BaseTabFragment();
            Bundle args = new Bundle();
            args.putInt(BaseTabFragment.LAYOUT_ID, R.layout.layout_recycler);
            fragment.setArguments(args);
            fragments.add(fragment);
        }
        mPager.setAdapter(new BaseFragmentPagerAdapter(getChildFragmentManager(), titles, fragments));
        mTabLayout.setupWithViewPager(mPager);
        setDefaultTab(mTabLayout.getTabCount() - 1);
    }

    /**
     * 设置默认选中的Tab
     */
    private void setDefaultTab(int tabIndex) {
        TabLayout.Tab tab = mTabLayout.getTabAt(tabIndex);
        if (tab != null) {
            tab.select();
        }
    }

    private void initEvent() {
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:将会打开搜索的Activity
                Toast.makeText(getActivity(), "将会打开编辑的Activity", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 处理日期问题
     * @return 星期字符串数组
     */
    private String[] handleWeekTitle() {

        SimpleDateFormat dateFormat =new SimpleDateFormat("E", Locale.CHINA);
        long currentTime = System.currentTimeMillis();
        String toadyWeek = dateFormat.format(new Date(currentTime));

        int index = 0;
        switch (toadyWeek) {
            case "周一":index = 0;break;
            case "周二":index = 1;break;
            case "周三":index = 2;break;
            case "周四":index = 3;break;
            case "周五":index = 4;break;
            case "周六":index = 5;break;
            case "周日":index = 6;break;
        }

        return createWeekSort(index);
    }

    /**
     * 对星期进行排序
     * @param index 日期
     * @return 排序后的日期数组
     */
    private String[] createWeekSort(int index) {
        String[] week = getTabTitles(R.array.WEEK_DAY);
        int preIndex;
        String splitChar = "x";
        StringBuilder builder = new StringBuilder();

        if (index < week.length) {
            for (int i = index + 1; i < week.length; i++) {
                builder.append(week[i]).append(splitChar);
            }
        }

        for (int i = 0; i < index; i++) {
            builder.append(week[i]).append(splitChar);
        }

        builder.append(week[index]);

        String result = builder.toString();
        switch (index) {
            case 0:preIndex = week.length - 1;break;
            case 7:preIndex = 0;break;
            default:preIndex = index - 1;break;
        }
        result = result.replaceAll(week[index], "今天").replaceAll(week[preIndex], "昨天");
        return result.split(splitChar);
    }

}
