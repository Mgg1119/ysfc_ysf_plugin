package com.fiveelements.unpay.xp.http;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Set;

/* loaded from: classes2.dex */
public class CookieUtils {
    public static HashMap<String, String> GetCookies(String cookie) {
        try {
            if (TextUtils.isEmpty(cookie)) {
                return null;
            }
            HashMap<String, String> map = new HashMap<>();
            String[] tmps = cookie.split(";");
            for (String str : tmps) {
                String[] strs = str.trim().split("=");
                if (strs.length == 2) {
                    String tps = strs[1].trim();
                    map.put(strs[0].trim(), tps.replace(" ", "+"));
                } else if (strs.length >= 3) {
                    String tps2 = str.trim().replace(strs[0].trim() + "=", "");
                    map.put(strs[0].trim(), tps2.replace(" ", "+"));
                }
            }
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String getCookie(HashMap<String, String> Cookies) {
        Set<String> set = Cookies.keySet();
        StringBuffer buffer = new StringBuffer();
        for (String key : set) {
            buffer.append(key);
            buffer.append("=");
            buffer.append(Cookies.get(key));
            buffer.append("; ");
        }
        return buffer.toString();
    }
}
