package com.chao.flyertea.bean;

public class Credit {

    /**
     * taskid : 12
     * tasktitle : 点击1次好价购买按钮
     * credit1 : 5
     * credit6 : 0
     * tasktip : 恭喜，成功点击一次好价购买按钮！
     * taskdesc : 点击1次好价购买按钮
     * taskimage : null
     * taskbtn : 去点击
     * taskcomment : null
     * type : 3
     */

    private String taskid;
    private String tasktitle;
    private String credit1;
    private String credit6;
    private String tasktip;
    private String taskdesc;
    private Object taskimage;
    private String taskbtn;
    private Object taskcomment;
    private String type;

    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    public String getTasktitle() {
        return tasktitle;
    }

    public void setTasktitle(String tasktitle) {
        this.tasktitle = tasktitle;
    }

    public String getCredit1() {
        return credit1;
    }

    public void setCredit1(String credit1) {
        this.credit1 = credit1;
    }

    public String getCredit6() {
        return credit6;
    }

    public void setCredit6(String credit6) {
        this.credit6 = credit6;
    }

    public String getTasktip() {
        return tasktip;
    }

    public void setTasktip(String tasktip) {
        this.tasktip = tasktip;
    }

    public String getTaskdesc() {
        return taskdesc;
    }

    public void setTaskdesc(String taskdesc) {
        this.taskdesc = taskdesc;
    }

    public Object getTaskimage() {
        return taskimage;
    }

    public void setTaskimage(Object taskimage) {
        this.taskimage = taskimage;
    }

    public String getTaskbtn() {
        return taskbtn;
    }

    public void setTaskbtn(String taskbtn) {
        this.taskbtn = taskbtn;
    }

    public Object getTaskcomment() {
        return taskcomment;
    }

    public void setTaskcomment(Object taskcomment) {
        this.taskcomment = taskcomment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
