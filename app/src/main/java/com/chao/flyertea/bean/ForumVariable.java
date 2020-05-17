package com.chao.flyertea.bean;

import java.util.List;

public class ForumVariable {
    private String auth;
    private String code;
    private String cookiepre;
    private String formhash;
    private String success;
    private List<ForumThread> forum_threadlist;

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

    public String getCookiepre() {
        return cookiepre;
    }

    public void setCookiepre(String cookiepre) {
        this.cookiepre = cookiepre;
    }

    public String getFormhash() {
        return formhash;
    }

    public void setFormhash(String formhash) {
        this.formhash = formhash;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<ForumThread> getForum_threadlist() {
        return forum_threadlist;
    }

    public void setForum_threadlist(List<ForumThread> forum_threadlist) {
        this.forum_threadlist = forum_threadlist;
    }
}
