package com.fiveelements.unpay.xp.utils;

import android.content.Context;
import android.widget.Toast;

import de.robv.android.xposed.XposedBridge;

/* loaded from: classes2.dex */
public class MsgShow implements Runnable {
    Context context;
    String msg;

    public MsgShow(Context context, String msg) {
        this.context = context;
        this.msg = msg;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            XposedBridge.log("" + this.msg);
            Toast.makeText(this.context, this.msg, 1).show();
        } catch (Exception e) {
            Tool.printException(e);
        }
    }
}
