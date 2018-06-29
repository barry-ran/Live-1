package com.horen.horenbase.bean;

/**
 * @author :ChenYangYi
 * @date :2018/06/29/12:44
 * @description :Movie模块基类
 * @github :https://github.com/chenyy0708
 */
public class BaseEntry<T> {

    /**
     * code : 500
     * message : can not find signature
     * data : []
     */

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return code == 0;
    }
}
