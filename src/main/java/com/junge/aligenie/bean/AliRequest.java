package com.junge.aligenie.bean;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/7/15 16:30
 */

public class AliRequest {

    /**
     * header : {"namespace":"AliGenie.Iot.Device.Control","name":"TurnOn","messageId":"1bd5d003-31b9-476f-ad03-71d471922820","payLoadVersion":1}
     * payload : {"accessToken":"access token","deviceId":"34234","deviceType":"XXX","attribute":"powerstate","value":"on","extensions":{"extension1":"","extension2":""}}
     */

    private HeaderBean header;
    private PayloadBean payload;

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
         * accessToken : access token
         * deviceId : 34234
         * deviceType : XXX
         * attribute : powerstate
         * value : on
         * extensions : {"extension1":"","extension2":""}
         */

        private String accessToken;
        private String deviceId;
        private String deviceType;
        private String attribute;
        private String value;
        private ExtensionsBean extensions;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public ExtensionsBean getExtensions() {
            return extensions;
        }

        public void setExtensions(ExtensionsBean extensions) {
            this.extensions = extensions;
        }

        public static class ExtensionsBean {
            /**
             * extension1 :
             * extension2 :
             */

            private String extension1;
            private String extension2;

            public String getExtension1() {
                return extension1;
            }

            public void setExtension1(String extension1) {
                this.extension1 = extension1;
            }

            public String getExtension2() {
                return extension2;
            }

            public void setExtension2(String extension2) {
                this.extension2 = extension2;
            }
        }

        @Override
        public String toString() {
            return "PayloadBean{" +
                    "accessToken='" + accessToken + '\'' +
                    ", deviceId='" + deviceId + '\'' +
                    ", deviceType='" + deviceType + '\'' +
                    ", attribute='" + attribute + '\'' +
                    ", value='" + value + '\'' +
                    ", extensions=" + extensions +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AliRequest{" +
                "header=" + header +
                ", payload=" + payload +
                '}';
    }
}
