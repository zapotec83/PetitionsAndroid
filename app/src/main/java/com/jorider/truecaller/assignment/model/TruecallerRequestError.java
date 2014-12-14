package com.jorider.truecaller.assignment.model;

/**
 * Created by jorge
 */
public class TruecallerRequestError {

    private int httpCode = 0;
    private String msg = null;

    public TruecallerRequestError() {
    }

    public TruecallerRequestError(int httpCode, String msg) {
        this.httpCode = httpCode;
        this.msg = msg;
    }

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
