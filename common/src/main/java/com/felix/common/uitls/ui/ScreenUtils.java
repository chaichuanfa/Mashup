package com.felix.common.uitls.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.PowerManager;
import android.util.TypedValue;
import android.view.View;

import java.lang.reflect.Method;
import java.util.List;

import timber.log.Timber;

/**
 * Created by chaichuanfa on 2019/1/18
 */
public final class ScreenUtils {

    public static final int DEFAULT_STATUS_BAR_HEIGHT_DP = 25;

    protected static Context mContext = null;

    private ScreenUtils() {
        //no instance
    }

    public static void setContext(Context context) {
        mContext = context.getApplicationContext();
    }

    public static int getScreenOrientation() {
        return mContext.getResources().getConfiguration().orientation;
    }

    //CHECKSTYLE:OFF
    public static int dip2px(int dipValue) {
        float reSize = mContext.getResources().getDisplayMetrics().density;
        return (int) ((dipValue * reSize) + 0.5);
    }

    public static int dip2px(float dipValue) {
        float reSize = mContext.getResources().getDisplayMetrics().density;
        return (int) ((dipValue * reSize) + 0.5);
    }

    public static int px2dip(int pxValue) {
        float reSize = mContext.getResources().getDisplayMetrics().density;
        return (int) ((pxValue / reSize) + 0.5);
    }

    public static float sp2px(int spValue) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue,
                mContext.getResources().getDisplayMetrics());
    }

    public static int getScreenWidth() {
        return mContext.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return mContext.getResources().getDisplayMetrics().heightPixels;
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap b = Bitmap.createBitmap(
                view.getWidth(), view.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.translate(-view.getScrollX(), -view.getScrollY());
        view.draw(c);
        return b;
    }

    public static boolean isAppOnForeground() {
        String packageName = mContext.getPackageName();
        ActivityManager activityManager = ((ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            // app on stack top
            if (packageName.equals(tasksInfo.get(0).topActivity
                    .getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isActivityOnForeground(String activityName) {

        ActivityManager activityManager = ((ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            Timber.d("getClassName: " + tasksInfo.get(0).topActivity.getClassName());
            // app on stack top
            if (tasksInfo.get(0).topActivity.getClassName().endsWith(activityName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isScreenOn() {
        PowerManager powerManager = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
        return powerManager.isScreenOn();
    }


    public static int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        if (mContext.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data,
                    mContext.getResources().getDisplayMetrics());
        }
        return 0;
    }

    public static int getStatusBarHeight() {
        int result = dip2px(DEFAULT_STATUS_BAR_HEIGHT_DP);
        int resourceId = mContext.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 判断设备是否存在NavigationBar
     *
     * @return true 存在, false 不存在
     */
    private static boolean hasNavigationBar() {
        boolean haveNav = false;
        try {
            //1.通过WindowManagerGlobal获取windowManagerService
            // 反射方法：IWindowManager windowManagerService = WindowManagerGlobal.getWindowManagerService();
            Class<?> windowManagerGlobalClass = Class.forName("android.view.WindowManagerGlobal");
            Method getWmServiceMethod = windowManagerGlobalClass.getDeclaredMethod("getWindowManagerService");
            getWmServiceMethod.setAccessible(true);
            //getWindowManagerService是静态方法，所以invoke null
            Object iWindowManager = getWmServiceMethod.invoke(null);

            //2.获取windowMangerService的hasNavigationBar方法返回值
            // 反射方法：haveNav = windowManagerService.hasNavigationBar();
            Class<?> iWindowManagerClass = iWindowManager.getClass();
            Method hasNavBarMethod = iWindowManagerClass.getDeclaredMethod("hasNavigationBar");
            hasNavBarMethod.setAccessible(true);
            haveNav = (Boolean) hasNavBarMethod.invoke(iWindowManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return haveNav;
    }
}