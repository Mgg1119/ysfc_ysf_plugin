package com.fiveelements.unpay.xp.model;

import com.google.gson.annotations.SerializedName;

public class BaseModel {

    /**
     * msg
     */
    @SerializedName("msg")
    private String msg;
    /**
     * resp
     */
    @SerializedName("resp")
    private String resp;
    /**
     * cmd
     */
    @SerializedName("cmd")
    private String cmd;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}
