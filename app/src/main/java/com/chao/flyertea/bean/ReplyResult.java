package com.chao.flyertea.bean;

public class ReplyResult {

    private String Charset;
    private String Version;
    private ReplyMessage Message;
//    private ReplyVariable Variables;

    public String getCharset() {
        return Charset;
    }

    public void setCharset(String charset) {
        Charset = charset;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public ReplyMessage getMessage() {
        return Message;
    }

    public void setMessage(ReplyMessage message) {
        Message = message;
    }

//    public ReplyVariable getVariables() {
//        return Variables;
//    }
//
//    public void setVariables(ReplyVariable variables) {
//        Variables = variables;
//    }
}
