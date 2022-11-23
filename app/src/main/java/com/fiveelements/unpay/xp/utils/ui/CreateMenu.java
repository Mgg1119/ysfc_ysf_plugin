package com.fiveelements.unpay.xp.utils.ui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.fiveelements.unpay.R;
import com.fiveelements.unpay.widget.DragView;
import com.fiveelements.unpay.xp.Constant;
import com.fiveelements.unpay.xp.http.RestClient;
import com.fiveelements.unpay.xp.model.LoginModel;
import com.fiveelements.unpay.xp.utils.DensityUtil;
import com.fiveelements.unpay.xp.utils.SPUtils;
import com.fiveelements.unpay.xp.utils.ShapeUtil;
import com.fiveelements.unpay.xp.utils.UiUtil;
import com.fiveelements.unpay.xp.utils.log.LogUtils;
import com.google.gson.Gson;

import org.json.JSONObject;

public class CreateMenu {

    public static TextView tipsView;
    private static TextView tvTips;
    private static Switch st;

    public static void initMenu(Activity activity) {
        Constant.passwordArrays = "";
        Constant.loginModel = null;
        float textCommonSize = 13f;
        // 可拖拽图标
        RelativeLayout contentView = new RelativeLayout(activity);
        contentView.setGravity(Gravity.CENTER_HORIZONTAL);
        contentView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));

        // 主布局
        LinearLayout layout = new LinearLayout(activity);
        layout.setOrientation(LinearLayout.VERTICAL);
        GradientDrawable layoutDrawable = new GradientDrawable();
        layoutDrawable.setColor(Color.parseColor("#CC000000"));
        layout.setBackground(layoutDrawable);
        layout.setClickable(true);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                DensityUtil.dpToPx(300, activity));
