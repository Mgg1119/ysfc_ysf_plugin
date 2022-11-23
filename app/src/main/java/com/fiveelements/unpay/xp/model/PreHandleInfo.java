package com.fiveelements.unpay.xp.model;

import com.google.gson.annotations.SerializedName;

public class PreHandleInfo extends BaseModel {

    /**
     * params
     */
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
         * isSupApplePay
         */
        @SerializedName("isSupApplePay")
        private String isSupApplePay;
        /**
         * tn
         */
        @SerializedName("tn")
        private String tn;

        public String getIsSupApplePay() {
            return isSupApplePay;
        }

        public void setIsSupApplePay(String isSupApplePay) {
            this.isSupApplePay = isSupApplePay;
        }

        public String getTn() {
            return tn;
        }

        public void setTn(String tn) {
            this.tn = tn;
        }
    }
}
