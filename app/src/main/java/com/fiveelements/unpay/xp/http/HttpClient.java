package com.fiveelements.unpay.xp.http;

import android.text.TextUtils;

import com.fiveelements.unpay.xp.Constant;
import com.fiveelements.unpay.xp.utils.StringUtils;
import com.fiveelements.unpay.xp.utils.log.LogUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClient {
    private static HttpClient mHttpClient;
    private OkHttpClient httpClient;

    private HttpClient() {
    }

    public static HttpClient getInstance() {
        if (mHttpClient == null) {
            mHttpClient = new HttpClient();
        }
        return mHttpClient;
    }

    public synchronized String httpPost(String url, String data) throws IOException {
        Response response;
        try {
            if (this.httpClient == null) {
                this.httpClient = new OkHttpClient();
            }
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json, text/plain, */*"), data);
            Request request = new Request.Builder().url(url).addHeader("", "").post(requestBody).build();
            response = this.httpClient.newCall(request).execute();
        } catch (Exception e) {
            return null;
        }
        return response.body().string();
    }

    public synchronized String httpGet(String url, String cookie) throws IOException {
        Response response;
        try {
            if (this.httpClient == null) {
                this.httpClient = new OkHttpClient();
            }
            Request request = new Request.Builder().url(url).addHeader("Accept", "application/json, text/plain, */*").addHeader("Cache-Control", "no-cache").addHeader("X-Requested-With", "XMLHttpRequest").addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 5.1.1; V1824A Build/LMY48Z; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36 MMWEBID/9696 MicroMessenger/7.0.12.1620(0x27000C34) Process/tools NetType/WIFI Language/zh_CN ABI/arm32").addHeader("Referer", "https://chong.qq.com/order/index.html?from=singlemessage&from=singlemessage").addHeader("Cookie", cookie).get().build();
            response = this.httpClient.newCall(request).execute();
            LogUtils.i("http", "Cookie-----" + cookie);
            response.headers("Set-Cookie");
            Headers headers = response.headers();
            headers.get("Set-Cookie");
            url.startsWith("https://wx.tenpay.com/userroll/userrolllist");
        } catch (Exception ex) {
            LogUtils.i("http", "error:" + ex.getMessage());
            return null;
        }
        return response.body().string();
    }

    public synchronized String httpGet2(String url) throws IOException {
        Response response;
        try {
            if (this.httpClient == null) {
                this.httpClient = new OkHttpClient();
            }
            Request request = new Request.Builder().url(url).addHeader("Cache-Control", "no-cache").addHeader("11", "22").addHeader("X-Requested-With", "XMLHttpRequest").addHeader("User-Agent", "Mozilla/5.0 (Linux; Android 5.1.1; V1824A Build/LMY48Z; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/52.0.2743.100 Mobile Safari/537.36 MMWEBID/9696 MicroMessenger/7.0.12.1620(0x27000C34) Process/tools NetType/WIFI Language/zh_CN ABI/arm32").get().build();
            response = this.httpClient.newCall(request).execute();
            List<String> data = response.headers("Set-Cookie");
            new StringBuilder();
            if (data != null && data.size() > 0) {
                HashMap<String, String> Cookies = new HashMap<>();
                for (String tmp : data) {
                    String ck = StringUtils.SubStr(tmp, ";");
                    if (!TextUtils.isEmpty(ck)) {
                        String[] tmps = ck.trim().split("=");
                        if (tmps.length >= 2) {
                            String str = tmps[0];
                            Cookies.put(str, ck.replace(tmps[0] + "=", ""));
                        }
                    }
                }
//                Constant.COOKIES2 = CookieUtils.getCookie(Cookies);
//                LogUtils.i("http", "tenpay----Constant.COOKIES2-----" + Constant.COOKIES2);
            }
            LogUtils.i("http", "tenpay----Cookie1-----" + data);
        } catch (Exception ex) {
            LogUtils.i("http", "error:" + ex.getMessage());
            return null;
        }
        return response.body().string();
    }
}
