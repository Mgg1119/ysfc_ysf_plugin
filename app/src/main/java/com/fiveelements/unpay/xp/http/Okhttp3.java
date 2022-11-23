package com.fiveelements.unpay.xp.http;

import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;


import com.fiveelements.unpay.xp.utils.Tool;
import com.fiveelements.unpay.xp.utils.log.LogUtils;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Okhttp3 {
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            TrustManager[] trustAllCerts = {new X509TrustManager() {
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            X509TrustManager x509TrustManager = new X509TrustManager() { // from class: com.mf.http.Okhttp3.2
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            };
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder().connectTimeout(3L, TimeUnit.MINUTES).writeTimeout(1L, TimeUnit.MINUTES).readTimeout(1L, TimeUnit.MINUTES).sslSocketFactory(sslSocketFactory, x509TrustManager).hostnameVerifier(new HostnameVerifier() { // from class: com.mf.http.Okhttp3.3
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Response get(String url, HashMap<String, String> headers, boolean asyncCookie) {
        try {
            OkHttpClient client = getOkHttpClient();
            Request.Builder builder = new Request.Builder();
            builder.url(url);
            Request.Builder builder2 = addReqHeader(url, builder, headers);
            builder2.get();
            Request request = builder2.build();
            Response response = client.newCall(request).execute();
            String host = request.url().host();
            if (asyncCookie) {
                asyncRespHeader(response, host);
            }
            return response;
        } catch (Exception e) {
            Tool.printException(e);
            return null;
        }
    }

    public static Response post(String url, HashMap<String, String> headers, RequestBody requestBody, boolean asyncCookie) {
        try {
            OkHttpClient client = getOkHttpClient();
            Request.Builder builder = new Request.Builder();
            builder.url(url);
            Request.Builder builder2 = addReqHeader(url, builder, headers);
            builder2.post(requestBody);
            Request request = builder2.build();
            Response response = client.newCall(request).execute();
            String host = request.url().host();
            if (asyncCookie) {
                asyncRespHeader(response, host);
            }
            return response;
        } catch (Exception e) {
            Tool.printException(e);
            return null;
        }
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder cb = new OkHttpClient.Builder();
        cb.followRedirects(false);
        OkHttpClient client = cb.build();
        return client;
    }

    private static Request.Builder addReqHeader(String url, Request.Builder builder, HashMap<String, String> headers) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        String cookie = cookieManager.getCookie(url);
        LogUtils.d("cookie:" + cookie);
        if (headers != null && !headers.isEmpty()) {
            String cookieV = headers.remove("Cookie");
            if (TextUtils.isEmpty(cookieV)) {
                if (!TextUtils.isEmpty(cookie)) {
                    builder.addHeader("Cookie", cookie);
                }
            } else {
                String cookieV2 = cookieV + ";" + cookie;
                if (!TextUtils.isEmpty(cookieV2)) {
                    builder.addHeader("Cookie", cookieV2);
                }
            }
            for (String key : headers.keySet()) {
                String v = headers.get(key);
                builder.addHeader(key, v);
            }
        } else if (!TextUtils.isEmpty(cookie)) {
            builder.addHeader("Cookie", cookie);
        }
        return builder;
    }

    private static void asyncRespHeader(Response response, String host) {
        LogUtils.d("host:" + host);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        String newCookie = response.header("Set-Cookie");
        if (!TextUtils.isEmpty(newCookie)) {
            cookieManager.setCookie(host, newCookie);
            CookieSyncManager.getInstance().sync();
            LogUtils.d(host + "\ncookie更新:" + newCookie);
        }
    }
}
