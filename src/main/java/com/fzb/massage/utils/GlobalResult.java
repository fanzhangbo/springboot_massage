package com.fzb.massage.utils;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
public class GlobalResult {

    private int code;
    private String msg;
    private Object data;

    public GlobalResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public GlobalResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static GlobalResult build(int code, String msg) {
        return new GlobalResult(code, msg);
    }

    public static GlobalResult build(int code, String msg, Object data) {
        return new GlobalResult(code, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
