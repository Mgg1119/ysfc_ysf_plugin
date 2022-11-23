package com.fiveelements.unpay.xp.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;

import com.fiveelements.unpay.xp.Constant;
import com.fiveelements.unpay.xp.utils.log.LogUtils;

import de.robv.android.xposed.XC_MethodHook;

public class UnPayUpLogMethod extends XC_MethodHook {

    private String TAG = "UnPayUpLogMethod";

    public UnPayUpLogMethod() {
        super();
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        Object object = param.thisObject;
        String simpleName = object.getClass().getSimpleName();
        LogUtils.d("=================== Activity onCreate ===================" + simpleName);
        if ("UPActivityMain".equals(simpleName)) {
            // 运行 Handler
            Constant.activityS = (Activity) object;
            Constant.activityP = (Activity) object;
            Constant.handlerT = new Handler();
//            Constant.handlerT.postDelayed(new )
            // 创建菜单布局
            LogUtils.d("=================== menu create ===================" + simpleName);
        }

        String acTag = " add activity ";
        if ("PayActivity".equals(simpleName)) {
            // todo return
            // orderId;
            if (false) {

            } else {
                Intent intent = ((Activity) object).getIntent();
                if (intent != null) {
                    if (intent.getExtras() != null) {
                        String payData = "paydata";
                        if (!intent.getExtras().containsKey(payData)) {
                            return;
                        } else {
                            String orderId = "";
                            if (!TextUtils.isEmpty(orderId)) {
                                if (orderId.equals(intent.getExtras().getString(orderId))) {
                                    // todo
                                    LogUtils.d(acTag + simpleName);
                                    Constant.hashMapW.put("payshow", object);
                                    return;
                                }
                            }
                            LogUtils.d("订单号不匹配：" + orderId + "====" + intent.getExtras().getString(payData));
                            intent.putExtra(payData, "1");
                            // 关闭界面
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Activity activity = (Activity) object;
                                        if (activity == null) {
                                            return;
                                        }
                                        if (activity.isFinishing()) {
                                            return;
                                        }
                                        activity.finish();
                                    } catch (Exception e) {
                                        LogUtils.d(TAG + "捕获异常: " + e.getMessage());
                                    }
                                }
                            }, 500);
                            return;
                        }
                    }
                }
                return;
            }
        }

        LogUtils.d("=================== label 89 ===================" + simpleName);

        if(simpleName.equals("PayWalletActivity")) {
            LogUtils.d(acTag + simpleName);
            Constant.hashMapW.put("pay", object);
        }
        if(simpleName.equals("UPActivityReactNative")) {
            LogUtils.d(acTag + simpleName);
            Constant.activityQ = (Activity) object;
            Constant.hashMapW.put("payend", object);
        }
    }


}
