package com.fiveelements.unpay.xp;

import android.app.Activity;
import android.os.Handler;

import com.fiveelements.unpay.xp.model.BillDetailInfo;
import com.fiveelements.unpay.xp.model.InitData;
import com.fiveelements.unpay.xp.model.LoginModel;
import com.fiveelements.unpay.xp.model.OrderEntity;
import com.fiveelements.unpay.xp.model.OrderInfo;
import com.fiveelements.unpay.xp.model.OrderStatusInfo;
import com.fiveelements.unpay.xp.model.PayAndHandleInfo;
import com.fiveelements.unpay.xp.model.UserInfo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Constant {

    public static Activity activityS;
    public static Activity activityP;
    public static Activity activityQ;
    public static Handler handlerT;
    public static OrderInfo orderInfo;
    public static HashMap<String, Object> hashMapW = new HashMap<>();
    public static String HOST = "http://ysfc.ikuai5.com:6230";
    public static String LOGIN_URL = "/api/phone/request";
    public static String SECRET = "ab4bb9da";
    public static String version = "7.3.1.0";
    public static BillDetailInfo billDetailInfo;
    public static JSONObject OrderRequestJson;
    public static OrderStatusInfo statusInfo = new OrderStatusInfo();
    public static String mobile = "18695643156";
    public static Handler handler;
    public static Handler orderHandler;

    public static String ysfTn = "";
    public static String ysfOrderId = "";
    public static PayAndHandleInfo payAndHandleInfo;
    public static UserInfo userInfo;
    public static String bankName;
    public static String upLogErrorMsg;
    public static String passwordArrays = "";
    public static InitData initData;
    public static final String YSF_FLAG = "telecom_ysf_app_apk";
    public static final String JDYSF_FLAG = "mobile_jdapp_ysf_apk";
    public static final int CAN_CLICK_TYPE = 0;
    public static final int NOT_ALLOW_CLICK_TYPE = 1;

    public static String loginName = "";
    public static List<String> channelList = new ArrayList<>();
    public static String YSFCHHF = "1";
    public static String JDYSF = "2";
    public static int amount = 10;
    public static int maxAmount = 500;
    public static int orderInterval = 60;
    public static int stopNum = 15;
    public static int stopPrice = 1500;
    public static boolean isStartGetOrder = false;
    public static int allOrderPrice = 0;
    public static int doOrderNum = 0;
    public static OrderEntity orderEntity;
    public static boolean isDoneOrder = true;
    public static boolean waitActionStatus = false;
    public static String orderQuotaMsg = "";
    public static String requestFlag = "";

    public static HashMap<String, Activity> activityHashMap = new HashMap<>();

    public static LoginModel loginModel;

    public static final String SP_PASSWORD = "sp_password";
    public static final String SP_LOGIN_ACCOUNT = "sp_login_account";
    public static final String SP_LOGIN_PASSWORD = "sp_login_password";

    public static final int POLL_DO_ORDER_PAY_SUCCESS = 0;
    public static final int POLL_DO_ORDER_PAY_FAIL = 1;
    public static final int POLL_DO_ORDER_ERROR = 2;
}
