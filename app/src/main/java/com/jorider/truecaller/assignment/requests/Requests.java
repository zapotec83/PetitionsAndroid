package com.jorider.truecaller.assignment.requests;

import android.os.AsyncTask;

import com.jorider.truecaller.assignment.constants.Constants;
import com.jorider.truecaller.assignment.listeners.CallbackFirstRequest;
import com.jorider.truecaller.assignment.listeners.CallbackSecondRequest;
import com.jorider.truecaller.assignment.listeners.CallbackThirdRequest;
import com.jorider.truecaller.assignment.requests.asynctask.AsyncTaskFirstRequest;
import com.jorider.truecaller.assignment.requests.asynctask.AsyncTaskSecondRequest;
import com.jorider.truecaller.assignment.requests.asynctask.AsyncTaskThirdRequest;

/**
 * Created by jorge
 */
public class Requests {

    public static final String TAG = Requests.class.getName();

    /**
     * Method to run the first request
     *
     * @param list      CallbackFirstRequest
     */
    public void runFirst(CallbackFirstRequest list) {
        new AsyncTaskFirstRequest(list).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * Method to run the second request
     *
     * @param list      CallbackSecondRequest
     */
    public void runSecond(CallbackSecondRequest list) {
        new AsyncTaskSecondRequest(list).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    /**
     * Method to run the third request
     *
     * @param list      CallbackThirdRequest
     */
    public void runThird(CallbackThirdRequest list) {
        new AsyncTaskThirdRequest(list).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
