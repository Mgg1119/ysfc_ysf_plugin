package com.fiveelements.unpay.xp.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Enumeration;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/* loaded from: classes2.dex */
public class Tool {
    public static String printException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        XposedBridge.log(sw.toString());
        logV(sw.toString());
        return sw.toString();
    }

    public static void logV(String msg) {
        Log.v("xx", "" + msg);
    }

    public static void printAllMethods(Class cla) {
        Method[] methods = cla.getDeclaredMethods();
        XposedBridge.log("class name:" + cla.getName());
        for (int a = 0; a < methods.length; a++) {
            Type[] types = methods[a].getGenericParameterTypes();
            for (int b = 0; b < types.length; b++) {
                XposedBridge.log("method name:" + methods[a].getName() + "arg" + b + " type:" + types[b].toString());
            }
        }
    }

    public static void printAllFiled(Object o) {
        if (o == null) {
            return;
        }
        try {
            Class c = o.getClass();
            String cn = c.getName();
            while (!cn.equals(Object.class.getName())) {
                Field[] fields = c.getDeclaredFields();
                XposedBridge.log("printAllFiled:" + cn);
                for (int a = 0; a < fields.length; a++) {
                    fields[a].setAccessible(true);
                    Object o0 = fields[a].get(o);
                    XposedBridge.log(fields[a].getName() + ":" + o0);
                }
                c = c.getSuperclass();
                cn = c.getName();
            }
        } catch (Exception e) {
        }
    }

    public static void printThrowable(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        XposedBridge.log(sw.toString());
    }

    public static void printBundle(Bundle bundle, String flag) {
        if (bundle == null) {
            return;
        }
        for (String key : bundle.keySet()) {
            Object v = bundle.get(key);
            if (v != null) {
                XposedBridge.log("" + flag + "  " + key + ":" + v.toString());
            }
        }
    }

    public static void printFAllArg(Object[] args) {
        if (args == null) {
            return;
        }
        for (int a = 0; a < args.length; a++) {
            try {
                Object arg = args[a];
                if (arg == null) {
                    XposedBridge.log("printFAllArg" + a + "==null");
                } else {
                    XposedBridge.log("printFAllArg" + a + ":" + new Gson().toJson(arg));
                }
            } catch (Exception e) {
                printException(e);
                return;
            }
        }
    }

    public static void showMsg(Context context, String msg) {
        new Handler(Looper.getMainLooper()).post(new MsgShow(context, msg));
    }

    public static String getString(ByteBuffer buffer) {
        try {
            Charset charset = Charset.forName("UTF-8");
            CharsetDecoder decoder = charset.newDecoder();
            CharBuffer charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
            return charBuffer.toString();
        } catch (Exception e) {
            printException(e);
            return null;
        }
    }

    public static void loadMySo(Context context) {
        try {
            String pn = context.getPackageName();
            PackageInfo pi = context.getPackageManager().getPackageInfo("com.home.htest", 0);
            String soPath = pi.applicationInfo.nativeLibraryDir + "/libxx.so";
            System.load(soPath);
            XposedBridge.log(pn + "加载SO:" + soPath + "成功");
        } catch (Exception e) {
            printException(e);
        }
    }

    public static void loadHookSo(Context context) {
        try {
            String pn = context.getPackageName();
            PackageInfo pi = context.getPackageManager().getPackageInfo("com.home.htest", 0);
            String soPath = pi.applicationInfo.nativeLibraryDir + "/libhook.so";
            System.load(soPath);
            XposedBridge.log(pn + "加载SO:" + soPath + "成功");
        } catch (Exception e) {
            printException(e);
        }
    }

    public static boolean copyFile(String scr, String des) {
        try {
            File from = new File(scr);
            File to = new File(des);
            if (!from.exists()) {
                XposedBridge.log("" + scr + "不存在复制失败");
                return false;
            }
            if (to.exists()) {
                to.delete();
            }
            to.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(scr);
            FileOutputStream fileOutputStream = new FileOutputStream(des);
            byte[] data = new byte[1024];
            while (true) {
                int r = fileInputStream.read(data);
                if (r != -1) {
                    fileOutputStream.write(data, 0, r);
                    fileOutputStream.flush();
                } else {
                    fileInputStream.close();
                    fileOutputStream.close();
                    XposedBridge.log("文件复制完毕" + des);
                    return true;
                }
            }
        } catch (Exception e) {
            printException(e);
            return false;
        }
    }

    public static boolean saveMsg2File(byte[] data, String path) {
        try {
            File file = new File(path);
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            printException(e);
            return false;
        }
    }

    public static boolean saveMsg2File(String data, String path) {
        try {
            File file = new File(path);
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            printException(e);
            return false;
        }
    }

    public static String getSigCharsString(String pn, String raw) {
        if (pn.equals("com.nexstreaming.app.kinemasterfree")) {
            return "308201e13082014aa003020102020451465daf300d06092a864886f70d01010505003034310b3009060355040613024b52310e300c0603550407130553656f756c31153013060355040a130c4e657853747265616d696e673020170d3133303331383030313935395a180f32303633303330363030313935395a3034310b3009060355040613024b52310e300c0603550407130553656f756c31153013060355040a130c4e657853747265616d696e6730819f300d06092a864886f70d010101050003818d0030818902818100c64483ff035fa1b413136963677d7b2802473b1416b97dda52c12f1d895fb32287e80a43e94fea4ef86f40b92199bd53a349fc6b59619b355d04bbce8412b9b6e586c1e4af1fa110d009efe2ee8554e7ba4a23405f799ec5712f197e6e853b903b5016bba43a690af59160d53631e0cfa700e23ba94cad6dad9e17539494f9e50203010001300d06092a864886f70d0101050500038181003ad7272d0f4683a73498058664da7580cb00db3390ece2c81e78eaee7c076fe56fac4901eaab6caf23bad82c28f47bb6975d4ffcf713e67fceb344bb5cb94a2b11618e173851852f18b3106abc7398cf4480878e1c0bd9a1536de404fae7dfe83f2266efe5181acc3c8736a5a2707e45390c9d817649a962fc89cc6aaa3b830b";
        }
        return raw;
    }

    public static int getVersionCode(ClassLoader classLoader, String packName) {
        try {
            Class ActivityThread = classLoader.loadClass("android.app.ActivityThread");
            Object currentActivityThread = XposedHelpers.callStaticMethod(ActivityThread, "currentActivityThread", new Object[0]);
            Context context = (Context) XposedHelpers.callMethod(currentActivityThread, "getSystemContext", new Object[0]);
            int versionCode = context.getPackageManager().getPackageInfo(packName, 0).versionCode;
            return versionCode;
        } catch (Exception e) {
            return -1;
        }
    }

    public static Field getField(Class cla, String name, String type) {
        try {
            Field[] fields = cla.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String tType = field.getType().getName();
                String tName = field.getName();
                if (tType.equals(type) && tName.equals(name)) {
                    return field;
                }
            }
            return null;
        } catch (Exception e) {
            printException(e);
            return null;
        }
    }

    public static Method getMethod(Class cla, String name, String[] ParameterType, String returnType) {
        try {
            Method[] methods = cla.getDeclaredMethods();
            for (Method method : methods) {
                method.setAccessible(true);
                if (method.getName().equals(name)) {
                    String mReturnType = method.getReturnType().getName();
                    if (mReturnType.equals(returnType)) {
                        Class[] ParameterTypes = method.getParameterTypes();
                        if (ParameterTypes.length == ParameterType.length) {
                            if (ParameterTypes.length == 0) {
                                return method;
                            }
                            boolean allSame = true;
                            int b = 0;
                            while (true) {
                                if (b >= ParameterTypes.length) {
                                    break;
                                }
                                String pType = ParameterTypes[b].getName();
                                if (pType.equals(ParameterType[b])) {
                                    b++;
                                } else {
                                    allSame = false;
                                    break;
                                }
                            }
                            if (allSame) {
                                return method;
                            }
                        }
                    }
                }
            }
            return null;
        } catch (Exception e) {
            printException(e);
            return null;
        }
    }

    public static boolean loadSo(Context context, String soName) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo("com.home.htest", 0);
            String soPath = applicationInfo.nativeLibraryDir + "/" + soName;
            System.load(soPath);
            String cn = context.getPackageName();
            XposedBridge.log(cn + "加载" + soPath + "成功");
            return false;
        } catch (Exception e) {
            printException(e);
            return false;
        }
    }

    public static boolean copyFile(File arg4, File arg5, byte[] extraData) {
        try {
            if (!arg4.exists()) {
                return false;
            }
            BufferedInputStream v1_1 = new BufferedInputStream(new FileInputStream(arg4));
            byte[] v2 = new byte[v1_1.available()];
            v1_1.read(v2);
            v1_1.close();
            if (!arg5.exists()) {
                arg5.createNewFile();
            }
            BufferedOutputStream v1_2 = new BufferedOutputStream(new FileOutputStream(arg5));
            v1_2.write(v2);
            v1_2.flush();
            if (extraData != null) {
                v1_2.write(extraData);
                v1_2.flush();
            }
            v1_2.close();
            XposedBridge.log("文件复制成功");
            return true;
        } catch (Exception e) {
            printException(e);
            return false;
        }
    }

    public static boolean copyFile(InputStream inputStream, OutputStream outputStream) {
        try {
            byte[] v2 = new byte[inputStream.available()];
            inputStream.read(v2);
            inputStream.close();
            outputStream.write(v2);
            outputStream.flush();
            outputStream.close();
            XposedBridge.log("文件复制成功");
            return true;
        } catch (Exception e) {
            printException(e);
            return false;
        }
    }

    public static String getServerIp() {
        InetAddress candidateAddress = null;
        try {
            Enumeration ifaces = NetworkInterface.getNetworkInterfaces();
            while (ifaces.hasMoreElements()) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                Enumeration inetAddrs = iface.getInetAddresses();
                while (inetAddrs.hasMoreElements()) {
                    InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
                    if (!inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            return inetAddr.getHostAddress();
                        }
                        if (candidateAddress == null) {
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
            if (candidateAddress != null) {
                return candidateAddress.getHostAddress();
            }
            InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
            if (jdkSuppliedAddress == null) {
                throw new UnknownHostException("The JDK InetAddress.getLocalHost() method unexpectedly returned null.");
            }
            return jdkSuppliedAddress.getHostAddress();
        } catch (Exception e) {
            printException(e);
            return null;
        }
    }

    public static Context getSystemContext() {
        try {
            Class activityThreadClass = XposedHelpers.findClass("android.app.ActivityThread", (ClassLoader) null);
            Object activityThread = XposedHelpers.callStaticMethod(activityThreadClass, "currentActivityThread", new Object[0]);
            Context context = (Context) XposedHelpers.callMethod(activityThread, "getSystemContext", new Object[0]);
            return context;
        } catch (Exception e) {
            printException(e);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x007c, code lost:
        de.robv.android.xposed.XposedHelpers.callMethod(r4, "close", new java.lang.Object[0]);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean columnExists(Object r11, String r12, String r13) {
        /*
            java.lang.String r0 = "isAfterLast"
            r1 = 0
            java.lang.String r2 = "PRAGMA table_info('%s')"
            r3 = 1
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L85
            r4[r1] = r12     // Catch: java.lang.Exception -> L85
            java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch: java.lang.Exception -> L85
            java.lang.String r4 = "rawQuery"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Exception -> L85
            r5[r1] = r2     // Catch: java.lang.Exception -> L85
            r6 = 0
            r5[r3] = r6     // Catch: java.lang.Exception -> L85
            java.lang.Object r4 = de.robv.android.xposed.XposedHelpers.callMethod(r11, r4, r5)     // Catch: java.lang.Exception -> L85
            java.lang.String r5 = "moveToFirst"
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L85
            java.lang.Object r5 = de.robv.android.xposed.XposedHelpers.callMethod(r4, r5, r6)     // Catch: java.lang.Exception -> L85
            java.lang.Boolean r5 = (java.lang.Boolean) r5     // Catch: java.lang.Exception -> L85
            boolean r5 = r5.booleanValue()     // Catch: java.lang.Exception -> L85
            java.lang.Object[] r6 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L85
            java.lang.Object r6 = de.robv.android.xposed.XposedHelpers.callMethod(r4, r0, r6)     // Catch: java.lang.Exception -> L85
            java.lang.Boolean r6 = (java.lang.Boolean) r6     // Catch: java.lang.Exception -> L85
            boolean r6 = r6.booleanValue()     // Catch: java.lang.Exception -> L85
        L36:
            if (r6 != 0) goto L3a
            r7 = 1
            goto L3b
        L3a:
            r7 = 0
        L3b:
            r7 = r7 & r5
            if (r7 == 0) goto L7c
            java.lang.String r7 = "getColumnIndex"
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L85
            java.lang.String r9 = "name"
            r8[r1] = r9     // Catch: java.lang.Exception -> L85
            java.lang.Object r7 = de.robv.android.xposed.XposedHelpers.callMethod(r4, r7, r8)     // Catch: java.lang.Exception -> L85
            java.lang.Integer r7 = (java.lang.Integer) r7     // Catch: java.lang.Exception -> L85
            int r7 = r7.intValue()     // Catch: java.lang.Exception -> L85
            java.lang.String r8 = "getString"
            java.lang.Object[] r9 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L85
            java.lang.Integer r10 = java.lang.Integer.valueOf(r7)     // Catch: java.lang.Exception -> L85
            r9[r1] = r10     // Catch: java.lang.Exception -> L85
            java.lang.Object r8 = de.robv.android.xposed.XposedHelpers.callMethod(r4, r8, r9)     // Catch: java.lang.Exception -> L85
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Exception -> L85
            boolean r9 = r8.equals(r13)     // Catch: java.lang.Exception -> L85
            if (r9 == 0) goto L67
            return r3
        L67:
            java.lang.String r9 = "moveToNext"
            java.lang.Object[] r10 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L85
            de.robv.android.xposed.XposedHelpers.callMethod(r4, r9, r10)     // Catch: java.lang.Exception -> L85
            java.lang.Object[] r9 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L85
            java.lang.Object r9 = de.robv.android.xposed.XposedHelpers.callMethod(r4, r0, r9)     // Catch: java.lang.Exception -> L85
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Exception -> L85
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Exception -> L85
            r6 = r9
            goto L36
        L7c:
            java.lang.String r0 = "close"
            java.lang.Object[] r3 = new java.lang.Object[r1]     // Catch: java.lang.Exception -> L85
            de.robv.android.xposed.XposedHelpers.callMethod(r4, r0, r3)     // Catch: java.lang.Exception -> L85
            goto L89
        L85:
            r0 = move-exception
            printException(r0)
        L89:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mf.xp.utils.Tool.columnExists(java.lang.Object, java.lang.String, java.lang.String):boolean");
    }

    public static byte[] toGzip(byte[] content) {
        try {
            ByteArrayInputStream v1 = new ByteArrayInputStream(content);
            ByteArrayOutputStream v2 = new ByteArrayOutputStream();
            GZIPOutputStream v3 = new GZIPOutputStream(v2);
            byte[] v0_2 = new byte[4096];
            while (true) {
                int v4 = v1.read(v0_2);
                if (v4 != -1) {
                    v3.write(v0_2, 0, v4);
                } else {
                    v3.flush();
                    v3.finish();
                    byte[] v0_3 = v2.toByteArray();
                    v1.close();
                    v2.close();
                    v3.close();
                    return v0_3;
                }
            }
        } catch (Exception e) {
            printException(e);
            return content;
        }
    }

    public static byte[] unGZip(byte[] data) {
        try {
            ByteArrayInputStream v1 = new ByteArrayInputStream(data);
            GZIPInputStream v2 = new GZIPInputStream(v1);
            byte[] v0_3 = new byte[4096];
            ByteArrayOutputStream v3 = new ByteArrayOutputStream();
            while (true) {
                int v4 = v2.read(v0_3, 0, v0_3.length);
                if (v4 != -1) {
                    v3.write(v0_3, 0, v4);
                } else {
                    byte[] v0_4 = v3.toByteArray();
                    v3.flush();
                    v3.close();
                    v2.close();
                    v1.close();
                    return v0_4;
                }
            }
        } catch (Exception e) {
            printException(e);
            return data;
        }
    }

    public static void loadLibrary0(String libName, ClassLoader callingClassloder) {
        try {
            Runtime runtime = Runtime.getRuntime();
            XposedHelpers.callMethod(runtime, "loadLibrary0", new Object[]{callingClassloder, libName});
            XposedBridge.log("loadLibrary0 加载SO:" + libName);
        } catch (Exception e) {
            printException(e);
        }
    }

    public static void load0(String soPath, Class fromClass) {
        try {
            Runtime runtime = Runtime.getRuntime();
            XposedHelpers.callMethod(runtime, "load0", new Object[]{fromClass, soPath});
            XposedBridge.log("load0 加载SO:" + soPath);
        } catch (Exception e) {
            printException(e);
        }
    }

    public static String nativeLoad(String filename, ClassLoader loader, String ldLibraryPath) {
        String r;
        try {
            int apiLever = Build.VERSION.SDK_INT;
            if (apiLever >= 28 || apiLever <= 17) {
                r = (String) XposedHelpers.callStaticMethod(Runtime.class, "nativeLoad", new Object[]{filename, loader});
            } else {
                r = (String) XposedHelpers.callStaticMethod(Runtime.class, "nativeLoad", new Object[]{filename, loader, ldLibraryPath});
            }
            XposedBridge.log("nativeLoad 加载SO:" + filename + " \nresult:" + r);
            return r;
        } catch (Exception e) {
            printException(e);
            return "";
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:34:0x0106
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:82)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:40)
        */
    public static void addNativePathToClassLoader(ClassLoader r18, String r19) {
        /*
            Method dump skipped, instructions count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mf.xp.utils.Tool.addNativePathToClassLoader(java.lang.ClassLoader, java.lang.String):void");
    }

    public static String bytes2HexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            sb.append(String.format("%02x", Byte.valueOf(b[i])));
        }
        return sb.toString();
    }
}
