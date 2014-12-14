package com.jorider.truecaller.assignment.model;

/**
 * Created by jorge on 14/12/14.
 */
public class MyHttpResponse {

    private AppRequestError error = null;
    private String content = "";

    public AppRequestError getError() {
        return error;
    }

    public void setError(AppRequestError error) {
        this.error = error;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
