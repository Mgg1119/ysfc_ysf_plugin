//package com.fiveelements.unpay;
//
//import android.app.Activity;
//import android.content.Context;
//import android.text.TextUtils;
//
//import org.json.JSONObject;
//
//import java.util.List;
//
//import de.robv.android.xposed.XposedBridge;
//import de.robv.android.xposed.XposedHelpers;
//
//public class b {
//    public static void a(XC_LoadPackage$LoadPackageParam arg10) {
//        XposedBridge.hookAllMethods(Activity.class, "onCreate", new a());
//        XposedHelpers.findAndHookMethod("com.unionpay.utils.UPLog", arg10.classLoader, "a", new Object[]{Integer.TYPE, String.class, String.class, Throwable.class, new b$a()});
//        XposedHelpers.findAndHookMethod("com.unionpay.utils.IJniInterface", arg10.classLoader, "d", new Object[]{String.class, new b$b()});
//        XposedHelpers.findAndHookMethod("com.unionpay.mobile.pay.utils.l", arg10.classLoader, "a", new Object[]{Integer.TYPE, String.class, String.class, new b$c()});
//        XposedHelpers.findAndHookConstructor("com.unionpay.mobile.pay.adapter.b", arg10.classLoader, new Object[]{Context.class, List.class, Integer.TYPE, new b$d(arg10)});
//        XposedHelpers.findAndHookConstructor("com.unionpay.mobile.pay.fragment.UPFragmentCardList", arg10.classLoader, new Object[]{new b$e()});
//        XposedHelpers.findAndHookMethod("com.unionpay.mobile.pay.model.UPRules", arg10.classLoader, "getRiskMsgStyle", new Object[]{new b$f()});
//        XposedHelpers.findAndHookMethod("com.unionpay.mobile.pay.model.UPRules", arg10.classLoader, "getRiskMsg", new Object[]{new b$g()});
//    }
//
//    public static void a(String arg0) {
//        b.b(arg0);
//    }
//
//    public static void b(String arg11) {
//        i v4_3;
//        d v2_3;
//        String v4_2;
//        boolean v4_1;
//        String v3;
//        JSONObject v1_2;
//        String v0 = "resp";
//        try {
//            boolean v1 = arg11.startsWith(v0);
//        } catch (Exception v0_1) {
//            goto label_268;
//        }
//
//        String v2 = "";
//        if (v1) {
//            try {
//                arg11 = arg11.replace("resp is:", ((CharSequence) v2)).replace(" ", ((CharSequence) v2)).replace("\r", ((CharSequence) v2)).replace("\n", ((CharSequence) v2));
//                label_16:
//                if (!arg11.startsWith("{")) {
//                    return;
//                }
//
//                b.b.d.a.k.a.a("cmd----:" + arg11);
//                v1_2 = new JSONObject(arg11);
//                v3 = v1_2.optString("cmd", v2);
//                b.b.d.a.k.a.a("cmd:" + v3);
//                v4_1 = TextUtils.isEmpty(((CharSequence) v3));
//                goto label_39;
//            } catch (Exception v0_1) {
//                goto label_268;
//            }
//        }
//
//        goto label_16;
//        label_39:
//        String v5 = "init";
//        if (v4_1) {
//            try {
//                v4_2 = v1_2.optString("title", v2);
//                if (!TextUtils.isEmpty(((CharSequence) v4_2))) {
//                    if (!v4_2.equals("付款详情")) {
//                    } else {
//                        v3 = v5;
//                        goto label_52;
//                    }
//                }
//
//                return;
//                label_52:
//                if (v3.equals("getpaycards")) {
//                    return;
//                }
//
//                v4_1 = v3.equals(v5);
//                goto label_57;
//            } catch (Exception v0_1) {
//                goto label_268;
//            }
//        }
//
//        goto label_52;
//        label_57:
//        v5 = "msg";
//        String v6 = "params";
//        String v7 = "00";
//        if (!v4_1) {
//            goto label_125;
//        }
//
//        try {
//            v0 = v1_2.optString(v0, v2);
//            boolean v2_1 = TextUtils.isEmpty(((CharSequence) v0));
//        } catch (Exception v0_1) {
//            goto label_268;
//        }
//
//        v4_2 = "银行卡解析：";
//        String v8 = "default_card";
//        if (!v2_1) {
//            try {
//                b.b.d.a.k.a.a("初始化状态码：" + v0);
//                if (v0.equals(v7)) {
//                    v2_3 = new d(v1_2.getJSONObject(v6).getJSONObject(v8));
//                    b.b.d.a.k.a.a(v4_2 + v2_3.toString());
//                    if (!TextUtils.isEmpty(v2_3.a)) {
//                        h.i = v2_3;
//                    }
//                } else {
//                    v2 = v1_2.getString(v5);
//                    if (TextUtils.isEmpty(((CharSequence) v2))) {
//                        v2 = "支付拉起失败";
//                    }
//
//                    h.a.a(j.y);
//                    v4_3 = h.a;
//                    v4_3.a(v0 + v2);
//                    return;
//                    label_109:
//                    v2_3 = new d(v1_2.getJSONObject(v8));
//                    b.b.d.a.k.a.a(v4_2 + v2_3.toString());
//                    if (TextUtils.isEmpty(v2_3.a)) {
//                        return;
//                    }
//
//                    h.i = v2_3;
//                }
//
//                return;
//                label_125:
//                if (v3.equals("rules")) {
//                    v0 = v1_2.optString(v0);
//                    b.b.d.a.k.a.a("切换支付方式状态码：" + v0);
//                    return;
//                }
//
//                v4_1 = v3.equals("query");
//                goto label_139;
//            } catch (Exception v0_1) {
//                goto label_268;
//            }
//        } else {
//            goto label_109;
//        }
//
//        return;
//        label_139:
//        v8 = "fail_msg";
//        String v9 = "支付成功";
//        if (!v4_1) {
//            goto label_200;
//        }
//
//        try {
//            JSONObject v0_2 = v1_2.getJSONObject(v6);
//            String v1_3 = v0_2.getString("status");
//            if (!arg11.contains(h.a.h)) {
//                return;
//            }
//
//            if (v1_3.equals(v7)) {
//                h.a.a(j.x);
//                h.a.a(v9);
//                h.a.j = true;
//            } else if (v1_3.equals("01")) {
//                h.a.a(j.q);
//                h.a.a(v9);
//                h.a.j = true;
//            } else if (v1_3.equals("03")) {
//                v2 = v0_2.getString(v8);
//                if (TextUtils.isEmpty(((CharSequence) v2))) {
//                    v2 = "下单结果失败";
//                }
//
//                h.a.a(j.y);
//                h.a.a(v2);
//            } else {
//                v2 = v0_2.optString(v8);
//                h.a.a(j.x);
//                v4_3 = h.a;
//                v4_3.a("未知的支付结果:" + v1_3 + v2);
//            }
//
//            return;
//            label_200:
//            if (v3.equals("sms")) {
//                h.a.a("需要短信验证");
//                return;
//            }
//
//            if ((v3.equals("verifyusrpaypwd")) && !h.a.k) {
//                h.a.k = true;
//                if (h.a.d().contains(((CharSequence) v9))) {
//                    return;
//                } else {
//                    if (v1_2.optString(v0, v2).equals(v7)) {
//                        v2 = v1_2.getJSONObject(v6).optString(v8);
//                        if (!TextUtils.isEmpty(((CharSequence) v2))) {
//                            if (!v2.contains("支付密码不正确，你还") && !v2.contains("支付密码已被锁定，请在")) {
//                                return;
//                            }
//
//                            h.a.a(j.y);
//                            h.a.a(v2);
//                        } else {
//                        }
//                    } else {
//                    }
//
//                    return;
//                }
//            }
//
//            if (!v3.equals("pay")) {
//                return;
//            }
//
//            if (!v1_2.optString(v0, v2).equals(v7)) {
//                v2 = v1_2.optString(v5);
//                if (!TextUtils.isEmpty(((CharSequence) v2)) && (v2.contains("可能存在风险"))) {
//                    h.a.a(j.y);
//                    h.a.a(v2);
//                }
//
//                return;
//            }
//
//            h.a.a(j.q);
//            h.a.a(v9);
//            h.a.j = true;
//        } catch (Exception v0_1) {
//            label_268:
//            v0_1.printStackTrace();
//        }
//    }
//}
//
//
