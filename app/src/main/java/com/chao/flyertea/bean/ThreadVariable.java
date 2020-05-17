package com.chao.flyertea.bean;

public class ThreadVariable {
    private String auth;
    private String code;
    private String success;
    private String formhash;
    private ThreadData data;

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getFormhash() {
        return formhash;
    }

    public void setFormhash(String formhash) {
        this.formhash = formhash;
    }

    public ThreadData getData() {
        return data;
    }

    public void setData(ThreadData data) {
        this.data = data;
    }
}
