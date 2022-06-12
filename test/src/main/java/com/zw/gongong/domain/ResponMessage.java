package com.zw.gongong.domain;

public class ResponMessage {
    private Integer code;//0为登录失败，1为登录成功
    private String message;//登录信息
    private Object xiangy;//响应数据

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ResponMessage{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", xiangy=" + xiangy +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getXiangy() {
        return xiangy;
    }

    public void setXiangy(Object xiangy) {
        this.xiangy = xiangy;
    }
}
