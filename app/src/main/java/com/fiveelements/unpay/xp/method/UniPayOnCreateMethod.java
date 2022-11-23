package com.fiveelements.unpay.xp.method;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.fiveelements.unpay.xp.Constant;
import com.fiveelements.unpay.xp.http.RestClient;
import com.fiveelements.unpay.xp.task.OrderAsyncTask;
import com.fiveelements.unpay.xp.utils.log.LogUtils;
import com.fiveelements.unpay.xp.utils.ui.CreateMenu;

import de.robv.android.xposed.XC_MethodHook;

public class UniPayOnCreateMethod extends XC_MethodHook {

    @SuppressLint("HandlerLeak")
    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        Object object = param.thisObject;
        String simpleName = object.getClass().getSimpleName();
        Activity activity = (Activity) object;
        Intent activityIntent = activity.getIntent();
        if (simpleName.equals("UPActivityMain")) {
            Constant.activityP = activity;
            // 创建布局
            Constant.handler = new Handler();
            Constant.orderHandler = new Handler() {
                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    boolean isSuccess = false;
                    String flag = "";
                    if (Constant.channelList.size() > 1) {
                        flag = Constant.requestFlag.equals(Constant.JDYSF_FLAG) ? Constant.YSF_FLAG : Constant.JDYSF_FLAG;
                    } else if (Constant.channelList.size() == 1) {
                        flag = Constant.requestFlag.equals(Constant.JDYSF_FLAG) ? Constant.JDYSF_FLAG : Constant.YSF_FLAG;
                    }
                    if (TextUtils.isEmpty(flag)) {
                        CreateMenu.setOrderTips("支付渠道获取失败，请重新做单.");
                        return;
                    }
                    if (msg.what == Constant.POLL_DO_ORDER_PAY_SUCCESS) {
                        /**
                         *  1、订单金额累加
                         *  2、做单次数增加
                         *  3、上报结果
                         *  4、清除已完成订单缓存
                         */
                        CreateMenu.setOrderTips("做单成功.");
                        Constant.allOrderPrice += Integer.parseInt(Constant.orderEntity.getData().getAmount());
                        Constant.doOrderNum += 1;
                        Constant.orderEntity = null;
                        isSuccess = true;
                        LogUtils.d("===============支付成功");
                    } else if (msg.what == Constant.POLL_DO_ORDER_PAY_FAIL) {
                        /**
                         * 1、上报结果
                         * 2、清除订单缓存
                         */
                        CreateMenu.setOrderTips("做单失败.");
                        LogUtils.d("===============支付失败");
                        Constant.orderEntity = null;
                    } else if (msg.what == Constant.POLL_DO_ORDER_ERROR) {
                        // 获取订单失败
                        Constant.orderEntity = null;
                        CreateMenu.setOrderTips("订单获取失败.");
                    }
                    if (!TextUtils.isEmpty(Constant.orderQuotaMsg)) {
                        Constant.isStartGetOrder = false;
                        CreateMenu.setOrderTips(Constant.orderQuotaMsg);
                        return;
                    }
                    if (!Constant.isStartGetOrder) {
                        return;
                    }
                    if (Constant.doOrderNum >= Constant.stopNum) {
                        CreateMenu.setOrderTips("已完成" + Constant.doOrderNum + "笔订单");
                        CreateMenu.closeSwitch();
                        Constant.isStartGetOrder = false;
                        return;
                    }
                    if (Constant.allOrderPrice >= Constant.stopPrice) {
                        CreateMenu.setOrderTips("已完成" + Constant.allOrderPrice + "元");
                        CreateMenu.closeSwitch();
                        Constant.isStartGetOrder = false;
                        return;
                    }
                    if (isSuccess) {
                        String finalFlag = flag;
                        Constant.orderHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RestClient.getOrderInfo(finalFlag);
                            }
                        }, Constant.orderInterval * 1000L);
                    } else {
                        // 支付失败，获取订单失败，如为继续做单情况下延迟2s继续获取订单
                        String finalFlag1 = flag;
                        Constant.orderHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RestClient.getOrderInfo(finalFlag1);
                            }
                        }, 2000L);
                    }

                }
            };
            Constant.handler.postDelayed(() -> CreateMenu.initMenu(activity), 5000);
        }
        if (simpleName.equals("PayActivity")) {
            if (activityIntent != null && activityIntent.getExtras() != null) {
                // 不含有支付数据
                if (!activityIntent.getExtras().containsKey("paydata")) {
                    return;
                }
                String payData = activityIntent.getExtras().getString("paydata");
                if (!TextUtils.isEmpty(payData) && Constant.ysfTn.equals(payData)) {
                    // 订单数据
                    LogUtils.d("add activity " + simpleName);
                    Constant.activityHashMap.put("payshow", activity);
                } else {
                    StringBuilder logBuilder = new StringBuilder();
                    logBuilder.append("订单号不匹配： ");
                    logBuilder.append(Constant.ysfTn);
                    logBuilder.append("===============: ");
                    logBuilder.append(payData);
                    activityIntent.putExtra("paydata", "1");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if (!activity.isFinishing()) {
                                    activity.finish();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, 500);
                }
            } else {
                return;
            }
        }
        if (simpleName.equals("PayWalletActivity")) {
            LogUtils.d("add activity " + simpleName);
            Constant.activityHashMap.put("pay", activity);
        }
        if (simpleName.equals("UPActivityReactNative")) {
            LogUtils.d("add activity " + simpleName);
            Constant.activityHashMap.put("payend", activity);
        }
    }
}
