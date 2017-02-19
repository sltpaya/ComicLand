package org.sltpaya.comiclands.fragment.main;

import android.os.Bundle;
import android.support.annotation.ArrayRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sltpaya.comiclands.R;

/**
 * Author: SLTPAYA
 * Date: 2017/2/11
 */
public abstract class BaseFragment extends Fragment {

    private ViewGroup mActionBarContainer;
    private ViewPager mPager;
    protected LayoutInflater mInflater;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_base, container, false);
        this.mActionBarContainer = (ViewGroup) mRootView.findViewById(R.id.appbar_container);
        this.mPager = (ViewPager) mRootView.findViewById(R.id.fragment_viewpager);
        this.mInflater = inflater;
        initViews();
        return mRootView;
    }

    /**
     * 初始化化Fragment视图方法，必须由子类实现
     */
    protected abstract void initViews();

    /**
     * 获取到Activity的根ViewGroup对象
     * @return ViewGroup
     */
    @SuppressWarnings("unused")
    protected View getRootView(){
        return ((ViewGroup)getActivity().findViewById(android.R.id.content)).getChildAt(0);
    }

    /**
     * 填充Fragment的AppBar内容
     * @param layoutId AppBar布局Id
     * @return 填充好的AppBar View
     */
    protected View inflateActionBar(@LayoutRes int layoutId) {
        return mInflater.inflate(layoutId, mActionBarContainer, true);
    }

    /**
     * 返回Fragment的ViewPager对象
     * @return ViewPager
     */
    protected ViewPager getViewPager() {
        return mPager;
    }

    /**
     * 提供给子类使用，获取Tab的标题
     * @param arrayId Array资源Id
     * @return String[]
     */
    protected String[] getTabTitles(@ArrayRes int arrayId) {
        return getActivity().getResources().getStringArray(arrayId);
    }

}
