package com.chao.flyertea.bean;

import java.util.ArrayList;

public class FavorVariable {
    private int success;
    private int code;
    private int cached;
    private String msg;
    private ArrayList<Favor> data;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCached() {
        return cached;
    }

    public void setCached(int cached) {
        this.cached = cached;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<Favor> getData() {
        return data;
    }

    public void setData(ArrayList<Favor> data) {
        this.data = data;
    }
}
