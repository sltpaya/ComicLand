
package org.sltpaya.tool;

import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import android.view.Gravity;
import android.content.Context;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.support.annotation.ColorRes;
import android.graphics.drawable.GradientDrawable;
import org.sltpaya.comiclands.R;

/**
 * Author: SLTPAYA
 * Date: 2017/2/14
 */
public class Toast {

    private int mGravity;
    private int xOffset;
    private int yOffset;
    private long mDuration;

    private View mNextView;
    private View mRootView;

    private Context mContext;
    private android.widget.Toast mToast;

    public static final long LENGTH_LONG = 3000;
    public static final long LENGTH_SHORT = 1000;

    public Toast(Context context) {
        this.mContext = context;
        mToast = new android.widget.Toast(context);
        mGravity = Gravity.CENTER;
        mToast.setDuration(android.widget.Toast.LENGTH_LONG);
        mToast.setGravity(mGravity, xOffset, yOffset);
    }

    /**
     * 自定义Toast
     * @param layout 传入的View对象
     * @param duration 持续的时间
     */
    public Toast toast(View layout, long duration) {
        mNextView = layout;
        mToast.setView(layout);
        setDuration(duration);
        return this;
    }

    /**
     * 设置在屏幕显示的位置
     *
     * @see Gravity
     */
    public Toast setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            this.mGravity = gravity;
            this.xOffset = xOffset;
            this.yOffset = yOffset;
            mToast.setGravity(gravity, xOffset, yOffset);
        }
        return this;
    }

    /**
     * 返回Toast在屏幕上的位置
     *
     * @return 位置
     * @see Gravity
     */
    public int getGravity() {
        return mGravity;
    }

    /**
     * 获取Toast持续显示的时间
     *
     * @return 持续显示的时间
     */
    public long getDuration() {
        return mDuration;
    }

    /**
     * 设置显示的持续时间
     *
     * @param duration 持续时间，默认为：LENGTH_SHORT
     * @see #LENGTH_SHORT
     */
    public Toast setDuration(long duration) {
        if (mToast != null) mDuration = duration;
        return this;
    }

    /**
     * 返回X坐标在屏幕上的偏移量
     */
    public int getXOffset() {
        return xOffset;
    }

    /**
     * 返回Y坐标在屏幕上的偏移量
     */
    public int getYOffset() {
        return yOffset;
    }

    /**
     * 返回Toast中包含的View
     *
     * @see #setView
     */
    public View getView() {
        return mNextView;
    }

    /**
     * 设置Toast中自定义的View
     *
     * @see #getView
     */
    public Toast setView(View view) {
        mNextView = view;
        mToast.setView(view);
        return this;
    }

    /**
     * 显示Toast
     */
    public void show() {
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mToast != null) mToast.show();
            }
        }, 0, LENGTH_LONG);
        /*延时time的时候执行内容！*/
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mToast.cancel();//关闭正在显示的View
                timer.cancel();     //撤销正在执行的定时器
            }
        }, mDuration == 0 ? LENGTH_SHORT : mDuration);
    }

    /**
     * 封装的makeText方法
     *
     * @param context  上下文
     * @param text     显示的文本
     * @param duration 持续时间（无限制）
     */
    public static Toast makeText(Context context, CharSequence text, long duration) {
        Toast toast = new Toast(context);

        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View v = inflate.inflate(R.layout.default_toast, null);
        TextView tv = (TextView) v.findViewById(R.id.message);

        tv.setText(text);
        toast.xOffset = 0;
        toast.yOffset = 300;
        toast.mDuration = duration;
        toast.mGravity = Gravity.BOTTOM;
        toast.mRootView = toast.mNextView = v;

        toast.mToast.setView(v);
        toast.mToast.setGravity(toast.mGravity, toast.xOffset, toast.yOffset);
        return toast;
    }

    /**
     * 封装的makeText方法
     *
     * @param context  上下文
     * @param resId    显示的文本资源id
     * @param duration 持续时间（无限制）
     * @throws Resources.NotFoundException 如果资源没有找到抛出异常
     */
    public static Toast makeText(Context context, @StringRes int resId, long duration)
            throws Resources.NotFoundException {
        return makeText(context, context.getResources().getText(resId), duration);
    }

    /**
     * 更新通过makeText()方法创建的的文本内容
     *
     * @param resId 新的文本资源id
     */
    public Toast setText(@StringRes int resId) {
        return setText(mContext.getText(resId));
    }

    /**
     * 更新通过makeText()方法创建的的文本内容
     *
     * @param s 新的文本
     */
    public Toast setText(CharSequence s) {
        TextView tv = (TextView) mRootView.findViewById(R.id.message);
        if (tv == null) {
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        }
        tv.setText(s);
        return this;
    }

    /**
     * 更新通过makeText()方法创建Toast的背景
     *
     * @param resId 颜色资源id
     */
    public Toast setBackgroundColor(@ColorRes int resId) {
        if (mRootView == null) {
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        }
        GradientDrawable gradient = (GradientDrawable) mRootView.getBackground();
        gradient.setColor(mContext.getResources().getColor(resId));
        return this;
    }

    /**
     * 设置Toast的字体颜色
     * @param resId 颜色的的资源id
     */
    public Toast setTextColor(int resId){
        TextView tv = (TextView) mRootView.findViewById(R.id.message);
        if (tv == null) {
            throw new RuntimeException("This Toast was not created with Toast.makeText()");
        }
        tv.setTextColor(resId);
        return this;
    }
}
