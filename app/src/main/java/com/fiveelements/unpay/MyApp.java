package com.fiveelements.unpay;

import android.app.Application;

public class MyApp extends Application {

    private static volatile MyApp instance;

    /**
     * 单例调用
     */
    public static MyApp getInstance() {
        if (instance == null) {
            synchronized (MyApp.class) {
                if (instance == null) {
                    instance = new MyApp();
                }
            }
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .build();
//
//        EasyConfig.with(okHttpClient)
//                // 是否打印日志
//                .setLogEnabled(BuildConfig.DEBUG)
//                // 设置服务器配置
//                .setServer(server)
//                // 设置请求处理策略
//                .setHandler(new RequestHandler())
//                // 设置请求重试次数
//                .setRetryCount(3)
//                // 添加全局请求参数
//                //.addParam("token", "6666666")
//                // 添加全局请求头
//                //.addHeader("time", "20191030")
//                // 启用配置
//                .into();
    }
}
