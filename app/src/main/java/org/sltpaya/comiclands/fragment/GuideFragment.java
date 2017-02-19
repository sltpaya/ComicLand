package org.sltpaya.comiclands.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.adapter.GuidePagerAdapter;

/**
 * Author: SLTPAYA
 * Date: 2017/2/10
 */
public class GuideFragment extends Fragment{

    private View mRootView;
    private ViewPager mGuidePager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.mRootView = inflater.inflate(R.layout.fragment_guide, container, false);
        init();
        return mRootView;
    }

    private void init() {
        initView();
        initData();
    }

    private void initView() {
        mGuidePager = (ViewPager)mRootView.findViewById(R.id.guide_viewpager);
    }

    private void initData() {
        int[] resId = {
            R.drawable.help1,
            R.drawable.help2,
            R.drawable.help3,
            R.drawable.help4
        };
        mGuidePager.setAdapter(new GuidePagerAdapter(getActivity(),resId));
    }

}
