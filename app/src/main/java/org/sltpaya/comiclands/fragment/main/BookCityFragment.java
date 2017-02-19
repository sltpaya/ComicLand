package org.sltpaya.comiclands.fragment.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.BaseFragmentPagerAdapter;
import org.sltpaya.comiclands.fragment.BookListTabFragment;
import org.sltpaya.comiclands.fragment.ClassifyTabFragment;
import org.sltpaya.comiclands.fragment.RankListTabFragment;
import org.sltpaya.comiclands.fragment.RecommendTabFragment;
import org.sltpaya.comiclands.fragment.VideoTabFragment;
import java.util.ArrayList;

/**
 * Author: SLTPAYA
 * Date: 2017/2/11
 */
public class BookCityFragment extends BaseFragment {

    private final String TAG = "BookCityFragment";
    private final boolean DEBUG = true;

    private TabLayout mTabLayout;
    private ViewPager mPager;
    private View mSearchButton;

    @Override
    public void initViews() {
        View inflate = inflateActionBar(R.layout.bar_book_city);
        this.mPager = getViewPager();
        this.mTabLayout = (TabLayout) inflate.findViewById(R.id.top_navigation);
        this.mSearchButton = inflate.findViewById(R.id.search_shelf);
        init();
    }

    private void init() {
        initData();
        initEvent();
    }

    private void initData() {
        String[] titles = getTabTitles(R.array.BOOK_CITY_TAB_TITLE);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecommendTabFragment());
        fragments.add(new RankListTabFragment());
        fragments.add(new ClassifyTabFragment());
        fragments.add(new BookListTabFragment());
        fragments.add(new VideoTabFragment());
        mPager.setOffscreenPageLimit(5);//viewpager将最大缓存5页数据
        mPager.setAdapter(new BaseFragmentPagerAdapter(getChildFragmentManager(), titles, fragments));
        mTabLayout.setupWithViewPager(mPager);
    }

    private void initEvent() {
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:将会打开搜索的Activity
                Toast.makeText(getActivity(), "将会打开搜索的Activity", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void logger(String des) {
        if (DEBUG) {
            Log.d(TAG, des);
        }
    }
}
