package org.sltpaya.comiclands.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Author: SLTPAYA
 * Date: 2017/2/11
 */
public class BaseFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private String[] titles;
    private ArrayList<Fragment> fragments;

    public BaseFragmentPagerAdapter(FragmentManager fm, String[] titles,
                                    ArrayList<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
