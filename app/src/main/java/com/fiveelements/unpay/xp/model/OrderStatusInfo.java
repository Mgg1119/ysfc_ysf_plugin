package com.fiveelements.unpay.xp.model;

public class OrderStatusInfo {

    public String tips;
    public boolean isSuccess = false;
    public int type = -100;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
