/*
 * Copyright (c) 2012-2016 Arne Schwabe
 * Distributed under the GNU GPL v2 with additional terms. For full terms see the file doc/LICENSE.txt
 */

package org.sltpaya.tool;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static android.content.ContentValues.TAG;

public class Utils {

    private static float density = 0;
    private static int widthPixels = 0;
    private static int heightPixels = 0;
    private static Handler mHandler;
    private static WifiManager mWifiManager;

    /**
     * 获取ApplicationContext，需要将Application替换为mApplication
     * {@link Context}
     *
     * @return Context
     */
    public static Context getContext() {
        return ToolApplication.getContext();
    }

    /**
     * 获取布局填充器
     * @return LayoutInflater
     */
    public static LayoutInflater getLayoutInflater() {
        return (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 获取系统的Resources
     * {@link Resources}
     *
     * @return Resources
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取ID对应的字符串资源
     * {@link Resources}
     *
     * @param id 字符串资源
     * @return String
     * @throws Resources.NotFoundException 如果提供的ID不存在抛出异常
     */
    public static String getString(@StringRes int id) {
        return getResources().getString(id);
    }

    /**
     * Context的getDrawable()方法已经过时，提供ContextCompat.getDrawable(Context,resId)方法获取
     * Drawable对象
     * {@link ContextCompat}
     * {@link android.support.v4.content.ContextCompatApi23}
     *
     * @param id Drawable资源ID
     * @return Drawable对象
     * @throws Resources.NotFoundException 如果提供的ID不存在抛出异常
     */
    public static Drawable getDrawable(@DrawableRes int id) {
        return ContextCompat.getDrawable(getContext(), id);
    }

    /**
     * 运行在主线程
     *
     * @param runnable r
     */
    public static void runOnUiThread(Runnable runnable) {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        mHandler.post(runnable);
    }

    /**
     * <p>Context的getColor方法已经过时，提供ContextCompat.getColor(Context,resID)方法获取颜色值</p>
     * <p>内部实现为：当api大于23时，使用ContextCompatApi23.getColor(Context,id);获取颜色值</p>
     * {@link ContextCompat}
     * {@link android.support.v4.content.ContextCompatApi23}
     *
     * @return 返回一个类似于0xAARRGGBB的值
     * @throws Resources.NotFoundException 如果提供的ID不存在抛出异常
     */
    @ColorInt
    public static int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(getContext(), resId);
    }

    /**
     * 初始化屏幕数据
     */
    private static void initMonitor() {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        density = displayMetrics.density;
        widthPixels = displayMetrics.widthPixels;
        heightPixels = displayMetrics.heightPixels;
    }

    /**
     * 获取屏幕密度
     *
     * @return density
     */
    public static float getDensity() {
        if (density == 0) initMonitor();
        return density;
    }

    /**
     * 获取屏幕宽度像素
     *
     * @return widthPixels
     */
    public static int getWidthPixels() {
        if (widthPixels == 0) initMonitor();
        return widthPixels;
    }

    /**
     * 获取屏幕长度像素
     *
     * @return HeightPixels
     */
    public static int getHeightPixels() {
        if (heightPixels == 0) initMonitor();
        return heightPixels;
    }

    /**
     * dp转为像素
     *
     * @param dp dp
     * @return 像素值
     */
    public static int dp2px(int dp) {
        return (int) (dp * getDensity() + 0.5f);
    }

    /**
     * 像素转为dp
     *
     * @param px 像素
     * @return dp值
     */
    public static int px2dp(int px) {
        return (int) (px / getDensity() + 0.5f);
    }

    /**
     * 获取当前应用版本名称
     * {@link  PackageManager}
     *
     * @return 版本号
     */
    public static String getApplicationVersionName() {
        Context context = getContext();
        PackageManager packageManager = context.getPackageManager();
        String versionName = null;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取当前应用版本代码VersionCode
     *
     * @return VersionCode
     */
    public static int getApplicationVersionCode() {
        Context context = getContext();
        PackageManager packageManager = context.getPackageManager();
        int versionCode = -1;
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取系统SDK的版本号 API-xx
     *
     * @return SDK版本号
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取热点状态
     *
     * @return 热点状态值（开启，正在开启，关闭，正在关闭，失败）
     * private final int WIFI_AP_STATE_DISABLING = 10;
     * private final int WIFI_AP_STATE_DISABLED = 11;
     * private final int WIFI_AP_STATE_ENABLING = 12;
     * private final int WIFI_AP_STATE_ENABLED = 13;
     * private final int WIFI_AP_STATE_FAILED = 14;
     * 需要权限：<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
     */
    public static int getWifiApState() {
        if (mWifiManager == null)
            mWifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        try {
            Method getWifiApState = mWifiManager.getClass().getDeclaredMethod("getWifiApState");
            return (int) getWifiApState.invoke(mWifiManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 14;
    }

    /**
     * 判断wifi的状态是否是开启的
     *
     * @return boolean
     */
    public static boolean isWifiApEnabled() {
        if (mWifiManager == null)
            mWifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        try {
            Method method = mWifiManager.getClass().getMethod("isWifiApEnabled");
            method.setAccessible(true);
            return (Boolean) method.invoke(mWifiManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 刷新WIFI信息
     *
     * @return WIFI信息的集合
     */
    private static HashMap<String, ArrayList<String>> getWifiApInfo() {
        HashMap<String, ArrayList<String>> mWifiInfoMap = new HashMap<>();
        ArrayList<String> ipList = new ArrayList<>();
        ArrayList<String> macList = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/net/arp"));
            String line, ip = null;
            String mac = null;
            while ((line = reader.readLine()) != null) {
                String[] part = line.split(" +");
                for (String item : part) {
                    ip = item.matches(".{1,3}\\..{1,3}\\..{1,3}\\..{1,3}") ? item : ip;
                    mac = item.matches(".{1,3}:.{1,3}:.{1,3}:.{1,3}:.{1,3}:.{1,3}") ? item : mac;
                }
                if (ip != null && mac != null) {
                    ipList.add(ip);
                    macList.add(mac);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mWifiInfoMap.put("ip", ipList);
        mWifiInfoMap.put("mac", macList);
        return mWifiInfoMap;
    }

    /**
     * 获取WIFI AP连接人数
     *
     * @return int
     * @throws RuntimeException 当WIFI未开启时，调用此方法将抛出异常
     * @see #getWifiApInfo()
     * @see #getWifiApState()
     */
    public static int getWifiApClientCount() {
        if (getWifiApState()==13||getWifiApState()==12) {
            HashMap<String, ArrayList<String>> map = getWifiApInfo();
            ArrayList<String> ip = map.get("ip");
            return ip.size();
        } else {
            throw new RuntimeException("The WifiAp is not enable!");
        }
    }

    /**
     * 获取热点所有的IP地址
     *
     * @return String
     * @throws RuntimeException 当WIFI未开启时，调用此方法将抛出异常
     * @see #getWifiApInfo()
     * @see #getWifiApState()
     */
    public static String getWifiApIp() {
        if (getWifiApState()==13||getWifiApState()==12) {
            StringBuilder builder = new StringBuilder();
            HashMap<String, ArrayList<String>> map = getWifiApInfo();
            ArrayList<String> ip = map.get("ip");
            for (String ipItem : ip) {
                builder.append(ipItem).append("\n");
            }
            return builder.toString();
        } else {
            throw new RuntimeException("The WifiAp is not enable!");
        }
    }


    /**
     * 开启热点
     *
     * @param ssid   热点名称
     * @param passwd 密码
     *               需要权限 <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
     */
    @SuppressWarnings("unused")
    public static void startWifiAp(String ssid, String passwd) {
        if (mWifiManager == null)
            mWifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        //wifi和热点不能同时打开，所以打开热点的时候需要关闭wifi
        if (mWifiManager.isWifiEnabled()) mWifiManager.setWifiEnabled(false);
        if (!isWifiApEnabled()) {
            Method method;
            try {
                //通过反射机制打开热点0
                method = mWifiManager.getClass().getMethod("setWifiApEnabled",
                        WifiConfiguration.class, boolean.class);
                WifiConfiguration netConfig = new WifiConfiguration();

                netConfig.SSID = ssid;
                netConfig.preSharedKey = passwd;

                netConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                netConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                netConfig.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                netConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                netConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                netConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                method.invoke(mWifiManager, netConfig, true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭WiFi热点
     */
    public static void closeWifiAp() {
        if (mWifiManager == null)
            mWifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
        if (isWifiApEnabled()) {
            try {
                Method method = mWifiManager.getClass().getMethod("getWifiApConfiguration");
                method.setAccessible(true);
                WifiConfiguration config = (WifiConfiguration) method.invoke(mWifiManager);
                Method method2 = mWifiManager.getClass().getMethod("setWifiApEnabled",
                        WifiConfiguration.class, boolean.class);
                method2.invoke(mWifiManager, config, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取APN状态
     * @return 开启或关闭
     */
    private boolean getApnStatus(){
        Matcher matcher;
        String name;
        try {
            Enumeration<NetworkInterface> niList = NetworkInterface.getNetworkInterfaces();
            if(niList != null) {
                for (NetworkInterface intf : Collections.list(niList)) {
                    if(!intf.isUp() || intf.getInterfaceAddresses().size() == 0) {
                        continue;
                    }
                    Log.d(TAG, "isVpnUsed() NetworkInterface Name: " + intf.getName());
                    name = intf.getName();
                    matcher =  Pattern.compile("tun\\d").matcher(name);
                    if(matcher.find()){
                        System.out.println("vpn打开了！！！！！！"+matcher.group());
                        return true;
                    }
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取SharedPreferenes
     * @param name 名称
     * @return SharedPreferenes
     */
    public static SharedPreferences getMainSharedPreferences(String name){
        return getContext().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    public static void putBoolean(SharedPreferences preferences,String key,boolean value){
        SharedPreferences.Editor edit = preferences.edit();
        edit.putBoolean(key,value).apply();
    }

    public static void putString(SharedPreferences preferences,String key,String value){
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(key,value).apply();
    }


    /**
     * 内部类Application，提供全局的ApplicationContext
     *
     * @see Application
     * {@link }
     */
    public static class ToolApplication extends Application {

        static Context[] mContexts = new Context[1];

        public static Context getContext() {
            return mContexts[0];
        }

        @Override
        public void onCreate() {
            super.onCreate();
            mContexts[0] = getApplicationContext();
        }

    }
}
