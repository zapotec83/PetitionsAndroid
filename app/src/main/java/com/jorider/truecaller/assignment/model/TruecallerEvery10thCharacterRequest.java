package com.jorider.truecaller.assignment.model;

/**
 * Created by jorge on 13/12/14.
 */
public class TruecallerEvery10thCharacterRequest {

    public String response = null;
    private TruecallerRequestError error = null;

    public TruecallerRequestError getError() {
        return error;
    }

    public void setError(TruecallerRequestError error) {
        this.error = error;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
