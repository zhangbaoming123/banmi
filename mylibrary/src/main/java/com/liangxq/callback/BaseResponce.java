package com.liangxq.callback;

/**
 * 项目名：MyMvpDemo
 * 包名：  com.liangxq.callback
 * 文件名：BaseResponce
 * 创建者：liangxq
 * 创建时间：2019/4/29  9:33
 * 描述：TODO
 */
public class BaseResponce<T> {
    int code;
    String desc;
    T result;

    public BaseResponce(int code, String desc, T result) {
        this.code = code;
        this.desc = desc;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
