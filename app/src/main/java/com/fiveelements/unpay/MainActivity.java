package com.fiveelements.unpay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.fiveelements.unpay.xp.Constant;
import com.fiveelements.unpay.xp.model.OrderEntity;
import com.fiveelements.unpay.xp.utils.RC4;
import com.fiveelements.unpay.xp.utils.ui.CreateMenu;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import de.robv.android.xposed.XposedBridge;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static Activity mainActivity;
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("====================MainActivity onCreate");
        mainActivity = this;
        Constant.activityP = this;
        Constant.handler = new Handler();
        CreateMenu.initMenu(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("====================MainActivity onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("====================MainActivity onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("====================MainActivity onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("====================MainActivity onPause");
    }
}