package com.junge.aligenie.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/7/15 16:27
 */

public class AliDiscoverResult extends Result{

    /**
     * header : {"namespace":"AliGenie.Iot.Device.Discovery","name":"DiscoveryDevicesResponse","messageId":"1bd5d003-31b9-476f-ad03-71d471922820","payLoadVersion":1}
     * payload : {"devices":[{"deviceId":"34ea34cf2e63","deviceName":"单孔插座","deviceType":"outlet","zone":"","brand":"","model":"","icon":"https://git.cn-hangzhou.oss-cdn.aliyun-inc.com/uploads/aicloud/aicloud-proxy-service/41baa00903a71c97e3533cf4e19a88bb/image.png","properties":[{"name":"powerstate","value":"off"}],"actions":["TurnOn","TurnOff"],"extensions":{"extension1":"","extension2":""}}]}
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
        private List<DevicesBean> devices = new ArrayList<>();

        public List<DevicesBean> getDevices() {
            return devices;
        }

        public void setDevices(List<DevicesBean> devices) {
            this.devices = devices;
        }

        public static class DevicesBean {
            /**
             * deviceId : 34ea34cf2e63
             * deviceName : 单孔插座
             * deviceType : outlet
             * zone :
             * brand :
             * model :
             * icon : https://git.cn-hangzhou.oss-cdn.aliyun-inc.com/uploads/aicloud/aicloud-proxy-service/41baa00903a71c97e3533cf4e19a88bb/image.png
             * properties : [{"name":"powerstate","value":"off"}]
             * actions : ["TurnOn","TurnOff"]
             * extensions : {"extension1":"","extension2":""}
             */

            private String deviceId;
            private String deviceName;
            private String deviceType;
            private String zone;
            private String brand;
            private String model;
            private String icon;
            private ExtensionsBean extensions;
            private List<PropertiesBean> properties;
            private List<String> actions;

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public String getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(String deviceType) {
                this.deviceType = deviceType;
            }

            public String getZone() {
                return zone;
            }

            public void setZone(String zone) {
                this.zone = zone;
            }

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public ExtensionsBean getExtensions() {
                return extensions;
            }

            public void setExtensions(ExtensionsBean extensions) {
                this.extensions = extensions;
            }

            public List<PropertiesBean> getProperties() {
                return properties;
            }

            public void setProperties(List<PropertiesBean> properties) {
                this.properties = properties;
            }

            public List<String> getActions() {
                return actions;
            }

            public void setActions(List<String> actions) {
                this.actions = actions;
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

            public static class PropertiesBean {
                /**
                 * name : powerstate
                 * value : off
                 */

                private String name;
                private String value;

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
    }
}
