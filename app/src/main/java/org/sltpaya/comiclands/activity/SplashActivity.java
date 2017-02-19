package org.sltpaya.comiclands.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.sltpaya.comiclands.R;
import org.sltpaya.comiclands.fragment.GuideFragment;
import org.sltpaya.comiclands.fragment.SplashFragment;
import org.sltpaya.comiclands.net.BannerHttp;
import org.sltpaya.comiclands.net.SplashHttp;
import org.sltpaya.tool.Utils;

import static org.sltpaya.comiclands.consts.Consts.FIRST_LAUNCH;

/**
 * Author: SLTPAYA
 * Date: 2017/2/10
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_splash);
        initViews();
    }

    private void initViews() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isFirst()) {
            transaction.replace(R.id.activity_splash, new GuideFragment()).commit();
            return;
        }
        transaction.replace(R.id.activity_splash, new SplashFragment()).commit();
        SplashHttp.requestSplashJson();
    }

    /**
     * <p>判断应用是否是第一次使用,如果是第一次使用，则进入引导页面
     * 否则就进行闪屏正常页面</p>
     * @return boolean
     */
    private boolean isFirst() {
        SharedPreferences defaultPreferences
                = getSharedPreferences(getPackageName() + "_setting", MODE_PRIVATE);
        boolean isFirst = defaultPreferences.getBoolean(FIRST_LAUNCH, true);
        if (isFirst) {
            SharedPreferences.Editor edit = defaultPreferences.edit();
            edit.putBoolean("isFirst", false).apply();
        }
        return isFirst;
    }

    /**
     * hide the status bar and set the img to status bar
     */
    @SuppressLint("InlinedApi")
    private void hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
