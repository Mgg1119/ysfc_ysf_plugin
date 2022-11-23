package com.fiveelements.unpay.xp.method;

import static com.fiveelements.unpay.xp.method.UPLogMethod.upLog;

import android.text.TextUtils;

import de.robv.android.xposed.XC_MethodHook;

public class UtilsLMethod extends XC_MethodHook {

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        try {
            if ((Integer) (param.args[0]) == 6) {
                return;
            }
            String args = (String) param.args[2];
            if (TextUtils.isEmpty(args)) {
                return;
            }
            upLog(args);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
