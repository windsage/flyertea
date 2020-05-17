package com.chao.flyertea.bean;

import java.util.List;

public class ForumData {
    private String tpp;
    private String page;
    private String hasnext;
    private String ver;
    private List<ForumThread> forum_threadlist;

    public String getTpp() {
        return tpp;
    }

    public void setTpp(String tpp) {
        this.tpp = tpp;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getHasnext() {
        return hasnext;
    }

    public void setHasnext(String hasnext) {
        this.hasnext = hasnext;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public List<ForumThread> getForum_threadlist() {
        return forum_threadlist;
    }

    public void setForum_threadlist(List<ForumThread> forum_threadlist) {
        this.forum_threadlist = forum_threadlist;
    }
}
