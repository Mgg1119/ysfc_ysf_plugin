package com.fiveelements.unpay.xp.utils.log;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
public class LogUtils {
    private static int Level = 0;
    public static boolean isLog = true;
    private static boolean isSave = true;
    private static RandomAccessFile randomAccessFile;
    private static RandomAccessFile randomAccessFile_db;
    private static String TAG = "apk_plugin_log";

    public static void i(String tag, String message) {
        if (isSave) {
            saveData(tag + ":" + message);
        }
        if (isLog && !TextUtils.isEmpty(message)) {
            Log.i(TAG, tag + ":" + message);
        }
    }

    public static void d(String message) {
        if (isSave) {
            saveData(message);
        }
        if (isLog && !TextUtils.isEmpty(message)) {
            Log.d(TAG, message);
        }
    }

    public static void socLog(String message) {
        if (isSave) {
            saveData(message);
        }
        if (isLog && !TextUtils.isEmpty(message)) {
            Log.i(TAG, message);
        }
    }

    public static void socLog(String message, int type) {
        if (isSave) {
            saveData(message);
        }
        if (isLog && !TextUtils.isEmpty(message)) {
            Log.i(TAG, message);
        }
    }

    public static void db(String message) {
        if (isSave) {
            saveData(message);
        }
        if (isLog && !TextUtils.isEmpty(message)) {
            Log.i(TAG, message);
        }
    }

    public static void saveData(String data) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd", Locale.CHINA);
            String formatStr = formatter.format(new Date());
            String path = "/sdcard/mf/zfb/log/log-" + formatStr + ".txt";
            File file = new File(path);
            if (!file.exists()) {
                new File("/sdcard/mf/zfb/log").mkdirs();
                file.createNewFile();
                randomAccessFile = new RandomAccessFile(file, "rw");
            }
            if (randomAccessFile == null) {
                randomAccessFile = new RandomAccessFile(file, "rw");
            }
            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss:SSS", Locale.CHINA);
            String formatStr2 = formatter2.format(new Date());
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write("\n".getBytes());
            randomAccessFile.write((formatStr2 + "  " + data).getBytes("utf-8"));
        } catch (Exception e) {
        }
    }
}
