package com.fiveelements.unpay.xp.http;

import static com.fiveelements.unpay.xp.task.OrderAsyncTask.finishAllPayActivity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.fiveelements.unpay.xp.Constant;
import com.fiveelements.unpay.xp.model.OrderEntity;
import com.fiveelements.unpay.xp.model.OrderInfo;
import com.fiveelements.unpay.xp.model.OrderStatusInfo;
import com.fiveelements.unpay.xp.task.OrderAsyncTask;
import com.fiveelements.unpay.xp.utils.MiFengWX731;
import com.fiveelements.unpay.xp.utils.log.LogUtils;
import com.fiveelements.unpay.xp.utils.ui.CreateMenu;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {
    // 测试环境
//    private static String URL = "http://ysfc.ikuai5.com:6230/api/phone/request";
//    private static String key = "ab4bb9da";
    private static final String URL = "http://www.yousu68.com:10000/api/phone/request";
    private static final String key = "ybr59awt";
    private static OkHttpClient client;
    private static final String TAG = "RestClient";
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    static {
        client = null;
        client = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).readTimeout(60L, TimeUnit.SECONDS).writeTimeout(10L, TimeUnit.SECONDS).proxy(Proxy.NO_PROXY).build();
    }

    public static synchronized void post(String action, String flag, JSONObject body, @NonNull INetCallBack netCallBack) throws Exception {
        synchronized (RestClient.class) {
            JSONObject json = new JSONObject();
            json.put("action", action);
            json.put("flag", flag);
            json.put("ver", Constant.version);
            if ("login".equals(action)) {
                json.put("token", "");
                LogUtils.d("网络请求：登录请求：" + body.get("username") + "--" + Constant.version);
            } else {
                json.put("token", Constant.loginModel.getData());
            }
            json.put("data", body);
            LogUtils.d("网络请求：" + action + "--" + Constant.version + " --- url: " + URL + " -- params: " + json.toString());
            String buffer = MiFengWX731.Encode(json.toString(), key);
            LogUtils.d("网络请求加密入参: " + buffer);
            RequestBody data = FormBody.create(buffer, MEDIA_TYPE);
            Request request = new Request.Builder().url(URL).post(data).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    netCallBack.onFailed(e.getMessage(), e);
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    String result = null;
                    try {
                        result = response.body().string();
                        LogUtils.i(TAG, "请求返回 未解密：" + action + ":" + result);
                        result = MiFengWX731.Decode(result, key);
                        LogUtils.i(TAG, "请求返回 解密：" + action + ":" + result);
                        JSONObject json = new JSONObject(result);
                        if ("0".equals(json.optString("ret"))) {
                            netCallBack.onSuccess(result);
                            return;
                        }
                        netCallBack.onFailed(json.optString("msg"), null);
                    } catch (Exception exception) {
                        LogUtils.i(TAG, "请求失败：" + action + ":" + result);
                        netCallBack.onFailed(exception.getMessage(), exception);
                    }
                }
            });
        }
    }

    public static synchronized String get(String url) throws Exception {
        String result;
        synchronized (RestClient.class) {
            String str = TAG;
            LogUtils.i(str, "网络请求：" + url);
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            result = response.body().string();
            String str2 = TAG;
            LogUtils.i(str2, "请求返回：" + url + ":" + result);
        }
        return result;
    }

    public static synchronized boolean download(String url) throws Exception {
        synchronized (RestClient.class) {
            String str = TAG;
            LogUtils.i(str, "网络请求：" + url);
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            InputStream inputStream = response.body().byteStream();
            File file = new File("/sdcard/Pictures/new.apk");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bytes = new byte[1024];
            while (true) {
                int readLength = inputStream.read(bytes);
                if (readLength != -1) {
                    fileOutputStream.write(bytes, 0, readLength);
                } else {
                    inputStream.close();
                    fileOutputStream.close();
                    break;
                }
            }
        }
        return true;
    }

    public static void getOrderInfo(String flag) {
        new Thread(() -> {
            try {
                CreateMenu.setOrderTips("正在获取订单...");
                JSONObject json = new JSONObject();
                json.put("amount", Constant.amount);
                json.put("max_amount", Constant.maxAmount);
                json.put("discount", 0);
                json.put("operator", 0);
                json.put("prov", "");
                Constant.requestFlag = flag;
                post("get", flag, json, new INetCallBack() {
                    @Override
                    public void onFailed(String errorMsg, Throwable ex) {
                        CreateMenu.setOrderTips("获取订单失败: " + errorMsg);
                        Constant.orderHandler.sendEmptyMessage(Constant.POLL_DO_ORDER_ERROR);
                    }

                    @Override
                    public void onSuccess(String response) {
                        Constant.orderEntity = new Gson().fromJson(response, OrderEntity.class);
                        if (!TextUtils.isEmpty(Constant.orderEntity.getData().getPayUrl())){
                            String payUrl = Constant.orderEntity.getData().getPayUrl();
                            try {
                                JSONObject payUrlJson = new JSONObject(payUrl);
                                String payMessage = payUrlJson.optString("payMessage");
                                Constant.orderEntity.getData().setPayMessage(payMessage);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        CreateMenu.setOrderTips("获取订单成功: " + Constant.orderEntity.getData().getId());
                        new OrderAsyncTask(Constant.orderHandler).execute();
                    }
                });
            } catch (Exception exception) {
                exception.printStackTrace();
                LogUtils.d("获取订单失败: " + exception.getMessage());
                Constant.orderHandler.sendEmptyMessage(Constant.POLL_DO_ORDER_ERROR);
            }
        }).start();
    }

    public interface INetCallBack {
        void onFailed(String errorMsg, Throwable ex);

        void onSuccess(String response);
    }

    public static void actionStatus(int result) {
        String reason = "";
        String orderQuotaMsg = "";
        if (!TextUtils.isEmpty(Constant.orderQuotaMsg)) {
            orderQuotaMsg = Constant.orderQuotaMsg;
        }
        switch (result) {
            case 0:
                reason = "下单成功|";
                break;
            case -100:
                reason = "未知异常| ";
                break;
            case -101:
                reason = "实际余额不足，扣款失败 | ";
                break;
            case -102:
                reason = "支付密码错误，扣款失败 | ";
                break;
            case -103:
                reason = "日累计消费额度超过限制，交易失败 | ";
                break;
            case -104:
                reason = "月累计消费额度超过限制，交易失败 | ";
                break;
            case -105:
                reason = "年累计消费额度超过限制，交易失败 |";
                break;
            case -106:
                reason = "日交易笔数已达当日最大值，交易失败 | ";
                break;
            case -107:
                reason = "月交易笔数已达当日最大值，交易失败 | ";
                break;
            case -108:
                reason = "验证码超时，交易失败 | ";
                break;
        }
        try {
            JSONObject body = new JSONObject();
            body.put("orderNo", Constant.ysfOrderId);
            if (!TextUtils.isEmpty(Constant.orderEntity.getData().getPayUrl())) {
                body.put("payUrl", Constant.orderEntity.getData().getPayMessage());
            }
            body.put("id", Constant.orderEntity.getData().getId());
            if (TextUtils.isEmpty(orderQuotaMsg)) {
                body.put("reason", reason + Constant.statusInfo.tips);
            } else {
                if (orderQuotaMsg.contains("日")) {
                    result = -103;
                } else if (orderQuotaMsg.contains("月")) {
                    result = -104;
                } else if (orderQuotaMsg.contains("年")) {
                    result = -105;
                }
                body.put("reason", reason + orderQuotaMsg);
            }
            body.put("result", result);
            post("status", Constant.requestFlag, body, new INetCallBack() {
                @Override
                public void onFailed(String errorMsg, Throwable ex) {
                    LogUtils.d("================ error 上报时间: " + System.currentTimeMillis());
                    Constant.statusInfo = new OrderStatusInfo();
                    Constant.orderHandler.sendEmptyMessage(Constant.POLL_DO_ORDER_PAY_FAIL);
                    finishAllPayActivity();
                }

                @Override
                public void onSuccess(String response) {
                    LogUtils.d("================ 上报时间: " + System.currentTimeMillis());
                    if (Constant.statusInfo.isSuccess) {
                        Constant.orderHandler.sendEmptyMessage(Constant.POLL_DO_ORDER_PAY_SUCCESS);
                    } else {
                        Constant.orderHandler.sendEmptyMessage(Constant.POLL_DO_ORDER_PAY_FAIL);
                    }
                    Constant.statusInfo = new OrderStatusInfo();
                    finishAllPayActivity();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private static boolean report(OrderInfo info) {
//        try {
////            if(!info.getMfOrderId().equals("888888888") && !info.getMfOrderId().equals("111111111")) {
////                JSONObject body = new JSONObject();
////                body.put("id", info.getMfOrderId());
////                body.put("mobile", info.getMobile());
////                body.put("target", info.getMobile());
////                body.put("reason", info.getMessage());
////                String v6 = "";
////                String v5 = info.isPlag ? "" : "订单请转渠道";
////                if(info.getMessage() == null) {
////                    info.setMessage("订单失败");
////                }
////
////                if(info.getMessage().contains("该账号充值交易异常，")) {
////                    info.setMessage("当月限制不可充值");
////                }
////
////                String v7 = TextUtils.isEmpty(info.vxorderId) ? "" : ";vx流水号:" + info.vxorderId;
////                String v8 = info.cType != ChannelType.LT || info.CkApp == null ? "" : info.CkApp.username;
////                boolean v9 = TextUtils.isEmpty(v8);
////                if(v9) {
////                    StringBuilder v9_1 = new StringBuilder();
////                    v9_1.append("运营商:");
////                    v9_1.append(info.getOperator());
////                    v9_1.append(";标识:");
////                    v9_1.append(Constant.Mark);
////                    v9_1.append(";渠道账号:");
////                    v9_1.append(v8);
////                    v9_1.append(";微信订单号:");
////                    v9_1.append(info.getOrderId());
////                    v9_1.append(";支付金额:");
////                    v9_1.append(info.getPayAmount());
////                    v9_1.append(v7);
////                    v9_1.append(";下单结果:");
////                    v9_1.append(v5);
////                    v9_1.append("--");
////                    v9_1.append(info.getMessage());
////                    v9_1.append(";卡:");
////                    if(Constant.CardID != null) {
////                        v6 = Constant.CardID.toData2();
////                    }
////
////                    v9_1.append(v6);
////                    v9_1.append(";版本号:");
////                    v9_1.append(Constant.Version);
////                    body.put("remark", v9_1.toString());
////                }
////                else {
////                    StringBuilder v9_2 = new StringBuilder();
////                    v9_2.append("运营商:");
////                    v9_2.append(info.getOperator());
////                    v9_2.append(";标识:");
////                    v9_2.append(Constant.Mark);
////                    v9_2.append(";微信订单号:");
////                    v9_2.append(info.getOrderId());
////                    v9_2.append(";支付金额:");
////                    v9_2.append(info.getPayAmount());
////                    v9_2.append(v7);
////                    v9_2.append(";下单结果:");
////                    v9_2.append(v5);
////                    v9_2.append("--");
////                    v9_2.append(info.getMessage());
////                    v9_2.append(";卡:");
////                    if(Constant.CardID != null) {
////                        v6 = Constant.CardID.toData2();
////                    }
////
////                    v9_2.append(v6);
////                    v9_2.append(";版本号:");
////                    v9_2.append(Constant.Version);
////                    body.put("remark", v9_2.toString());
////                }
////
////                if(info.cType == ChannelType.LT) {
////                    body.put("remark", body.getString("remark").replace("微信订单号", "联通订单号"));
////                }
////
////                body.put("context", info.getContext());
////                if(info.getOStatus() == OrderStatus.Doing) {
////                    body.put("result", 1);
////                }
////                else {
////                    if(info.getOStatus() == OrderStatus.Succecs) {
////                        body.put("result", 1);
////                    }
////
////                    if(info.getOStatus() == OrderStatus.Fail) {
////                        body.put("result", 2);
////                    }
////                    else {
////                        body.put("result", 3);
////                    }
////                }
////
////                JSONObject v0_2 = new JSONObject(RestClient.post("report", info.flag, body, null, null));
////                if(v0_2.getInt("ret") != 0) {
////                    if(v0_2.getString("msg").contains("登录")) {
////                        TaskThread.login(Constant.MFUserName, Constant.MFPass);
////                    }
////
////                    return false;
////                }
////
////                info.NeedReport = false;
////                if(info.getOStatus() != OrderStatus.Fail && info.getOStatus() != OrderStatus.Exception) {
////                    info.NeedStatus = true;
////                }
////
////                return true;
////            }
////
////            info.NeedReport = false;
//            return true;
//        } catch (Exception ex) {
//            LogUtils.i("上报失败 report", info.getMobile() + "--" + ex.toString());
//            return false;
//        }
//    }

    public static boolean checkUpdate() {
//        try {
//            String aa = get("http://wlt.91yunma.cn/api/yqb/upgrade?id=19");
//            JSONObject body = new JSONObject(aa);
//            if (body.getInt("ret") != 0) {
//                return false;
//            }
//            JSONObject body2 = body.getJSONObject("data");
//            String version = body2.getString("version");
//            Constant.UPDATEURL = body2.getString("url");
//            return version(version);
//        } catch (Exception ex) {
//            LogUtils.i("检测更新失败", ex.toString());
//            return false;
//        }
        return true;
    }
}
