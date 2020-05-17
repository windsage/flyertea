package com.chao.flyertea.bean;

public class Result<T, Y> {
    private String Charset;
    private String Version;
    private Y Message;
    private T Variables;

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

    public Y getMessage() {
        return Message;
    }

    public void setMessage(Y message) {
        Message = message;
    }

    public T getVariables() {
        return Variables;
    }

    public void setVariables(T variables) {
        Variables = variables;
    }
}
