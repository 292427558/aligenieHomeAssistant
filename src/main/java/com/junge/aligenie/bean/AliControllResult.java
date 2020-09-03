package com.junge.aligenie.bean;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/7/15 16:40
 */

public class AliControllResult extends Result{


    /**
     * header : {"namespace":"AliGenie.Iot.Device.Control","name":"ErrorResponse","messageId":"1bd5d003-31b9-476f-ad03-71d471922820","payLoadVersion":1}
     * payload : {"deviceId":"34234","errorCode":"DEVICE_NOT_SUPPORT_FUNCTION","message":"device not support"}
     */

    private HeaderBean header;
    private PayloadBean payload = new PayloadBean();

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public PayloadBean getPayload() {
        return payload;
    }

    public void setPayload(PayloadBean payload) {
        this.payload = payload;
    }


    public static class PayloadBean {
        /**
         * deviceId : 34234
         * errorCode : DEVICE_NOT_SUPPORT_FUNCTION
         * message : device not support
         */

        private String deviceId;
        private String errorCode;
        private String message;

        public String getDeviceId() {
            return deviceId;
        }

        public PayloadBean setDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public PayloadBean setErrorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public String getMessage() {
            return message;
        }

        public PayloadBean setMessage(String message) {
            this.message = message;
            return this;
        }

        @Override
        public String toString() {
            return "PayloadBean{" +
                    "deviceId='" + deviceId + '\'' +
                    ", errorCode='" + errorCode + '\'' +
                    ", message='" + message + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AliControllResult{" +
                "header=" + header +
                ", payload=" + payload +
                '}';
    }
}
