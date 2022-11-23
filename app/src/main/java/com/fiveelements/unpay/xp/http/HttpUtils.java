package com.fiveelements.unpay.xp.http;

import android.text.TextUtils;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.fiveelements.unpay.xp.model.Proxyip;
import com.fiveelements.unpay.xp.utils.StringUtils;
import com.fiveelements.unpay.xp.utils.log.LogUtils;


import org.jsoup.helper.HttpConnection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class HttpUtils {
    public String cookie;
    public Proxyip proxyip;
    public String Agent = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Mobile Safari/537.36";
    public HashMap<String, String> Cookies = new HashMap<>();
    public String contentType = FastJsonJsonView.DEFAULT_CONTENT_TYPE;
    public int timeout = 10000;
    public HashMap<String, String> headers = new HashMap<>();

    public String Get(String urlPath) {
        return Get(urlPath, "");
    }

    public String Getp(String urlPath) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Cache-Control", "no-cache");
        headers.put("X-Requested-With", "XMLHttpRequest");
        headers.put("User-Agent", this.Agent);
        return Get(urlPath, headers, false);
    }

    public String GetLocation(String urlPath) {
        return GetLocation(urlPath, "");
    }

    public String GetLocation(String urlPath, String refer) {
        return Get(urlPath, refer, true);
    }

    public String GetYD(String urlPath, String refer) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Origin", refer);
        headers.put(HttpConnection.CONTENT_TYPE, this.contentType);
        headers.put("Charset", "UTF-8");
        headers.put("sec-ch-ua", "\"Google Chrome\";v=\"93\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"93\"");
        headers.put("Accept", "application/json, text/plain, */*");
        if (!TextUtils.isEmpty(refer)) {
            headers.put("Referer", refer);
        }
        if (!TextUtils.isEmpty(this.cookie)) {
            headers.put("Cookie", this.cookie);
        }
        if (this.headers.size() > 0) {
            headers.putAll(this.headers);
        }
        headers.put("User-Agent", this.Agent);
        return Get(urlPath, headers, false);
    }

    public String GetNotType(String urlPath, String refer) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Charset", "UTF-8");
        headers.put("User-Agent", this.Agent);
        if (!TextUtils.isEmpty(refer)) {
            headers.put("Referer", refer);
        }
        if (!TextUtils.isEmpty(this.cookie)) {
            headers.put("Cookie", this.cookie);
        }
        if (this.headers.size() > 0) {
            headers.putAll(this.headers);
        }
        return Get(urlPath, headers, false);
    }

    public String Get(String urlPath, String refer) {
        return Get(urlPath, refer, false);
    }

    public String Get(String urlPath, String refer, boolean isLocation) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Charset", "UTF-8");
        headers.put(HttpConnection.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("User-Agent", this.Agent);
        if (!TextUtils.isEmpty(refer)) {
            headers.put("Referer", refer);
        }
        if (!TextUtils.isEmpty(this.cookie)) {
            headers.put("Cookie", this.cookie);
        }
        if (this.headers.size() > 0) {
            headers.putAll(this.headers);
        }
        return Get(urlPath, headers, isLocation);
    }

    public String Get12580(String urlPath, String refer) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Charset", "UTF-8");
        headers.put(HttpConnection.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("User-Agent", this.Agent);
        if (!TextUtils.isEmpty(refer)) {
            headers.put("Referer", refer);
        }
        if (!TextUtils.isEmpty(this.cookie)) {
            headers.put("Cookie", this.cookie);
        }
        if (this.headers.size() > 0) {
            headers.putAll(this.headers);
        }
        return Get(urlPath, headers, "gbk", false);
    }

    public String GetJson(String urlPath, String refer) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Charset", "UTF-8");
        headers.put(HttpConnection.CONTENT_TYPE, "application/json; charset=UTF-8");
        headers.put("User-Agent", this.Agent);
        if (!TextUtils.isEmpty(refer)) {
            headers.put("Referer", refer);
        }
        if (!TextUtils.isEmpty(this.cookie)) {
            headers.put("Cookie", this.cookie);
        }
        if (this.headers.size() > 0) {
            headers.putAll(this.headers);
        }
        return Get(urlPath, headers, false);
    }

    public String Post(String urlPath, String bodString) {
        return Post(urlPath, bodString, "");
    }

    public String PostLocation(String urlPath, String bodString) {
        return Post(urlPath, bodString, "", true);
    }

    public String PostYD(String urlPath, String body, String refer) {
        return PostYD(urlPath, body, refer, this.contentType);
    }

    public String PostYD(String urlPath, String body, String refer, String contentType) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Origin", refer);
        headers.put(HttpConnection.CONTENT_TYPE, contentType);
        headers.put("Charset", "UTF-8");
        headers.put("sec-ch-ua", "\"Google Chrome\";v=\"93\", \" Not;A Brand\";v=\"99\", \"Chromium\";v=\"93\"");
        headers.put("Accept", "application/json, text/plain, */*");
        if (!TextUtils.isEmpty(refer)) {
            headers.put("Referer", refer);
        }
        if (!TextUtils.isEmpty(this.cookie)) {
            headers.put("Cookie", this.cookie);
        }
        headers.put("User-Agent", this.Agent);
        if (this.headers.size() > 0) {
            headers.putAll(this.headers);
        }
        return Post(urlPath, body, headers, "UTF-8");
    }

    public String Post(String urlPath, String body, String refer, boolean isLocation) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Charset", "UTF-8");
        headers.put(HttpConnection.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("User-Agent", this.Agent);
        if (this.headers.size() > 0) {
            headers.putAll(this.headers);
        }
        if (!TextUtils.isEmpty(refer)) {
            headers.put("Referer", refer);
        }
        if (!TextUtils.isEmpty(this.cookie)) {
            headers.put("Cookie", this.cookie);
        }
        return Post(urlPath, body, headers, "UTF-8", isLocation);
    }

    public String Post12580(String urlPath, String body, String refer) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Charset", "UTF-8");
        headers.put(HttpConnection.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        headers.put("User-Agent", this.Agent);
        if (this.headers.size() > 0) {
            headers.putAll(this.headers);
        }
        if (!TextUtils.isEmpty(refer)) {
            headers.put("Referer", refer);
        }
        if (!TextUtils.isEmpty(this.cookie)) {
            headers.put("Cookie", this.cookie);
        }
        return Post(urlPath, body, headers, "gbk", false);
    }

    public String Post(String urlPath, String body, String refer) {
        return Post(urlPath, body, refer, false);
    }

    public String PostNotType(String urlPath, String body, String refer) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Charset", "UTF-8");
        headers.put("User-Agent", this.Agent);
        if (this.headers.size() > 0) {
            headers.putAll(this.headers);
        }
        if (!TextUtils.isEmpty(refer)) {
            headers.put("Referer", refer);
        }
        if (!TextUtils.isEmpty(this.cookie)) {
            headers.put("Cookie", this.cookie);
        }
        return Post(urlPath, body, headers, "UTF-8", false);
    }

    public String PostJson(String urlPath, String body) {
        return PostJson(urlPath, body, null);
    }

    public String PostJson(String urlPath, String body, String refer) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Charset", "UTF-8");
        headers.put(HttpConnection.CONTENT_TYPE, "application/json; charset=UTF-8");
        headers.put("User-Agent", this.Agent);
        if (this.headers.size() > 0) {
            headers.putAll(this.headers);
        }
        if (!TextUtils.isEmpty(refer)) {
            headers.put("Referer", refer);
        }
        if (!TextUtils.isEmpty(this.cookie)) {
            headers.put("Cookie", this.cookie);
        }
        return Post(urlPath, body, headers, "UTF-8");
    }

    public String Get(String urlPath, HashMap<String, String> headers, boolean isLocation) {
        return Get(urlPath, headers, "utf-8", isLocation);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0158 A[Catch: Exception -> 0x015c, TRY_LEAVE, TryCatch #1 {Exception -> 0x015c, blocks: (B:49:0x010c, B:51:0x0118, B:53:0x011c, B:54:0x0129, B:56:0x0158), top: B:62:0x010c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public String Get(String r18, HashMap<String, String> r19, String r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 350
            To view this dump change 'Code comments level' option to
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hello.rebot.net.HttpUtils.Get(java.lang.String, java.util.HashMap, java.lang.String, boolean):java.lang.String");
    }

    public String Post(String urlPath, String body, HashMap<String, String> headers, String charset, boolean isLocation) {
        HttpURLConnection connection;
        String location;
        try {
            try {
                URL url = new URL(urlPath);
                if (this.proxyip != null) {
                    InetSocketAddress addr = new InetSocketAddress(this.proxyip.url, this.proxyip.port);
                    Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
                    HttpURLConnection connection2 = (HttpURLConnection) url.openConnection(proxy);
                    connection = connection2;
                } else {
                    HttpURLConnection connection3 = (HttpURLConnection) url.openConnection();
                    connection = connection3;
                }
                connection.setInstanceFollowRedirects(false);
                connection.setConnectTimeout(this.timeout);
                connection.setReadTimeout(10000);
                connection.setDoOutput(true);
                Set<String> keySet = headers.keySet();
                for (String key : keySet) {
                    try {
                        try {
                            connection.addRequestProperty(key, headers.get(key));
                        } catch (Exception e) {
                            return null;
                        }
                    } catch (Exception e2) {
                        return null;
                    }
                }
                OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), charset);
                try {
                    out.write(body);
                    out.flush();
                    out.close();
                    uploadCk(connection);
                    int code = connection.getResponseCode();
                    LogUtils.d("请求code:" + code);
                    if (isLocation) {
                        if (code < 300 || code >= 400) {
                            location = "";
                        } else {
                            location = connection.getHeaderField("Location");
                        }
                        try {
                            connection.disconnect();
                        } catch (Exception e3) {
                        }
                        return location;
                    }
                    InputStream is = connection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is, charset);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder builder = new StringBuilder();
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        }
                        StringBuilder builder2 = builder;
                        builder2.append(line + "\n");
                        builder = builder2;
                    }
                    StringBuilder builder3 = builder;
                    br.close();
                    isr.close();
                    is.close();
                    builder3.deleteCharAt(builder3.length() - 1);
                    try {
                        connection.disconnect();
                    } catch (Exception e4) {
                    }
                    LogUtils.d("网络请求返回: " + builder3.toString());
                    return builder3.toString();
                } catch (Exception e5) {
                    LogUtils.d("网络请求返回 error e5: " + e5.getMessage());
                    return null;
                }
            } catch (Exception e6) {
                LogUtils.d("网络请求返回 error e6: " + e6.getMessage());
                return null;
            }
        } catch (Exception e7) {
            LogUtils.d("网络请求返回 error e7: " + e7.getMessage());
        }
        return null;
    }

    public String Post(String urlPath, String body, HashMap<String, String> headers, String charset) {
        return Post(urlPath, body, headers, charset, false);
    }

    private void uploadCk(URLConnection connection) {
        List<String> list;
        try {
            if (connection.getHeaderFields() != null && connection.getHeaderFields().containsKey("Set-Cookie")
                    && (list = connection.getHeaderFields().get("Set-Cookie")) != null && list.size() != 0) {
                for (String tmp : list) {
                    String ck = StringUtils.SubStr(tmp, ";");
                    if (!TextUtils.isEmpty(ck)) {
                        String[] tmps = ck.trim().split("=");
                        if (tmps.length >= 2) {
                            HashMap<String, String> hashMap = this.Cookies;
                            String str = tmps[0];
                            hashMap.put(str, ck.replace(tmps[0] + "=", "").trim().replace(" ", ""));
                        }
                    }
                }
                this.cookie = CookieUtils.getCookie(this.Cookies);
            }
        } catch (Exception e) {
        }
    }

    /**
     * GET请求
     *
     * @return
     */
    public String GetRequest(String urlPath, HashMap<String, String> headers, String charset, boolean isLocation) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String location;
        try {
            URL url = new URL(urlPath);
            if (this.proxyip != null) {
                InetSocketAddress addr = new InetSocketAddress(this.proxyip.url, this.proxyip.port);
                Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
                HttpURLConnection connection2 = (HttpURLConnection) url.openConnection(proxy);
                connection = connection2;
            } else {
                HttpURLConnection connection3 = (HttpURLConnection) url.openConnection();
                connection = connection3;
            }
            connection.setRequestMethod("GET");
            connection.setInstanceFollowRedirects(false);
            connection.setConnectTimeout(this.timeout);
            connection.setReadTimeout(10000);
//            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            Set<String> keySet = headers.keySet();
            for (String key : keySet) {
                connection.addRequestProperty(key, headers.get(key));
            }
            uploadCk(connection);
            int code = connection.getResponseCode();
            LogUtils.d("请求code:" + code);
            if (isLocation) {
                if (code < 300 || code >= 400) {
                    location = "";
                } else {
                    location = connection.getHeaderField("Location");
                }
                try {
                    connection.disconnect();
                } catch (Exception e3) {
                }
                return location;
            }

            is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, charset));
            StringBuilder sbf = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                sbf.append(line);
                sbf.append("\r\n");
            }
            System.out.println("get请求返回参数: " + sbf.toString());
            return sbf.toString();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.disconnect();
                }

                System.out.println("GET-请求结束");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
