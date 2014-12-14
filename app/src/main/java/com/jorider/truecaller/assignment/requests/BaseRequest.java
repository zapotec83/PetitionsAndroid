package com.jorider.truecaller.assignment.requests;

/**
 * Created by jorge on 13/12/14.
 */
public abstract class BaseRequest {

    public static final int NATIVE = 1;

    /**
     * USE IT IN CASE YOU WANT TO TRY TO MAKE THE REQUESTS USING THE VOLLEY LIBRARY
     */
    public static final int VOLLEY = 2;

    /**
     * Method to execute the request
     */
    public void run(int library) {

    }
}
