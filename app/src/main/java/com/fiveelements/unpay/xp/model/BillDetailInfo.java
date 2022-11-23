package com.fiveelements.unpay.xp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BillDetailInfo {

    /**
     * province
     */
    @SerializedName("province")
    private String province;
    /**
     * form
     */
    @SerializedName("form")
    private List<FormDTO> form;
    /**
     * channelType
     */
    @SerializedName("channelType")
    private String channelType;
    /**
     * title
     */
    @SerializedName("title")
    private String title;
    /**
     * operator
     */
    @SerializedName("operator")
    private String operator;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<FormDTO> getForm() {
        return form;
    }

    public void setForm(List<FormDTO> form) {
        this.form = form;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public static class FormDTO {
        /**
         * name
         */
        @SerializedName("name")
        private String name;
        /**
         * label
         */
        @SerializedName("label")
        private String label;
        /**
         * type
         */
        @SerializedName("type")
        private String type;
        /**
         * value
         */
        @SerializedName("value")
        private String value;
        /**
         * options
         */
        @SerializedName("options")
        private List<OptionsDTO> options;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public List<OptionsDTO> getOptions() {
            return options;
        }

        public void setOptions(List<OptionsDTO> options) {
            this.options = options;
        }

        public static class OptionsDTO {
            /**
             * amount
             */
            @SerializedName("amount")
            private String amount;
            /**
             * costPrice
             */
            @SerializedName("costPrice")
            private String costPrice;
            /**
             * denomination
             */
            @SerializedName("denomination")
            private String denomination;

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public String getCostPrice() {
                return costPrice;
            }

            public void setCostPrice(String costPrice) {
                this.costPrice = costPrice;
            }

            public String getDenomination() {
                return denomination;
            }

            public void setDenomination(String denomination) {
                this.denomination = denomination;
            }
        }
    }
}
