package com.jorider.truecaller.assignment.model;

/**
 * Created by jorge on 13/12/14.
 */
public class AppRequestError {

    private int httpCode = 0;
    private String msg = null;

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
