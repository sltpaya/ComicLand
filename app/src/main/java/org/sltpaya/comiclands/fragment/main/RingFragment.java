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
public class RingFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mPager;
    private View mSearchButton;
    private View mMessageButton;

    @Override
    public void initViews() {
        View inflate = inflateActionBar(R.layout.bar_ring);
        mTabLayout = (TabLayout) inflate.findViewById(R.id.top_navigation);
        mMessageButton = inflate.findViewById(R.id.message_ring);
        mSearchButton = inflate.findViewById(R.id.edit_ring);
        mPager = getViewPager();
        init();
    }

    private void init() {
        initData();
        initEvent();
    }

    private void initData() {
        String[] titles = getTabTitles(R.array.RING_TAB_TITLE);
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
        mMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "将会打开信息的Activity", Toast.LENGTH_LONG).show();
            }
        });
    }

}
