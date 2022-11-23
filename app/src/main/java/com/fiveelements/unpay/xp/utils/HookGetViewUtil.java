package com.fiveelements.unpay.xp.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.fiveelements.unpay.xp.utils.log.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class HookGetViewUtil {

    public static View getView(Activity activity, String params) {
        try {
            LogUtils.d("view ---------- in ----------");
            StringBuilder builder = new StringBuilder();
            builder.append("view -logViewma:");
            builder.append(params);
            for (View view : getViewList(activity.getWindow().getDecorView())) {
                try {
                    Object viewObject = ClassLoaderUtils.hookGetClass(view, "getText", null);
                    if (viewObject != null) {
                        if (viewObject.toString().equals(params)) {
                            return view;
                        }
                    }
                } catch (Exception e) {
                    StringBuilder builder1 = new StringBuilder();
                    builder1.append("for error: ");
                    builder1.append(e.getMessage());
                    LogUtils.d(builder.toString());
                }
            }
        } catch (Exception exception) {
            StringBuilder builder = new StringBuilder();
            builder.append("getAllChildViews error: ");
            builder.append(exception.getMessage());
            LogUtils.d(builder.toString());
        }
        return null;
    }

    public static List<View> getViewList(View paramView) {
        ArrayList localArrayList = new ArrayList();
        if ((paramView instanceof ViewGroup)) {
            ViewGroup localViewGroup = (ViewGroup) paramView;
            for (int i = 0; i < localViewGroup.getChildCount(); i++) {
                paramView = localViewGroup.getChildAt(i);
                localArrayList.add(paramView);
                localArrayList.addAll(getViewList(paramView));
            }
        }
        return localArrayList;
    }
}
