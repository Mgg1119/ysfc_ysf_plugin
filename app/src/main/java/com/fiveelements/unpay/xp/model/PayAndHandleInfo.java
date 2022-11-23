package com.fiveelements.unpay.xp.model;

import com.google.gson.annotations.SerializedName;

public class PayAndHandleInfo extends BaseModel{


    @SerializedName("params")
    private ParamsDTO params;

    public ParamsDTO getParams() {
        return params;
    }

    public void setParams(ParamsDTO params) {
        this.params = params;
    }

    public static class ParamsDTO {
        /**
         * tn
         */
        @SerializedName("tn")
        private String tn;
        /**
         * orderId
         */
        @SerializedName("orderId")
        private String orderId;

        public String getTn() {
            return tn;
        }

        public void setTn(String tn) {
            this.tn = tn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
    }
}
