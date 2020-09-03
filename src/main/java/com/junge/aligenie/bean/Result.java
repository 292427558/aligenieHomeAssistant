package com.junge.aligenie.bean;

import java.io.Serializable;

/**
 * mode class
 *
 * @Author LiuJun
 * @Date 2020/7/15 16:57
 */

public class Result implements Serializable {

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
