package com.fiveelements.unpay.xp.task;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fiveelements.unpay.xp.Constant;
import com.fiveelements.unpay.xp.http.HttpUtils;
import com.fiveelements.unpay.xp.http.RestClient;
import com.fiveelements.unpay.xp.model.UserInfo;
import com.fiveelements.unpay.xp.net.YSFRequestUtil;
import com.fiveelements.unpay.xp.utils.HookGetViewUtil;
import com.fiveelements.unpay.xp.utils.log.LogUtils;

import java.security.interfaces.ECPrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OrderAsyncTask extends AsyncTask<String, Integer, String> {

    private static final String TAG = "OrderAsyncTask: ";
    public static List<View> passwordViews;
    public static boolean inputDone = false;
    public static boolean isInputPassword = false;
    public static int PAY_SUCCESS = 0;
    public static int PAY_FAIL = 1;
    public Handler handler;

    public OrderAsyncTask(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected String doInBackground(String... strings) {
        if (TextUtils.isEmpty(Constant.orderEntity.getData().getPayMessage())) {
            // 云闪付支付渠道
            boolean isSuccess = YSFRequestUtil.queryMobileAndBannerInfo();
            if (!isSuccess) {
                setPayStatus(false, "下单失败");
                return "下单失败";
            }

            boolean isPaySuccess = YSFRequestUtil.prePayAndHandle();
            if (!isPaySuccess) {
                return "下单失败";
            }
        } else {
            Constant.ysfOrderId = Constant.orderEntity.getData().getOrderNo();
            Constant.ysfTn = Constant.orderEntity.getData().getPayMessage();
        }
        LogUtils.d("启动支付");
        String logBuilder = "订单 手机号码：" + Constant.orderEntity.getData().getMobile() +
                "------------orderId: " +
                Constant.ysfOrderId +
                " 开始支付";
        LogUtils.d(logBuilder);
        YSFRequestUtil.startPayActivity(Constant.activityP);
        if (payActionInfo()) {
            // 开始上报支付状态
            return "支付成功";
        } else {
            return "支付失败";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            Thread.sleep(2000L);
            Constant.handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Constant.activityP, s, Toast.LENGTH_LONG).show();
                }
            });
            RestClient.actionStatus(Constant.statusInfo.type);
        } catch (Exception exception) {
            handler.sendEmptyMessage(Constant.POLL_DO_ORDER_ERROR);
            exception.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("开始做单了: ");
        LogUtils.d(TAG + stringBuilder);
    }

    public static boolean payActionInfo() {
        // 将旧缓存数据清除 等待界面打开
        Constant.activityHashMap.clear();
        Constant.initData = null;
        isInputPassword = false;
        Constant.statusInfo.isSuccess = false;
        Constant.statusInfo.tips = "";
        int n = 0;
        while (true) {
            if (n >= 30) {
                break;
            }
            try {
                Thread.sleep(1000L);
                if (containsPayActivity()) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            n++;
        }
        // 查看银行卡信息是否存在
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500L);
                if (Constant.initData != null) {
                    break;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        if (Constant.initData == null) {
            LogUtils.d("支付方式获取错误");
            setPayStatus(false, "支付方式获取错误");
            return false;
        }
        // todo 切换付款方式
        LogUtils.d("等待支付");
        if (confirmPayViewClick()) {
            for (int i = 0; i < 60; i++) {
                try {
                    Thread.sleep(1000L);
                    // 查询支付状态
                    if (Constant.statusInfo.isSuccess) {
                        Thread.sleep(1000L);
                        break;
                    }
                    if (Constant.statusInfo.tips.contains("支付密码不正确") || Constant.statusInfo.tips.contains("支付密码已被锁定")) {
                        break;
                    }
                    if (Constant.statusInfo.tips.equals("需要短信验证")) {
                        break;
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            if (Constant.statusInfo.tips.equals("需要短信验证")) {
                for (int i = 0; i < 60; i++) {
                    try {
                        Thread.sleep(1000L);
                        if (Constant.statusInfo.isSuccess) {
                            Thread.sleep(2000L);
                            break;
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
            if (!Constant.statusInfo.isSuccess && Constant.statusInfo.tips.equals("需要短信验证")) {
                LogUtils.d("短信输入等待超时");
                finishAllPayActivity();
                try {
                    Thread.sleep(3000L);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            return Constant.statusInfo.isSuccess;
        }
        return false;
    }

    public static void finishAllPayActivity() {
        Constant.handler.post(new Runnable() {
            @Override
            public void run() {
                for (final String s : Constant.activityHashMap.keySet()) {
                    try {
                        final Activity activity = Constant.activityHashMap.get(s);
                        if (activity == null || activity.isFinishing()) {
                            continue;
                        }
                        activity.finish();
                    } catch (Exception ex) {
                    }
                }
            }
        });
    }

    public static void setPayStatus(boolean isSuccess, String tips) {
        Constant.statusInfo.isSuccess = isSuccess;
        Constant.statusInfo.tips = tips;
    }

    public static boolean containsPayActivity() {
        boolean containsKey = Constant.activityHashMap.containsKey("pay");
        boolean hasView = false;
        if (containsKey) {
            if (HookGetViewUtil.getView(Constant.activityHashMap.get("pay"), "付款详情") != null) {
                hasView = true;
            }
            return hasView;
        }
        return false;
    }

    public static boolean confirmPayViewClick() {
        try {
            Constant.handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        View view = HookGetViewUtil.getView(Constant.activityHashMap.get("pay"), "确认付款");
                        StringBuilder builder = new StringBuilder();
                        builder.append("确认付款 perform: ");
                        builder.append(view);
                        LogUtils.d(builder.toString());
                        if (view != null) {
                            view.performClick();
                        }
                    } catch (Exception e) {
                    }
                }
            });
            Thread.sleep(1500L);
            View view = HookGetViewUtil.getView(Constant.activityHashMap.get("pay"), "请输入支付密码");
            StringBuilder builder = new StringBuilder();
            builder.append("请输入支付密码 performClick: ");
            builder.append(view);
            LogUtils.d(builder.toString());
            inputDone = false;
            isInputPassword = false;
            if (view != null) {
                passwordViews = getPasswordView();
                if (passwordViews == null || passwordViews.size() != 6) {
                    Thread.sleep(1500);
                    passwordViews = getPasswordView();
                }
                if (passwordViews != null && passwordViews.size() == 6) {
                    Constant.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                isInputPassword = true;
                                for (View passwordView : passwordViews) {
                                    passwordView.performClick();
                                }
                                inputDone = true;
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                    });
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(1000L);
                        if (inputDone) {
                            break;
                        }
                    }
                    return isInputPassword;
                }
                return false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public static List<View> getPasswordView() {
        try {
            final ArrayList<View> list = new ArrayList<View>();
            for (char str : Constant.passwordArrays.toCharArray()) {
                final Activity activity = Constant.activityHashMap.get("pay");
                String sb = str + "";
                final View view = HookGetViewUtil.getView(activity, sb);
                if (view == null || !view.isClickable()) {
                    return null;
                }
                list.add(view);
            }
            if (list.size() == 6) {
                return list;
            }
        } catch (Exception ex) {
        }
        return null;
    }
}
