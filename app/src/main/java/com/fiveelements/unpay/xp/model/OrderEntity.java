package com.fiveelements.unpay.xp.model;

import com.google.gson.annotations.SerializedName;

public class OrderEntity extends BaseModel {


    /**
     * data
     */
    @SerializedName("data")
    private DataDTO data;

    public static class DataDTO {
        /**
         * id
         */
        @SerializedName("id")
        private Integer id;
        /**
         * amount
         */
        @SerializedName("amount")
        private String amount;
        /**
         * discount
         */
        @SerializedName("discount")
        private String discount;
        /**
         * mobile
         */
        @SerializedName("mobile")
        private String mobile;
        /**
         * operator
         */
        @SerializedName("operator")
        private String operator;
        /**
         * prov
         */
        @SerializedName("prov")
        private String prov;

        @SerializedName("orderNo")
        private String orderNo;

        @SerializedName("payUrl")
        private String payUrl;

        private String payMessage;

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getPayUrl() {
            return payUrl;
        }

        public void setPayUrl(String payUrl) {
            this.payUrl = payUrl;
        }

        public String getPayMessage() {
            return payMessage;
        }

        public void setPayMessage(String payMessage) {
            this.payMessage = payMessage;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public String getProv() {
            return prov;
        }

        public void setProv(String prov) {
            this.prov = prov;
        }
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }
}
