package org.sltpaya.comiclands.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Author: SLTPAYA
 * Date: 2017/2/12
 */
@SuppressWarnings("unused")
public class BottomBehavior extends CoordinatorLayout.Behavior<View> {

    public BottomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**确定所提供的子视图是否有另一个特定的同级视图作为布局从属。*/
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;/*子控件是依赖AppBarLayout的*/
    }

    /**用于响应从属布局的变化*/
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float translationY = Math.abs(dependency.getTop());//获取更随布局的顶部位置
        child.setTranslationY(translationY*2);
        return true;
    }

}
