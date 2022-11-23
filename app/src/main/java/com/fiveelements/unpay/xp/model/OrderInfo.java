package com.fiveelements.unpay.xp.model;

import java.util.ArrayList;

public class OrderInfo {

    //      createTime=" + this.s + ", payTime=" + this.t + ",
    //      TextList=" + this.z + ", mInfo=" + null + ", oStatus=" + this.A + '}';
    private String mfOrderId;
    private String orderId;
    private String mobile;
    private int amount;
    private String payAmount;
    private boolean ispwd;
    private String message;
    private long createTime;
    private long payTime;
    private ArrayList TextList;
    private OrderStatus oStatus;

    public String getMfOrderId() {
        return mfOrderId;
    }

    public void setMfOrderId(String mfOrderId) {
        this.mfOrderId = mfOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public boolean isIspwd() {
        return ispwd;
    }

    public void setIspwd(boolean ispwd) {
        this.ispwd = ispwd;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public ArrayList getTextList() {
        return TextList;
    }

    public void setTextList(ArrayList textList) {
        TextList = textList;
    }

    public OrderStatus getoStatus() {
        return oStatus;
    }

    public void setoStatus(OrderStatus oStatus) {
        this.oStatus = oStatus;
    }
}
