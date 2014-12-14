package com.jorider.truecaller.assignment.model;

/**
 * Created by jorge on 13/12/14.
 */
public class Truecaller10thCharacterRequest {

    private TruecallerRequestError error = null;
    public char response;

    public TruecallerRequestError getError() {
        return error;
    }

    public void setError(TruecallerRequestError error) {
        this.error = error;
    }

    public char getResponse() {
        return response;
    }

    public void setResponse(char response) {
        this.response = response;
    }
}
