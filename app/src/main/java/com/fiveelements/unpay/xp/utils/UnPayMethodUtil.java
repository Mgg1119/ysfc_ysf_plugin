package com.fiveelements.unpay.xp.utils;

import android.app.Activity;
import android.content.Context;

import java.util.List;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class UnPayMethodUtil {
    
    public static void findAndHookMethod(XC_LoadPackage.LoadPackageParam params) {
        XposedBridge.hookAllMethods(Activity.class, "onCreate", new UnPayUpLogMethod());
//        XposedHelpers.findAndHookMethod("com.unionpay.utils.UPLog", params.classLoader, "a", new Object[]{Integer.TYPE, String.class, String.class, Throwable.class, new b$a()});
//        XposedHelpers.findAndHookMethod("com.unionpay.utils.IJniInterface", params.classLoader, "d", new Object[]{String.class, new b$b()});
//        XposedHelpers.findAndHookMethod("com.unionpay.mobile.pay.utils.l", params.classLoader, "a", new Object[]{Integer.TYPE, String.class, String.class, new b$c()});
//        XposedHelpers.findAndHookConstructor("com.unionpay.mobile.pay.adapter.b", params.classLoader, new Object[]{Context.class, List.class, Integer.TYPE, new b$d(params)});
//        XposedHelpers.findAndHookConstructor("com.unionpay.mobile.pay.fragment.UPFragmentCardList", params.classLoader, new Object[]{new b$e()});
//        XposedHelpers.findAndHookMethod("com.unionpay.mobile.pay.model.UPRules", params.classLoader, "getRiskMsgStyle", new Object[]{new b$f()});
//        XposedHelpers.findAndHookMethod("com.unionpay.mobile.pay.model.UPRules", params.classLoader, "getRiskMsg", new Object[]{new b$g()});
    }
}
