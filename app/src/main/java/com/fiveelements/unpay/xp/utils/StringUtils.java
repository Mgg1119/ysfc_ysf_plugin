package com.fiveelements.unpay.xp.utils;

import android.text.TextUtils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Set;

/* loaded from: classes2.dex */
public class StringUtils {
    public static String SubStr(String str1, String start, String end) {
        int index;
        try {
            if (TextUtils.isEmpty(str1) || (index = str1.indexOf(start)) < 0) {
                return null;
            }
            String tmp = str1.substring(index).replace(start, "");
            int endIndex = tmp.indexOf(end);
            return endIndex >= 0 ? tmp.substring(0, endIndex) : tmp;
        } catch (Exception e) {
            return null;
        }
    }

    public static String SubStr(String str1, String end) {
        int index;
        try {
            if (TextUtils.isEmpty(str1) || (index = str1.indexOf(end)) < 0) {
                return null;
            }
            return str1.substring(0, index);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getParam(String content, String args) throws Exception {
        HashMap<String, String> map = getParamMap(content);
        Set<String> keySet = map.keySet();
        StringBuilder builder = new StringBuilder();
        for (String key : keySet) {
            builder.append(key);
            builder.append("=");
            builder.append(URLEncoder.encode(map.get(key), "utf-8"));
            builder.append(args);
        }
        if (builder.toString().endsWith(args)) {
            builder.delete(builder.length() - args.length(), builder.length());
        }
        return builder.toString();
    }

    public static String getParam(HashMap<String, String> map, String args) throws Exception {
        Set<String> keySet = map.keySet();
        StringBuilder builder = new StringBuilder();
        for (String key : keySet) {
            builder.append(key);
            builder.append("=");
            builder.append(URLEncoder.encode(map.get(key), "utf-8"));
            builder.append(args);
        }
        if (builder.toString().endsWith(args)) {
            builder.delete(builder.length() - args.length(), builder.length());
        }
        return builder.toString();
    }

    public static HashMap<String, String> getParamMap(String content) {
        String[] tmps = content.split("\n");
        HashMap<String, String> map = new HashMap<>();
        if (tmps.length == 0) {
            return map;
        }
        for (String str : tmps) {
            if (!"".equals(str) && !str.startsWith("<")) {
                String[] tmp = str.split(":");
                if (tmp.length >= 2) {
                    String key = tmp[0].trim();
                    String value = SubStr(str, "value=\"", "\"");
                    map.put(key, value);
                }
            }
        }
        return map;
    }

    public static byte[] hexToByteArray(String arg5) throws Exception {
        if (arg5 != null) {
            if (arg5.length() % 2 == 0) {
                int v0 = arg5.length() / 2;
                byte[] v1 = new byte[v0];
                for (int v2 = 0; v2 < v0; v2++) {
                    int v3 = v2 * 2;
                    v1[v2] = hexToByte(arg5.substring(v3, v3 + 2));
                }
                return v1;
            }
            throw new Exception();
        }
        throw new Exception();
    }

    public static byte hexToByte(String arg2) throws Exception {
        if (arg2 != null) {
            if (arg2.length() == 2) {
                byte[] v2 = arg2.getBytes();
                return (byte) ((a((char) v2[0]) * 16) + a((char) v2[1]));
            }
            throw new Exception();
        }
        throw new Exception();
    }

    public static int a(char arg2) throws Exception {
        if (arg2 >= '0' && arg2 <= '9') {
            int v2 = ((byte) arg2) - 48;
            return v2;
        } else if (arg2 >= 'A' && arg2 <= 'F') {
            return ((byte) arg2) - 55;
        } else {
            if (arg2 < 'a' || arg2 > 'f') {
                return 0;
            }
            return ((byte) arg2) - 87;
        }
    }
}
