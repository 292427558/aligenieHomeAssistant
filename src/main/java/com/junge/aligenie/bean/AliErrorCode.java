package com.junge.aligenie.bean;

public interface AliErrorCode {
    //错误代码
    //access_token is invalidate
    public static final String ACCESS_TOKEN_INVALIDATE = "ACCESS_TOKEN_INVALIDATE";

    //device is offline
    public static final String IOT_DEVICE_OFFLINE = "IOT_DEVICE_OFFLINE";

    //device is not exist
    public static final String DEVICE_IS_NOT_EXIST = "DEVICE_IS_NOT_EXIST";

    //invalidate params
    public static final String INVALIDATE_PARAMS = "INVALIDATE_PARAMS";

    //device not support
    public static final String DEVICE_NOT_SUPPORT_FUNCTION = "DEVICE_NOT_SUPPORT_FUNCTION";

    public static final String SERVICE_ERROR = "SERVICE_ERROR";

    //invalidate control order
    public static final String INVALIDATE_CONTROL_ORDER = "INVALIDATE_CONTROL_ORDER";

}