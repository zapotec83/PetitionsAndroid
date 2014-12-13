package com.jorider.truecaller.assignment.listeners;

import com.jorider.truecaller.assignment.model.AppRequestError;

/**
 * Created by jorge on 13/12/14.
 */
public interface BaseListener {

    /**
     *
     * @param error     AppRequestError
     */
    public void onErrorRequest(AppRequestError error);
}
