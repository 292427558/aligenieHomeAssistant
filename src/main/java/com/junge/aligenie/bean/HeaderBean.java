package com.junge.aligenie.bean;

public class HeaderBean {
        /**
         * namespace : AliGenie.Iot.Device.Control
         * name : TurnOn
         * messageId : 1bd5d003-31b9-476f-ad03-71d471922820
         * payLoadVersion : 1
         */

        private String namespace;
        private String name;
        private String messageId;
        private int payLoadVersion;

    public HeaderBean() {
    }

    public HeaderBean(String namespace, String name, String messageId, int payLoadVersion) {
        this.namespace = namespace;
        this.name = name;
        this.messageId = messageId;
        this.payLoadVersion = payLoadVersion;
    }

    public String getNamespace() {
            return namespace;
        }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getPayLoadVersion() {
        return payLoadVersion;
    }

    public void setPayLoadVersion(int payLoadVersion) {
        this.payLoadVersion = payLoadVersion;
    }

    @Override
    public String toString() {
        return "HeaderBean{" +
                "namespace='" + namespace + '\'' +
                ", name='" + name + '\'' +
                ", messageId='" + messageId + '\'' +
                ", payLoadVersion=" + payLoadVersion +
                '}';
    }
}