//        params.gravity = Gravity.CENTER_HORIZONTAL;
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setLayoutParams(params);

        EditText etAccount = new EditText(activity);
        etAccount.setTextSize(textCommonSize);
        etAccount.setHint("请输入账号");
        etAccount.setHintTextColor(Color.parseColor("#ffffff"));
        etAccount.setTextColor(Color.parseColor("#ffffff"));
        LinearLayout.LayoutParams accountParams = new LinearLayout.LayoutParams(DensityUtil.dpToPx(150, activity),
                DensityUtil.dpToPx(40, activity));
        etAccount.setLayoutParams(accountParams);

        TextView textView = new TextView(activity);
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setTextSize(textCommonSize);
        textView.setText("账号：");

        LinearLayout accountLayout = new LinearLayout(activity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.topMargin = DensityUtil.dpToPx(70, activity);
        accountLayout.setLayoutParams(layoutParams);
        accountLayout.setOrientation(LinearLayout.HORIZONTAL);
        accountLayout.addView(textView);
        accountLayout.addView(etAccount);

        // 密码
        EditText etPassword = new EditText(activity);
        etPassword.setTextSize(textCommonSize);
        etPassword.setHintTextColor(Color.parseColor("#ffffff"));
        etPassword.setTextColor(Color.parseColor("#ffffff"));
        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPassword.setHint("请输入密码");
        LinearLayout.LayoutParams passwordParams = new LinearLayout.LayoutParams(DensityUtil.dpToPx(150, activity),
                DensityUtil.dpToPx(40, activity));
        etPassword.setLayoutParams(passwordParams);

        TextView textView1 = new TextView(activity);
        textView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        textView1.setTextColor(Color.parseColor("#ffffff"));
        textView1.setTextSize(textCommonSize);
        textView1.setText("密码：");

        LinearLayout linearLayout = new LinearLayout(activity);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.topMargin = DensityUtil.dpToPx(70, activity);
        linearLayout.setLayoutParams(layoutParams1);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.addView(textView1);
        linearLayout.addView(etPassword);

        // 保存密码按钮
        LinearLayout.LayoutParams saveParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        saveParams.leftMargin = 10;
        CheckBox btnSave = new CheckBox(activity);
        btnSave.setTextColor(Color.parseColor("#00A8FF"));
        btnSave.setTextSize(11f);

        btnSave.setText("保存");
        btnSave.setLayoutParams(saveParams);

        // 银行卡密码、保存按钮 线性布局
        LinearLayout passwordLayout = new LinearLayout(activity);
        passwordLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams bankPasswordParams = new LinearLayout.LayoutParams(-2, -2);
        passwordLayout.setLayoutParams(bankPasswordParams);
        // 银行卡密码输入框
        EditText bankPassword = new EditText(activity);
        bankPassword.setHintTextColor(Color.parseColor("#ffffff"));
        bankPassword.setTextColor(Color.parseColor("#ffffff"));
        bankPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        String password = SPUtils.getInstance(Constant.activityP).getString(Constant.SP_PASSWORD);
        if (!TextUtils.isEmpty(password)) {
            btnSave.setChecked(true);
            Constant.passwordArrays = password;
            bankPassword.setText(password);
        }
        bankPassword.setTextSize(textCommonSize);
        bankPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        bankPassword.setHint("请输入银行卡密码");
        LinearLayout.LayoutParams bankParams = new LinearLayout.LayoutParams(-2, -2);
        bankPassword.setLayoutParams(bankParams);

        Button btnDoOrder = new Button(activity);
        btnDoOrder.setTextSize(textCommonSize);
        btnDoOrder.setTextColor(Color.parseColor("#000000"));
        btnDoOrder.setText("去做单");
        // 添加子view
        passwordLayout.addView(btnDoOrder);
        passwordLayout.addView(bankPassword);
        passwordLayout.addView(btnSave);

        // 登录及关闭
        LinearLayout btnLayout = new LinearLayout(activity);
        btnLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams btnLayoutParams = new LinearLayout.LayoutParams(-2, -2);
        btnLayout.setLayoutParams(btnLayoutParams);

        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(-2, -2);
        btnParams.rightMargin = 60;

        Button btnLogin = new Button(activity);
        btnLogin.setTag(Constant.CAN_CLICK_TYPE);
        btnLogin.setTextSize(textCommonSize);
        btnLogin.setTextColor(Color.parseColor("#000000"));
        btnLogin.setText("登录");
        btnLogin.setLayoutParams(btnParams);

        LinearLayout.LayoutParams btnParams2 = new LinearLayout.LayoutParams(-2, -2);
        btnParams2.leftMargin = 60;
        Button btnClose = new Button(activity);
        btnClose.setTextSize(textCommonSize);
        btnLogin.setTextColor(Color.parseColor("#000000"));
        btnClose.setText("关闭");
        btnClose.setLayoutParams(btnParams2);
        btnLayout.addView(btnClose);
        btnLayout.addView(btnLogin);

        tipsView = new TextView(activity);
        tipsView.setGravity(Gravity.START | Gravity.BOTTOM);
        tipsView.setPadding(10, 0, 0, 30);
        tipsView.setTextColor(Color.parseColor("#D63834"));
        tipsView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));

        layout.addView(accountLayout);
        layout.addView(linearLayout);
        layout.addView(btnLayout);
        layout.addView(passwordLayout);
        layout.addView(tipsView);

        LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                DensityUtil.dpToPx(400, activity));
        contentView.addView(layout, contentParams);

        // 可拖拽图标
        DragView freeView = new DragView(activity);
        freeView.setImageResource(R.mipmap.ic_fe_start);
        LinearLayout.LayoutParams freeParams = new LinearLayout.LayoutParams(DensityUtil.dpToPx(80, activity),
                DensityUtil.dpToPx(80, activity));
        freeParams.topMargin = UiUtil.getMaxHeight(activity) - DensityUtil.dpToPx(200, activity);
        freeParams.leftMargin = (UiUtil.getMaxWidth(activity) / 2) - DensityUtil.dpToPx(40, activity);
        freeParams.gravity = Gravity.CENTER;
        freeView.setLayoutParams(freeParams);
        contentView.addView(freeView, freeParams);
        layout.setVisibility(View.INVISIBLE);

        activity.addContentView(contentView, new LinearLayout.LayoutParams(-1, -1));
        String account = SPUtils.getInstance(activity).getString(Constant.SP_LOGIN_ACCOUNT);
        String pwd = SPUtils.getInstance(activity).getString(Constant.SP_LOGIN_PASSWORD);
        if (!TextUtils.isEmpty(account)) {
            etAccount.setText(account);
        }
        if (!TextUtils.isEmpty(pwd)) {
            etPassword.setText(pwd);
        }

        btnClose.setOnClickListener(v -> {
            layout.setVisibility(View.INVISIBLE);
        });

        btnLogin.setOnClickListener(v -> {
            int tag = (int) btnLogin.getTag();
            if (tag == Constant.NOT_ALLOW_CLICK_TYPE) {
                setTips("正在登录中,请勿重复点击...");
                return;
            }
            if (TextUtils.isEmpty(etAccount.getText().toString())) {
                setTips("请输入账号.");
                return;
            }
            if (TextUtils.isEmpty(etPassword.getText().toString())) {
                setTips("请输入密码.");
                return;
            }
            if (Constant.loginModel != null && Constant.loginModel.getData() != null && Constant.loginName.equals(etAccount.getText().toString())) {
                setTips("已经登录过了.");
                return;
            }
            setTips("正在登录中,请稍后...");
            new Thread(() -> {
                try {
                    btnLogin.setTag(Constant.NOT_ALLOW_CLICK_TYPE);
                    JSONObject loginParams = new JSONObject();
                    loginParams.put("username", etAccount.getText().toString());
                    loginParams.put("password", etPassword.getText().toString());
                    RestClient.post("login", Constant.YSF_FLAG, loginParams, new RestClient.INetCallBack() {
                        @Override
                        public void onFailed(String errorMsg, Throwable ex) {
                            btnLogin.setTag(Constant.CAN_CLICK_TYPE);
                            setTips("登录失败,请重试. " + errorMsg);
                        }

                        @Override
                        public void onSuccess(String response) {
                            Constant.loginName = etAccount.getText().toString();
                            btnLogin.setTag(Constant.CAN_CLICK_TYPE);
                            setTips("登录成功");
                            // 保存登录账号
                            save(Constant.SP_LOGIN_ACCOUNT, etAccount.getText().toString(), activity);
                            // 保存登录密码
                            save(Constant.SP_LOGIN_PASSWORD, etPassword.getText().toString(), activity);
                            Constant.loginModel = new Gson().fromJson(response, LoginModel.class);
                        }
                    });
                } catch (Exception e) {
                    btnLogin.setTag(Constant.CAN_CLICK_TYPE);
                    setTips("登录失败,请重试 : " + e.getMessage());
                    e.printStackTrace();
                }
            }).start();
        });

        freeView.setOnClickListener(v -> layout.setVisibility(layout.getVisibility() == View.VISIBLE ? View.INVISIBLE : View.VISIBLE));
        // 去做单
        btnDoOrder.setOnClickListener(v -> {
            if (checkLoginAndPassword(bankPassword.getText().toString(), activity)) {
                savePassword(btnSave.isChecked(), bankPassword.getText().toString(), activity);
                layout.removeAllViews();
                layout.addView(createOrderUI(activity));
            }
        });
    }

    /**
     * 数据存储
     */
    public static void save(String key, String value, Activity activity) {
        SPUtils.getInstance(activity).put(key, value);
    }

    public static void savePassword(boolean isSave, String password, Activity activity) {
        if (isSave) {
            SPUtils.getInstance(activity).put(Constant.SP_PASSWORD, password);
        } else {
            SPUtils.getInstance(activity).remove(Constant.SP_PASSWORD);
        }
        Constant.passwordArrays = password;
        LogUtils.d("===============支付密码为：" + Constant.passwordArrays);
    }

    public static boolean checkLoginAndPassword(String password, Activity activity) {
        if (Constant.loginModel == null || Constant.loginModel.getData() == null) {
            setTips("请先登录");
            return false;
        }
        if (TextUtils.isEmpty(password) || password.length() != 6) {
            setTips("请配置ysf密码");
            return false;
        }
        return true;
    }

    public static void setTips(String tips) {
        if (tipsView != null && Constant.handler != null) {
            Constant.handler.post(new Runnable() {
                @Override
                public void run() {
                    tipsView.setText(tips);
                }
            });
        }
    }

    /**
     * 做单界面
     */
    public static View createOrderUI(Activity activity) {
        Constant.channelList.clear();
        // 做单布局
        int greenColor = Color.parseColor("#5BB734");
        int redColor = Color.parseColor("#D63834");
        int whiteColor = Color.parseColor("#ffffff");
        float textCommonSize = 14f;
        float etTextSize = 15f;
        LinearLayout contentLayout = new LinearLayout(activity);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams contentLayoutParams = new LinearLayout.LayoutParams(-1, -1);
        contentLayoutParams.topMargin = DensityUtil.dpToPx(60, activity);
        contentLayout.setPadding(16, 0, 0, 0);
        contentLayout.setLayoutParams(contentLayoutParams);
        // 版本信息
        TextView tvVersion = new TextView(activity);
        tvVersion.setTextColor(Color.parseColor("#ffffff"));
        tvVersion.setTextSize(textCommonSize);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("登录账号：");
        stringBuilder.append(SPUtils.getInstance(activity).getString(Constant.SP_LOGIN_ACCOUNT, ""));
        stringBuilder.append("\r\r\r");
        stringBuilder.append("版本：");
        stringBuilder.append(Constant.version);
        stringBuilder.append("\n");
        tvVersion.setText(stringBuilder.toString());
        // 提示
        TextView tvWarning = new TextView(activity);
        tvWarning.setTextSize(textCommonSize);
        tvWarning.setTextColor(greenColor);
        tvWarning.setText("禁止手动干预支付流程,干预造成反撸自负!!! \n\n做单渠道选择：(多选)");
        // 渠道
        LinearLayout channelLayout = new LinearLayout(activity);
        channelLayout.setOrientation(LinearLayout.HORIZONTAL);
        // ysf渠道
        CheckBox ysfCheckBox = new CheckBox(activity);
        ysfCheckBox.setTextColor(redColor);
        ysfCheckBox.setText("云闪付充值    ");
        ysfCheckBox.setChecked(true);
        // ysf渠道
        CheckBox ysfJdCheckBox = new CheckBox(activity);
        ysfJdCheckBox.setTextColor(redColor);
        ysfJdCheckBox.setText("京东云闪付");
        ysfJdCheckBox.setChecked(true);

        Constant.channelList.add(Constant.YSFCHHF);
        Constant.channelList.add(Constant.JDYSF);
        channelLayout.addView(ysfCheckBox);
        channelLayout.addView(ysfJdCheckBox);
        // 银行卡选择
        TextView tvBank = new TextView(activity);
        tvBank.setTextSize(textCommonSize);
        tvBank.setText("银行卡选择：默认");
        tvBank.setTextColor(whiteColor);

        // 做单面额
        LinearLayout amountLayout = new LinearLayout(activity);
        amountLayout.setOrientation(LinearLayout.HORIZONTAL);

        TextView tvAmount = new TextView(activity);
        tvAmount.setTextColor(whiteColor);
        tvAmount.setTextSize(textCommonSize);
        tvAmount.setText("做单面额选择: ");
        // 最小金额
        EditText etAmount = new EditText(activity);
        etAmount.setTextSize(etTextSize);
        etAmount.setTextColor(greenColor);
        etAmount.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        etAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
        etAmount.setText("10");
        TextView tv = new TextView(activity);
        tv.setTextColor(whiteColor);
        tv.setTextSize(textCommonSize);
        tv.setText(" 至: ");
        // 最大金额
        EditText etMaxAmount = new EditText(activity);
        etMaxAmount.setTextSize(etTextSize);
        etMaxAmount.setTextColor(greenColor);
        etMaxAmount.setInputType(InputType.TYPE_CLASS_NUMBER);
        etMaxAmount.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        etMaxAmount.setText("500");
        amountLayout.addView(tvAmount);
        amountLayout.addView(etAmount);
        amountLayout.addView(tv);
        amountLayout.addView(etMaxAmount);
        // 做单间隔
        LinearLayout intervalLayout = new LinearLayout(activity);
        intervalLayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView tvInterval = new TextView(activity);
        tvInterval.setText("做单间隔");
        tvInterval.setTextSize(textCommonSize);
        tvInterval.setTextColor(whiteColor);
        EditText etInterval = new EditText(activity);
        etInterval.setTextSize(etTextSize);
        etInterval.setTextColor(greenColor);
        etInterval.setText("60");
        etInterval.setInputType(InputType.TYPE_CLASS_NUMBER);
        etInterval.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        etInterval.setGravity(Gravity.CENTER_HORIZONTAL);
        LinearLayout.LayoutParams intervalParams = new LinearLayout.LayoutParams(DensityUtil.dpToPx(60, activity), -2);
        etInterval.setLayoutParams(intervalParams);
        TextView tvSecond = new TextView(activity);
        tvSecond.setText("秒");
        tvSecond.setTextSize(textCommonSize);
        tvSecond.setTextColor(whiteColor);
        intervalLayout.addView(tvInterval);
        intervalLayout.addView(etInterval);
        intervalLayout.addView(tvSecond);
        // 停止条件
        LinearLayout stopConditionLayout = new LinearLayout(activity);
        stopConditionLayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv1 = new TextView(activity);
        tv1.setText("做单");
        tv1.setTextColor(whiteColor);
        EditText etNumber = new EditText(activity);
        etNumber.setTextSize(etTextSize);
        etNumber.setTextColor(greenColor);
        etNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        etNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        etNumber.setText("15");
        TextView tv2 = new TextView(activity);
        tv2.setText("笔停止接单  ");
        tv2.setTextColor(whiteColor);
        EditText etPrice = new EditText(activity);
        etPrice.setTextSize(etTextSize);
        etPrice.setTextColor(greenColor);
        etPrice.setInputType(InputType.TYPE_CLASS_NUMBER);
        etPrice.setFilters(new InputFilter[]{new InputFilter.LengthFilter(5)});
        etPrice.setText("1500");
        TextView tv3 = new TextView(activity);
        tv3.setText("元停止接单\n");
        tv3.setTextColor(whiteColor);
        stopConditionLayout.addView(tv1);
        stopConditionLayout.addView(etNumber);
        stopConditionLayout.addView(tv2);
        stopConditionLayout.addView(etPrice);
        stopConditionLayout.addView(tv3);
        // 开始做单
        LinearLayout starOrderLayout = new LinearLayout(activity);
        starOrderLayout.setOrientation(LinearLayout.HORIZONTAL);
        TextView tv4 = new TextView(activity);
        tv4.setTextColor(whiteColor);
        tv4.setText("开始做单：  ");
        st = new Switch(activity);
        int size = DensityUtil.dpToPx(20, activity);
        GradientDrawable drawable = ShapeUtil.createShape(DensityUtil.dpToPx(3, activity), -1, GradientDrawable.OVAL,
                "#00000000", "#ff71a5", size, size);
        GradientDrawable background = new GradientDrawable();
        background.setCornerRadius(DensityUtil.dpToPx(15, activity));
        background.setColor(Color.parseColor("#FFE4ED"));
        st.setThumbDrawable(drawable);
//        st.setTrackResource(background);
        st.setTrackDrawable(background);
        starOrderLayout.addView(tv4);
        starOrderLayout.addView(st);
        st.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // 开始做单
                if (Constant.channelList.isEmpty()) {
                    st.setChecked(false);
                    tvTips.setText("请先选择做单渠道");
                    return;
                }
                if (TextUtils.isEmpty(etAmount.getText().toString())) {
                    st.setChecked(false);
                    tvTips.setText("请输入做单面值最小值");
                    return;
                }
                if (TextUtils.isEmpty(etMaxAmount.getText().toString())) {
                    st.setChecked(false);
                    tvTips.setText("请输入做单面值最大值");
                    return;
                }
                if (TextUtils.isEmpty(etInterval.getText().toString())) {
                    st.setChecked(false);
                    tvTips.setText("请输入做单间隔");
                    return;
                }
                int number = Integer.parseInt(etInterval.getText().toString());
                if (number < 10) {
                    st.setChecked(false);
                    tvTips.setText("做单间隔需要大于等于10秒");
                    return;
                }
                if (TextUtils.isEmpty(etNumber.getText().toString())) {
                    st.setChecked(false);
                    tvTips.setText("请输入停止做单笔数");
                    return;
                }
                if (TextUtils.isEmpty(etPrice.getText().toString())) {
                    st.setChecked(false);
                    tvTips.setText("请输入停止做单金额...");
                    return;
                }
                Constant.amount = Integer.parseInt(etAmount.getText().toString());
                Constant.maxAmount = Integer.parseInt(etMaxAmount.getText().toString());
                Constant.orderInterval = Integer.parseInt(etInterval.getText().toString());
                Constant.stopNum = Integer.parseInt(etNumber.getText().toString());
                Constant.stopPrice = Integer.parseInt(etPrice.getText().toString());
                Constant.allOrderPrice = 0;
                Constant.doOrderNum = 0;
                Constant.isStartGetOrder = true;
                tvTips.setText("正在做单...");
                Constant.isDoneOrder = true;
                String flag = Constant.channelList.get(0).equals(Constant.JDYSF) ? Constant.JDYSF_FLAG : Constant.YSF_FLAG;
                RestClient.getOrderInfo(flag);
            } else {
                // 停止做单
                Constant.isStartGetOrder = false;
                Constant.orderHandler.removeCallbacksAndMessages(null);
                tvTips.setText("停止做单");
            }
        });
        ysfCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Constant.channelList.add(Constant.YSFCHHF);
            } else {
                Constant.channelList.remove(Constant.YSFCHHF);
            }
        });
        ysfJdCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Constant.channelList.add(Constant.JDYSF);
            } else {
                Constant.channelList.remove(Constant.JDYSF);
            }
        });
        tvTips = new TextView(activity);
        tvTips.setTextColor(redColor);

        // 添加至主布局
        contentLayout.addView(tvVersion);
        contentLayout.addView(tvWarning);
        contentLayout.addView(channelLayout);
        contentLayout.addView(tvBank);
        contentLayout.addView(amountLayout);
        contentLayout.addView(intervalLayout);
        contentLayout.addView(stopConditionLayout);
        contentLayout.addView(starOrderLayout);
        contentLayout.addView(tvTips);

        return contentLayout;
    }

    public static void setOrderTips(String tips) {
        if (Constant.handler != null && tvTips != null) {
            Constant.handler.post(new Runnable() {
                @Override
                public void run() {
                    if (Constant.isStartGetOrder) {
                        tvTips.setText(tips);
                    } else {
                        if (TextUtils.isEmpty(Constant.orderQuotaMsg)) {
                            tvTips.setText("停止做单.");
                        } else {
                            tvTips.setText(Constant.orderQuotaMsg);
                        }
                    }
                }
            });
        }
    }

    public static void closeSwitch() {
        if (Constant.handler != null && st != null) {
            Constant.handler.post(new Runnable() {
                @Override
                public void run() {
                    st.setChecked(false);
                }
            });
        }
    }
}
