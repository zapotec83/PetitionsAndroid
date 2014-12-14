package com.jorider.truecaller.assignment.model;

/**
 * Created by jorge
 */
public class MyHttpResponse {

    private String content = null;
    private TruecallerRequestError requestError = null;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TruecallerRequestError getRequestError() {
        return requestError;
    }

    public void setRequestError(TruecallerRequestError requestError) {
        this.requestError = requestError;
    }
}
