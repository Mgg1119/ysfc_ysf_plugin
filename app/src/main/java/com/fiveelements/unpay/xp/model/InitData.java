package com.fiveelements.unpay.xp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InitData {
    /**
     * v
     */
    @SerializedName("v")
    private String v;
    /**
     * cmd
     */
    @SerializedName("cmd")
    private String cmd;
    /**
     * reqtm
     */
    @SerializedName("reqtm")
    private String reqtm;
    /**
     * resp
     */
    @SerializedName("resp")
    private String resp;
    /**
     * params
     */
    @SerializedName("params")
    private ParamsDTO params;

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getReqtm() {
        return reqtm;
    }

    public void setReqtm(String reqtm) {
        this.reqtm = reqtm;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public ParamsDTO getParams() {
        return params;
    }

    public void setParams(ParamsDTO params) {
        this.params = params;
    }

    public static class ParamsDTO {
        /**
         * sid
         */
        @SerializedName("sid")
        private String sid;
        /**
         * uid
         */
        @SerializedName("uid")
        private String uid;
        /**
         * title
         */
        @SerializedName("title")
        private String title;
        /**
         * username
         */
        @SerializedName("username")
        private String username;
        /**
         * upgradeInfo
         */
        @SerializedName("upgrade_info")
        private UpgradeInfoDTO upgradeInfo;
        /**
         * initButton
         */
        @SerializedName("init_button")
        private InitButtonDTO initButton;
        /**
         * upcardMsg
         */
        @SerializedName("upcard_msg")
        private String upcardMsg;
        /**
         * tradePrivilege
         */
        @SerializedName("trade_privilege")
        private String tradePrivilege;
        /**
         * supAddNewCard
         */
        @SerializedName("sup_add_new_card")
        private String supAddNewCard;
        /**
         * supNfc
         */
        @SerializedName("sup_nfc")
        private String supNfc;
        /**
         * supUpcardMicroPay
         */
        @SerializedName("sup_upcard_micro_pay")
        private String supUpcardMicroPay;
        /**
         * merId
         */
        @SerializedName("mer_id")
        private String merId;
        /**
         * f55
         */
        @SerializedName("f55")
        private F55DTO f55;
        /**
         * orderItem
         */
        @SerializedName("order_item")
        private OrderItemDTO orderItem;
        /**
         * panItem
         */
        @SerializedName("pan_item")
        private OrderItemDTO panItem;
        /**
         * promotionItem
         */
        @SerializedName("promotion_item")
        private OrderItemDTO promotionItem;
        /**
         * paymentItem
         */
        @SerializedName("payment_item")
        private PaymentItemDTO paymentItem;
        /**
         * defaultCard
         */
        @SerializedName("default_card")
        private DefaultCardDTO defaultCard;
        /**
         * userInfo
         */
        @SerializedName("user_info")
        private List<DefCardRulesDTO.RulesDTO> userInfo;
        /**
         * closeUpcardEncrypt
         */
        @SerializedName("close_upcard_encrypt")
        private String closeUpcardEncrypt;
        /**
         * supAndrPay
         */
        @SerializedName("sup_andr_pay")
        private String supAndrPay;
        /**
         * transType
         */
        @SerializedName("trans_type")
        private String transType;
        /**
         * supSmartPromotion
         */
        @SerializedName("sup_smart_promotion")
        private String supSmartPromotion;
        /**
         * cardShowSmartPromotion
         */
        @SerializedName("card_show_smart_promotion")
        private String cardShowSmartPromotion;
        /**
         * smartPromotionTimeout
         */
        @SerializedName("smart_promotion_timeout")
        private String smartPromotionTimeout;
        /**
         * smartRecommendTimeout
         */
        @SerializedName("smart_recommend_timeout")
        private String smartRecommendTimeout;
        /**
         * defCardRules
         */
        @SerializedName("def_card_rules")
        private DefCardRulesDTO defCardRules;
        /**
         * secret
         */
        @SerializedName("secret")
        private String secret;
        /**
         * checkHash
         */
        @SerializedName("check_hash")
        private String checkHash;

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public UpgradeInfoDTO getUpgradeInfo() {
            return upgradeInfo;
        }

        public void setUpgradeInfo(UpgradeInfoDTO upgradeInfo) {
            this.upgradeInfo = upgradeInfo;
        }

        public InitButtonDTO getInitButton() {
            return initButton;
        }

        public void setInitButton(InitButtonDTO initButton) {
            this.initButton = initButton;
        }

        public String getUpcardMsg() {
            return upcardMsg;
        }

        public void setUpcardMsg(String upcardMsg) {
            this.upcardMsg = upcardMsg;
        }

        public String getTradePrivilege() {
            return tradePrivilege;
        }

        public void setTradePrivilege(String tradePrivilege) {
            this.tradePrivilege = tradePrivilege;
        }

        public String getSupAddNewCard() {
            return supAddNewCard;
        }

        public void setSupAddNewCard(String supAddNewCard) {
            this.supAddNewCard = supAddNewCard;
        }

        public String getSupNfc() {
            return supNfc;
        }

        public void setSupNfc(String supNfc) {
            this.supNfc = supNfc;
        }

        public String getSupUpcardMicroPay() {
            return supUpcardMicroPay;
        }

        public void setSupUpcardMicroPay(String supUpcardMicroPay) {
            this.supUpcardMicroPay = supUpcardMicroPay;
        }

        public String getMerId() {
            return merId;
        }

        public void setMerId(String merId) {
            this.merId = merId;
        }

        public F55DTO getF55() {
            return f55;
        }

        public void setF55(F55DTO f55) {
            this.f55 = f55;
        }

        public OrderItemDTO getOrderItem() {
            return orderItem;
        }

        public void setOrderItem(OrderItemDTO orderItem) {
            this.orderItem = orderItem;
        }

        public OrderItemDTO getPanItem() {
            return panItem;
        }

        public void setPanItem(OrderItemDTO panItem) {
            this.panItem = panItem;
        }

        public OrderItemDTO getPromotionItem() {
            return promotionItem;
        }

        public void setPromotionItem(OrderItemDTO promotionItem) {
            this.promotionItem = promotionItem;
        }

        public PaymentItemDTO getPaymentItem() {
            return paymentItem;
        }

        public void setPaymentItem(PaymentItemDTO paymentItem) {
            this.paymentItem = paymentItem;
        }

        public DefaultCardDTO getDefaultCard() {
            return defaultCard;
        }

        public void setDefaultCard(DefaultCardDTO defaultCard) {
            this.defaultCard = defaultCard;
        }

        public List<DefCardRulesDTO.RulesDTO> getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(List<DefCardRulesDTO.RulesDTO> userInfo) {
            this.userInfo = userInfo;
        }

        public String getCloseUpcardEncrypt() {
            return closeUpcardEncrypt;
        }

        public void setCloseUpcardEncrypt(String closeUpcardEncrypt) {
            this.closeUpcardEncrypt = closeUpcardEncrypt;
        }

        public String getSupAndrPay() {
            return supAndrPay;
        }

        public void setSupAndrPay(String supAndrPay) {
            this.supAndrPay = supAndrPay;
        }

        public String getTransType() {
            return transType;
        }

        public void setTransType(String transType) {
            this.transType = transType;
        }

        public String getSupSmartPromotion() {
            return supSmartPromotion;
        }

        public void setSupSmartPromotion(String supSmartPromotion) {
            this.supSmartPromotion = supSmartPromotion;
        }

        public String getCardShowSmartPromotion() {
            return cardShowSmartPromotion;
        }

        public void setCardShowSmartPromotion(String cardShowSmartPromotion) {
            this.cardShowSmartPromotion = cardShowSmartPromotion;
        }

        public String getSmartPromotionTimeout() {
            return smartPromotionTimeout;
        }

        public void setSmartPromotionTimeout(String smartPromotionTimeout) {
            this.smartPromotionTimeout = smartPromotionTimeout;
        }

        public String getSmartRecommendTimeout() {
            return smartRecommendTimeout;
        }

        public void setSmartRecommendTimeout(String smartRecommendTimeout) {
            this.smartRecommendTimeout = smartRecommendTimeout;
        }

        public DefCardRulesDTO getDefCardRules() {
            return defCardRules;
        }

        public void setDefCardRules(DefCardRulesDTO defCardRules) {
            this.defCardRules = defCardRules;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public String getCheckHash() {
            return checkHash;
        }

        public void setCheckHash(String checkHash) {
            this.checkHash = checkHash;
        }

        public static class UpgradeInfoDTO {
            /**
             * type
             */
            @SerializedName("type")
            private String type;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class InitButtonDTO {
            /**
             * label
             */
            @SerializedName("label")
            private String label;
            /**
             * action
             */
            @SerializedName("action")
            private String action;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }
        }

        public static class F55DTO {
            /**
             * orderAmount
             */
            @SerializedName("order_amount")
            private String orderAmount;
            /**
             * orderCurrency
             */
            @SerializedName("order_currency")
            private String orderCurrency;
            /**
             * transType
             */
            @SerializedName("trans_type")
            private String transType;
            /**
             * merName
             */
            @SerializedName("mer_name")
            private String merName;

            public String getOrderAmount() {
                return orderAmount;
            }

            public void setOrderAmount(String orderAmount) {
                this.orderAmount = orderAmount;
            }

            public String getOrderCurrency() {
                return orderCurrency;
            }

            public void setOrderCurrency(String orderCurrency) {
                this.orderCurrency = orderCurrency;
            }

            public String getTransType() {
                return transType;
            }

            public void setTransType(String transType) {
                this.transType = transType;
            }

            public String getMerName() {
                return merName;
            }

            public void setMerName(String merName) {
                this.merName = merName;
            }
        }

        public static class OrderItemDTO {
            /**
             * label
             */
            @SerializedName("label")
            private String label;
            /**
             * name
             */
            @SerializedName("name")
            private String name;
            /**
             * type
             */
            @SerializedName("type")
            private String type;
            /**
             * placeholder
             */
            @SerializedName("placeholder")
            private String placeholder;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPlaceholder() {
                return placeholder;
            }

            public void setPlaceholder(String placeholder) {
                this.placeholder = placeholder;
            }
        }

        public static class PaymentItemDTO {
            /**
             * name
             */
            @SerializedName("name")
            private String name;
            /**
             * value
             */
            @SerializedName("value")
            private String value;
            /**
             * type
             */
            @SerializedName("type")
            private String type;
            /**
             * placeholder
             */
            @SerializedName("placeholder")
            private String placeholder;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPlaceholder() {
                return placeholder;
            }

            public void setPlaceholder(String placeholder) {
                this.placeholder = placeholder;
            }
        }

        public static class DefaultCardDTO {
            /**
             * bindId
             */
            @SerializedName("bindId")
            private String bindId;
            /**
             * pan
             */
            @SerializedName("pan")
            private String pan;
            /**
             * name
             */
            @SerializedName("name")
            private String name;
            /**
             * attrName
             */
            @SerializedName("attrName")
            private String attrName;
            /**
             * nameSuffix
             */
            @SerializedName("nameSuffix")
            private String nameSuffix;
            /**
             * issuerCode
             */
            @SerializedName("issuerCode")
            private String issuerCode;
            /**
             * enable
             */
            @SerializedName("enable")
            private String enable;
            /**
             * payInfos
             */
            @SerializedName("payInfos")
            private List<PayInfosDTO> payInfos;
            /**
             * proCardStatus
             */
            @SerializedName("proCardStatus")
            private String proCardStatus;
            /**
             * supInstalment
             */
            @SerializedName("supInstalment")
            private String supInstalment;
            /**
             * cardOrg
             */
            @SerializedName("cardOrg")
            private String cardOrg;
            /**
             * cardOrgLogo
             */
            @SerializedName("cardOrgLogo")
            private String cardOrgLogo;
            /**
             * firstPayInfo
             */
            @SerializedName("firstPayInfo")
            private FirstPayInfoDTO firstPayInfo;

            public String getBindId() {
                return bindId;
            }

            public void setBindId(String bindId) {
                this.bindId = bindId;
            }

            public String getPan() {
                return pan;
            }

            public void setPan(String pan) {
                this.pan = pan;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAttrName() {
                return attrName;
            }

            public void setAttrName(String attrName) {
                this.attrName = attrName;
            }

            public String getNameSuffix() {
                return nameSuffix;
            }

            public void setNameSuffix(String nameSuffix) {
                this.nameSuffix = nameSuffix;
            }

            public String getIssuerCode() {
                return issuerCode;
            }

            public void setIssuerCode(String issuerCode) {
                this.issuerCode = issuerCode;
            }

            public String getEnable() {
                return enable;
            }

            public void setEnable(String enable) {
                this.enable = enable;
            }

            public List<PayInfosDTO> getPayInfos() {
                return payInfos;
            }

            public void setPayInfos(List<PayInfosDTO> payInfos) {
                this.payInfos = payInfos;
            }

            public String getProCardStatus() {
                return proCardStatus;
            }

            public void setProCardStatus(String proCardStatus) {
                this.proCardStatus = proCardStatus;
            }

            public String getSupInstalment() {
                return supInstalment;
            }

            public void setSupInstalment(String supInstalment) {
                this.supInstalment = supInstalment;
            }

            public String getCardOrg() {
                return cardOrg;
            }

            public void setCardOrg(String cardOrg) {
                this.cardOrg = cardOrg;
            }

            public String getCardOrgLogo() {
                return cardOrgLogo;
            }

            public void setCardOrgLogo(String cardOrgLogo) {
                this.cardOrgLogo = cardOrgLogo;
            }

            public FirstPayInfoDTO getFirstPayInfo() {
                return firstPayInfo;
            }

            public void setFirstPayInfo(FirstPayInfoDTO firstPayInfo) {
                this.firstPayInfo = firstPayInfo;
            }

            public static class FirstPayInfoDTO {
                /**
                 * payment
                 */
                @SerializedName("payment")
                private PaymentDTO payment;
                /**
                 * cardStatus
                 */
                @SerializedName("cardStatus")
                private String cardStatus;
                /**
                 * cardStatusDesc
                 */
                @SerializedName("cardStatusDesc")
                private String cardStatusDesc;

                public PaymentDTO getPayment() {
                    return payment;
                }

                public void setPayment(PaymentDTO payment) {
                    this.payment = payment;
                }

                public String getCardStatus() {
                    return cardStatus;
                }

                public void setCardStatus(String cardStatus) {
                    this.cardStatus = cardStatus;
                }

                public String getCardStatusDesc() {
                    return cardStatusDesc;
                }

                public void setCardStatusDesc(String cardStatusDesc) {
                    this.cardStatusDesc = cardStatusDesc;
                }

                public static class PaymentDTO {
                    /**
                     * mode
                     */
                    @SerializedName("mode")
                    private String mode;
                    /**
                     * name
                     */
                    @SerializedName("name")
                    private String name;
                    /**
                     * action
                     */
                    @SerializedName("action")
                    private String action;
                    /**
                     * level
                     */
                    @SerializedName("level")
                    private Integer level;

                    public String getMode() {
                        return mode;
                    }

                    public void setMode(String mode) {
                        this.mode = mode;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getAction() {
                        return action;
                    }

                    public void setAction(String action) {
                        this.action = action;
                    }

                    public Integer getLevel() {
                        return level;
                    }

                    public void setLevel(Integer level) {
                        this.level = level;
                    }
                }
            }

            public static class PayInfosDTO {
                /**
                 * payment
                 */
                @SerializedName("payment")
                private FirstPayInfoDTO.PaymentDTO payment;
                /**
                 * cardStatus
                 */
                @SerializedName("cardStatus")
                private String cardStatus;
                /**
                 * cardStatusDesc
                 */
                @SerializedName("cardStatusDesc")
                private String cardStatusDesc;

                public FirstPayInfoDTO.PaymentDTO getPayment() {
                    return payment;
                }

                public void setPayment(FirstPayInfoDTO.PaymentDTO payment) {
                    this.payment = payment;
                }

                public String getCardStatus() {
                    return cardStatus;
                }

                public void setCardStatus(String cardStatus) {
                    this.cardStatus = cardStatus;
                }

                public String getCardStatusDesc() {
                    return cardStatusDesc;
                }

                public void setCardStatusDesc(String cardStatusDesc) {
                    this.cardStatusDesc = cardStatusDesc;
                }
            }
        }

        public static class DefCardRulesDTO {
            /**
             * rules
             */
            @SerializedName("rules")
            private List<RulesDTO> rules;
            /**
             * pan
             */
            @SerializedName("pan")
            private String pan;
            /**
             * timeoutMsg
             */
            @SerializedName("timeout_msg")
            private String timeoutMsg;
            /**
             * payPwdSecureKey
             */
            @SerializedName("pay_pwd_secure_key")
            private String payPwdSecureKey;

            public List<RulesDTO> getRules() {
                return rules;
            }

            public void setRules(List<RulesDTO> rules) {
                this.rules = rules;
            }

            public String getPan() {
                return pan;
            }

            public void setPan(String pan) {
                this.pan = pan;
            }

            public String getTimeoutMsg() {
                return timeoutMsg;
            }

            public void setTimeoutMsg(String timeoutMsg) {
                this.timeoutMsg = timeoutMsg;
            }

            public String getPayPwdSecureKey() {
                return payPwdSecureKey;
            }

            public void setPayPwdSecureKey(String payPwdSecureKey) {
                this.payPwdSecureKey = payPwdSecureKey;
            }

            public static class RulesDTO {
                /**
                 * label
                 */
                @SerializedName("label")
                private String label;
                /**
                 * name
                 */
                @SerializedName("name")
                private String name;
                /**
                 * value
                 */
                @SerializedName("value")
                private String value;
                /**
                 * type
                 */
                @SerializedName("type")
                private String type;
                /**
                 * tip
                 */
                @SerializedName("tip")
                private String tip;
                /**
                 * readonly
                 */
                @SerializedName("readonly")
                private String readonly;
                /**
                 * placeholder
                 */
                @SerializedName("placeholder")
                private String placeholder;

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getTip() {
                    return tip;
                }

                public void setTip(String tip) {
                    this.tip = tip;
                }

                public String getReadonly() {
                    return readonly;
                }

                public void setReadonly(String readonly) {
                    this.readonly = readonly;
                }

                public String getPlaceholder() {
                    return placeholder;
                }

                public void setPlaceholder(String placeholder) {
                    this.placeholder = placeholder;
                }
            }
        }
    }
}
