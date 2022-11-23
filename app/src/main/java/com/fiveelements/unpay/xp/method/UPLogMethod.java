package com.fiveelements.unpay.xp.method;

import static com.fiveelements.unpay.xp.Constant.initData;

import android.text.TextUtils;

import com.fiveelements.unpay.xp.Constant;
import com.fiveelements.unpay.xp.http.RestClient;
import com.fiveelements.unpay.xp.model.InitData;
import com.fiveelements.unpay.xp.utils.log.LogUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

import de.robv.android.xposed.XC_MethodHook;

public class UPLogMethod extends XC_MethodHook {

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        String args = (String) param.args[2];
        if (TextUtils.isEmpty(args)) {
            return;
        }
        upLog(args);
    }

    public static void upLog(String result) {
        try {
            boolean hasResp = result.startsWith("resp");
            String replaceResp = "";
            if (hasResp) {
                replaceResp = result.replace("resp is:", "").replace(" ", "").replace("\r", "").replace("\n", "");
            }
            if (!replaceResp.startsWith("{")) {
                return;
            }
            LogUtils.d("=========== uplog: " + result);
            StringBuilder paramBuilder = new StringBuilder();
            paramBuilder.append("cmd----:");
            paramBuilder.append(replaceResp);
            LogUtils.d(paramBuilder.toString());
            JSONObject jsonObject = new JSONObject(replaceResp);
            String cmd = jsonObject.optString("cmd", "");
            StringBuilder cmdBuilder = new StringBuilder();
            cmdBuilder.append("cmd:");
            cmdBuilder.append(cmd);
            LogUtils.d(cmdBuilder.toString());
            boolean hasCmd = TextUtils.isEmpty(cmd);
            if (hasCmd) {
                cmd = jsonObject.optString("title", "");
                if ((!TextUtils.isEmpty(cmd)) && (cmd.equals("付款详情"))) {
                    cmd = "init";
                } else {
                    return;
                }
            }
            if (!cmd.equals("getpaycards")) {
                if (cmd.equals("init")) {
                    String resp = jsonObject.optString("resp", "");
                    initData = new Gson().fromJson(replaceResp, InitData.class);
                    if (!TextUtils.isEmpty(resp)) {
                        String log = "初始化状态码： " + resp;
                        LogUtils.d(log);
                        if ("00".equals(resp)) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append("银行卡解析：");
                            stringBuilder.append(initData.getParams().getDefaultCard());
                            LogUtils.d(stringBuilder.toString());
                            if (!TextUtils.isEmpty(initData.getParams().getDefaultCard().getName())) {
                                Constant.bankName = initData.getParams().getDefaultCard().getName();
                            }
                        } else {
                            String msg = jsonObject.optString("msg", "支付拉起失败");
                            Constant.upLogErrorMsg = resp + " " + msg;
                            LogUtils.d(resp + " " + msg);
                        }
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("银行卡解析：");
                        stringBuilder.append(initData.getParams().getDefaultCard());
                        LogUtils.d(stringBuilder.toString());
                        if (!TextUtils.isEmpty(initData.getParams().getDefaultCard().getName())) {
                            Constant.bankName = initData.getParams().getDefaultCard().getName();
                        }
                    }
                } else if (cmd.equals("rules")) {
                    String resp = jsonObject.optString("resp");
                    LogUtils.d("切换支付方式状态码：" + resp);
                } else {
                    if ("query".equals(cmd)) {
                        JSONObject params = jsonObject.getJSONObject("param");
                        String status = params.optString("status");
                        if ("00".equals(status)) {
                            LogUtils.d("支付成功");
                            Constant.statusInfo.isSuccess = true;
                            Constant.statusInfo.type = 0;
                            Constant.statusInfo.tips = "支付成功";
                        } else if ("01".equals(status)) {
                            Constant.statusInfo.isSuccess = true;
                            Constant.statusInfo.tips = "支付成功";
                            Constant.statusInfo.type = 0;
                            LogUtils.d("支付成功");
                        } else if ("03".equals(status)) {
                            String errorMsg = jsonObject.optString("fail_msg", "下单结果失败");
                            Constant.statusInfo.isSuccess = false;
                            Constant.statusInfo.type = -100;
                            Constant.statusInfo.tips = errorMsg;
                            LogUtils.d(errorMsg);
                        } else {
                            String errorMsg = jsonObject.optString("fail_msg");
                            String stringBuilder = "未知的支付结果： " +
                                    status +
                                    errorMsg;
                            Constant.statusInfo.isSuccess = false;
                            Constant.statusInfo.type = -100;
                            Constant.statusInfo.tips = errorMsg;
                            LogUtils.d(stringBuilder);
                        }
                    } else if ("sms".equals(cmd)) {
                        LogUtils.d("需要短信验证");
                        Constant.statusInfo.isSuccess = false;
                        Constant.statusInfo.tips = "需要短信验证";
                    } else if ("verifyusrpaypwd".equals(cmd)) {
                        if (Constant.statusInfo.tips.contains("支付成功")) {
                            return;
                        }
                        String resp = jsonObject.optString("resp", "");
                        if (!TextUtils.isEmpty(resp) && resp.equals("00")) {
                            String failMsg = jsonObject.getJSONObject("params").optString("fail_msg");
                            if (!TextUtils.isEmpty(failMsg) && (failMsg.contains("支付密码不正确") || failMsg.contains("支付密码已被锁定"))) {
                                Constant.statusInfo.isSuccess = false;
                                Constant.statusInfo.type = -102;
                                Constant.statusInfo.tips = failMsg;
                            }
                        }
                    } else if ("pay".equals(cmd)) {
                        if (!jsonObject.optString("resp", "").equals("00")) {
                            String msg = jsonObject.optString("msg");
                            if ((!TextUtils.isEmpty(msg)) && (msg.contains("可能存在风险"))) {
                                Constant.statusInfo.isSuccess = false;
                                Constant.statusInfo.type = -100;
                                Constant.statusInfo.tips = msg;
                            }
                        } else {
                            Constant.statusInfo.isSuccess = true;
                            Constant.statusInfo.type = 0;
                            Constant.statusInfo.tips = "支付成功";
                        }
                    }
                }
            }
//            if (Constant.statusInfo.isSuccess && "支付成功".equals(Constant.statusInfo.tips)) {
//                handler.sendEmptyMessage(Constant.POLL_DO_ORDER_PAY_SUCCESS);
//            } else {
//                handler.sendEmptyMessage(Constant.POLL_DO_ORDER_PAY_FAIL);
//            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
