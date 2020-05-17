package com.chao.flyertea.bean;

import java.util.List;

public class ThreadData {
    private int totalpage;
    private int ppp;
    private List<ThreadPost> posts;
    private ThreadDetail threaddetail;

    public int getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(int totalpage) {
        this.totalpage = totalpage;
    }

    public int getPpp() {
        return ppp;
    }

    public void setPpp(int ppp) {
        this.ppp = ppp;
    }

    public List<ThreadPost> getPosts() {
        return posts;
    }

    public void setPosts(List<ThreadPost> posts) {
        this.posts = posts;
    }

    public ThreadDetail getThreaddetail() {
        return threaddetail;
    }

    public void setThreaddetail(ThreadDetail threaddetail) {
        this.threaddetail = threaddetail;
    }
}
