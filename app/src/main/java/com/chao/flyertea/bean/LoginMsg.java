package com.chao.flyertea.bean;

public class LoginMsg {


    /**
     * messageval : login_succeed
     * messagestr : 欢迎您回来，钻石会员 飞翔的荷兰号，现在将转入登录前页面
     */

    private String messageval;
    private String messagestr;

    public String getMessageval() {
        return messageval;
    }

    public void setMessageval(String messageval) {
        this.messageval = messageval;
    }

    public String getMessagestr() {
        return messagestr;
    }

    public void setMessagestr(String messagestr) {
        this.messagestr = messagestr;
    }
}
