package com.fiveelements.unpay.xp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fiveelements.unpay.R;
import com.fiveelements.unpay.widget.DragView;
import com.fiveelements.unpay.xp.http.HttpUtils;
import com.fiveelements.unpay.xp.http.RestClient;
import com.fiveelements.unpay.xp.method.UPLogMethod;
import com.fiveelements.unpay.xp.method.UniPayOnCreateMethod;
import com.fiveelements.unpay.xp.method.UpRulesRiskMsgMethod;
import com.fiveelements.unpay.xp.method.UpRulesRiskMsgStyleMethod;
import com.fiveelements.unpay.xp.method.UtilsLMethod;
import com.fiveelements.unpay.xp.model.LoginModel;
import com.fiveelements.unpay.xp.net.YSFRequestUtil;
import com.fiveelements.unpay.xp.task.OrderAsyncTask;
import com.fiveelements.unpay.xp.utils.ClassLoaderUtils;
import com.fiveelements.unpay.xp.utils.DensityUtil;
import com.fiveelements.unpay.xp.utils.InvokeUtils;
import com.fiveelements.unpay.xp.utils.SPUtils;
import com.fiveelements.unpay.xp.utils.UiUtil;
import com.fiveelements.unpay.xp.utils.log.LogUtils;
import com.fiveelements.unpay.xp.utils.ui.CreateMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XpMain implements IXposedHookLoadPackage {
    public static String TAG = "FE_XP_LOG: ";
    public static JSONObject json;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if ("com.unionpay".equals(loadPackageParam.packageName)) {
            XposedBridge.hookAllMethods(Activity.class, "onCreate", new UniPayOnCreateMethod());
            XposedHelpers.findAndHookMethod("com.unionpay.utils.UPLog", loadPackageParam.classLoader,
                    "a", Integer.TYPE, String.class, String.class, Throwable.class, new UPLogMethod());
            XposedHelpers.findAndHookMethod("com.unionpay.utils.IJniInterface",
                    loadPackageParam.classLoader, "d", String.class, new IJniInterface());
            XposedHelpers.findAndHookMethod("com.unionpay.mobile.pay.utils.l",
                    loadPackageParam.classLoader, "a", Integer.TYPE, String.class, String.class, new UtilsLMethod());
            XposedHelpers.findAndHookMethod("com.unionpay.mobile.pay.model.UPRules",
                    loadPackageParam.classLoader, "getRiskMsgStyle", new UpRulesRiskMsgStyleMethod());
            XposedHelpers.findAndHookMethod("com.unionpay.mobile.pay.model.UPRules",
                    loadPackageParam.classLoader, "getRiskMsg", new UpRulesRiskMsgMethod());
        }
    }

    public static class IJniInterface extends XC_MethodHook {
        @Override
        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            try {
                Object result = param.getResult();
                StringBuilder builder = new StringBuilder();
                builder.append(TAG);
                builder.append("IJniInterface ----d----decryptMsg--- arg1:");
                builder.append(param.args[0]);
                builder.append("----return:");
                builder.append(result);
                LogUtils.d(builder.toString());
                if (result != null && ((String) result).startsWith("{")) {
                    JSONObject json = new JSONObject();
                    String resp = json.optString("resp", "");
                    String cmd = json.optString("cmd");
                    if (TextUtils.isEmpty(cmd)) {
                        return;
                    }
                    if (cmd.equals("order/prehandle") && !"00".equals(resp)) {
                        String msg = json.optString("msg", "交易失败");
                        StringBuilder logBuilder = new StringBuilder();
                        logBuilder.append(resp);
                        logBuilder.append(msg);
                        LogUtils.d(logBuilder.toString());
                    }
                }
            } catch (Exception e) {
                LogUtils.d("IJniInterface error: " + e.getMessage());
            }
        }
    }
}
