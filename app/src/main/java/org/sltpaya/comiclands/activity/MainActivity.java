package org.sltpaya.comiclands.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.fragment.main.BookCityFragment;
import org.sltpaya.comiclands.fragment.main.BookShelfFragment;
import org.sltpaya.comiclands.fragment.main.LastestComicFragment;
import org.sltpaya.comiclands.fragment.main.MineFragment;
import org.sltpaya.comiclands.fragment.main.RingFragment;
import org.sltpaya.tablayout.TabLayoutBuilder;
import org.sltpaya.tablayout.XTabLayout;

import java.util.ArrayList;

/**
 * Author: SLTPAYA
 * Date: 2017/2/10
 */
public class MainActivity extends AppCompatActivity {

    private TabLayoutBuilder mTabBuilder;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<Fragment> alreadyAddFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initViews();
        initData();
        inflateBottomView();
        bottomNavigationEvent();
    }

    private void initViews() {
        mTabBuilder = (TabLayoutBuilder) findViewById(R.id.main_bottom_navigation);
    }

    private void initData() {
        fragments.add(new BookShelfFragment());
        fragments.add(new BookCityFragment());
        fragments.add(new LastestComicFragment());
        fragments.add(new RingFragment());
        fragments.add(new MineFragment());
    }

    private void inflateBottomView() {
        int[] textColor = {
                0xff333333,
                0xffffffff
        };
        int[] resId = {
                R.drawable.heart_selector,
                R.drawable.ufo_selector,
                R.drawable.diamond_selector,
                R.drawable.ring_selector,
                R.drawable.mine_selector
        };
        String[] bottomTitle = getResources().getStringArray(R.array.BOTTOM_NAVIGATION_TITLE);
        mTabBuilder.setTextSize(12);
        mTabBuilder.setBottomMargin(2);
        for (int i = 0; i < fragments.size(); i++) {
            mTabBuilder.addTab(mTabBuilder.newTab());
            mTabBuilder.addTab(new TabLayoutBuilder.ItemStatus(
                    bottomTitle[i], resId[i], textColor[0], textColor[1]));
        }
        mTabBuilder.build();
        setDefaultTab(1);
    }

    /**
     * 设置默认Tab选择,必须位于build()之后调用才有效
     * 不调用此方法，无法显示出界面
     *
     * @param index Tab索引值
     */
    private void setDefaultTab(int index) {
        XTabLayout.Tab tab = mTabBuilder.getTabAt(index);
        if (tab != null) {
            tab.select();
            initFragment(index);
        }
    }

    /**
     * 初始化所有的Fragment，显示和隐藏，添加，每点击一个添加一个Fragment.
     * @param index {@link #fragments}中对应的Fragment
     */
    private void initFragment(int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment nowFragment = fragments.get(index);

        boolean isAdd = alreadyAddFragments.contains(nowFragment);
        if (!isAdd) {
            transaction.add(R.id.content, nowFragment).show(nowFragment);
            alreadyAddFragments.add(nowFragment);
        }
        transaction.show(nowFragment);

        for (int i = 0; i < fragments.size(); i++) {
            if (i != index) {
                transaction.hide(fragments.get(i));
            }
        }

        transaction.commit();
    }

    private void bottomNavigationEvent() {
        mTabBuilder.addOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                initFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
            }
        });
    }

    /**
     * 按下返回键不退出，置为后台
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(false);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
        //注释的原因是：可能导致应用后台时间长后，Fragment的加载问题出现
        //尚不明确是否具有作用
    }
}
