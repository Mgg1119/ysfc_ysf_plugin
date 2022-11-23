package com.fiveelements.unpay.xp.utils;

import android.text.TextUtils;

import com.fiveelements.unpay.xp.Constant;

import de.robv.android.xposed.XC_MethodHook;

public class UnPayUpLogChildMethod extends XC_MethodHook {

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        try {
            Object object = param.args[2];
            if (TextUtils.isEmpty((CharSequence) object)) {
                return;
            }
            if (Constant.orderInfo == null) {
                return;
            }

            if (!Constant.orderInfo.isIspwd()) {
                return;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
