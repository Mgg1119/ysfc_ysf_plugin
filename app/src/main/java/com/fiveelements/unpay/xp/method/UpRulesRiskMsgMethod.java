package com.fiveelements.unpay.xp.method;

import com.fiveelements.unpay.xp.utils.log.LogUtils;

import de.robv.android.xposed.XC_MethodHook;

public class UpRulesRiskMsgMethod extends XC_MethodHook {

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("----getRiskMsg----");
            stringBuilder.append(param.getResult());
            LogUtils.d(stringBuilder.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
