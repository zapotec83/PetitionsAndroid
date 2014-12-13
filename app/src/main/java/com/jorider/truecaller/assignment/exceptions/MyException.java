package com.jorider.truecaller.assignment.exceptions;

/**
 * Created by jorge on 13/12/14.
 */
public class MyException extends Exception {

    public MyException(String detailMessage) {
        super(detailMessage);
    }

    public MyException() {
        super();
    }
}
