package org.sltpaya.comiclands.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.activity.MainActivity;

import java.util.ArrayList;

/**
 * Author: SLTPAYA
 * Date: 2017/2/10
 */
public class GuidePagerAdapter extends PagerAdapter {

    private int[] resId;
    private Activity mContext;
    private Button mButton;
    private ArrayList<View> mViews;

    public GuidePagerAdapter(Activity mContext, int[] resId) {
        this.resId = resId;
        this.mContext = mContext;
        initView();
    }

    @Override
    public int getCount() {
        return resId.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    private void initView() {
        mViews = new ArrayList<>();
        LayoutInflater inflater
                = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for (int i = 0; i < resId.length; i++) {
            View view = inflater.inflate(R.layout.guide_pager_item, null);
            ImageView img = (ImageView) view.findViewById(R.id.guide_img);
            mButton = (Button) view.findViewById(R.id.guide_pass);
            mButton.setVisibility(View.GONE);
            if (i == resId.length - 1) {
                mButton.setVisibility(View.VISIBLE);
            }
            initEvent();
            img.setImageResource(resId[i]);
            mViews.add(view);
        }
    }

    private void initEvent() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterMain();
            }
        });
    }

    private void enterMain() {
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
        mContext.finish();
    }

}
