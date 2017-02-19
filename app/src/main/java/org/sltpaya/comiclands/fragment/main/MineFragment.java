package org.sltpaya.comiclands.fragment.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Toast;
import org.sltpaya.comiclands.R;

/**
 * Author: SLTPAYA
 * Date: 2017/2/11
 */
public class MineFragment extends BaseFragment {

    private View mSettingButton;
    private View mScanButton;
    private LayoutInflater mInflater;
    private View mActionBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.mine_fragment, container, false);
        mActionBar = mRootView.findViewById(R.id.mine_bar);
        this.mInflater = inflater;
        initViews();
        return mRootView;
    }

    public void initViews() {
        mSettingButton = mActionBar.findViewById(R.id.mine_title);
        mScanButton = mActionBar.findViewById(R.id.scan_mine);
        init();
    }

    private void init() {
        initData();
        initEvent();
    }

    private void initData() {

    }

    private void initEvent() {
        mSettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO:将会打开搜索的Activity
                Toast.makeText(getActivity(), "将会打开设置的Activity", Toast.LENGTH_LONG).show();
            }
        });
        mScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "将会打开扫描的Activity", Toast.LENGTH_LONG).show();
            }
        });
    }

}
