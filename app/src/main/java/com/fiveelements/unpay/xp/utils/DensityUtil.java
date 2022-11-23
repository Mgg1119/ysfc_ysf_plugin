package com.fiveelements.unpay.xp.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * 屏幕单位转换
 */
public class DensityUtil {

    /**
     * DP 转 PX
     */
    public static int dpToPx(float dp, Activity activity) {
        if (dp == 0) return 0;
        float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * PX 转 DP
     */
    public static int pxToDp(float px, Activity activity) {
        float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取屏幕宽度
     */
    public static int getWidth(Activity activity){
        WindowManager wm = (WindowManager) activity.getApplication().getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(outMetrics);
            return outMetrics.widthPixels;
        }
        return 0;
    }

    /**
     * 获取屏幕高度
     */
    public static int getHeight(Activity activity){
        WindowManager wm = (WindowManager) activity.getApplication().getSystemService(Context.WINDOW_SERVICE);
        if (wm != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(outMetrics);
            return outMetrics.heightPixels;
        }
        return 0;
    }
}
