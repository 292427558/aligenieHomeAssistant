package com.junge.aligenie.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/7/15 16:45
 */

public class AliStateResult extends Result{

    /**
     * properties : [{"name":"temperature","value":"27"}]
     * header : {"namespace":"AliGenie.Iot.Device.Query","name":"QueryTemperatureResponse","messageId":"1bd5d003-31b9-476f-ad03-71d471922820","payLoadVersion":1}
     * payload : {"deviceId":"34234"}
     */

    private HeaderBean header;
    private PayloadBean payload = new PayloadBean();
    private List<PropertiesBean> properties = new ArrayList<>();

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

    public List<PropertiesBean> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertiesBean> properties) {
        this.properties = properties;
    }


    public static class PayloadBean {
        /**
         * deviceId : 34234
         */

        private String deviceId;

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }
    }

    public static class PropertiesBean {
        /**
         * name : temperature
         * value : 27
         */

        private String name;
        private String value;

        public PropertiesBean() {
        }

        public PropertiesBean(String name, String value) {
            this.name = name;
            this.value = value;
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
    }
}
