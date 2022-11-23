package com.fiveelements.unpay.xp.net;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.fiveelements.unpay.xp.Constant;
import com.fiveelements.unpay.xp.http.HttpUtils;
import com.fiveelements.unpay.xp.model.BillDetailInfo;
import com.fiveelements.unpay.xp.model.PayAndHandleInfo;
import com.fiveelements.unpay.xp.model.UserInfo;
import com.fiveelements.unpay.xp.utils.ClassLoaderUtils;
import com.fiveelements.unpay.xp.utils.InvokeUtils;
import com.fiveelements.unpay.xp.utils.log.LogUtils;
import com.fiveelements.unpay.xp.utils.ui.CreateMenu;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class YSFRequestUtil {

    public static String queryMobileAndBannerInfoUrl = "https://life.95516.com/life/inApp/sjcz/queryMobileAndBannerInfo";
    public static String prePayAndHandleUrl = "https://life.95516.com/life/inApp/sjcz/prePayAndHandle";
    public static String userGetUrl = "https://base.95516.com/app/inApp/user/get";


    /**
     * 获取银行卡信息
     *
     * @return
     */
    public static boolean getUnPayUserInfo() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("currentPage", 1);
            jsonObject.put("types", "43,45");
            jsonObject.put("orderStatus", "");
            jsonObject.put("year", "");
            ClassLoader classLoader = Constant.activityP.getClassLoader();
            String str = (String) ClassLoaderUtils.invokeMethod("com.unionpay.utils.IJniInterface", "c", new Object[]{"android"}, classLoader);
            String str2 = (String) ClassLoaderUtils.invokeMethod("com.unionpay.utils.IJniInterface", "c", new Object[]{"27"}, classLoader);
            Map mapData = getLoginMapData(userGetUrl);
            if (mapData == null) {
                setTips(false, "数据初始化失败");
            }
            StringBuilder paramsBuilder = new StringBuilder();
            paramsBuilder.append(userGetUrl);
            paramsBuilder.append("?osName=");
            paramsBuilder.append(str);
            paramsBuilder.append("&osVersion=");
            paramsBuilder.append(str2);
            HttpUtils httpUtils = new HttpUtils();
            String encResult = httpUtils.GetRequest(paramsBuilder.toString(), (HashMap<String, String>) mapData, "UTF-8", false);
            String decResult = (String) ClassLoaderUtils.invokeMethod("com.unionpay.utils.IJniInterface", "d", new Object[]{encResult}, classLoader);
            Constant.userInfo = new Gson().fromJson(decResult, UserInfo.class);
            if (!"00".equals(Constant.userInfo.getResp())) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("获取数据失败 getLoginMobile error:");
                stringBuilder.append(decResult);
                LogUtils.d(stringBuilder.toString());
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            StringBuilder errLog = new StringBuilder();
            errLog.append("YSF getLoginMobile 获取失败 error:");
            errLog.append(e.getMessage());
            LogUtils.d(errLog.toString());
            return false;
        }
    }

    public static boolean queryMobileAndBannerInfo() {
        String denomination = Constant.orderEntity.getData().getAmount() + "元";
        LogUtils.d("===============充值号码： " + Constant.orderEntity.getData().getMobile() + " 充值金额: " + denomination);
        String billDetailStr = "";
        Constant.OrderRequestJson = null;
        try {
            JSONObject requestJson = new JSONObject();
            requestJson.put("type", "43");
            requestJson.put("bussCode", "I1_9800_000B");
            JSONObject billQueryInfo = new JSONObject();
            billQueryInfo.put("usr_num", Constant.orderEntity.getData().getMobile());
            billQueryInfo.put("signature", "");
            requestJson.put("billQueryInfo", billQueryInfo.toString());
            ClassLoader classLoader = Constant.activityP.getClassLoader();
            String iJniKey = (String) ClassLoaderUtils.invokeMethod("com.unionpay.utils.IJniInterface", "c",
                    new Object[]{requestJson.toString()}, classLoader);
            Map mobileInfoRequest = getRequestMap(queryMobileAndBannerInfoUrl, iJniKey);
            if (mobileInfoRequest == null) {
                LogUtils.d("数据初始化失败");
                return false;
            }
            HttpUtils httpUtils = new HttpUtils();
            LogUtils.d("===================: " + mobileInfoRequest);
            String encResult = httpUtils.Post(YSFRequestUtil.queryMobileAndBannerInfoUrl, iJniKey, (HashMap) mobileInfoRequest, "UTF-8", false);
            String decResult = (String) ClassLoaderUtils.invokeMethod("com.unionpay.utils.IJniInterface", "d", new Object[]{encResult}, classLoader);
            JSONObject jsonStr = new JSONObject(decResult);
            if (!jsonStr.optString("resp").equals("00")) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("获取数据失败 getMobileCharge result:");
                stringBuilder.append(decResult);
                LogUtils.d(stringBuilder.toString());
                return false;
            } else {
                billDetailStr = jsonStr.getJSONObject("params").optString("billDetailInfo");
                Gson gson = new Gson();
                if (TextUtils.isEmpty(billDetailStr)) {
                    CreateMenu.setOrderTips("查询账单失败，请您稍后再试");
                    return false;
                }
                Constant.billDetailInfo = gson.fromJson(billDetailStr, BillDetailInfo.class);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("获取号码运营商信息：");
                for (BillDetailInfo.FormDTO formDTO : Constant.billDetailInfo.getForm()) {
                    if ("caller_loc".equals(formDTO.getName())) {
                        // 运营商信息
                        stringBuilder.append(formDTO.getValue());
                        LogUtils.d(stringBuilder.toString());
                    }
                    if ("bill_id".equals(formDTO.getName())) {
                        StringBuilder stringBuilder1 = new StringBuilder();
                        // 面值匹配
                        for (BillDetailInfo.FormDTO.OptionsDTO item : formDTO.getOptions()) {
                            if (denomination.equals(item.getDenomination())) {
                                Constant.OrderRequestJson = new JSONObject();
                                stringBuilder1.append("匹配到面值: ");
                                stringBuilder1.append(item.getDenomination());
                                Constant.OrderRequestJson.put("amount", item.getAmount());
                                Constant.OrderRequestJson.put("denomination", item.getDenomination());
                                Constant.OrderRequestJson.put("costPrice", item.getCostPrice());
                                Constant.OrderRequestJson.put("channelType", Constant.billDetailInfo.getChannelType());
                                Constant.OrderRequestJson.put("province", Constant.billDetailInfo.getProvince());
                                Constant.OrderRequestJson.put("operator", Constant.billDetailInfo.getOperator());
                                break;
                            }
                        }
                    }
                }
                if (Constant.OrderRequestJson == null) {
                    Constant.statusInfo.isSuccess = false;
                    Constant.statusInfo.tips = "面值暂时无货";
                    String logBuilder = "getMobileCharge result:" + billDetailStr;
                    LogUtils.d(logBuilder);
                    return false;
                }
                return true;
            }
        } catch (Exception e) {
            StringBuilder errorBuilder = new StringBuilder();
            Constant.statusInfo.tips = "号码验证异常失败";
            errorBuilder.append("YSF getMobileCharge 获取失败 error:");
            errorBuilder.append(e.getMessage());
            errorBuilder.append("----------- ");
            errorBuilder.append(billDetailStr);
            LogUtils.d(errorBuilder.toString());
            return false;
        }
    }

    public static Map getRequestMap(String urlPath, String key) {
        try {
            ClassLoader classLoader = Constant.activityP.getClassLoader();
            Object uPIDInstance = ClassLoaderUtils.getConstructorInstance("com.unionpay.network.model.UPID", new Class[]{Integer.TYPE},
                    new Object[]{141}, classLoader);
            ClassLoaderUtils.setFiled(uPIDInstance, "isNetWorkError", Boolean.FALSE);
            ClassLoaderUtils.setFiled(uPIDInstance, "mAdditionalData", 1002);
            ClassLoaderUtils.setFiled(uPIDInstance, "mShowError", Boolean.FALSE);
            ClassLoaderUtils.setFiled(uPIDInstance, "mUrl", urlPath);
            Object localObject2 = ClassLoaderUtils.getNetWorkField("com.unionpay.network.EncryptValue$Encrypt", "VID", classLoader);

            Class localClass = classLoader.loadClass("com.unionpay.network.e");
            Object proxyInstance = Proxy.newProxyInstance(classLoader, new Class[]{localClass}, new InvokeUtils());

            Object netWorkYaMethodResult = ClassLoaderUtils.invokeNetWorkYaMethod("com.unionpay.network.y", "a",
                    new Class[]{uPIDInstance.getClass(), String.class, localObject2.getClass(), String.class, Class.class, Integer.TYPE, localClass},
                    new Object[]{uPIDInstance, urlPath, localObject2, key, null, 30000, proxyInstance}, classLoader);
            if (netWorkYaMethodResult == null) {
                return null;
            }
            return (Map) ClassLoaderUtils.invokeJ(netWorkYaMethodResult, "j", null);
        } catch (Exception er) {
            er.printStackTrace();
            StringBuilder builder = new StringBuilder();
            builder.append("request:error");
            builder.append(er.getMessage());
            System.out.println(builder);
        }
        return null;
    }

    public static Map getLoginMapData(String params) {
        try {
            ClassLoader classLoader = Constant.activityP.getClassLoader();
            Object localObject1 = ClassLoaderUtils.getConstructorInstance("com.unionpay.network.model.UPID", new Class[]{Integer.TYPE}, new Object[]{141}, classLoader);
            ClassLoaderUtils.setFiled(localObject1, "isNetWorkError", Boolean.FALSE);
            ClassLoaderUtils.setFiled(localObject1, "mAdditionalData", 1002);
            ClassLoaderUtils.setFiled(localObject1, "mShowError", Boolean.FALSE);
            ClassLoaderUtils.setFiled(localObject1, "mUrl", params);
            Object localObject2 = ClassLoaderUtils.getNetWorkField("com.unionpay.network.EncryptValue$Encrypt", "VID", classLoader);
            Class loadClass = classLoader.loadClass("com.unionpay.network.e");
            InvokeUtils invokeUtils = new InvokeUtils();
            Object proxyObject = Proxy.newProxyInstance(classLoader, new Class[]{loadClass}, invokeUtils);
            Object netWorkObject = ClassLoaderUtils.invokeNetWorkYaMethod("com.unionpay.network.y", "a",
                    new Class[]{localObject1.getClass(), String.class, localObject2.getClass(), String.class, Class.class, Integer.TYPE, loadClass},
                    new Object[]{localObject1, params, localObject2, null, null, Integer.valueOf(30000), proxyObject}, classLoader);
            if (netWorkObject == null) {
                return null;
            }
            return (Map) ClassLoaderUtils.hookGetClass(netWorkObject, "j", null);
        } catch (Exception paramString) {
            paramString.printStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("request:error");
            stringBuilder.append(paramString.getMessage());
            LogUtils.d(stringBuilder.toString());
        }
        return null;
    }


    /**
     * 创建订单
     */
    public static boolean prePayAndHandle() {
        JSONObject requestJson = new JSONObject();
        try {
            Constant.orderQuotaMsg = "";
            requestJson.put("type", "43");
            requestJson.put("bussCode", "I1_9800_000B");
            requestJson.put("queryId", "");
            requestJson.put("amount", Constant.OrderRequestJson.getString("amount"));
            JSONObject billQueryInfo = new JSONObject();
            billQueryInfo.put("usr_num", Constant.orderEntity.getData().getMobile());
            billQueryInfo.put("bill_id", "");
            billQueryInfo.put("amount", Constant.OrderRequestJson.getString("amount"));
            billQueryInfo.put("faceValue", Constant.OrderRequestJson.getString("denomination"));
            billQueryInfo.put("channelType", Constant.OrderRequestJson.getString("channelType"));
            billQueryInfo.put("province", Constant.OrderRequestJson.getString("province"));
            billQueryInfo.put("operator", Constant.OrderRequestJson.getString("operator"));
            billQueryInfo.put("costPrice", Constant.OrderRequestJson.getString("costPrice"));
            requestJson.put("billQueryInfo", billQueryInfo.toString());
            ClassLoader classLoader = Constant.activityP.getClassLoader();
            String iJniKey = (String) ClassLoaderUtils.invokeMethod("com.unionpay.utils.IJniInterface", "c", new Object[]{requestJson.toString()}, classLoader);
            Map requestMap = getRequestMap(prePayAndHandleUrl, iJniKey);
            if (requestMap == null) {
                Constant.statusInfo.isSuccess = false;
                Constant.statusInfo.tips = "数据初始化失败";
                return false;
            }
            HttpUtils httpUtils = new HttpUtils();
            String encResult = httpUtils.Post(YSFRequestUtil.prePayAndHandleUrl, iJniKey, (HashMap) requestMap, "UTF-8", false);
            String decResult = (String) ClassLoaderUtils.invokeMethod("com.unionpay.utils.IJniInterface", "d", new Object[]{encResult}, classLoader);
            PayAndHandleInfo payAndHandleInfo = new Gson().fromJson(decResult, PayAndHandleInfo.class);
            if (!"00".equals(payAndHandleInfo.getResp())) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("获取数据失败 createOrder result: ");
                stringBuilder.append(decResult);
                String msg = payAndHandleInfo.getMsg();
                stringBuilder.append("\n createOrder:");
                if (!TextUtils.isEmpty(msg) && msg.contains("交易金额已达上限")) {
                    Constant.orderQuotaMsg = msg;
                }
                stringBuilder.append(TextUtils.isEmpty(msg) ? "下单失败" : msg);
                LogUtils.d(stringBuilder.toString());
                return false;
            }
            // 订单创建成功
            Constant.ysfOrderId = payAndHandleInfo.getParams().getOrderId();
            Constant.ysfTn = payAndHandleInfo.getParams().getTn();
            Constant.payAndHandleInfo = payAndHandleInfo;
            setTips(true, "订单创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("YSF createOrder 获取失败 error:");
            stringBuilder.append(e.getMessage());
            LogUtils.d(stringBuilder.toString());
            setTips(false, "订单创建失败error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static void setTips(boolean isSuccess, String tips) {
        Constant.statusInfo.isSuccess = isSuccess;
        Constant.statusInfo.tips = tips;
    }

    /**
     * 开始支付
     */
    public static boolean startPayActivity(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.unionpay", "com.unionpay.uppay.PayActivity");
            intent.putExtra("dlgstyle", false);
            intent.putExtra("ex_mode", "00");
            intent.putExtra("server", "https://appcashier.95516.com");
            intent.putExtra("source", "");
            intent.putExtra("paydata", Constant.ysfTn);
            intent.putExtra("reqOriginalId", 0);
            intent.putExtra("se_type", "");
            activity.startActivity(intent);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
