package com.fiveelements.unpay.xp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfo extends BaseModel{

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
         * loginName
         */
        @SerializedName("loginName")
        private String loginName;
        /**
         * realName
         */
        @SerializedName("realName")
        private String realName;
        /**
         * welcomeWord
         */
        @SerializedName("welcomeWord")
        private String welcomeWord;
        /**
         * encryptCdhdUsrId
         */
        @SerializedName("encryptCdhdUsrId")
        private String encryptCdhdUsrId;
        /**
         * urid
         */
        @SerializedName("urid")
        private String urid;
        /**
         * certStatus
         */
        @SerializedName("certStatus")
        private String certStatus;
        /**
         * certTp
         */
        @SerializedName("certTp")
        private String certTp;
        /**
         * supDownloadBill
         */
        @SerializedName("supDownloadBill")
        private String supDownloadBill;
        /**
         * maskCertId
         */
        @SerializedName("maskCertId")
        private String maskCertId;
        /**
         * hceUserId
         */
        @SerializedName("hceUserId")
        private String hceUserId;
        /**
         * loginMobile
         */
        @SerializedName("loginMobile")
        private String loginMobile;
        /**
         * eduStatus
         */
        @SerializedName("eduStatus")
        private String eduStatus;
        /**
         * cntryCode
         */
        @SerializedName("cntryCode")
        private String cntryCode;
        /**
         * overSeaStatus
         */
        @SerializedName("overSeaStatus")
        private String overSeaStatus;
        /**
         * userSwitchs
         */
        @SerializedName("userSwitchs")
        private List<UserSwitchsDTO> userSwitchs;
        /**
         * faceRecgCert
         */
        @SerializedName("faceRecgCert")
        private String faceRecgCert;
        /**
         * faceRecgLoginSwitch
         */
        @SerializedName("faceRecgLoginSwitch")
        private String faceRecgLoginSwitch;
        /**
         * encryptCdhdUsrIdNat
         */
        @SerializedName("encryptCdhdUsrIdNat")
        private String encryptCdhdUsrIdNat;
        /**
         * encryptCdhdUsrIdByNat
         */
        @SerializedName("encryptCdhdUsrIdByNat")
        private String encryptCdhdUsrIdByNat;
        /**
         * respCode
         */
        @SerializedName("respCode")
        private String respCode;
        /**
         * respMsg
         */
        @SerializedName("respMsg")
        private String respMsg;

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getWelcomeWord() {
            return welcomeWord;
        }

        public void setWelcomeWord(String welcomeWord) {
            this.welcomeWord = welcomeWord;
        }

        public String getEncryptCdhdUsrId() {
            return encryptCdhdUsrId;
        }

        public void setEncryptCdhdUsrId(String encryptCdhdUsrId) {
            this.encryptCdhdUsrId = encryptCdhdUsrId;
        }

        public String getUrid() {
            return urid;
        }

        public void setUrid(String urid) {
            this.urid = urid;
        }

        public String getCertStatus() {
            return certStatus;
        }

        public void setCertStatus(String certStatus) {
            this.certStatus = certStatus;
        }

        public String getCertTp() {
            return certTp;
        }

        public void setCertTp(String certTp) {
            this.certTp = certTp;
        }

        public String getSupDownloadBill() {
            return supDownloadBill;
        }

        public void setSupDownloadBill(String supDownloadBill) {
            this.supDownloadBill = supDownloadBill;
        }

        public String getMaskCertId() {
            return maskCertId;
        }

        public void setMaskCertId(String maskCertId) {
            this.maskCertId = maskCertId;
        }

        public String getHceUserId() {
            return hceUserId;
        }

        public void setHceUserId(String hceUserId) {
            this.hceUserId = hceUserId;
        }

        public String getLoginMobile() {
            return loginMobile;
        }

        public void setLoginMobile(String loginMobile) {
            this.loginMobile = loginMobile;
        }

        public String getEduStatus() {
            return eduStatus;
        }

        public void setEduStatus(String eduStatus) {
            this.eduStatus = eduStatus;
        }

        public String getCntryCode() {
            return cntryCode;
        }

        public void setCntryCode(String cntryCode) {
            this.cntryCode = cntryCode;
        }

        public String getOverSeaStatus() {
            return overSeaStatus;
        }

        public void setOverSeaStatus(String overSeaStatus) {
            this.overSeaStatus = overSeaStatus;
        }

        public List<UserSwitchsDTO> getUserSwitchs() {
            return userSwitchs;
        }

        public void setUserSwitchs(List<UserSwitchsDTO> userSwitchs) {
            this.userSwitchs = userSwitchs;
        }

        public String getFaceRecgCert() {
            return faceRecgCert;
        }

        public void setFaceRecgCert(String faceRecgCert) {
            this.faceRecgCert = faceRecgCert;
        }

        public String getFaceRecgLoginSwitch() {
            return faceRecgLoginSwitch;
        }

        public void setFaceRecgLoginSwitch(String faceRecgLoginSwitch) {
            this.faceRecgLoginSwitch = faceRecgLoginSwitch;
        }

        public String getEncryptCdhdUsrIdNat() {
            return encryptCdhdUsrIdNat;
        }

        public void setEncryptCdhdUsrIdNat(String encryptCdhdUsrIdNat) {
            this.encryptCdhdUsrIdNat = encryptCdhdUsrIdNat;
        }

        public String getEncryptCdhdUsrIdByNat() {
            return encryptCdhdUsrIdByNat;
        }

        public void setEncryptCdhdUsrIdByNat(String encryptCdhdUsrIdByNat) {
            this.encryptCdhdUsrIdByNat = encryptCdhdUsrIdByNat;
        }

        public String getRespCode() {
            return respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }

        public String getRespMsg() {
            return respMsg;
        }

        public void setRespMsg(String respMsg) {
            this.respMsg = respMsg;
        }

        public static class UserSwitchsDTO {
            /**
             * switchName
             */
            @SerializedName("switchName")
            private String switchName;
            /**
             * state
             */
            @SerializedName("state")
            private String state;

            public String getSwitchName() {
                return switchName;
            }

            public void setSwitchName(String switchName) {
                this.switchName = switchName;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }
        }
    }
}
