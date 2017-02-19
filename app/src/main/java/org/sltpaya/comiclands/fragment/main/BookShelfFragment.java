package org.sltpaya.comiclands.fragment.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Toast;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.BaseFragmentPagerAdapter;
import org.sltpaya.comiclands.fragment.tab.TabFragment;

import java.util.ArrayList;

/**
 * Author: SLTPAYA
 * Date: 2017/2/11
 */
public class BookShelfFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mPager;
    private View mSearchButton;
    private View mEditButton;

    @Override
    public void initViews() {
        //动态替换成对应的AppBar
        View inflate = inflateActionBar(R.layout.bar_bookshelf);
        //初始化控件
        mTabLayout = (TabLayout) inflate.findViewById(R.id.top_navigation);
        mEditButton = inflate.findViewById(R.id.edit_shelf);
        mSearchButton = inflate.findViewById(R.id.search_shelf);
        mPager = getViewPager();
        init();
    }

    private void init() {
        initData();
        initEvent();
    }

    private void initData() {
        String[] titles = getTabTitles(R.array.BOOK_SHELF_TAB_TITLE);
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (String title : titles) {
            TabFragment fragment = new TabFragment();
            Bundle args = new Bundle();
            args.putInt(TabFragment.LAYOUT_ID, R.layout.layout_recycler);
            fragment.setArguments(args);
            fragments.add(fragment);
        }
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
        mEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "将会打开编辑的Activity", Toast.LENGTH_LONG).show();
            }
        });
    }
}
