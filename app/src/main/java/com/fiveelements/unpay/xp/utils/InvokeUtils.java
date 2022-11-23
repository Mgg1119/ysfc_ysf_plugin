package com.fiveelements.unpay.xp.utils;

import com.fiveelements.unpay.xp.utils.log.LogUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvokeUtils implements InvocationHandler {
    public void a() {
    }

    public void b() {
    }

    public void c() {
    }

    public Object invoke(Object paramObject, Method paramMethod, Object[] paramArrayOfObject) {
        paramObject = paramMethod.getName();
        StringBuilder builder = new StringBuilder();
        builder.append("OauthAuthorize invoke--:");
        builder.append((String) paramObject);
        LogUtils.d(builder.toString());
        int i = -1;
        try {
            int j = ((String) paramObject).hashCode();
            if (j != -1349867671) {
                if (j != -52561448) {
                    while (!((String) paramObject).equals("onError")) {
                        do {
                            while (!((String) paramObject).equals("onResult")) {
                            }
                            i = 2;
                            break;
                        } while (!((String) paramObject).equals("onAnalysisResult"));
                        i = 0;
                        break;
                    }
                    i = 1;
                    if (j != 1463983836) {
                        if (i != 0) {
                            if (i != 1) {
                                if (i == 2) {
                                    paramObject = paramArrayOfObject[0];
                                    paramObject = (String) paramArrayOfObject[1];
                                    c();
                                }
                            } else {
                                paramObject = paramArrayOfObject[0];
                                paramObject = (String) paramArrayOfObject[1];
                                paramObject = (String) paramArrayOfObject[2];
                                b();
                            }
                        } else {
                            paramObject = paramArrayOfObject[0];
                            paramObject = paramArrayOfObject[1];
                            a();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            String stringBuilder = "OauthAuthorize invoke error:" +
                    e.getMessage() +
                    "   object:" +
                    paramObject;
            LogUtils.d(stringBuilder);
        }
        return null;
    }
}
